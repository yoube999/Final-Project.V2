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
	private Integer attractionsInventoryId;
	
	@Column(name = "attractions_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String attractionsName;
	
	@Column(name = "attractions_address", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String attractionsAddress;
	
	@Column(name = "availability_date")
	private java.sql.Date availabilityDate;
	
	@Column(name = "adult")
	private Integer adult;
	
	@Column(name = "child")
	private Integer child;

	
	@Override
	public String toString() {
		return "SelectAttractionsInventoryView [attractionsInventoryId=" + attractionsInventoryId + ", attractionsName="
				+ attractionsName + ", attractionsAddress=" + attractionsAddress + ", availabilityDate="
				+ availabilityDate + ", adult=" + adult + ", child=" + child + "]";
	}


	public Integer getAttractionsInventoryId() {
		return attractionsInventoryId;
	}
	public void setAttractionsInventoryId(Integer attractionsInventoryId) {
		this.attractionsInventoryId = attractionsInventoryId;
	}
	public String getAttractionsName() {
		return attractionsName;
	}
	public void setAttractionsName(String attractionsName) {
		this.attractionsName = attractionsName;
	}
	public String getAttractionsAddress() {
		return attractionsAddress;
	}
	public void setAttractionsAddress(String attractionsAddress) {
		this.attractionsAddress = attractionsAddress;
	}
	public java.sql.Date getAvailabilityDate() {
		return availabilityDate;
	}
	public void setAvailabilityDate(java.sql.Date availabilityDate) {
		this.availabilityDate = availabilityDate;
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
