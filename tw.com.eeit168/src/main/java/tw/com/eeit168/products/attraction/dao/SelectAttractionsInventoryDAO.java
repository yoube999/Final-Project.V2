package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import tw.com.eeit168.products.attraction.model.SelectAttractionsInventoryView;

public interface SelectAttractionsInventoryDAO {

	SelectAttractionsInventoryView select(Integer id);
	
	List<SelectAttractionsInventoryView> selectAll();
	
}
