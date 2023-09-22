//package tw.com.eeit168.products.accommodation.model;
//
//import java.util.Date;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name ="accommodation_order")
//public class AccommodationOrder {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	
//	@Column(name = "order_id")
//	private int orderId;
//	
//	@ManyToOne
//	@JoinColumn(name = "record_id", nullable = false)
//	private Record recordId;
//	
//	@ManyToOne
//	@JoinColumn(name = "accommodation_id", nullable = false)
//	private Accommodation accommodation;
//	
//	@Column(name= "room_type_name", nullable = false, columnDefinition = "nvarchar")
//	private String roomTypeName;
//	
//	
//	@Column(name = "checked_in_date", nullable = false)
//	private Date checkedInDate;
//	
//	@Column(name = "checked_out_date", nullable = false)
//	private Date checkedOutDate;
//	
//	@Column(name = "total_price", nullable = false)
//	private int totalPrice;
//	
//	@Column(name = "record_accommodation_status", nullable = false, columnDefinition = "nvarchar")
//	private String recordAccommodationStatus;
//
//	public AccommodationOrder() {
//		
//	}
//	
//	public AccommodationOrder(int orderId, Record recordid, Accommodation accommodation, String roomTypeName,
//			Date checkedInDate, Date checkedOutDate, int totalPrice, String recordAccommodationStatus) {
//		super();
//		this.orderId = orderId;
//		this.recordId = recordid;
//		this.accommodation = accommodation;
//		this.roomTypeName = roomTypeName;
//		this.checkedInDate = checkedInDate;
//		this.checkedOutDate = checkedOutDate;
//		this.totalPrice = totalPrice;
//		this.recordAccommodationStatus = recordAccommodationStatus;
//	}
//
//	public int getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(int orderId) {
//		this.orderId = orderId;
//	}
//
//	public Record getRecordid() {
//		return recordId;
//	}
//
//	public void setRecordid(Record recordid) {
//		this.recordId = recordid;
//	}
//
//	public Accommodation getAccommodation() {
//		return accommodation;
//	}
//
//	public void setAccommodation(Accommodation accommodation) {
//		this.accommodation = accommodation;
//	}
//
//	public String getRoomTypeName() {
//		return roomTypeName;
//	}
//
//	public void setRoomTypeName(String roomTypeName) {
//		this.roomTypeName = roomTypeName;
//	}
//
//	public Date getCheckedInDate() {
//		return checkedInDate;
//	}
//
//	public void setCheckedInDate(Date checkedInDate) {
//		this.checkedInDate = checkedInDate;
//	}
//
//	public Date getCheckedOutDate() {
//		return checkedOutDate;
//	}
//
//	public void setCheckedOutDate(Date checkedOutDate) {
//		this.checkedOutDate = checkedOutDate;
//	}
//
//	public int getTotalPrice() {
//		return totalPrice;
//	}
//
//	public void setTotalPrice(int totalPrice) {
//		this.totalPrice = totalPrice;
//	}
//
//	public String getRecordAccommodationStatus() {
//		return recordAccommodationStatus;
//	}
//
//	public void setRecordAccommodationStatus(String recordAccommodationStatus) {
//		this.recordAccommodationStatus = recordAccommodationStatus;
//	}
//	
//	
//	
//}
