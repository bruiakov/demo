package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.entity.MealType;

public interface MealTypeRepository extends JpaRepository<MealType, Long> {

}
