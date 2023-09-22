package tw.com.eeit168.products.attraction.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "attractions_pictures")
public class AttractionsPicturesBean {

	@Id
	@Column(name = "attractions_pictures_id")
	private Integer attractions_pictures_id;
	
	@JoinColumn(name = "attractions_id")
	private Integer attractions_id;
	
	@Column(name = "url_image")
	private String url_image;
	
	
	@Override
	public String toString() {
		return "AttractionsPicturesBean [attractions_pictures_id=" + attractions_pictures_id + ", attractions_id="
				+ attractions_id + ", url_image=" + url_image + "]";
	}
	
	
	public Integer getAttractions_pictures_id() {
		return attractions_pictures_id;
	}
	public void setAttractions_pictures_id(Integer attractions_pictures_id) {
		this.attractions_pictures_id = attractions_pictures_id;
	}
	public Integer getAttractions_id() {
		return attractions_id;
	}
	public void setAttractions_id(Integer attractions_id) {
		this.attractions_id = attractions_id;
	}
	public String getUrl_image() {
		return url_image;
	}
	public void setUrl_image(String url_image) {
		this.url_image = url_image;
	}
	
}
