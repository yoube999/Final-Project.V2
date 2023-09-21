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
	private int priceId;
	
	@ManyToOne
	@JoinColumn(name = "accommodation_id", nullable = false)
	private Accommodation accommodation;
	
	@ManyToOne
	@JoinColumn(name = "room_type_id", nullable = false)
	private AccommodationRoomType accommodationRoomType;
	
	@Column(name = "weekday_price", nullable = false)
	private int weekdayPrice;
	
	
	public AccommodationPrice() {
		
	}
	
	public AccommodationPrice(int priceId, Accommodation accommodation, AccommodationRoomType accommodationRoomType,
			int weekdayPrice, int weekendPrice) {
		super();
		this.priceId = priceId;
		this.accommodation = accommodation;
		this.accommodationRoomType = accommodationRoomType;
		this.weekdayPrice = weekdayPrice;
		this.weekendPrice = weekendPrice;
	}


	public int getPriceId() {
		return priceId;
	}


	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}


	public Accommodation getAccommodation() {
		return accommodation;
	}


	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}


	public AccommodationRoomType getAccommodationRoomType() {
		return accommodationRoomType;
	}


	public void setAccommodationRoomType(AccommodationRoomType accommodationRoomType) {
		this.accommodationRoomType = accommodationRoomType;
	}


	public int getWeekdayPrice() {
		return weekdayPrice;
	}


	public void setWeekdayPrice(int weekdayPrice) {
		this.weekdayPrice = weekdayPrice;
	}


	public int getWeekendPrice() {
		return weekendPrice;
	}


	public void setWeekendPrice(int weekendPrice) {
		this.weekendPrice = weekendPrice;
	}


	@Column(name = "weekend_price", nullable = false)
	private int weekendPrice;
}
