package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "select_attractions_inventory")
public class SelectAttractionsInventoryView {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "attractions_inventory_id")
	private Integer attractions_inventory_id;
	
	@Column(name = "attractions_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String attractions_name;
	
	@Column(name = "attractions_address", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String attractions_address;
	
	@Column(name = "availability_date")
	private java.sql.Date availability_date;
	
	@Column(name = "adult")
	private Integer adult;
	
	@Column(name = "child")
	private Integer child;

	
	@Override
	public String toString() {
		return "SelectAttractionsInventoryView [attractions_inventory_id=" + attractions_inventory_id
				+ ", attractions_name=" + attractions_name + ", attractions_address=" + attractions_address
				+ ", availability_date=" + availability_date + ", adult=" + adult + ", child=" + child + "]";
	}


	public Integer getAttractions_inventory_id() {
		return attractions_inventory_id;
	}
	public void setAttractions_inventory_id(Integer attractions_inventory_id) {
		this.attractions_inventory_id = attractions_inventory_id;
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
	public java.sql.Date getAvailability_date() {
		return availability_date;
	}
	public void setAvailability_date(java.sql.Date availability_date) {
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
