package com.restaurant.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity(name = "MEAL")
public class Meal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "PRICE", nullable = false)
	private Double price;
	@ManyToOne
	@JoinColumn(name = "MEAL_CATEGIRY_ID")
	private MealCategory mealCategory;
	@ManyToOne
	@JoinColumn(name = "MEAL_TYPE_ID")
	private MealType mealType;
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany(mappedBy = "meal", cascade = CascadeType.REMOVE)
	private List<LunchMenu> lunchMenu;
}
