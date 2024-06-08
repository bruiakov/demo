package com.ecb.currency.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // (exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
@EnableJpaRepositories("com.ecb.currency.rate.repository")
@ComponentScan(basePackages = { "com.ecb.currency.rate.component", "com.ecb.currency.rate.service",
		"com.ecb.currency.rate.mapper", "com.ecb.currency.rate.rest" })
public class CurrencyRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateApplication.class, args);
	}

}
