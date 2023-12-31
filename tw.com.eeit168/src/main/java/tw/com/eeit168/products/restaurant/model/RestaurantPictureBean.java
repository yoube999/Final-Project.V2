package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "restaurant_pictures")
public class RestaurantPictureBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "restaurant_pictures_id")
	private Integer restaurantPicturesId;
	
	@JoinColumn(name = "restaurant_id")
	private Integer restaurantId;
	
	@Column(name = "url_image")
	private String urlImage;

	
	@Override
	public String toString() {
		return "RestaurantPictureBean [restaurantPicturesId=" + restaurantPicturesId + ", restaurantId=" + restaurantId
				+ ", urlImage=" + urlImage + "]";
	}

	
	public Integer getRestaurantPicturesId() {
		return restaurantPicturesId;
	}
	public void setRestaurantPicturesId(Integer restaurantPicturesId) {
		this.restaurantPicturesId = restaurantPicturesId;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
}
