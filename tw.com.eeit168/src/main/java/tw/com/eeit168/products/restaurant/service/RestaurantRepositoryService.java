package tw.com.eeit168.products.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.repository.RestaurantRepository;

@Service //註解類別處理運算邏輯(企業邏輯)
@Transactional
public class RestaurantRepositoryService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public RestaurantBean findById(Integer id) {
		Optional<RestaurantBean> optional = restaurantRepository.findById(id);
		if(optional != null && optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public List<RestaurantBean> findAll() {
		return restaurantRepository.findAll();
	}
	
} 
