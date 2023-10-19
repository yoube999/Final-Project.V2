package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureView;

public interface SelectRestaurantPictureViewDAO {
	
	public List<SelectRestaurantPictureView> selectAllRestaurantPicture(int start, int row);
	
	public long count();
}
