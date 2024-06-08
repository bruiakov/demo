package com.ecb.currency.rate.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyDTO {
	private LocalDate date;
	private String currencyCode;
	private String currencyDescription;
	private Double rate;
}
