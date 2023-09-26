package tw.com.eeit168.products.accommodation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accommodation_photos")
public class AccommodationPhotos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer photoId;
	
//	@ManyToOne
	@JoinColumn(name = "accommodation_id", nullable = false)
	private Integer accommodationId;
	
	@Column(name = "photo_url", nullable = false)
	private String photoUrl;

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public Integer getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Integer accommodationId) {
		this.accommodationId = accommodationId;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	@Override
	public String toString() {
		return "AccommodationPhotos [photoId=" + photoId + ", accommodationId=" + accommodationId + ", photoUrl="
				+ photoUrl + "]";
	}

	
	
	


	
}
