package tw.com.eeit168.products.accommodation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity//是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "select_accommodation_inventory_roomtype_price")
public class SelectAccommodationInventoryRoomtypePriceView {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//這個用來標記此欄位是自動產生
	@Column(name = "inventory_id")
	private Integer accommodationInventoryId;
	
	@Column(name = "accommodation_name", nullable = false, columnDefinition = "nvarchar(255)")
	private String accommodationName;
	
	@Column(name = "room_type_name", nullable = false, columnDefinition = "nvarchar")
	private String roomTypeName;
	
	@Column(name = "availability_date")
	private java.sql.Date availabilityDate;
	
	@Column(name = "available_rooms")
	private Integer availableRooms;
	
	@Column(name = "weekday_price", nullable = false)
	private Integer weekdayPrice;
	
	@Column(name = "weekend_price", nullable = false)
	private Integer weekendPrice;

	public Integer getAccommodationInventoryId() {
		return accommodationInventoryId;
	}

	public void setAccommodationInventoryId(Integer accommodationInventoryId) {
		this.accommodationInventoryId = accommodationInventoryId;
	}

	public String getAccommodationName() {
		return accommodationName;
	}

	public void setAccommodationName(String accommodationName) {
		this.accommodationName = accommodationName;
	}

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public java.sql.Date getAvailabilityDate() {
		return availabilityDate;
	}

	public void setAvailabilityDate(java.sql.Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}

	public Integer getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(Integer availableRooms) {
		this.availableRooms = availableRooms;
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
		return "SelectAccommodationInventoryRoomtypePriceView [accommodationInventoryId=" + accommodationInventoryId
				+ ", accommodationName=" + accommodationName + ", roomTypeName=" + roomTypeName + ", availabilityDate="
				+ availabilityDate + ", availableRooms=" + availableRooms + ", weekdayPrice=" + weekdayPrice
				+ ", weekendPrice=" + weekendPrice + "]";
	}

	
	
}
