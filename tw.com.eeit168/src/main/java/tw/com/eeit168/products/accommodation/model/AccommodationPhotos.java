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
	private int photoId;
	
	@ManyToOne
	@JoinColumn(name = "accommodation_id", nullable = false)
	private Accommodation accommodation;
	
	@Column(name = "photo_url", nullable = false)
	private String photoUrl;

	
	public AccommodationPhotos() {
		
	}
	
	
	public AccommodationPhotos(int photoId, Accommodation accommodation, String photoUrl) {
		super();
		this.photoId = photoId;
		this.accommodation = accommodation;
		this.photoUrl = photoUrl;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
}
