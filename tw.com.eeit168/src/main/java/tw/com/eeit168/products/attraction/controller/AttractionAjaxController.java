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
import tw.com.eeit168.products.attraction.service.AttractionRepositoryService;
import tw.com.eeit168.products.attraction.service.BlurFindAttractionService;

@RestController //@Controller+@ResponseBody
@RequestMapping(path = {"/product"})
public class AttractionAjaxController {

	@Autowired
	private AttractionRepositoryService attractionRepositoryService;
	
	@Autowired
	private BlurFindAttractionService blurFindAttractionService;
	
	@GetMapping(path = {"/attraction/{attractions_id}"}) //以id搜尋
	public String findById(@PathVariable(name = "attractions_id") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		AttractionBean result = attractionRepositoryService.findById(id);
		if(result != null) {
			JSONObject item = new JSONObject()
					.put("attractions_id", result.getAttractions_id())
					.put("attractions_name", result.getAttractions_name())
					.put("attractions_address", result.getAttractions_address())
					.put("descriptions", result.getDescriptions())
					.put("open_time", result.getOpen_time())
					.put("close_time", result.getClose_time())
					.put("contact_number", result.getContact_number())
					.put("times_purchased", result.getTimes_purchased());
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
						.put("attractions_id", attraction.getAttractions_id())
						.put("attractions_name", attraction.getAttractions_name())
						.put("attractions_address", attraction.getAttractions_address())
						.put("descriptions", attraction.getDescriptions())
						.put("open_time", attraction.getOpen_time())
						.put("close_time", attraction.getClose_time())
						.put("contact_number", attraction.getContact_number())
						.put("times_purchased", attraction.getTimes_purchased());
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
		List<AttractionBean> result = attractionRepositoryService.selectTop5();
		if(result != null && !result.isEmpty()) {
			for(AttractionBean attraction :result) {
				JSONObject item = new JSONObject()
						.put("attractions_id", attraction.getAttractions_id())
						.put("attractions_name", attraction.getAttractions_name())
						.put("attractions_address", attraction.getAttractions_address())
						.put("descriptions", attraction.getDescriptions())
						.put("open_time", attraction.getOpen_time())
						.put("close_time", attraction.getClose_time())
						.put("contact_number", attraction.getContact_number())
						.put("times_purchased", attraction.getTimes_purchased());
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
						.put("attractions_id", attraction.getAttractions_id())
						.put("attractions_name", attraction.getAttractions_name())
						.put("attractions_address", attraction.getAttractions_address())
						.put("descriptions", attraction.getDescriptions())
						.put("open_time", attraction.getOpen_time())
						.put("close_time", attraction.getClose_time())
						.put("contact_number", attraction.getContact_number())
						.put("times_purchased", attraction.getTimes_purchased());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
//	@GetMapping(path = {"/attraction/bypricedesc"}) //以價格搜尋(高-低)
//	public String findAllByPriceDesc() {
//		JSONObject responseJson = new JSONObject();
//		JSONArray array = new JSONArray();
//		List<AttractionBean> result = attractionRepositoryService.findAllByPriceDesc();
//		if(result != null && !result.isEmpty()) {
//			for(AttractionBean attraction : result) {
//				JSONObject item = new JSONObject()
//						.put("attractions_id", attraction.getAttractions_id())
//						.put("attractions_name", attraction.getAttractions_name())
//						.put("attractions_address", attraction.getAttractions_address())
//						.put("descriptions", attraction.getDescriptions())
//						.put("open_time", attraction.getOpen_time())
//						.put("close_time", attraction.getClose_time())
//						.put("contact_number", attraction.getContact_number())
//						.put("times_purchased", attraction.getTimes_purchased());
//				array = array.put(item);
//			}
//		}
//		responseJson.put("list", array);
//		return responseJson.toString();
//	}
	
//	@GetMapping(path = {"/attraction/bypriceasc"}) //以價格搜尋(低-高)
//	public String findAllByPriceAsc() {
//		JSONObject responseJson = new JSONObject();
//		JSONArray array = new JSONArray();
//		List<AttractionBean> result = attractionRepositoryService.findAllByPriceAsc();
//		if(result != null && !result.isEmpty()) {
//			for(AttractionBean attraction : result) {
//				JSONObject item = new JSONObject()
//						.put("attractions_id", attraction.getAttractions_id())
//						.put("attractions_name", attraction.getAttractions_name())
//						.put("attractions_address", attraction.getAttractions_address())
//						.put("descriptions", attraction.getDescriptions())
//						.put("open_time", attraction.getOpen_time())
//						.put("close_time", attraction.getClose_time())
//						.put("contact_number", attraction.getContact_number())
//						.put("times_purchased", attraction.getTimes_purchased());
//				array = array.put(item);
//			}
//		}
//		responseJson.put("list", array);
//		return responseJson.toString();
//	}
	
	@GetMapping(path = {"/attraction/search"}) //餐廳名稱及地點的模糊搜尋
	public String blurFind(@RequestParam(value = "keyword", required = false) String keyword) { //require=false表示keyword可不填
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectAttractionsInventoryView> result = blurFindAttractionService.blurFind(keyword);
		if(result != null && !result.isEmpty()) {
			for(SelectAttractionsInventoryView attraction : result) {
				JSONObject item = new JSONObject()
						.put("attractions_inventory_id", attraction.getAttractions_inventory_id())
						.put("attractions_name", attraction.getAttractions_name())
						.put("attractions_address", attraction.getAttractions_address())
						.put("availability_date", attraction.getAvailability_date())
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
			@RequestParam(value = "checkInDate") String checkInDate, 
			@RequestParam(value = "checkOutDate") String checkOutDate) {
		java.sql.Date hqlCheckIn = java.sql.Date.valueOf(checkInDate); //將一個符合特定格式的日期字串轉換成java.sql.Date
		java.sql.Date hqlCheckOut = java.sql.Date.valueOf(checkOutDate); //將一個符合特定格式的日期字串轉換成java.sql.Date
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<SelectAttractionsInventoryView> result = blurFindAttractionService.blurDateFind(hqlCheckIn, hqlCheckOut);
		if(result != null && !result.isEmpty()) {
			for(SelectAttractionsInventoryView attraction : result) {
				JSONObject item = new JSONObject()
						.put("attractions_inventory_id", attraction.getAttractions_inventory_id())
						.put("attractions_name", attraction.getAttractions_name())
						.put("attractions_address", attraction.getAttractions_address())
						.put("availability_date", attraction.getAvailability_date())
						.put("adult", attraction.getAdult())
						.put("child", attraction.getChild());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
}
