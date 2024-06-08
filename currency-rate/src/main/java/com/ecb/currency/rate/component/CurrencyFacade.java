package com.ecb.currency.rate.component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ecb.currency.rate.dto.CurrencyDTO;
import com.ecb.currency.rate.mapper.CurrencyValueMapper;
import com.ecb.currency.rate.repository.CurrencyValueRepository;

@Component
public class CurrencyFacade {

	private CurrencyValueRepository currencyValueRepository;
	private CurrencyValueMapper currencyValueMapperImpl;

	@Autowired
	public void setCurrencyValueRepository(CurrencyValueRepository currencyValueRepository) {
		this.currencyValueRepository = currencyValueRepository;
	}

	@Autowired
	public void setCurrencyValueMapperImpl(CurrencyValueMapper currencyValueMapperImpl) {
		this.currencyValueMapperImpl = currencyValueMapperImpl;
	}

	public List<CurrencyDTO> getCurrencyList(LocalDate date) {
		return currencyValueRepository.findByCurrencyDate_Date(date).stream()
				.map(i -> currencyValueMapperImpl.transformToCurrencyDTO(i)).collect(Collectors.toList());
	}

	public List<CurrencyDTO> getCurrencyByCode(String code) {
		return currencyValueRepository.findByCurrency_CurrencyCodeOrderByCurrencyDate_Date(code).stream()
				.map(i -> currencyValueMapperImpl.transformToCurrencyDTO(i)).collect(Collectors.toList());
	}
}
