package tw.com.eeit168.products.restaurant.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;
import tw.com.eeit168.products.restaurant.service.RestaurantRepositoryService;

@RestController //@Controller+@ResponseBody
@RequestMapping(path = {"/product"})
public class RestaurantAjaxController {
	
	@Autowired
	private RestaurantRepositoryService restaurantRepositoryService;
		
	@GetMapping(path = {"/restaurant/{restaurant_id}"}) //以id搜尋
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
	
	@GetMapping(path = {"/restaurant"}) //搜尋全部
	public String findAll() {
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

	@GetMapping(path = "/restaurant/top5") //Top5
	public String selectTop5() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<RestaurantBean> result = restaurantRepositoryService.selectTop5();
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
	
	@GetMapping(path = {"/restaurantdesc"}) //以被購買次數搜尋(多-少)
	public String findAllByPurchasedDesc() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<RestaurantBean> result = restaurantRepositoryService.findAllByPurchasedDesc();
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
	
	@GetMapping(path = {"/restaurant/bypricedesc"}) //以價格搜尋(高-低)
	public String findAllByPriceDesc() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<RestaurantBean> result = restaurantRepositoryService.findAllByPriceDesc();
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
	
	@GetMapping(path = {"/restaurant/bypriceasc"}) //以價格搜尋(低-高)
	public String findAllByPriceAsc() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<RestaurantBean> result = restaurantRepositoryService.findAllByPriceAsc();
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
	
	@GetMapping(path = {"/restaurant/search"}) //餐廳名稱及地點的模糊搜尋
	public String blurFind(@RequestParam(value = "keyword", required = false) String keyword) { //require=false表示keyword可不填
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectRestaurantInventoryView> result = restaurantRepositoryService.blurFind(keyword);
		if(result != null && !result.isEmpty()) {
			for(SelectRestaurantInventoryView restaurant : result) {
				JSONObject item = new JSONObject()
					.put("restaurant_Inventory_id", restaurant.getRestaurant_Inventory_id())
					.put("restaurant_name", restaurant.getRestaurant_name())
					.put("restaurant_address", restaurant.getRestaurant_address())
					.put("availability_date", restaurant.getAvailability_date());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = "/restaurant/searchdate") //餐廳日期的模糊搜尋
	public String blurDateFind(
			@RequestParam(value = "checkInDate") String checkInDate, 
			@RequestParam(value = "checkOutDate") String checkOutDate) {
		java.sql.Date hqlCheckIn = java.sql.Date.valueOf(checkInDate); //將一個符合特定格式的日期字串轉換成java.sql.Date
		java.sql.Date hqlCheckOut = java.sql.Date.valueOf(checkOutDate); //將一個符合特定格式的日期字串轉換成java.sql.Date
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectRestaurantInventoryView> result = restaurantRepositoryService.blurDateFind(hqlCheckIn, hqlCheckOut);
		if(result != null && !result.isEmpty()) {
			for(SelectRestaurantInventoryView restaurant : result) {
				JSONObject item = new JSONObject()
					.put("restaurant_Inventory_id", restaurant.getRestaurant_Inventory_id())
					.put("restaurant_name", restaurant.getRestaurant_name())
					.put("restaurant_address", restaurant.getRestaurant_address())
					.put("availability_date", restaurant.getAvailability_date())
					.put("lunch", restaurant.getLunch())
					.put("dinner", restaurant.getDinner());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	
	
	
//	@GetMapping(path = {"/restaurant"}) //這是restful
//	public ResponseEntity<?> findAll() {
//		List<RestaurantBean> result = restaurantRepositoryService.findAll();
//		ResponseEntity<List<RestaurantBean>> response = ResponseEntity
//				.ok()
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(result);
//		return response;
//	}

}
