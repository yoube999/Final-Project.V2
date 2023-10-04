package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "restaurant_inventory")
public class RestaurantInventoryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "restaurant_Inventory_id")
	private Integer restaurantInventoryId;
	
	@JoinColumn(name = "restaurant_id")
	private Integer restaurantId;
	
	@Column(name = "availability_date")
	private java.sql.Date availabilityDate;
	
	@Column(name = "lunch")
	private Integer lunch;
	
	@Column(name = "dinner")
	private Integer dinner;

	
	@Override
	public String toString() {
		return "RestaurantInventoryBean [restaurantInventoryId=" + restaurantInventoryId + ", restaurantId="
				+ restaurantId + ", availabilityDate=" + availabilityDate + ", lunch=" + lunch + ", dinner=" + dinner
				+ "]";
	}


	public Integer getRestaurantInventoryId() {
		return restaurantInventoryId;
	}
	public void setRestaurantInventoryId(Integer restaurantInventoryId) {
		this.restaurantInventoryId = restaurantInventoryId;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public java.sql.Date getAvailabilityDate() {
		return availabilityDate;
	}
	public void setAvailabilityDate(java.sql.Date availabilityDate) {
		this.availabilityDate = availabilityDate;
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
