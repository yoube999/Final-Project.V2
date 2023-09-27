package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //@Entity是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "restaurant")
public class RestaurantBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "restaurant_id")
	private Integer restaurant_id;
	
	@Column(name = "restaurant_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String restaurant_name;
	
	@Column(name = "restaurant_address", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String restaurant_address;
	
	@Column(name = "descriptions", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String descriptions;
	
	@Column(name = "contact_number")
	private String contact_number;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "times_purchased")
	private Integer times_purchased;

	
	@Override
	public String toString() {
		return "RestaurantBean [restaurant_id=" + restaurant_id + ", restaurant_name=" + restaurant_name
				+ ", restaurant_address=" + restaurant_address + ", descriptions=" + descriptions + ", contact_number="
				+ contact_number + ", price=" + price + ", times_purchased=" + times_purchased + "]";
	}


	public Integer getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
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
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getTimes_purchased() {
		return times_purchased;
	}
	public void setTimes_purchased(Integer times_purchased) {
		this.times_purchased = times_purchased;
	}
	
}
