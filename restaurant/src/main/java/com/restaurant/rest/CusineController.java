package com.restaurant.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.entity.Cusine;
import com.restaurant.entity.Lunch;
import com.restaurant.fasade.CusineFasade;

@RestController
@RequestMapping(path = "api")
public class CusineController {

	@Autowired
	private CusineFasade cusineFasade;

	@GetMapping(path = "/cuisines/{cusineId}/lunch")
	private List<Lunch> getLanchByCusine(@PathVariable(name = "cusineId") Long cusineId) {
		return cusineFasade.getLanchByCusine(cusineId);
	}

	@GetMapping(path = "/cuisines")
	private List<Cusine> getListCusine() {
		return cusineFasade.getAllCusine();
	}

	@PostMapping(path = "/cuisines")
	public void addCusine(@RequestBody(required = true) Cusine cusine) {
		cusineFasade.addCusine(cusine);
	}

	@DeleteMapping(path = "/cuisines/{id}")
	public void deleteCusine(@PathVariable(name = "id") Long id) {
		cusineFasade.deleteCusine(id);
	}
}
