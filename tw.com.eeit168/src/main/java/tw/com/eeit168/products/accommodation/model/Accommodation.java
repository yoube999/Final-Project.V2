package tw.com.eeit168.products.accommodation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accommodation")
public class Accommodation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//使用底层数据库的自增字段来生成主键。适用于大多数数据库，如 MySQL、PostgreSQL 等。每个插入操作后，数据库会为新记录分配一个唯一的自增 ID。
	
	@Column(name = "accommodation_id")
	private int accommodationId;
	
	@Column(name = "accommodation_name", nullable = false)
	private String accommodationName;
	
	@Column(name = "accommodation_address", nullable = false)
	private String accommodationAddress;
	
	@Column(name = "contact_number", nullable = false)
	private String contactNumber;
	
	@Column(name = "times_purchased", nullable = false)
	private int timesPurchased;

	public int getAccommodationid() {
		return accommodationId;
	}

	//Constructors
	public Accommodation() {
		
	}
	
	public Accommodation(int accommodationid, String accommodationName, String accommodationAddress,
			String contactNumber, int timesPurchased) {
		super();
		this.accommodationId = accommodationid;
		this.accommodationName = accommodationName;
		this.accommodationAddress = accommodationAddress;
		this.contactNumber = contactNumber;
		this.timesPurchased = timesPurchased;
	}

	public void setAccommodationid(int accommodationid) {
		this.accommodationId = accommodationid;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getTimesPurchased() {
		return timesPurchased;
	}

	public void setTimesPurchased(int timesPurchased) {
		this.timesPurchased = timesPurchased;
	}
}
