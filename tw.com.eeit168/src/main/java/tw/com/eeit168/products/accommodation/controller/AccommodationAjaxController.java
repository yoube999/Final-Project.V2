package tw.com.eeit168.products.accommodation.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.model.AccommodationInventory;
import tw.com.eeit168.products.accommodation.model.AccommodationPrice;
import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;
import tw.com.eeit168.products.accommodation.service.AccommodationSearchService;

@RestController
@RequestMapping("/accommodation")
public class AccommodationAjaxController {
	
	@Autowired
	private AccommodationSearchService accommodationSearchService;
//	@Autowired
//	private AccommodationRoomTypeRepository accommodationRoomTypeRepository;
	@GetMapping(path = {"/search"})
	public String searchAccommodations(
			@RequestParam(value = "keyword", required = false) String keyword){
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		List<Accommodation> accommodations = accommodationSearchService.findAccommodationName(keyword);
		if(accommodations != null && !accommodations.isEmpty()) {
			for(Accommodation accommodation: accommodations) {
				JSONObject item = new JSONObject()
				.put("accommodationId", accommodation.getAccommodationId())
				.put("accommodationName", accommodation.getAccommodationName())
				.put("accommodationAddress", accommodation.getAccommodationAddress())
				.put("descriptions", accommodation.getDescriptions())
				.put("contactNumber", accommodation.getContactNumber())
				.put("timesPurchased", accommodation.getTimesPurchased());
				jsonArray = jsonArray.put(item);
			}
		}
		responseJson.put("list", jsonArray);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/searchByCriteria/checkInOutDate"})
	public String searchAccommodationsByCriteria(
			@RequestParam(value = "checkInDate") String checkInDate,
			@RequestParam(value = "checkOutDate") String checkOutDate){
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		// Convert the checkInDate from string to java.sql.Date
		java.sql.Date sqlCheckInDate = java.sql.Date.valueOf(checkInDate);
		java.sql.Date sqlCheckOutDate = java.sql.Date.valueOf(checkOutDate);
		List<AccommodationInventory> inventoryByDate = accommodationSearchService.findAccommodationsByDate(sqlCheckInDate, sqlCheckOutDate);
		if(inventoryByDate != null && !inventoryByDate.isEmpty()) {
			for(AccommodationInventory accommodationInventory: inventoryByDate) {
				JSONObject item = new JSONObject()
				.put("accommodationId", accommodationInventory.getAccommodationId())
				.put("roomTypeId", accommodationInventory.getRoomTypeId())
				.put("availabilityDate", accommodationInventory.getAvailabilityDate())
				.put("availableRooms", accommodationInventory.getAvailableRooms());
				
				 jsonArray = jsonArray.put(item);
			}
		}
		responseJson.put("list", jsonArray);
		return responseJson.toString();
	}
	
//	@GetMapping(path = {"/searchByCriteria/checkInOutDate"})
//	public ResponseEntity<List<AccommodationInventory>> searchAccommodationsByCriteria(
//			@RequestParam(value = "checkInDate") String checkInDate,
//			@RequestParam(value = "checkOutDate") String checkOutDate){
//		
//		// Convert the checkInDate from string to java.sql.Date
//		java.sql.Date sqlCheckInDate = java.sql.Date.valueOf(checkInDate);
//		java.sql.Date sqlCheckOutDate = java.sql.Date.valueOf(checkOutDate);
//		List<AccommodationInventory> inventoryByDate = accommodationSearchService.findAccommodationsByDate(sqlCheckInDate, sqlCheckOutDate);
//		return ResponseEntity.ok(inventoryByDate);
//	}
	@GetMapping("/searchByCriteria/requiredRooms")
	public ResponseEntity<List<AccommodationInventory>> searchAccommodationsByCriteria(
			@RequestParam(value = "requiredRooms") Integer requiredRooms){
		
		// Convert the checkInDate from string to java.sql.Date
		List<AccommodationInventory> inventoryByRooms = accommodationSearchService.findAccommodationsByRooms(requiredRooms);
		return ResponseEntity.ok(inventoryByRooms);
	}
	
	 @GetMapping("/searchByCriteria/roomTypes")
	    public ResponseEntity<List<List<AccommodationRoomType>>> getRoomTypes(
	            @RequestParam("totalGuests") int totalGuests,
	            @RequestParam("requiredRooms") int requiredRooms) {

	        List<List<AccommodationRoomType>> roomTypeCombinations = accommodationSearchService.findRoomCombinations(totalGuests, requiredRooms);

	        return ResponseEntity.ok().body(roomTypeCombinations);
	    }
	 @GetMapping("/searchPriceRange")
	 public ResponseEntity<?> searchAccommodationPrices(
			 @RequestParam("minWeekdayPrice") int minWeekdayPrice,
	            @RequestParam("maxWeekdayPrice") int maxWeekdayPrice,
	            @RequestParam("minWeekendPrice") int minWeekendPrice,
	            @RequestParam("maxWeekendPrice") int maxWeekendPrice) {
		 List<AccommodationPrice> prices = accommodationSearchService.getPricesInPriceRangeForWeekdaysAndWeekends(minWeekdayPrice, maxWeekdayPrice, minWeekendPrice, maxWeekendPrice);
		   if (prices.isEmpty()) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("無此範圍之結果");
		    }
		 return ResponseEntity.ok(prices);
	 }
	 
	 
//	@GetMapping("/searchByCriteria/roomTypes")
//	public ResponseEntity<List<AccommodationRoomType>> getRoomTypes() {
//		// 獲取房型列表，按照"max occupancy"（即"capacity"）降序排列
//		List<AccommodationRoomType> roomTypesCapacityDesc = accommodationRoomTypeRepository.findAllByOrderByCapacityDesc();
//		
//		// 返回視圖名稱（假設視圖名稱為"roomTypes"）
//		return ResponseEntity.ok().body(roomTypesCapacityDesc);
//	}
}
