package com.restaurant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.restaurant.entity.Cusine;
import com.restaurant.entity.Lunch;
import com.restaurant.entity.Meal;
import com.restaurant.entity.OrderPart;
import com.restaurant.fasade.CusineFasade;
import com.restaurant.fasade.LunchFacade;
import com.restaurant.fasade.MealFasade;
import com.restaurant.fasade.OrderFasade;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
class RestaurantApplicationTests {

	@Autowired
	private CusineFasade cusineFasade;
	@Autowired
	private LunchFacade lunchFacade;
	@Autowired
	private OrderFasade orderFasade;
	@Autowired
	private MealFasade mealFasade;

	@Test
	public void testAddOrder() {
		var mealList = lunchFacade.getMealsByLunch(1L);
		var order = orderFasade.createOrder(mealList);
		assertThat(order).isNotNull();
		assertEquals(2, order.getOrders().size());
	}

	@Test
	public void testDeleteOrder() {
		var mealList = lunchFacade.getMealsByLunch(1L);
		var order = orderFasade.createOrder(mealList);
		var orderNumber = order.getOrderNumber();
		orderFasade.deleteOrder(order.getId());
		assertThrowsExactly(NoSuchElementException.class, () -> orderFasade.getOrderByNumber(orderNumber));
	}

	@Test
	public void testUpdateOrder() {
		var mealList = lunchFacade.getMealsByLunch(1L);
		var order = orderFasade.createOrder(mealList);
		mealList = lunchFacade.getMealsByLunch(2L);
		orderFasade.updateOrder(order.getId(), mealList);
		order = orderFasade.getOrderByNumber(order.getOrderNumber());
		assertThat(order).isNotNull();
		assertThat(order.getOrders().stream().map(OrderPart::getMeal).collect(Collectors.toList())).hasSameElementsAs(mealList);
	}

	@Test
	public void testFindAll() {
		assertEquals(3, cusineFasade.getAllCusine().size());
	}

	@Test
	public void testAddCusine() {
		var size = cusineFasade.getAllCusine().size();
		var cusine = new Cusine();
		cusine.setName("Test Cusine");
		cusineFasade.addCusine(cusine);
		assertEquals(++size, cusineFasade.getAllCusine().size());
	}

	@Test
	public void testDeleteCusine() {
		cusineFasade.deleteCusine(1L);
		assertEquals(0, cusineFasade.getLanchByCusine(1L).size());
	}

	@Test
	public void testLunchAdd() {
		var lunch = new Lunch();
		lunch.setName("Test Lunch");
		var cusine = cusineFasade.getAllCusine().stream().findFirst().orElseThrow();
		lunch.setCusine(cusine);
		var newLunch = lunchFacade.addLunch(lunch);
		var lunchs = cusineFasade.getLanchByCusine(cusine.getId());
		assertThat(newLunch).isIn(lunchs);
	}


	@Test
	public void testLunchDelete() {
		var lunchs1 = cusineFasade.getLanchByCusine(2L);
		lunchFacade.deleteLunch(lunchs1.get(0).getId());
		var lunchs2 = cusineFasade.getLanchByCusine(2L);
		assertNotEquals(lunchs1.size(), lunchs2.size());
	}

	@Test
	public void testMealDrink() {
		assertEquals(4, mealFasade.getAllDrink().size());
	}

	@Test
	public void testMealDessert() {
		assertEquals(3, mealFasade.getAllDessert().size());
	}

	@Test
	public void testMealAdd() {
		var newMeal = new Meal();
		newMeal.setName("Test Meal");
		newMeal.setPrice(111.11);
		newMeal = mealFasade.addMeal(newMeal);
		var listMeal = mealFasade.getAllMeals();
		assertThat(newMeal).isIn(listMeal);
	}

	@Test
	public void testMealDelete() {
		var listMeal1 = mealFasade.getAllMeals();
		mealFasade.deleteMeal(listMeal1.get(0).getId());
		var listMeal2 = mealFasade.getAllMeals();
		assertNotEquals(listMeal1.size(), listMeal2.size());
	}
}
