package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.entity.MealCategory;

public interface MealCategoryRepository extends JpaRepository<MealCategory, Long> {

}
