package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "select_attractions_picture")
public class SelectAttractionsPictureView {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "attractions_pictures_id")
	private Integer attractionsPicturesId;
	
	@Column(name = "attractions_name", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String attractionsName;
	
	@Column(name = "descriptions", columnDefinition = "nvarchar") //如有要用nvarchar必須要有此annotation
	private String descriptions;
	
	@Column(name = "adult_price")
	private Integer adultPrice;
	
	@Column(name = "times_purchased")
	private Integer timesPurchased;

	@Column(name = "url_image")
	private String urlImage;

	
	@Override
	public String toString() {
		return "SelectAttractionsPictureView [attractionsPicturesId=" + attractionsPicturesId + ", attractionsName="
				+ attractionsName + ", descriptions=" + descriptions + ", adultPrice=" + adultPrice
				+ ", timesPurchased=" + timesPurchased + ", urlImage=" + urlImage + "]";
	}

	
	public Integer getAttractionsPicturesId() {
		return attractionsPicturesId;
	}
	public void setAttractionsPicturesId(Integer attractionsPicturesId) {
		this.attractionsPicturesId = attractionsPicturesId;
	}
	public String getAttractionsName() {
		return attractionsName;
	}
	public void setAttractionsName(String attractionsName) {
		this.attractionsName = attractionsName;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public Integer getAdultPrice() {
		return adultPrice;
	}
	public void setAdultPrice(Integer adultPrice) {
		this.adultPrice = adultPrice;
	}
	public Integer getTimesPurchased() {
		return timesPurchased;
	}
	public void setTimesPurchased(Integer timesPurchased) {
		this.timesPurchased = timesPurchased;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
}
