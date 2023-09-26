package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import tw.com.eeit168.products.accommodation.model.AccommodationPrice;

public interface AccommodationPriceDAO {
	public abstract AccommodationPrice select(Integer id);
	
	public abstract List<AccommodationPrice> selectAll();
	
	public abstract AccommodationPrice insert(AccommodationPrice bean);
	
	public abstract AccommodationPrice update(AccommodationPrice bean);
	
	public abstract boolean delete(Integer id);
}
