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
@Table(name = "accommodation_room_type")
public class AccommodationRoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_type_id")
	private int roomTypeId;
	
	@ManyToOne
	@JoinColumn(name = "accommodation_id", nullable = false)
	private Accommodation accommodation;
	
	@Column(name = "room_type_name", nullable = false)
	private String roomTypeName;
	
	
	@Column(name = "capacity", nullable = false)
	private String capacity;
	
	
	public int getRoomTypeId() {
		return roomTypeId;
	}


	public AccommodationRoomType() {
		
	}
	
	public AccommodationRoomType(int roomTypeId, Accommodation accommodation, String roomTypeName, String capacity,
			int bedsAmount) {
		super();
		this.roomTypeId = roomTypeId;
		this.accommodation = accommodation;
		this.roomTypeName = roomTypeName;
		this.capacity = capacity;
		this.bedsAmount = bedsAmount;
	}


	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}


	public Accommodation getAccommodation() {
		return accommodation;
	}


	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}


	public String getRoomTypeName() {
		return roomTypeName;
	}


	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}


	public String getCapacity() {
		return capacity;
	}


	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}


	public int getBedsAmount() {
		return bedsAmount;
	}


	public void setBedsAmount(int bedsAmount) {
		this.bedsAmount = bedsAmount;
	}


	@Column(name = "beds_amount", nullable = false)
	private int bedsAmount;
}
