package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant_inventory")
public class RestaurantInventoryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_Inventory_id")
	private Integer restaurant_Inventory_id;
	
	@JoinColumn(name = "restaurant_id")
	private Integer restaurant_id;
	
	@Column(name = "availability_date")
	private java.util.Date availability_date;
	
	@Column(name = "lunch")
	private Integer lunch;
	
	@Column(name = "dinner")
	private Integer dinner;
	
	
	@Override
	public String toString() {
		return "RestaurantInventoryBean [restaurant_Inventory_id=" + restaurant_Inventory_id + ", restaurant_id="
				+ restaurant_id + ", availability_date=" + availability_date + ", lunch=" + lunch + ", dinner=" + dinner
				+ "]";
	}
	
	
	public Integer getRestaurant_Inventory_id() {
		return restaurant_Inventory_id;
	}
	public void setRestaurant_Inventory_id(Integer restaurant_Inventory_id) {
		this.restaurant_Inventory_id = restaurant_Inventory_id;
	}
	public Integer getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public java.util.Date getAvailability_date() {
		return availability_date;
	}
	public void setAvailability_date(java.util.Date availability_date) {
		this.availability_date = availability_date;
	}
	public Integer getLunch() {
		return lunch;
	}
	public void setLunch(Integer lunch) {
		this.lunch = lunch;
	}
	public Integer getDinner() {
		return dinner;
	}
	public void setDinner(Integer dinner) {
		this.dinner = dinner;
	}
	
}
