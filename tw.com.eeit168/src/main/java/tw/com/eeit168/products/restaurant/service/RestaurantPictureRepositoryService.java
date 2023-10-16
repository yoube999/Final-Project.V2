package tw.com.eeit168.products.restaurant.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tw.com.eeit168.products.restaurant.model.RestaurantPictureBean;
import tw.com.eeit168.products.restaurant.repository.RestaurantPictureRepository;
import tw.com.eeit168.products.restaurant.util.ImageConvert;

@Service //註解類別處理運算邏輯(企業邏輯)
public class RestaurantPictureRepositoryService {
	
	@Autowired
	private RestaurantPictureRepository restaurantPictureRepository;
	
	//新增照片
	public RestaurantPictureBean create(String json, MultipartFile file) {
		try {
			JSONObject object = new JSONObject(json);
			Integer restaurantId = object.isNull("restaurantId") ? null : object.getInt("restaurantId");
			if(file != null) {
				RestaurantPictureBean insert = new RestaurantPictureBean();
				String base64 = ImageConvert.convertToBase64(file);
				insert.setRestaurantId(restaurantId);
				insert.setUrlImage(base64);
				return restaurantPictureRepository.save(insert);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	//判斷id是否存在
	public boolean exists(Integer id) {
		return restaurantPictureRepository.existsById(id);
	}
	
}
