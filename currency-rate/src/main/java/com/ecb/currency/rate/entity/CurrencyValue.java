package com.ecb.currency.rate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CURRENCY_VALUE", schema = "CURRENCY_RATE")
public class CurrencyValue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name="currency_date_id", nullable=false, updatable=false)
	private CurrencyDate currencyDate;
	@ManyToOne
	@JoinColumn(name="currency_id", nullable=false, updatable=false)
	private Currency currency;
	@Column(name = "rate")
	private Double rate;
}
