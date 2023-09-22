package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import tw.com.eeit168.products.accommodation.model.Accommodation;

public interface AccommodationDAO {
//	Accommodation searchByKeyword(String keyword);
	List<Accommodation> searchByKeyword(String keyword);	// 修正回傳型態為 List<Accommodation>
}
