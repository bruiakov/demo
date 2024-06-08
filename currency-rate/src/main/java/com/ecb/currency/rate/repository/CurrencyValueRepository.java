package com.ecb.currency.rate.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecb.currency.rate.entity.CurrencyValue;

public interface CurrencyValueRepository extends JpaRepository<CurrencyValue, Long> {

	public List<CurrencyValue> findByCurrencyDate_Date(LocalDate date);

	public List<CurrencyValue> findByCurrency_CurrencyCodeOrderByCurrencyDate_Date(String code);
}
