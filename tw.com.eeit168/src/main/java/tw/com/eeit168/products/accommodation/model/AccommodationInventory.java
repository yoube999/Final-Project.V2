package tw.com.eeit168.products.accommodation.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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