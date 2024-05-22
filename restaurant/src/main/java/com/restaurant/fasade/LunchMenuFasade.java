package com.restaurant.fasade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.entity.LunchMenu;
import com.restaurant.repository.LunchMenuRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class LunchMenuFasade {

	private LunchMenuRepository lunchMenuRepository;

	@Autowired
	public void setLunchMenuRepository(LunchMenuRepository lunchMenuRepository) {
		this.lunchMenuRepository = lunchMenuRepository;
	}

	public LunchMenu addLunchMenu(@NotNull LunchMenu lunchMenu) {
		return lunchMenuRepository.save(lunchMenu);
	}

	public void deleteLunchMenu(@NotNull Long lunchMenuId) {
		lunchMenuRepository.findById(lunchMenuId).ifPresent(i -> lunchMenuRepository.delete(i));
	}

}
