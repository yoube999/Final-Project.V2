package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attractions")
public class AttractionBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attractions_id")
	private Integer attractions_id;
	
	@Column(name = "attractions_name", columnDefinition = "nvarchar")
	private String attractions_name;
	
	@Column(name = "attractions_address", columnDefinition = "nvarchar")
	private String attractions_address;
	
	@Column(name = "descriptions", columnDefinition = "nvarchar")
	private String descriptions;
	
	@Column(name = "open_time")
	private String open_time;
	
	@Column(name = "close_time")
	private String close_time;
	
	@Column(name = "contact_number")
	private String contact_number;
	
	@Column(name = "times_purchased")
	private Integer times_purchased;

	
	@Override
	public String toString() {
		return "AttractionBean [attractions_id=" + attractions_id + ", attractions_name=" + attractions_name
				+ ", attractions_address=" + attractions_address + ", descriptions=" + descriptions + ", open_time="
				+ open_time + ", close_time=" + close_time + ", contact_number=" + contact_number + ", times_purchased="
				+ times_purchased + "]";
	}


	public Integer getAttractions_id() {
		return attractions_id;
	}
	public void setAttractions_id(Integer attractions_id) {
		this.attractions_id = attractions_id;
	}
	public String getAttractions_name() {
		return attractions_name;
	}
	public void setAttractions_name(String attractions_name) {
		this.attractions_name = attractions_name;
	}
	public String getAttractions_address() {
		return attractions_address;
	}
	public void setAttractions_address(String attractions_address) {
		this.attractions_address = attractions_address;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getOpen_time() {
		return open_time;
	}
	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}
	public String getClose_time() {
		return close_time;
	}
	public void setClose_time(String close_time) {
		this.close_time = close_time;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public Integer getTimes_purchased() {
		return times_purchased;
	}
	public void setTimes_purchased(Integer times_purchased) {
		this.times_purchased = times_purchased;
	}
	
}
