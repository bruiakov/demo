package com.restaurant.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {

	List<Meal> findByMealType_Code(String mealTypeCode);

	List<Meal> findByMealCategory_Code(String mealCategoryCode);

}
