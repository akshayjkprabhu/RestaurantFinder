package com.ajp.dinein.domain.model

data class Restaurant(
		val restaurantId : Int,
		val restaurantName : String,
		val cuisineType : String,
		val image : String?,
		val location : String?,
		val reviewCount : Int
) : Matcher, IListItem {
	override fun matches(searchTerm : String) : Boolean {
		return restaurantName has searchTerm ||
		       cuisineType has searchTerm
	}
	
	override fun itemType() = ItemType.CONTENT
	
	fun reviews() = "$reviewCount Reviews"
}

data class RestaurantMenu(
		val restaurantId : Int,
		val categories : List<Category>
) : Matcher {
	override fun matches(searchTerm : String) : Boolean {
		return categories.any { it.matches(searchTerm) }
	}
}

data class Category(
		val catId : Int,
		val catName : String,
		val items : List<MenuItem>
) : Matcher {
	override fun matches(searchTerm : String) : Boolean {
		return items.any { it.matches(searchTerm) }
	}
}

data class MenuItem(
		val itemId : Int,
		val itemName : String,
		val price : Double
) : Matcher {
	
	override fun matches(searchTerm : String) : Boolean {
		return itemName has searchTerm
	}
}

infix fun String.has(searchTerm : String) : Boolean {
	return this.lowercase().contains(searchTerm.lowercase())
}

interface Matcher {
	fun matches(searchTerm : String) : Boolean
}

interface IListItem {
	fun itemType() : ItemType
}

enum class ItemType(val id : Int) {
	HEADER(1),
	ERROR(2),
	CONTENT(3)
}
