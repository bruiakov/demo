package com.ecb.currency.rate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ecb.currency.rate.component.WebClient;
import com.ecb.currency.rate.entity.Currency;
import com.ecb.currency.rate.repository.CurrencyRepository;
import com.ecb.currency.rate.service.GrabCurrency;
import com.ecb.currency.rate.utils.XMLHandler;

import static org.mockito.BDDMockito.given;

@SpringBootTest
class CurrencyRateApplicationTests {

	@MockBean
	private CurrencyRepository currencyRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testWebClient() {
		WebClient wc = new WebClient();
		var result = wc.getECBCurrency();
		assertThat(result).isNotEmpty();
	}

	@Test
	public void testCurrencyRepositorySave() {
		Currency curr = new Currency();
		curr.setId(1L);
		curr.setCurrencyCode("USD");
		curr.setCurrencyDescription("US dollar");
		given(this.currencyRepository.save(any(Currency.class))).willReturn(curr);
	}

	@Test
	public void testParseXML() {
		GrabCurrency grab = new GrabCurrency();
		XMLHandler handler = new XMLHandler();
		try {
			var file = new FileInputStream("./src/main/resources/eurofxref-daily.xml");
			handler = grab.processXML(file);
		} catch (FileNotFoundException e) {
			assertTrue(false);
		}
		assertThat(handler.getParseResult()).hasSize(30);
	}

}
