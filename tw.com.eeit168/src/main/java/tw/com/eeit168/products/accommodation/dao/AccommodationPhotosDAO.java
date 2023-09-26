package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import tw.com.eeit168.products.accommodation.model.AccommodationPhotos;

public interface AccommodationPhotosDAO {
	public abstract AccommodationPhotos select(Integer id);
	
	public abstract List<AccommodationPhotos> selectAll();
	
	public abstract AccommodationPhotos insert(AccommodationPhotos bean);
	
	public abstract AccommodationPhotos update(AccommodationPhotos bean);
	
	public abstract boolean delete(Integer id);
}
