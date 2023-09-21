package tw.com.eeit168.products.attraction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "attractions")
public class AttractionBean {
	
	@Id
	@Column(name = "attractions_id")
	private Integer attractions_id;
	
	@Column(name = "attractions_name")
	private String attractions_name;
	
	@Column(name = "attractions_address")
	private String attractions_address;
	
	@Column(name = "descriptions")
	private String descriptions;
	
	@Column(name = "open_time")
	private java.util.Date open_time;
	
	@Column(name = "close_time")
	private java.util.Date close_time;
	
	@Column(name = "contact_number")
	private String contact_number;
	
	@Column(name = "rating")
	private String rating;
	
	@Column(name = "times_purchased")
	private Integer times_purchased;
	
	
	@Override
	public String toString() {
		return "AttractionBean [attractions_id=" + attractions_id + ", attractions_name=" + attractions_name
				+ ", attractions_address=" + attractions_address + ", descriptions=" + descriptions + ", open_time="
				+ open_time + ", close_time=" + close_time + ", contact_number=" + contact_number + ", rating=" + rating
				+ ", times_purchased=" + times_purchased + "]";
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
	public java.util.Date getOpen_time() {
		return open_time;
	}
	public void setOpen_time(java.util.Date open_time) {
		this.open_time = open_time;
	}
	public java.util.Date getClose_time() {
		return close_time;
	}
	public void setClose_time(java.util.Date close_time) {
		this.close_time = close_time;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Integer getTimes_purchased() {
		return times_purchased;
	}
	public void setTimes_purchased(Integer times_purchased) {
		this.times_purchased = times_purchased;
	}

}
