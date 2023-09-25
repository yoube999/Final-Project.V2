package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurant_pictures")
public class RestaurantPictureBean {

	@Id
	@Column(name = "restaurant_pictures_id")
	private Integer restaurant_pictures_id;
	
	@JoinColumn(name = "restaurant_id")
	private Integer restaurant_id;
	
	@Column(name = "url_image")
	private String url_image;
	
	
	@Override
	public String toString() {
		return "RestaurantPicturesBean [restaurant_pictures_id=" + restaurant_pictures_id + ", restaurant_id="
				+ restaurant_id + ", url_image=" + url_image + "]";
	}
	
	
	public Integer getRestaurant_pictures_id() {
		return restaurant_pictures_id;
	}
	public void setRestaurant_pictures_id(Integer restaurant_pictures_id) {
		this.restaurant_pictures_id = restaurant_pictures_id;
	}
	public Integer getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getUrl_image() {
		return url_image;
	}
	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}
	
}
