package tw.com.eeit168.products.accommodation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "select_accommodation_picture")
public class SelectAccommodationPicture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photo_id")
	private Integer accommodationPhotoId;
	
	@Column(name = "accommodation_name", nullable = false, columnDefinition = "nvarchar(255)")
	private String accommodationName;
	
	@Column(name ="photo_url", nullable = false)
	private String photoUrl;
}
