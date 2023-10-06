package tw.com.eeit168.products.accommodation.DTO;

public class AccommodationDTO {
	private Integer accommodationId;
	
	private String accommodationName;
	
	private String accommodationAddress;
	
	private Integer timesPurchased;
	
	private Integer weekdayPrice;
	
	private Integer weekendPrice;

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

	public String getAccommodationAddress() {
		return accommodationAddress;
	}

	public void setAccommodationAddress(String accommodationAddress) {
		this.accommodationAddress = accommodationAddress;
	}

	public Integer getTimesPurchased() {
		return timesPurchased;
	}

	public void setTimesPurchased(Integer timesPurchased) {
		this.timesPurchased = timesPurchased;
	}

	public Integer getWeekdayPrice() {
		return weekdayPrice;
	}

	public void setWeekdayPrice(Integer weekdayPrice) {
		this.weekdayPrice = weekdayPrice;
	}

	public Integer getWeekendPrice() {
		return weekendPrice;
	}

	public void setWeekendPrice(Integer weekendPrice) {
		this.weekendPrice = weekendPrice;
	}

	@Override
	public String toString() {
		return "AccommodationDTO [accommodationId=" + accommodationId + ", accommodationName=" + accommodationName
				+ ", accommodationAddress=" + accommodationAddress + ", timesPurchased=" + timesPurchased
				+ ", weekdayPrice=" + weekdayPrice + ", weekendPrice=" + weekendPrice + "]";
	}

	
	
}
