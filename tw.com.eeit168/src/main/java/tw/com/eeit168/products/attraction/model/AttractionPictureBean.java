package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity //是JPA(Java Persistence API)中的一個重要註解，用於定義資料庫實體類別，讓開發者可以使用Java程式碼來操作資料庫中的資料
@Table(name = "attractions_pictures")
public class AttractionPictureBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //這個用來標記此欄位是自動產生
	@Column(name = "attractions_pictures_id")
	private Integer attractionsPicturesId;
	
	@JoinColumn(name = "attractions_id")
	private Integer attractionsId;
	
	@Column(name = "url_image")
	private String urlImage;

	
	@Override
	public String toString() {
		return "AttractionPictureBean [attractionsPicturesId=" + attractionsPicturesId + ", attractionsId="
				+ attractionsId + ", urlImage=" + urlImage + "]";
	}

	
	public Integer getAttractionsPicturesId() {
		return attractionsPicturesId;
	}
	public void setAttractionsPicturesId(Integer attractionsPicturesId) {
		this.attractionsPicturesId = attractionsPicturesId;
	}
	public Integer getAttractionsId() {
		return attractionsId;
	}
	public void setAttractionsId(Integer attractionsId) {
		this.attractionsId = attractionsId;
	}
	public String getUrlImage() {
		return urlImage;
	}
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
}