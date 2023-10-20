package tw.com.eeit168.products.restaurant.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.model.RestaurantPictureBean;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureView;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureViewTop5;
import tw.com.eeit168.products.restaurant.service.RestaurantPictureRepositoryService;
import tw.com.eeit168.products.restaurant.service.RestaurantRepositoryService;
import tw.com.eeit168.products.restaurant.service.SelectRestaurantPictureRepositoryService;

@RestController //@Controller+@ResponseBody
@CrossOrigin
@RequestMapping(path = {"/product"})
public class RestaurantAjaxController {
	
	@Autowired
	private RestaurantRepositoryService restaurantRepositoryService;
	
	@Autowired
	private RestaurantPictureRepositoryService restaurantPictureRepositoryService;
	
	@Autowired
	private SelectRestaurantPictureRepositoryService selectRestaurantPictureRepositoryService;
		
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
	
	@PostMapping(path = {"/restaurant/insert"}) //新增
	public String create(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		JSONObject object = new JSONObject(body);
		Integer restaurantId = object.isNull("restaurantId") ? null : object.getInt("restaurantId");
		if(restaurantRepositoryService.exists(restaurantId)) {
			responseJson.put("message", "id已存在，新增失敗");
			responseJson.put("success", false);
		} else {
			RestaurantBean result = restaurantRepositoryService.create(body);
			if(result == null) {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			}
		}
		return responseJson.toString();
	}
	
	@PutMapping(path = {"/restaurant/{restaurantId}"}) //修改
	public String modify(@PathVariable(name = "restaurantId") Integer id, @RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		if(!restaurantRepositoryService.exists(id)) {
			responseJson.put("message", "id不存在");
			responseJson.put("success", false);
		} else {
			RestaurantBean result = restaurantRepositoryService.modify(body);
			if(result == null) {
				responseJson.put("message", "修改失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "修改成功");
				responseJson.put("success", true);
			}
		}
		return responseJson.toString();
	}
	
	@DeleteMapping(path = {"/restaurant/{restaurantId}"}) //刪除
	public String remove(@PathVariable(name = "restaurantId") Integer id) {
		JSONObject responseJson = new JSONObject();
		if(!restaurantRepositoryService.exists(id)) {
			responseJson.put("message", "id不存在");
			responseJson.put("success", false);
		} else {
			if(restaurantRepositoryService.remove(id)) {
				responseJson.put("message", "刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

	@GetMapping("/search/top5Restaurants")
    public String getTop5Restaurants() {
        List<SelectRestaurantPictureViewTop5> top5Restaurants = restaurantRepositoryService.getTop5RestaurantsByTimesPurchased();
        
        JSONArray jsonArray = new JSONArray();
        for (SelectRestaurantPictureViewTop5 restaurant : top5Restaurants) {
            JSONObject item = new JSONObject()
//            		.put("restaurant_id", restaurant.getRestaurantPicturesId())
					.put("restaurant_name", restaurant.getRestaurantName())
					.put("descriptions", restaurant.getDescriptions())
					.put("price", restaurant.getPrice())
					.put("times_purchased", restaurant.getTimesPurchased())
            		.put("urlImage", restaurant.getUrlImage());
            jsonArray.put(item);
        }

        JSONObject responseJson = new JSONObject();
        responseJson.put("list", jsonArray);

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
	public String findPictureByName(@RequestParam(value = "restaurantName") String restaurantName) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectRestaurantPictureView> result = restaurantRepositoryService.findPictureByName(restaurantName);
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
	
	@PostMapping(path = {"/restaurant/picture"}) //新增照片
	public String createImage(@RequestParam("file") MultipartFile file, String body) {
		JSONObject responseJson = new JSONObject();
		RestaurantPictureBean restaurantPicture = null;
		try {
			restaurantPicture = restaurantPictureRepositoryService.create(body, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(restaurantPicture == null) {
			responseJson.put("message", "新增失敗");
			responseJson.put("success", false);
		} else {
			responseJson.put("message", "新增成功");
			responseJson.put("success", true);
		}
		return responseJson.toString();
	}
	
	@PostMapping(path = {"/restaurant/allandpicture"}) //找餐廳List資料
	public String findRestaurantList(@RequestBody String json) {
		JSONObject requesetJson = new JSONObject(json);
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		// 提取 start 參數和 row 參數
		int start = requesetJson.getInt("start");
		int row = requesetJson.getInt("row");
		List<SelectRestaurantPictureView> result = selectRestaurantPictureRepositoryService.selectAllRestaurantPicture(start, row);
		// 回傳給前端查詢資料總數做為分頁依據
		long count = selectRestaurantPictureRepositoryService.count();
		responseJson.put("count", count);
		if(result != null && !result.isEmpty()) {
			for(SelectRestaurantPictureView picture : result) {
				JSONObject item = new JSONObject()
						.put("restaurant_pictures_id", picture.getRestaurantPicturesId())
						.put("restaurant_name", picture.getRestaurantName())
						.put("price", picture.getPrice())
						.put("descriptions", picture.getDescriptions())
						.put("url_image", picture.getUrlImage());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/restaurant/picture/{restaurant_id}"}) //以id搜尋照片
	public String findPictureById(@PathVariable(name = "restaurant_id") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		RestaurantPictureBean result = restaurantPictureRepositoryService.findById(id);
		if(result != null) {
			JSONObject item = new JSONObject()
					.put("url_image", result.getUrlImage());
			array = array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
}
