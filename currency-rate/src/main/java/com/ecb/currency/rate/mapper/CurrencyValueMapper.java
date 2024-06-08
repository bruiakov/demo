package com.ecb.currency.rate.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecb.currency.rate.dto.CurrencyDTO;
import com.ecb.currency.rate.dto.CurrencyValueDTO;
import com.ecb.currency.rate.entity.CurrencyValue;

@Mapper(componentModel = "spring")
public interface CurrencyValueMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "currencyDate.date", source = "date")
	@Mapping(target = "rate", source = "rate")
	@Mapping(target = "currency", source = "currency")
	CurrencyValue transformToCurrencyValue(CurrencyValueDTO dto);

	@Mapping(target = "date", source = "currencyDate.date")
	@Mapping(target = "currencyCode", source = "currency.currencyCode")
	@Mapping(target = "currencyDescription", source = "currency.currencyDescription")
	@Mapping(target = "rate", source = "rate")
	CurrencyDTO transformToCurrencyDTO(CurrencyValue entity);
}
