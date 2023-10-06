package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "restaurant")
public class RestaurantBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "restaurant_id")
	private Integer restaurantId;
	
	@Column(name = "restaurant_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String restaurantName;
	
	@Column(name = "restaurant_address", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String restaurantAddress;
	
	@Column(name = "descriptions", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String descriptions;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "times_purchased")
	private Integer timesPurchased;
	
	@Column(name = "product_status")
	private boolean productStatus;

	
	@Override
	public String toString() {
		return "RestaurantBean [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantAddress=" + restaurantAddress + ", descriptions=" + descriptions + ", contactNumber="
				+ contactNumber + ", price=" + price + ", timesPurchased=" + timesPurchased + ", productStatus="
				+ productStatus + "]";
	}


	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
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
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getTimesPurchased() {
		return timesPurchased;
	}
	public void setTimesPurchased(Integer timesPurchased) {
		this.timesPurchased = timesPurchased;
	}
	public boolean isProductStatus() {
		return productStatus;
	}
	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}
	
}
