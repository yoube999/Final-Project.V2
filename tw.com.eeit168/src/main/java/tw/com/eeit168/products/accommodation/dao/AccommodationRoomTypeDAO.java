package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;

public interface AccommodationRoomTypeDAO {
	
	public abstract List<AccommodationRoomType> searchByKeyword (String keyword);
	
	public abstract List<AccommodationRoomType> selectAll();
	
	public abstract AccommodationRoomType insert(AccommodationRoomType bean);
	
	public abstract AccommodationRoomType update(AccommodationRoomType bean);
	
	public abstract boolean delete(Integer id);
}
