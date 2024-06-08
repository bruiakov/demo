package com.ecb.currency.rate.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import com.ecb.currency.rate.component.WebClient;
import com.ecb.currency.rate.dto.CurrencyValueDTO;
import com.ecb.currency.rate.entity.Currency;
import com.ecb.currency.rate.entity.CurrencyDate;
import com.ecb.currency.rate.entity.CurrencyValue;
import com.ecb.currency.rate.mapper.CurrencyValueMapper;
import com.ecb.currency.rate.repository.CurrencyDateRepository;
import com.ecb.currency.rate.repository.CurrencyRepository;
import com.ecb.currency.rate.repository.CurrencyValueRepository;
import com.ecb.currency.rate.utils.XMLHandler;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GrabCurrency {
	private CurrencyRepository currencyRepository;
	private CurrencyValueRepository currencyValueRepository;
	private CurrencyDateRepository currencyDateRepository;
	private WebClient webClient;
	private CurrencyValueMapper currencyValueMapperImpl;

	@Autowired
	public void setCurrencyRepository(CurrencyRepository currencyRepository) {
		this.currencyRepository = currencyRepository;
	}

	@Autowired
	public void setWebClient(WebClient webClient) {
		this.webClient = webClient;
	}

	@Autowired
	public void setCurrencyValueMapperImpl(CurrencyValueMapper currencyValueMapperImpl) {
		this.currencyValueMapperImpl = currencyValueMapperImpl;
	}

	@Autowired
	public void setCurrencyValueRepository(CurrencyValueRepository currencyValueRepository) {
		this.currencyValueRepository = currencyValueRepository;
	}

	@Autowired
	public void setCurrencyDateRepository(CurrencyDateRepository currencyDateRepository) {
		this.currencyDateRepository = currencyDateRepository;
	}

	private List<CurrencyValueDTO> fillCurrency(@NonNull List<CurrencyValueDTO> result) {
		var currencyAll = currencyRepository.findAll();
		result.forEach(i -> i.setCurrency(currencyAll.stream()
				.filter(ii -> ii.getCurrencyCode().equals(i.getCurrencyCode())).findFirst().orElseGet(() -> {
					var newCurr = new Currency();
					newCurr.setCurrencyCode(i.getCurrencyCode());
					return currencyRepository.save(newCurr);
				})));
		return result;
	}

	public XMLHandler processXML(InputStream is) {
		var handler = new XMLHandler();
		var factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			parser.parse(is, handler);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			log.debug("Parse error {}", e.getLocalizedMessage());
		}
		return handler;
	}

	private List<CurrencyValue> fillCurrencyDate(@NonNull List<CurrencyValue> currencyValue, @NonNull List<CurrencyDate> currencyDate) {
		currencyValue.forEach(i -> {
			var currDate = currencyDate.stream().filter(ii -> ii.getDate().equals(i.getCurrencyDate().getDate()))
					.findFirst().orElseThrow();
			i.setCurrencyDate(currDate);
		});
		return currencyValue;
	}

	@Scheduled(cron = "0 0 18 * * *")
	public void getCurrency() {
		log.info("Starting get currency rate {}", Instant.now());
		var webResult = webClient.getECBCurrency();
		if (webResult == null) {
			return;
		}
		var is = new ByteArrayInputStream(webResult.getBytes());
		var handler = processXML(is);
		var currencyValueList = handler.getParseResult();
		var currencyDate = currencyDateRepository.findFirstByOrderByDateDesc();
		if (currencyDate.isPresent()) {
			currencyValueList = currencyValueList.stream().filter(i -> i.getDate()
					.isAfter(currencyDate.map(CurrencyDate::getDate).get())).collect(Collectors.toList());
		}
		if (currencyValueList.isEmpty()) {
			log.info("There are no new currency data {}", Instant.now());
			return;
		}
		currencyValueList = fillCurrency(currencyValueList);
		var result = currencyValueList.stream().map(i -> currencyValueMapperImpl.transformToCurrencyValue(i))
				.collect(Collectors.toList());
		var dateList = result.stream().map(i -> i.getCurrencyDate()).distinct().collect(Collectors.toList());
		dateList = currencyDateRepository.saveAll(dateList);
		result = fillCurrencyDate(result, dateList);
		currencyValueRepository.saveAll(result);
		log.info("Currency saved {}", Instant.now());
	}
}
