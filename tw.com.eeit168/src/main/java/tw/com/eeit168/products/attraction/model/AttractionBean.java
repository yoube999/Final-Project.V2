package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "attractions")
public class AttractionBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "attractions_id")
	private Integer attractionsId;
	
	@Column(name = "attractions_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String attractionsName;
	
	@Column(name = "attractions_address", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String attractionsAddress;
	
	@Column(name = "descriptions", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String descriptions;
	
	@Column(name = "open_time")
	private String openTime;
	
	@Column(name = "close_time")
	private String closeTime;
	
	@Column(name = "contact_number")
	private String contactNumber;
	
	@Column(name = "times_purchased")
	private Integer timesPurchased;

	
	@Override
	public String toString() {
		return "AttractionBean [attractionsId=" + attractionsId + ", attractionsName=" + attractionsName
				+ ", attractionsAddress=" + attractionsAddress + ", descriptions=" + descriptions + ", openTime="
				+ openTime + ", closeTime=" + closeTime + ", contactNumber=" + contactNumber + ", timesPurchased="
				+ timesPurchased + "]";
	}


	public Integer getAttractionsId() {
		return attractionsId;
	}
	public void setAttractionsId(Integer attractionsId) {
		this.attractionsId = attractionsId;
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
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Integer getTimesPurchased() {
		return timesPurchased;
	}
	public void setTimesPurchased(Integer timesPurchased) {
		this.timesPurchased = timesPurchased;
	}
	
}
