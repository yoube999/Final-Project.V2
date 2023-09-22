package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;

public interface RestaurantDAO {

	RestaurantBean select(Integer id);
	
//	List<RestaurantBean> selectAll();
//	
//	boolean insert(RestaurantBean bean);
//	
//	RestaurantBean update(RestaurantBean bean);
//	
//	boolean delete(Integer id);
	
}
