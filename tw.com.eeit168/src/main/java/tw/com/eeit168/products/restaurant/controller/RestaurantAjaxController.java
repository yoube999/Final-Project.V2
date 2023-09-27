package tw.com.eeit168.products.restaurant.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.service.RestaurantRepositoryService;

@RestController
@RequestMapping(path = {"/products"})
public class RestaurantAjaxController {
	
	@Autowired
	private RestaurantRepositoryService restaurantRepositoryService;
	
	@GetMapping(path = {"/restaurants/{restaurant_id}"})
	public String findById(@PathVariable(name = "restaurant_id") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		RestaurantBean result = restaurantRepositoryService.findById(id);
		if(result != null) {
			JSONObject item = new JSONObject()
					.put("restaurant_id", result.getRestaurant_id())
					.put("restaurant_name", result.getRestaurant_name())
					.put("restaurant_address", result.getRestaurant_address())
					.put("descriptions", result.getDescriptions())
					.put("contact_number", result.getContact_number())
					.put("price", result.getPrice())
					.put("times_purchased", result.getTimes_purchased());
			array = array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/restaurants"})
	public String findAllRestaurant() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<RestaurantBean> result = restaurantRepositoryService.findAll();
		if(result != null && !result.isEmpty()) {
			for(RestaurantBean restaurant : result) {
				JSONObject item = new JSONObject()
						.put("restaurant_id", restaurant.getRestaurant_id())
						.put("restaurant_name", restaurant.getRestaurant_name())
						.put("restaurant_address", restaurant.getRestaurant_address())
						.put("descriptions", restaurant.getDescriptions())
						.put("contact_number", restaurant.getContact_number())
						.put("price", restaurant.getPrice())
						.put("times_purchased", restaurant.getTimes_purchased());
				array = array.put(item);			
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
//	@GetMapping(path = {"/restaurants"}) //這是restful
//	public ResponseEntity<?> findAll() {
//		List<RestaurantBean> result = restaurantRepositoryService.findAll();
//		ResponseEntity<List<RestaurantBean>> response = ResponseEntity
//				.ok()
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(result);
//		return response;
//	}

}
