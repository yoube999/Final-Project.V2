package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "select_attractions_ticket")
public class SelectAttractionsTicketView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "attractions_ticket_id")
	private Integer attractionsTicketId;
	
	@Column(name = "attractions_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String attractionsName;
	
	@Column(name = "times_purchased")
	private Integer timesPurchased;
	
	@Column(name = "valid_date")
	private java.sql.Date validDate;
	
	@Column(name = "adult_price")
	private Integer adultPrice;
	
	@Column(name = "child_price")
	private Integer childPrice;

	
	@Override
	public String toString() {
		return "SelectAttractionsTicketView [attractionsTicketId=" + attractionsTicketId + ", attractionsName="
				+ attractionsName + ", timesPurchased=" + timesPurchased + ", validDate=" + validDate + ", adultPrice="
				+ adultPrice + ", childPrice=" + childPrice + "]";
	}

	
	public Integer getAttractionsTicketId() {
		return attractionsTicketId;
	}
	public void setAttractionsTicketId(Integer attractionsTicketId) {
		this.attractionsTicketId = attractionsTicketId;
	}
	public String getAttractionsName() {
		return attractionsName;
	}
	public void setAttractionsName(String attractionsName) {
		this.attractionsName = attractionsName;
	}
	public Integer getTimesPurchased() {
		return timesPurchased;
	}
	public void setTimesPurchased(Integer timesPurchased) {
		this.timesPurchased = timesPurchased;
	}
	public java.sql.Date getValidDate() {
		return validDate;
	}
	public void setValidDate(java.sql.Date validDate) {
		this.validDate = validDate;
	}
	public Integer getAdultPrice() {
		return adultPrice;
	}
	public void setAdultPrice(Integer adultPrice) {
		this.adultPrice = adultPrice;
	}
	public Integer getChildPrice() {
		return childPrice;
	}
	public void setChildPrice(Integer childPrice) {
		this.childPrice = childPrice;
	}
	
}
