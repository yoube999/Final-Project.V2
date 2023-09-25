package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import tw.com.eeit168.products.restaurant.model.RestaurantPictureBean;

public interface RestaurantPictureDAO {
	
	RestaurantPictureBean select(Integer id);
	
	List<RestaurantPictureBean> selectAll();
	
	RestaurantPictureBean insert(RestaurantPictureBean bean);
	
	RestaurantPictureBean update(RestaurantPictureBean bean);
	
	boolean delete(Integer id);
		
}
