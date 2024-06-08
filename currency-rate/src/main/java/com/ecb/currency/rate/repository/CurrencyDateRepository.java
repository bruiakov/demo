package com.ecb.currency.rate.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecb.currency.rate.entity.CurrencyDate;

public interface CurrencyDateRepository extends JpaRepository<CurrencyDate, Long> {

	Optional<CurrencyDate> findFirstByOrderByDateDesc();

}
