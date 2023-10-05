package tw.com.eeit168.products.restaurant.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureView;
import tw.com.eeit168.products.restaurant.service.RestaurantRepositoryService;

@RestController //@Controller+@ResponseBody
@CrossOrigin
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
					.put("restaurant_id", result.getRestaurantId())
					.put("restaurant_name", result.getRestaurantName())
					.put("restaurant_address", result.getRestaurantAddress())
					.put("descriptions", result.getDescriptions())
					.put("contact_number", result.getContactNumber())
					.put("price", result.getPrice())
					.put("times_purchased", result.getTimesPurchased());
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
						.put("restaurant_id", restaurant.getRestaurantId())
						.put("restaurant_name", restaurant.getRestaurantName())
						.put("restaurant_address", restaurant.getRestaurantAddress())
						.put("descriptions", restaurant.getDescriptions())
						.put("contact_number", restaurant.getContactNumber())
						.put("price", restaurant.getPrice())
						.put("times_purchased", restaurant.getTimesPurchased());
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
						.put("restaurant_id", restaurant.getRestaurantId())
						.put("restaurant_name", restaurant.getRestaurantName())
						.put("restaurant_address", restaurant.getRestaurantAddress())
						.put("descriptions", restaurant.getDescriptions())
						.put("contact_number", restaurant.getContactNumber())
						.put("price", restaurant.getPrice())
						.put("times_purchased", restaurant.getTimesPurchased());
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
						.put("restaurant_id", restaurant.getRestaurantId())
						.put("restaurant_name", restaurant.getRestaurantName())
						.put("restaurant_address", restaurant.getRestaurantAddress())
						.put("descriptions", restaurant.getDescriptions())
						.put("contact_number", restaurant.getContactNumber())
						.put("price", restaurant.getPrice())
						.put("times_purchased", restaurant.getTimesPurchased());
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
						.put("restaurant_id", restaurant.getRestaurantId())
						.put("restaurant_name", restaurant.getRestaurantName())
						.put("restaurant_address", restaurant.getRestaurantAddress())
						.put("descriptions", restaurant.getDescriptions())
						.put("contact_number", restaurant.getContactNumber())
						.put("price", restaurant.getPrice())
						.put("times_purchased", restaurant.getTimesPurchased());
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
						.put("restaurant_id", restaurant.getRestaurantId())
						.put("restaurant_name", restaurant.getRestaurantName())
						.put("restaurant_address", restaurant.getRestaurantAddress())
						.put("descriptions", restaurant.getDescriptions())
						.put("contact_number", restaurant.getContactNumber())
						.put("price", restaurant.getPrice())
						.put("times_purchased", restaurant.getTimesPurchased());
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
					.put("restaurant_Inventory_id", restaurant.getRestaurantInventoryId())
					.put("restaurant_name", restaurant.getRestaurantName())
					.put("restaurant_address", restaurant.getRestaurantAddress())
					.put("availability_date", restaurant.getAvailabilityDate());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = "/restaurant/searchdate") //餐廳日期的模糊搜尋
	public String blurDateFind(
			@RequestParam(value = "orderDate") String orderDate) {
		java.sql.Date hqlOrder = java.sql.Date.valueOf(orderDate); //將一個符合特定格式的日期字串轉換成java.sql.Date
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectRestaurantInventoryView> result = restaurantRepositoryService.blurDateFind(hqlOrder);
		if(result != null && !result.isEmpty()) {
			for(SelectRestaurantInventoryView restaurant : result) {
				JSONObject item = new JSONObject()
					.put("restaurant_Inventory_id", restaurant.getRestaurantInventoryId())
					.put("restaurant_name", restaurant.getRestaurantName())
					.put("restaurant_address", restaurant.getRestaurantAddress())
					.put("availability_date", restaurant.getAvailabilityDate())
					.put("lunch", restaurant.getLunch())
					.put("dinner", restaurant.getDinner());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/restaurant/searchpicture"}) //用餐廳名稱搜尋圖片
	public String findPictureByName(@RequestParam(value = "name") String name) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectRestaurantPictureView> result = restaurantRepositoryService.findPictureByName(name);
		if(result != null && !result.isEmpty()) {
			for(SelectRestaurantPictureView restaurant : result) {
				JSONObject item = new JSONObject()
					.put("restaurant_pictures_id", restaurant.getRestaurantPicturesId())
					.put("restaurant_name", restaurant.getRestaurantName())
					.put("url_image", restaurant.getUrlImage());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

}
