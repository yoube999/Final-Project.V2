package tw.com.eeit168.products.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureView;
import tw.com.eeit168.products.restaurant.repository.RestaurantRepository;

@Service //註解類別處理運算邏輯(企業邏輯)
@Transactional
public class RestaurantRepositoryService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
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
	
	//Top5
	public List<RestaurantBean> selectTop5() {
		return restaurantRepository.selectTop5();
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
	public List<SelectRestaurantPictureView> findPictureByName(String name) {
		return restaurantRepository.findPictureByName(name);
	}
	
} 
