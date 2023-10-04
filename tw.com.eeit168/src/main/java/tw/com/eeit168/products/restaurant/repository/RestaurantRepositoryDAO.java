package tw.com.eeit168.products.restaurant.repository;

import java.util.List;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureView;

public interface RestaurantRepositoryDAO {
	
	public List<RestaurantBean> selectTop5();
	
	public List<RestaurantBean> findAllByPurchasedDesc();
	
	public List<RestaurantBean> findAllByPriceDesc();
	
	public List<RestaurantBean> findAllByPriceAsc();
	
	public List<SelectRestaurantInventoryView> blurFind(String keyword);
	
	public List<SelectRestaurantInventoryView> blurDateFind(java.sql.Date orderDate);
	
	public List<SelectRestaurantPictureView> findPictureByName(String name);

}
