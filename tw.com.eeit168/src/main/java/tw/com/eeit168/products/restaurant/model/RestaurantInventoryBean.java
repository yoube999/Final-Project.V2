package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity //@Entity是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "restaurant_inventory")
public class RestaurantInventoryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "restaurant_Inventory_id")
	private Integer restaurant_Inventory_id;
	
	@JoinColumn(name = "restaurant_id")
	private Integer restaurant_id;
	
	@Column(name = "availability_date")
	private java.sql.Date availability_date;
	
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
	public java.sql.Date getAvailability_date() {
		return availability_date;
	}
	public void setAvailability_date(java.sql.Date availability_date) {
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
