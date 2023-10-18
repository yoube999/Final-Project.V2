package tw.com.eeit168.products.accommodation.model;

public class RoomTotalPrice {
	private String roomTypeName;
	
    private Integer totalPrice;
    
    private Integer totalRooms;
    
//    public RoomTotalPrice(String roomTypeName, Integer totalPrice) {
//        this.roomTypeName = roomTypeName;
//        
//        this.totalPrice = totalPrice;
//    }

	public String getRoomTypeName() {
		return roomTypeName;
	}

	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalRooms() {
		return totalRooms;
	}

	public void setTotalRooms(Integer totalRooms) {
		this.totalRooms = totalRooms;
	}

	@Override
	public String toString() {
		return "RoomTotalPrice [roomTypeName=" + roomTypeName + ", totalPrice=" + totalPrice + ", totalRooms="
				+ totalRooms + "]";
	}

	
    
    

}
