package com.ecb.currency.rate.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.ecb.currency.rate.dto.CurrencyValueDTO;

import lombok.Getter;

public class XMLHandler extends DefaultHandler {
	@Getter
	private List<CurrencyValueDTO> parseResult = new ArrayList<>();
	private final String DATE_FORMAT = "yyyy-MM-dd";
	private DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
	private LocalDate date = LocalDate.now();
	private Double rate;

	private void parseInstant(String strDate) {
		if (strDate != null) {
			this.date = LocalDate.parse(strDate, format);
		}
	}

	private Double parseRate(String rate) {
		if (rate != null) {
			return Double.parseDouble(rate);
		}
		return 0.0;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		if (qName.equals("Cube")) {
			parseInstant(attributes.getValue("time"));
			var currency = attributes.getValue("currency");
			if (currency != null) {
				this.rate = parseRate(attributes.getValue("rate"));
				parseResult.add(CurrencyValueDTO.builder().date(date).currencyCode(currency).rate(rate).build());
			}
		}
	}
}
