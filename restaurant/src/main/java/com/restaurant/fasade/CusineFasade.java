package com.restaurant.fasade;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.restaurant.entity.Cusine;
import com.restaurant.entity.Lunch;
import com.restaurant.repository.CusineRepository;
import com.restaurant.repository.LunchRepository;

import jakarta.validation.constraints.NotNull;

@Service
public class CusineFasade {

	private LunchRepository lunchRepository;
	
	private CusineRepository cusineRepository;

	@Autowired
	public void setLunchRepository(LunchRepository lunchRepository) {
		this.lunchRepository = lunchRepository;
	}

	@Autowired
	public void setCusineRepository(CusineRepository cusineRepository) {
		this.cusineRepository = cusineRepository;
	}

	public List<Cusine> getAllCusine() {
		return cusineRepository.findAll();
	}

	public List<Lunch> getLanchByCusine(@NotNull Long cusineId) {
		return lunchRepository.findByCusine_Id(cusineId);
	}

	public void addCusine(@NotNull Cusine cusine) {
		cusineRepository.save(cusine);
	}

	public void deleteCusine(@NotNull Long id) {
		var cusine = cusineRepository.findById(id);
		cusine.ifPresent(i -> cusineRepository.delete(i));
	}
}
