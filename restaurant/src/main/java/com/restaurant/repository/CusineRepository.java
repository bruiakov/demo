package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.entity.Cusine;

public interface CusineRepository extends JpaRepository<Cusine, Long> {

}
