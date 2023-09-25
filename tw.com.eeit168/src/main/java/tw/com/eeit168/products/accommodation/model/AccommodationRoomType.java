package tw.com.eeit168.products.accommodation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "accommodation_room_type")
public class AccommodationRoomType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_type_id")
	private Integer roomTypeId;
	
//	@ManyToOne
	@JoinColumn(name = "accommodation_id", nullable = false)
	private Integer accommodationId;
	
	@Column(name = "room_type_name", nullable = false, columnDefinition = "nvarchar")
	private String roomTypeName;
	
	
	@Column(name = "capacity", nullable = false, columnDefinition = "nvarchar")
	private String capacity;
	
	@Column(name = "beds_amount", nullable = false)
	private Integer bedsAmount;

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Integer getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Integer accommodationId) {
		this.accommodationId = accommodationId;
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

	public Integer getBedsAmount() {
		return bedsAmount;
	}

	public void setBedsAmount(Integer bedsAmount) {
		this.bedsAmount = bedsAmount;
	}

	@Override
	public String toString() {
		return "AccommodationRoomType [roomTypeId=" + roomTypeId + ", accommodationId=" + accommodationId
				+ ", roomTypeName=" + roomTypeName + ", capacity=" + capacity + ", bedsAmount=" + bedsAmount + "]";
	}
	
	

}
