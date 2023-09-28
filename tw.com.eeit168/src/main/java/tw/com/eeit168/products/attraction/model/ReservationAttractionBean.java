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
	private Integer reservation_attractions_id;
	
	@JoinColumn(name = "record_id")
	private Integer record_id;
	
	@JoinColumn(name = "attractions_id")
	private Integer attractions_id;
	
	@Column(name = "reservation_date")
	private java.sql.Date reservation_date;
	
	@Column(name = "total_count")
	private Integer total_count;
	
	@Column(name = "total_price")
	private Integer total_price;
	
	@Column(name = "record_attractions_status", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String record_attractions_status;
	
	
	@Override
	public String toString() {
		return "ReservationAttractionsBean [reservation_attractions_id=" + reservation_attractions_id + ", record_id="
				+ record_id + ", attractions_id=" + attractions_id + ", reservation_date=" + reservation_date
				+ ", total_count=" + total_count + ", total_price=" + total_price + ", record_attractions_status="
				+ record_attractions_status + "]";
	}
	
	
	public Integer getReservation_attractions_id() {
		return reservation_attractions_id;
	}
	public void setReservation_attractions_id(Integer reservation_attractions_id) {
		this.reservation_attractions_id = reservation_attractions_id;
	}
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	public Integer getAttractions_id() {
		return attractions_id;
	}
	public void setAttractions_id(Integer attractions_id) {
		this.attractions_id = attractions_id;
	}
	public java.sql.Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(java.sql.Date reservation_date) {
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
	public String getRecord_attractions_status() {
		return record_attractions_status;
	}
	public void setRecord_attractions_status(String record_attractions_status) {
		this.record_attractions_status = record_attractions_status;
	}
	
}
