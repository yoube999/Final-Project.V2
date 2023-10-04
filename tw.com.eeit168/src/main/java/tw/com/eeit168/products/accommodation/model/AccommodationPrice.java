package tw.com.eeit168.products.accommodation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accommodation_price")
public class AccommodationPrice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer priceId;
	
//	@ManyToOne
	@JoinColumn(name = "accommodation_id", nullable = false)
	private Integer accommodationId;
	
//	@ManyToOne
	@JoinColumn(name = "room_type_id", nullable = false)
	private Integer roomTypeId;
	
	@Column(name = "weekday_price", nullable = false)
	private Integer weekdayPrice;
	
	@Column(name = "weekend_price", nullable = false)
	private Integer weekendPrice;

	public Integer getPriceId() {
		return priceId;
	}

	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}

	public Integer getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Integer accommodationId) {
		this.accommodationId = accommodationId;
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Integer getWeekdayPrice() {
		return weekdayPrice;
	}

	public void setWeekdayPrice(Integer weekdayPrice) {
		this.weekdayPrice = weekdayPrice;
	}

	public Integer getWeekendPrice() {
		return weekendPrice;
	}

	public void setWeekendPrice(Integer weekendPrice) {
		this.weekendPrice = weekendPrice;
	}

	@Override
	public String toString() {
		return "AccommodationPrice [priceId=" + priceId + ", accommodationId=" + accommodationId + ", roomTypeId="
				+ roomTypeId + ", weekdayPrice=" + weekdayPrice + ", weekendPrice=" + weekendPrice + "]";
	}

	
	

	
	
	
}
