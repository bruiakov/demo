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
import com.restaurant.entity.Meal;
import com.restaurant.fasade.MealFasade;

@RestController
@RequestMapping(path = "api")
public class MealController {

	@Autowired
	private MealFasade mealFasade;

	@GetMapping(path = "/meals/drinks")
	public List<Meal> getAllDrink() {
		return mealFasade.getAllDrink();
	}

	@GetMapping(path = "/meals/desserts")
	public List<Meal> getAllDessert() {
		return mealFasade.getAllDessert();
	}

	@GetMapping(path = "/meals")
	public List<Meal> getAllMeals() {
		return mealFasade.getAllMeals();
	}

	@PostMapping(path = "/meals")
	public void addMeal(@RequestBody Meal meal) {
		mealFasade.addMeal(meal);
	}

	@DeleteMapping(path = "/meals/{id}")
	public void deleteMeal(@PathVariable Long id) {
		mealFasade.deleteMeal(id);
	}
}
