package com.restaurant.fasade;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.entity.Lunch;
import com.restaurant.entity.LunchMenu;
import com.restaurant.entity.Meal;
import com.restaurant.repository.LunchMenuRepository;
import com.restaurant.repository.LunchRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class LunchFacade {

	private LunchRepository lunchRepository;
	private LunchMenuRepository lunchMenuRepository;

	@Autowired
	public void setLunchRepository(LunchRepository lunchRepository) {
		this.lunchRepository = lunchRepository;
	}

	@Autowired
	public void setLunchMenuRepository(LunchMenuRepository lunchMenuRepository) {
		this.lunchMenuRepository = lunchMenuRepository;
	}

	public List<Meal> getMealsByLunch(Long lunchId) {
		if (lunchId == null) {
			return Collections.emptyList();
		}
		return lunchMenuRepository.findByLunch_Id(lunchId).stream().map(LunchMenu::getMeal)
				.collect(Collectors.toList());
	}

	public List<LunchMenu> getLunchMenusByLunch(Long lunchId) {
		if (lunchId == null) {
			return Collections.emptyList();
		}
		return lunchMenuRepository.findByLunch_Id(lunchId);
	}

	public Lunch addLunch(@NotNull Lunch lunch) {
		return lunchRepository.save(lunch);
	}

	public void deleteLunch(@NotNull Long id) {
		var lunch = lunchRepository.findById(id);
		lunch.ifPresent(i -> lunchRepository.delete(i));
	}
}
