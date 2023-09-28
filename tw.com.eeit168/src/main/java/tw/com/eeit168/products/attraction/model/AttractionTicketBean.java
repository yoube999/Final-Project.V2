package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "attractions_ticket")
public class AttractionTicketBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "attractions_ticket_id")
	private Integer attractions_ticket_id;
	
	@JoinColumn(name = "attractions_id")
	private Integer attractions_id;
	
	@Column(name = "valid_date")
	private java.sql.Date valid_date;
	
	@Column(name = "adult_price")
	private Integer adult_price;
	
	@Column(name = "child_price")
	private Integer child_price;
	
	
	@Override
	public String toString() {
		return "AttractionsTicketBean [attractions_ticket_id=" + attractions_ticket_id + ", attractions_id="
				+ attractions_id + ", valid_date=" + valid_date + ", adult_price=" + adult_price + ", child_price="
				+ child_price + "]";
	}
	
	
	public Integer getAttractions_ticket_id() {
		return attractions_ticket_id;
	}
	public void setAttractions_ticket_id(Integer attractions_ticket_id) {
		this.attractions_ticket_id = attractions_ticket_id;
	}
	public Integer getAttractions_id() {
		return attractions_id;
	}
	public void setAttractions_id(Integer attractions_id) {
		this.attractions_id = attractions_id;
	}
	public java.sql.Date getValid_date() {
		return valid_date;
	}
	public void setValid_date(java.sql.Date valid_date) {
		this.valid_date = valid_date;
	}
	public Integer getAdult_price() {
		return adult_price;
	}
	public void setAdult_price(Integer adult_price) {
		this.adult_price = adult_price;
	}
	public Integer getChild_price() {
		return child_price;
	}
	public void setChild_price(Integer child_price) {
		this.child_price = child_price;
	}
	
}
