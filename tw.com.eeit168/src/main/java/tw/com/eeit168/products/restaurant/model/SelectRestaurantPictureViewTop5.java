package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "select_restaurant_pictrue_top5")
public class SelectRestaurantPictureViewTop5 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "restaurant_pictures_id")
	private Integer restaurantPicturesId;
	
//	@JoinColumn(name = "restaurant_id", insertable = false, updatable = false)
//	private Integer restaurantId;
	
	@Column(name = "restaurant_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation )
	private String restaurantName;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "descriptions", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation )
	private String descriptions;
	
	@Column(name = "url_image")
	private String urlImage;
	
	@Column(name = "times_purchased")
	private Integer timesPurchased;

	public Integer getRestaurantPicturesId() {
		return restaurantPicturesId;
	}

	public void setRestaurantPicturesId(Integer restaurantPicturesId) {
		this.restaurantPicturesId = restaurantPicturesId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Integer getTimesPurchased() {
		return timesPurchased;
	}

	public void setTimesPurchased(Integer timesPurchased) {
		this.timesPurchased = timesPurchased;
	}

	@Override
	public String toString() {
		return "SelectRestaurantPictureViewTop5 [restaurantPicturesId=" + restaurantPicturesId + ", restaurantName="
				+ restaurantName + ", price=" + price + ", descriptions=" + descriptions + ", urlImage=" + urlImage
				+ ", timesPurchased=" + timesPurchased + "]";
	}

	
	
	
}
