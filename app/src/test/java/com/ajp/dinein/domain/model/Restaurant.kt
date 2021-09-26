package com.ajp.dinein.domain.model

import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test

class RestaurantTest {
	
	private lateinit var restaurant : Restaurant
	
	@Before
	fun initialize() {
		restaurant = Restaurant(2, "Shanthi Sagar", "Indian, South Indian, Breakfast")
	}
	
	@Test
	fun `cuisine matching, but not restaurant`() {
		val searchTerm = "South Indian"
		val result = restaurant.matches(searchTerm)
		assertEquals(result, true)
	}
	
	@Test
	fun `restaurant name is matching but not cuisine`() {
		val searchTerm = "Shanthi"
		val result = restaurant.matches(searchTerm)
		assertEquals(result, true)
	}
	
	
	@After
	fun destroy() {
	
	}
}

class MatcherTest {
	lateinit var text : String
	
	@Before
	fun initialize() {
		text = "Shanti Sagar"
	}
	
	@Test
	fun `case sensitive match`() {
		val searchTerm = "shanti sagAr"
		val result = text has searchTerm
		assertEquals(result, true)
	}
	
	@Test
	fun `random text match`() {
		val searchTerm = "shanti sagar920"
		val result = text has searchTerm
		assertEquals(result, false)
	}
	
	@Test
	fun `empty text will not match`() {
		val searchTerm = ""
		val result = text has searchTerm
		assertEquals(result, true)
	}
}