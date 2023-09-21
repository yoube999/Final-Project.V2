package tw.com.eeit168.products.restaurant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class RestaurantBean {
	
	@Id
	@Column(name = "restaurant_id")
	private Integer restaurant_id;
	
	@Column(name = "restaurant_name")
	private String restaurant_name;
	
	@Column(name = "restaurant_address")
	private String restaurant_address;
	
	@Column(name = "contact_number")
	private String contact_number;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "rating")
	private String rating;
	
	@Column(name = "times_purchased")
	private Integer times_purchased;

	
	@Override
	public String toString() {
		return "RestaurantBean [restaurant_id=" + restaurant_id + ", restaurant_name=" + restaurant_name
				+ ", restaurant_address=" + restaurant_address + ", contact_number=" + contact_number + ", price="
				+ price + ", rating=" + rating + ", times_purchased=" + times_purchased + "]";
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
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Integer getTimes_purchased() {
		return times_purchased;
	}
	public void setTimes_purchased(Integer times_purchased) {
		this.times_purchased = times_purchased;
	}
	
}
