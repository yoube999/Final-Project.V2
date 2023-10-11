package tw.com.eeit168.products.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureView;
import tw.com.eeit168.products.restaurant.repository.SelectRestaurantPictureRepository;

@Service //註解類別處理運算邏輯(企業邏輯)
public class SelectRestaurantPictureRepositoryService {

	@Autowired
	private SelectRestaurantPictureRepository selectRestaurantPictureRepository;
	
	public List<SelectRestaurantPictureView> findAll() {
		return selectRestaurantPictureRepository.findAll();
	}
	
}
