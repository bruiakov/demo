package com.ecb.currency.rate.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecb.currency.rate.component.CurrencyFacade;
import com.ecb.currency.rate.dto.CurrencyDTO;

@RestController
@RequestMapping(path = "api")
public class CurrencyRateController {

	private CurrencyFacade currencyFacade;

	@Autowired
	public void setCurrencyFacad(CurrencyFacade currencyFacad) {
		this.currencyFacade = currencyFacad;
	}
	
	//@CrossOrigin(value = "http://localhost:4200/")
	@GetMapping(path = "/currencies/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CurrencyDTO> getCurrencyList(@PathVariable(name = "date") LocalDate date) {
		return currencyFacade.getCurrencyList(date);
	}

	//@CrossOrigin(value = "http://localhost:4200/")
	@GetMapping(path = "/currencies/currency/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CurrencyDTO> getCurrencyByCode(@PathVariable(name = "code") String code) {
		return currencyFacade.getCurrencyByCode(code);
	}
}
