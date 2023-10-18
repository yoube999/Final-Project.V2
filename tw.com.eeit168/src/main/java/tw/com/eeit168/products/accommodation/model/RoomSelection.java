package tw.com.eeit168.products.accommodation.model;

public class RoomSelection {
	private String roomTypeName;
	
    private Integer totalRooms;

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public Integer getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(Integer totalRooms) {
		this.totalRooms = totalRooms;
	}

	@Override
	public String toString() {
		return "RoomSelection [roomTypeName=" + roomTypeName + ", totalRooms=" + totalRooms + "]";
	}
    
    
}
