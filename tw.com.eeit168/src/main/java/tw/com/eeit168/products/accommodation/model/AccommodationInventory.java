package tw.com.eeit168.products.accommodation.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "accommodation_inventory")
public class AccommodationInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//使用底层数据库的自增字段来生成主键。适用于大多数数据库，如 MySQL、PostgreSQL 等。每个插入操作后，数据库会为新记录分配一个唯一的自增 ID。
	private int inventoryId;
	
	@ManyToOne
	@JoinColumn(name = "accommodation_id", nullable = false)
	private Accommodation accommodation;
	
	@ManyToOne
	@JoinColumn(name = "room_type_id", nullable = false)
	private AccommodationRoomType roomType;
	
	
	public AccommodationInventory() {
		
	}
	
	
	public AccommodationInventory(int inventoryId, Accommodation accommodation, AccommodationRoomType roomType,
			Date availabilityDate, int availableRooms) {
		super();
		this.inventoryId = inventoryId;
		this.accommodation = accommodation;
		this.roomType = roomType;
		this.availabilityDate = availabilityDate;
		this.availableRooms = availableRooms;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Accommodation getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	public AccommodationRoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(AccommodationRoomType roomType) {
		this.roomType = roomType;
	}

	public Date getAvailabilityDate() {
		return availabilityDate;
	}

	public void setAvailabilityDate(Date availabilityDate) {
		this.availabilityDate = availabilityDate;
	}

	public int getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}

	@Column(name = "availability_date")
	private Date availabilityDate;
	
	@Column(name = "available_rooms")
	private int availableRooms;
}



//inventory_id int identity(1,1) primary key not null,
//accommodation_id int references accommodation(accommodation_id) not null,
//room_type_id int references accommodation_room_type(room_type_id) not null,
//availability_date datetime not null,
//available_rooms int not null default 0