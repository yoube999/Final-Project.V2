package tw.com.eeit168.products.accommodation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "select_accommodation_picture_price")
public class SelectAccommodationPhotosPriceView {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "photo_id")
	private Integer accommodationPhotoId;

	@JoinColumn(name = "accommodation_id", insertable = false, updatable = false)
	private Integer accommodationId;

	@Column(name = "accommodation_name", nullable = false, columnDefinition = "nvarchar(255)")
	private String accommodationName;

	@Column(name = "descriptions", nullable = false, columnDefinition = "nvarchar(255)")
	private String descriptions;
	
	@Column(name = "photo_url", nullable = false)
	private String photoUrl;
	
	@Column(name = "min_weekday_price", nullable = false)
	private Integer minWeekdayPrice;
	
	@Column(name = "times_purchased", nullable = false)
	private int timesPurchased;
	
	@Column(name = "item_type", nullable = false)
	private String itemType;

	public Integer getAccommodationPhotoId() {
		return accommodationPhotoId;
	}

	public void setAccommodationPhotoId(Integer accommodationPhotoId) {
		this.accommodationPhotoId = accommodationPhotoId;
	}

	public Integer getAccommodationId() {
		return accommodationId;
	}

	public void setAccommodationId(Integer accommodationId) {
		this.accommodationId = accommodationId;
	}

	public String getAccommodationName() {
		return accommodationName;
	}

	public void setAccommodationName(String accommodationName) {
		this.accommodationName = accommodationName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Integer getMinWeekdayPrice() {
		return minWeekdayPrice;
	}

	public void setMinWeekdayPrice(Integer minWeekdayPrice) {
		this.minWeekdayPrice = minWeekdayPrice;
	}

	public int getTimesPurchased() {
		return timesPurchased;
	}

	public void setTimesPurchased(int timesPurchased) {
		this.timesPurchased = timesPurchased;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	@Override
	public String toString() {
		return "SelectAccommodationPhotosPriceView [accommodationPhotoId=" + accommodationPhotoId + ", accommodationId="
				+ accommodationId + ", accommodationName=" + accommodationName + ", descriptions=" + descriptions
				+ ", photoUrl=" + photoUrl + ", minWeekdayPrice=" + minWeekdayPrice + ", timesPurchased="
				+ timesPurchased + ", itemType=" + itemType + "]";
	}

		
}
