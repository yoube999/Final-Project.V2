package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import tw.com.eeit168.products.restaurant.model.RestaurantInventoryBean;

public interface RestaurantInventoryDAO {
	
	RestaurantInventoryBean select(Integer id);
	
	List<RestaurantInventoryBean> selectAll();
	
	RestaurantInventoryBean insert(RestaurantInventoryBean bean);
	
	RestaurantInventoryBean update(RestaurantInventoryBean bean);
	
	boolean delete(Integer id);
	
}
