package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;

public interface SelectRestaurantInventoryDAO {

	SelectRestaurantInventoryView select(Integer id);
	
	List<SelectRestaurantInventoryView> selectAll();
	
} 
