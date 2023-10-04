package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "reservation_attractions")
public class ReservationAttractionBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "reservation_attractions_id")
	private Integer reservationAttractionsId;
	
	@JoinColumn(name = "record_id")
	private Integer recordId;
	
	@JoinColumn(name = "attractions_id")
	private Integer attractionsId;
	
	@Column(name = "reservation_date")
	private java.sql.Date reservationDate;
	
	@Column(name = "total_count")
	private Integer totalCount;
	
	@Column(name = "total_price")
	private Integer totalPrice;
	
	@Column(name = "record_attractions_status", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String recordAttractionsStatus;

	
	@Override
	public String toString() {
		return "ReservationAttractionBean [reservationAttractionsId=" + reservationAttractionsId + ", recordId="
				+ recordId + ", attractionsId=" + attractionsId + ", reservationDate=" + reservationDate
				+ ", totalCount=" + totalCount + ", totalPrice=" + totalPrice + ", recordAttractionsStatus="
				+ recordAttractionsStatus + "]";
	}


	public Integer getReservationAttractionsId() {
		return reservationAttractionsId;
	}
	public void setReservationAttractionsId(Integer reservationAttractionsId) {
		this.reservationAttractionsId = reservationAttractionsId;
	}
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getAttractionsId() {
		return attractionsId;
	}
	public void setAttractionsId(Integer attractionsId) {
		this.attractionsId = attractionsId;
	}
	public java.sql.Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(java.sql.Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getRecordAttractionsStatus() {
		return recordAttractionsStatus;
	}
	public void setRecordAttractionsStatus(String recordAttractionsStatus) {
		this.recordAttractionsStatus = recordAttractionsStatus;
	}
	
}
