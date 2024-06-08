package com.ecb.currency.rate.component;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class WebClient {

	private RestClient client = RestClient.builder().baseUrl("https://www.ecb.europa.eu").build();
	
	public String getECBCurrency() {
		return client.get().uri("stats/eurofxref/eurofxref-hist-90d.xml").accept(MediaType.APPLICATION_XML).retrieve().body(String.class);
	}
}
