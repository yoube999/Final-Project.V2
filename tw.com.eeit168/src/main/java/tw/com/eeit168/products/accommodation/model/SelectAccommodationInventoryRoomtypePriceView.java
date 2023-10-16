package tw.com.eeit168.products.accommodation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity//是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "select_accommodation_inventory_roomtype_price")
public class SelectAccommodationInventoryRoomtypePriceView {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//這個用來標記此欄位是自動產生
	@Column(name = "inventory_id")
	private Integer accommodationInventoryId;
	
//	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id", insertable = false, updatable = false)
    private Integer accommodationId;
	
	@Column(name = "accommodation_name", nullable = false, columnDefinition = "nvarchar(255)")
	private String accommodationName;
	
	@Column(name = "room_type_name", nullable = false, columnDefinition = "nvarchar")
	private String roomTypeName;
	
	@Column(name = "availability_date")
	private java.sql.Date availabilityDate;
	
	@Column(name = "available_rooms")
	private Integer availableRooms;
	
	@Column(name = "beds_amount", nullable = false)
	private Integer bedsAmount;
	
	@Column(name = "weekday_price", nullable = false)
	private Integer weekdayPrice;
	
	@Column(name = "weekend_price", nullable = false)
	private Integer weekendPrice;

	@Column(name = "times_purchased", nullable = false)
	private int timesPurchased;

	public Integer getAccommodationInventoryId() {
		return accommodationInventoryId;
	}

	public void setAccommodationInventoryId(Integer accommodationInventoryId) {
		this.accommodationInventoryId = accommodationInventoryId;
	}

	public Integer getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Integer accommodationId) {
		this.accommodationId = accommodationId;
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

	public Integer getBedsAmount() {
		return bedsAmount;
	}

	public void setBedsAmount(Integer bedsAmount) {
		this.bedsAmount = bedsAmount;
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

	public int getTimesPurchased() {
		return timesPurchased;
	}

	public void setTimesPurchased(int timesPurchased) {
		this.timesPurchased = timesPurchased;
	}

	@Override
	public String toString() {
		return "SelectAccommodationInventoryRoomtypePriceView [accommodationInventoryId=" + accommodationInventoryId
				+ ", accommodationId=" + accommodationId + ", accommodationName=" + accommodationName
				+ ", roomTypeName=" + roomTypeName + ", availabilityDate=" + availabilityDate + ", availableRooms="
				+ availableRooms + ", bedsAmount=" + bedsAmount + ", weekdayPrice=" + weekdayPrice + ", weekendPrice="
				+ weekendPrice + ", timesPurchased=" + timesPurchased + "]";
	}

	
	
}
