package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "attractions_inventory")
public class AttractionInventoryBean {

	@Id
	@Column(name = "attractions_inventory_id")
	private Integer attractions_inventory_id;
	
	@JoinColumn(name = "attractions_id")
	private Integer attractions_id;
	
	@Column(name = "availability_date")
	private java.util.Date availability_date;
	
	@Column(name = "adult")
	private Integer adult;
	
	@Column(name = "child")
	private Integer child;
	
	
	@Override
	public String toString() {
		return "AttractionsInventoryBean [attractions_inventory_id=" + attractions_inventory_id + ", attractions_id="
				+ attractions_id + ", availability_date=" + availability_date + ", adult=" + adult + ", child=" + child
				+ "]";
	}
	
	
	public Integer getAttractions_inventory_id() {
		return attractions_inventory_id;
	}
	public void setAttractions_inventory_id(Integer attractions_inventory_id) {
		this.attractions_inventory_id = attractions_inventory_id;
	}
	public Integer getAttractions_id() {
		return attractions_id;
	}
	public void setAttractions_id(Integer attractions_id) {
		this.attractions_id = attractions_id;
	}
	public java.util.Date getAvailability_date() {
		return availability_date;
	}
	public void setAvailability_date(java.util.Date availability_date) {
		this.availability_date = availability_date;
	}
	public Integer getAdult() {
		return adult;
	}
	public void setAdult(Integer adult) {
		this.adult = adult;
	}
	public Integer getChild() {
		return child;
	}
	public void setChild(Integer child) {
		this.child = child;
	}
		
}
