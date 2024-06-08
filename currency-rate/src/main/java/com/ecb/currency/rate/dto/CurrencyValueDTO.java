package com.ecb.currency.rate.dto;

import java.time.LocalDate;

import com.ecb.currency.rate.entity.Currency;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyValueDTO {
	private LocalDate date;
	private String currencyCode;
	private Double rate;
	private Currency currency;
}
