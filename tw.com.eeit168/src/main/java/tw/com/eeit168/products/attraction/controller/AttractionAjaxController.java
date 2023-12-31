package tw.com.eeit168.products.attraction.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.products.attraction.model.AttractionBean;
import tw.com.eeit168.products.attraction.model.SelectAttractionsInventoryView;
import tw.com.eeit168.products.attraction.model.SelectAttractionsPictureView;
import tw.com.eeit168.products.attraction.model.SelectAttractionsTicketView;
import tw.com.eeit168.products.attraction.service.AttractionRepositoryService;

@RestController //@Controller+@ResponseBody
@RequestMapping(path = {"/product"})
public class AttractionAjaxController {

	@Autowired
	private AttractionRepositoryService attractionRepositoryService;
	
	@GetMapping(path = {"/attraction/{attractions_id}"}) //以id搜尋
	public String findById(@PathVariable(name = "attractions_id") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		AttractionBean result = attractionRepositoryService.findById(id);
		if(result != null) {
			JSONObject item = new JSONObject()
					.put("attractions_id", result.getAttractionsId())
					.put("attractions_name", result.getAttractionsName())
					.put("attractions_address", result.getAttractionsAddress())
					.put("descriptions", result.getDescriptions())
					.put("open_time", result.getOpenTime())
					.put("close_time", result.getCloseTime())
					.put("contact_number", result.getContactNumber())
					.put("times_purchased", result.getTimesPurchased());
			array = array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/attraction"}) //搜尋全部
	public String findAll() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<AttractionBean> result = attractionRepositoryService.findAll();
		if(result != null && !result.isEmpty()) {
			for(AttractionBean attraction : result) {
				JSONObject item = new JSONObject()
						.put("attractions_id", attraction.getAttractionsId())
						.put("attractions_name", attraction.getAttractionsName())
						.put("attractions_address", attraction.getAttractionsAddress())
						.put("descriptions", attraction.getDescriptions())
						.put("open_time", attraction.getOpenTime())
						.put("close_time", attraction.getCloseTime())
						.put("contact_number", attraction.getContactNumber())
						.put("times_purchased", attraction.getTimesPurchased());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/attraction/top5"}) //Top5
	public String selectTop5() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectAttractionsTicketView> result = attractionRepositoryService.selectTop5();
		if(result != null && !result.isEmpty()) {
			for(SelectAttractionsTicketView attraction :result) {
				JSONObject item = new JSONObject()
						.put("attractions_name", attraction.getAttractionsName())
						.put("times_purchased", attraction.getTimesPurchased())
						.put("adult_price", attraction.getAdultPrice());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/attractiondesc"}) //以被購買次數搜尋(多-少)
	public String findAllByPurchasedDesc() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<AttractionBean> result = attractionRepositoryService.findAllByPurchasedDesc();
		if(result != null && !result.isEmpty()) {
			for(AttractionBean attraction : result) {
				JSONObject item = new JSONObject()
						.put("attractions_id", attraction.getAttractionsId())
						.put("attractions_name", attraction.getAttractionsName())
						.put("attractions_address", attraction.getAttractionsAddress())
						.put("descriptions", attraction.getDescriptions())
						.put("open_time", attraction.getOpenTime())
						.put("close_time", attraction.getCloseTime())
						.put("contact_number", attraction.getContactNumber())
						.put("times_purchased", attraction.getTimesPurchased());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/attraction/bypricedesc"}) //以價格搜尋(高-低)
	public String findAllByPriceDesc() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectAttractionsTicketView> result = attractionRepositoryService.findAllByPriceDesc();
		if(result != null && !result.isEmpty()) {
			for(SelectAttractionsTicketView attraction : result) {
				JSONObject item = new JSONObject()
						.put("attractions_ticket_id", attraction.getAttractionsTicketId())
						.put("attractions_name", attraction.getAttractionsName())
						.put("valid_date", attraction.getValidDate())
						.put("adult_price", attraction.getAdultPrice())
						.put("child_price", attraction.getChildPrice());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/attraction/bypriceasc"}) //以價格搜尋(低-高)
	public String findAllByPriceAsc() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectAttractionsTicketView> result = attractionRepositoryService.findAllByPriceAsc();
		if(result != null && !result.isEmpty()) {
			for(SelectAttractionsTicketView attraction : result) {
				JSONObject item = new JSONObject()
						.put("attractions_ticket_id", attraction.getAttractionsTicketId())
						.put("attractions_name", attraction.getAttractionsName())
						.put("valid_date", attraction.getValidDate())
						.put("adult_price", attraction.getAdultPrice())
						.put("child_price", attraction.getChildPrice());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/attraction/search"}) //餐廳名稱及地點的模糊搜尋
	public String blurFind(@RequestParam(value = "keyword", required = false) String keyword) { //require=false表示keyword可不填
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectAttractionsInventoryView> result = attractionRepositoryService.blurFind(keyword);
		if(result != null && !result.isEmpty()) {
			for(SelectAttractionsInventoryView attraction : result) {
				JSONObject item = new JSONObject()
						.put("attractions_inventory_id", attraction.getAttractionsInventoryId())
						.put("attractions_name", attraction.getAttractionsName())
						.put("attractions_address", attraction.getAttractionsAddress())
						.put("availability_date", attraction.getAvailabilityDate())
						.put("adult", attraction.getAdult())
						.put("child", attraction.getChild());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/attraction/searchdate"}) //餐廳日期的模糊搜尋
	public String blurDateFind(
			@RequestParam(value = "orderDate") String orderDate) {
		java.sql.Date hqlOrder = java.sql.Date.valueOf(orderDate); //將一個符合特定格式的日期字串轉換成java.sql.Date
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectAttractionsInventoryView> result = attractionRepositoryService.blurDateFind(hqlOrder);
		if(result != null && !result.isEmpty()) {
			for(SelectAttractionsInventoryView attraction : result) {
				JSONObject item = new JSONObject()
						.put("attractions_inventory_id", attraction.getAttractionsInventoryId())
						.put("attractions_name", attraction.getAttractionsName())
						.put("attractions_address", attraction.getAttractionsAddress())
						.put("availability_date", attraction.getAvailabilityDate())
						.put("adult", attraction.getAdult())
						.put("child", attraction.getChild());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/attraction/searchpicture"}) //用景點名稱搜尋圖片
	public String findPictureByName(@RequestParam(value = "name") String name) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectAttractionsPictureView> result = attractionRepositoryService.findPictureByName(name);
		if(result != null && !result.isEmpty()) {
			for(SelectAttractionsPictureView attraction : result) {
				JSONObject item = new JSONObject()
					.put("attractions_pictures_id", attraction.getAttractionsPicturesId())
					.put("attractions_name", attraction.getAttractionsName())
					.put("url_image", attraction.getUrlImage());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
}
