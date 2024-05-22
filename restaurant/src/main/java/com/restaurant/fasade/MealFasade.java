package com.restaurant.fasade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurant.entity.Meal;
import com.restaurant.repository.MealRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class MealFasade {

	private MealRepository mealRepository;

	@Autowired
	public void setMealRepository(MealRepository mealRepository) {
		this.mealRepository = mealRepository;
	}

	public List<Meal> getAllDrink() {
		return mealRepository.findByMealType_Code("DRINK");
	}

	public List<Meal> getAllDessert() {
		return mealRepository.findByMealCategory_Code("DESSERT");
	}

	public List<Meal> getAllMeals() {
		return mealRepository.findAll();
	}

	public Meal addMeal(@NotNull Meal meal) {
		return mealRepository.save(meal);
	}
	
	public void deleteMeal(@NotNull Long id) {
		var meal = mealRepository.findById(id);
		meal.ifPresent(i -> mealRepository.delete(i));
	}
}