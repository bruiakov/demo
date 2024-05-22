package com.restaurant.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.entity.Lunch;

public interface LunchRepository extends JpaRepository<Lunch, Long> {

	List<Lunch> findByCusine_Id(Long cusineId);
}
