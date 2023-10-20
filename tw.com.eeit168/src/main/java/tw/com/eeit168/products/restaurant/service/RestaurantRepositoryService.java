package tw.com.eeit168.products.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureView;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureViewTop5;
import tw.com.eeit168.products.restaurant.repository.RestaurantRepository;
import tw.com.eeit168.products.restaurant.repository.SelectRestaurantPictureViewTop5Repository;

@Service //註解類別處理運算邏輯(企業邏輯)
public class RestaurantRepositoryService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private SelectRestaurantPictureViewTop5Repository selectRestaurantPictureViewTop5Repository;
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	//以id搜尋
	public RestaurantBean findById(Integer id) {
		Optional<RestaurantBean> result = restaurantRepository.findById(id);
		if(result != null && result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	//搜尋全部
	public List<RestaurantBean> findAll() {
		return restaurantRepository.findAll();
	}
	
	//新增
	public RestaurantBean create(String json) {
		try {
			JSONObject object = new JSONObject(json);
			String restaurantName = object.isNull("restaurantName") ? null : object.getString("restaurantName");
			String restaurantAddress = object.isNull("restaurantAddress") ? null : object.getString("restaurantAddress");
			String descriptions = object.isNull("descriptions") ? null : object.getString("descriptions");
			String contactNumber = object.isNull("contactNumber") ? null : object.getString("contactNumber");
			Integer price = object.isNull("price") ? null : object.getInt("price");
			Integer timesPurchased = object.isNull("timesPurchased") ? null : object.getInt("timesPurchased");
			boolean productStatus = object.isNull("productStatus") ? null : object.getBoolean("productStatus");
			RestaurantBean insert = new RestaurantBean();
			insert.setRestaurantName(restaurantName);
			insert.setRestaurantAddress(restaurantAddress);
			insert.setDescriptions(descriptions);
			insert.setContactNumber(contactNumber);
			insert.setPrice(price);
			insert.setTimesPurchased(timesPurchased);
			insert.setProductStatus(productStatus);
			return restaurantRepository.save(insert);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//修改
	public RestaurantBean modify(String json) {
		try {
			JSONObject object = new JSONObject(json);
			Integer restaurantId = object.isNull("restaurantId") ? null : object.getInt("restaurantId");
			String restaurantName = object.isNull("restaurantName") ? null : object.getString("restaurantName");
			String restaurantAddress = object.isNull("restaurantAddress") ? null : object.getString("restaurantAddress");
			String descriptions = object.isNull("descriptions") ? null : object.getString("descriptions");
			String contactNumber = object.isNull("contactNumber") ? null : object.getString("contactNumber");
			Integer price = object.isNull("price") ? null : object.getInt("price");
			Integer timesPurchased = object.isNull("timesPurchased") ? null : object.getInt("timesPurchased");
			boolean productStatus = object.isNull("productStatus") ? null : object.getBoolean("productStatus");
			Optional<RestaurantBean> result = restaurantRepository.findById(restaurantId);
			if(result != null && result.isPresent()) {
				RestaurantBean update = new RestaurantBean();
				update.setRestaurantId(restaurantId);
				update.setRestaurantName(restaurantName);
				update.setRestaurantAddress(restaurantAddress);
				update.setDescriptions(descriptions);
				update.setContactNumber(contactNumber);
				update.setPrice(price);
				update.setTimesPurchased(timesPurchased);
				update.setProductStatus(productStatus);
				return restaurantRepository.save(update);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//刪除
	public boolean remove(Integer id) {
		try {
			if(restaurantRepository.existsById(id)) {
				restaurantRepository.deleteById(id);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//判斷id是否存在
	public boolean exists(Integer id) {
		return restaurantRepository.existsById(id);
	}
	
	//Top5
	public List<RestaurantBean> selectTop5() {
		return restaurantRepository.selectTop5();
	}
	
	//Top5 包含圖片
	public List<SelectRestaurantPictureViewTop5> getTop5RestaurantsByTimesPurchased() {
        return selectRestaurantPictureViewTop5Repository.findTop5ByOrderByTimesPurchasedDesc();
    }
	
	//以被購買次數搜尋(多-少)
	public List<RestaurantBean> findAllByPurchasedDesc() {
		return restaurantRepository.findAllByPurchasedDesc();
	}
	
	//以價格搜尋(高-低)
	public List<RestaurantBean> findAllByPriceDesc() {
		return restaurantRepository.findAllByPriceDesc();
	}

	//以價格搜尋(低-高)
	public List<RestaurantBean> findAllByPriceAsc() {
		return restaurantRepository.findAllByPriceAsc();
	}
	
	//餐廳名稱及地點的模糊搜尋
	public List<SelectRestaurantInventoryView> blurFind(String keyword) {
		return restaurantRepository.blurFind(keyword);
	}

	//餐廳日期的模糊搜尋
	public List<SelectRestaurantInventoryView> blurDateFind(java.sql.Date orderDate) {
		return restaurantRepository.blurDateFind(orderDate);
	}
	
	//用餐廳名稱搜尋圖片
	public List<SelectRestaurantPictureView> findPictureByName(String restaurantName) {
		return restaurantRepository.findPictureByName(restaurantName);
	}
	
} 
