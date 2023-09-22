package tw.com.eeit168.products.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservation_restuarant")
public class ReservationRestuarantBean {
	
	@Id
	@Column(name = "reservation_restuarant_id")
	private Integer reservation_restuarant_id;
	
	@JoinColumn(name = "record_id")
	private Integer record_id;
	
	@JoinColumn(name = "restaurant_id")
	private Integer restaurant_id;
	
	@Column(name = "reservation_date")
	private java.util.Date reservation_date;
	
	@Column(name = "total_count")
	private Integer total_count;
	
	@Column(name = "total_price")
	private Integer total_price;
	
	@Column(name = "record_restuarant_status")
	private String record_restuarant_status;

	
	@Override
	public String toString() {
		return "ReservationRestuarantBean [reservation_restuarant_id=" + reservation_restuarant_id + ", record_id="
				+ record_id + ", restaurant_id=" + restaurant_id + ", reservation_date=" + reservation_date
				+ ", total_count=" + total_count + ", total_price=" + total_price + ", record_restuarant_status="
				+ record_restuarant_status + "]";
	}
	

	public Integer getReservation_restuarant_id() {
		return reservation_restuarant_id;
	}

	public void setReservation_restuarant_id(Integer reservation_restuarant_id) {
		this.reservation_restuarant_id = reservation_restuarant_id;
	}

	public Integer getRecord_id() {
		return record_id;
	}

	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}

	public Integer getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Integer restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public java.util.Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(java.util.Date reservation_date) {
		this.reservation_date = reservation_date;
	}

	public Integer getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}

	public Integer getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}

	public String getRecord_restuarant_status() {
		return record_restuarant_status;
	}

	public void setRecord_restuarant_status(String record_restuarant_status) {
		this.record_restuarant_status = record_restuarant_status;
	}	
	
}
