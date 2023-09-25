package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import tw.com.eeit168.products.accommodation.model.Accommodation;

public interface AccommodationDAO {
//	Accommodation searchByKeyword(String keyword);
	public abstract List<Accommodation> searchByKeyword(String keyword);	// 修正回傳型態為 List<Accommodation>
	
	public abstract List<Accommodation> selectAll();
	
	public abstract Accommodation insert(Accommodation bean);
	
	public abstract Accommodation update(Accommodation bean);
	
	public abstract boolean delete(Integer id);
	
}
