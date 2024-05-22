package com.restaurant.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.entity.LunchMenu;
import com.restaurant.fasade.LunchMenuFasade;


@RestController
@RequestMapping(path = "/api")
public class LunchMenuController {

	@Autowired
	private LunchMenuFasade lunchMenuFasade;

	@PostMapping(path = "/lunchMenu")
	public LunchMenu addLunchMenu(@RequestBody LunchMenu lunchMenu) {
		return lunchMenuFasade.addLunchMenu(lunchMenu);
	}

	@DeleteMapping(path = "/lunchMenu/{id}")
	public void deleteLunchMenu(@PathVariable(name = "id") Long id) {
		lunchMenuFasade.deleteLunchMenu(id);
	}
}

