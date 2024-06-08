package com.ecb.currency.rate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecb.currency.rate.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
