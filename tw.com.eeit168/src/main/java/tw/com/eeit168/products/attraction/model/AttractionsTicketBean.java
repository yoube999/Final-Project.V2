package tw.com.eeit168.products.attraction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attractions_ticket")
public class AttractionsTicketBean {

	@Id
	@Column(name = "attractions_ticket_id")
	private Integer attractions_ticket_id;
	
	@Column(name = "attractions_id")
	private Integer attractions_id;
	
	@Column(name = "valid_date")
	private java.util.Date valid_date;
	
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
	public java.util.Date getValid_date() {
		return valid_date;
	}
	public void setValid_date(java.util.Date valid_date) {
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
