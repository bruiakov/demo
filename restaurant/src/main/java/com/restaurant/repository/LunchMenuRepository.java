package com.restaurant.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.entity.LunchMenu;

public interface LunchMenuRepository extends JpaRepository<LunchMenu, Long> {

	List<LunchMenu> findByLunch_Id(Long lunchId);
}
