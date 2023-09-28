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
	private Integer restaurant_Inventory_id;
	
	@Column(name = "restaurant_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String restaurant_name;
	
	@Column(name = "restaurant_address", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation )
	private String restaurant_address;
	
	@Column(name = "availability_date")
	private java.sql.Date availability_date;

	
	@Override
	public String toString() {
		return "SelectRestaurantInventoryView [restaurant_Inventory_id=" + restaurant_Inventory_id
				+ ", restaurant_name=" + restaurant_name + ", restaurant_address=" + restaurant_address
				+ ", availability_date=" + availability_date + "]";
	}


	public Integer getRestaurant_Inventory_id() {
		return restaurant_Inventory_id;
	}
	public void setRestaurant_Inventory_id(Integer restaurant_Inventory_id) {
		this.restaurant_Inventory_id = restaurant_Inventory_id;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getRestaurant_address() {
		return restaurant_address;
	}
	public void setRestaurant_address(String restaurant_address) {
		this.restaurant_address = restaurant_address;
	}
	public java.sql.Date getAvailability_date() {
		return availability_date;
	}
	public void setAvailability_date(java.sql.Date availability_date) {
		this.availability_date = availability_date;
	}

}
