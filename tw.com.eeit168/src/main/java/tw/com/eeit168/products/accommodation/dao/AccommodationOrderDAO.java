package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import tw.com.eeit168.products.accommodation.model.AccommodationOrder;

public interface AccommodationOrderDAO {
	public abstract AccommodationOrder select(Integer id);
	
	public abstract List<AccommodationOrder> selectAll();
	
	public abstract AccommodationOrder insert(AccommodationOrder bean);
	
	public abstract AccommodationOrder update(AccommodationOrder bean);
	
	public abstract boolean delete(Integer id);
}
