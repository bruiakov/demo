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
import com.restaurant.entity.Lunch;
import com.restaurant.entity.LunchMenu;
import com.restaurant.entity.Meal;
import com.restaurant.fasade.LunchFacade;

@RestController
@RequestMapping(path = "api")
public class LunchController {

	@Autowired
	private LunchFacade mealFacade;

	@GetMapping(path = "/lunch/{lunchId}/meals")
	public List<Meal> getMealsByLunch(@PathVariable(name = "lunchId") Long lunchId) {
		return mealFacade.getMealsByLunch(lunchId);
	}

	@GetMapping(path = "/lunch/{lunchId}/lunchMenus")
	public List<LunchMenu> getLunchMenusByLunch(@PathVariable(name = "lunchId") Long lunchId) {
		return mealFacade.getLunchMenusByLunch(lunchId);
	}

	@PostMapping(path = "/lunch")
	public void addLunch(@RequestBody() Lunch lunch) {
		mealFacade.addLunch(lunch);
	}
	
	@DeleteMapping(path = "/lunch/{id}")
	public void deleteLunch(@PathVariable Long id) {
		mealFacade.deleteLunch(id);
	}
}
