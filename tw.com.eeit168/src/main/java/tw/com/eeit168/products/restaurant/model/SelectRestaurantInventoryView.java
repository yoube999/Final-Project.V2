package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "select_restaurant_inventory")
public class SelectRestaurantInventoryView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "restaurant_Inventory_id")
	private Integer restaurantInventoryId;
	
	@Column(name = "restaurant_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String restaurantName;
	
	@Column(name = "restaurant_address", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation )
	private String restaurantAddress;
	
	@Column(name = "availability_date")
	private java.sql.Date availabilityDate;
	
	@Column(name = "lunch")
	private Integer lunch;
	
	@Column(name = "dinner")
	private Integer dinner;

	
	@Override
	public String toString() {
		return "SelectRestaurantInventoryView [restaurantInventoryId=" + restaurantInventoryId + ", restaurantName="
				+ restaurantName + ", restaurantAddress=" + restaurantAddress + ", availabilityDate=" + availabilityDate
				+ ", lunch=" + lunch + ", dinner=" + dinner + "]";
	}


	public Integer getRestaurantInventoryId() {
		return restaurantInventoryId;
	}
	public void setRestaurantInventoryId(Integer restaurantInventoryId) {
		this.restaurantInventoryId = restaurantInventoryId;
	}
	public String getRestaurantName() {
		return restaurantName;
	}
	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}
	public String getRestaurantAddress() {
		return restaurantAddress;
	}
	public void setRestaurantAddress(String restaurantAddress) {
		this.restaurantAddress = restaurantAddress;
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
