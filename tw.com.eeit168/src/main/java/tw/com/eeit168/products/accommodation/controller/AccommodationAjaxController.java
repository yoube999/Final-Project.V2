package tw.com.eeit168.products.accommodation.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.model.AccommodationInventory;
import tw.com.eeit168.products.accommodation.model.AccommodationPhotos;
import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;
import tw.com.eeit168.products.accommodation.model.RoomSelection;
import tw.com.eeit168.products.accommodation.model.SelectAccommodationInventoryRoomtypePriceView;
import tw.com.eeit168.products.accommodation.model.SelectAccommodationPhotosPriceView;
import tw.com.eeit168.products.accommodation.repository.AccommodationPhotosRepository;
import tw.com.eeit168.products.accommodation.repository.AccommodationRepository;
import tw.com.eeit168.products.accommodation.service.AccommodationSearchService;
import tw.com.eeit168.products.accommodation.util.ImageToBase64Converter;

@RestController
@RequestMapping("/eeit168/accommodation")
@CrossOrigin
public class AccommodationAjaxController {
	
	@Autowired
	private AccommodationSearchService accommodationSearchService;
	@Autowired
	private AccommodationRepository accommodationRepository;
	@Autowired
	private AccommodationPhotosRepository accommodationPhotosRepository;
	
//	@Autowired
//	private AccommodationPriceRepository accommodationPriceRepository;
	
//	@Autowired
//	private AccommodationRoomTypeRepository accommodationRoomTypeRepository;
	
	@GetMapping(path = {"/findAll"})
    public String getAllAccommodations() {
		JSONObject responseJson = new JSONObject();
		List<Accommodation> accommodations = accommodationSearchService.getAllAccommodations();
		JSONArray jsonArray = new JSONArray();
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
	
	//抓出所有資料以及分頁功能
	@PostMapping("/selectAllMain")
	public String selectAll(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		long count = accommodationSearchService.count(json);
		responseJson.put("count", count);
		
		List<SelectAccommodationPhotosPriceView> accommodations = accommodationSearchService.selectAll(json);
		JSONArray jsonArray = new JSONArray();
		if(accommodations != null && !accommodations.isEmpty()) {
			for(SelectAccommodationPhotosPriceView accommodation: accommodations) {
				JSONObject item = new JSONObject()
						.put("accommodationPhotoId", accommodation.getAccommodationPhotoId())
						.put("accommodationId", accommodation.getAccommodationId())
						.put("accommodationName", accommodation.getAccommodationName())
						.put("descriptions", accommodation.getDescriptions())
						.put("photoUrl", accommodation.getPhotoUrl())
						.put("minWeekdayPrice", accommodation.getMinWeekdayPrice());
				jsonArray = jsonArray.put(item);
			}
		}
		responseJson.put("list", jsonArray);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/findbyaccommodationId/{accommodationId}"})
	public String getAccommodationDetailsById(@PathVariable Integer accommodationId) {
		JSONObject responseJson = new JSONObject();
		List<Accommodation> getAccommodationDetailsById = accommodationSearchService.getAccommodationById(accommodationId);
		JSONArray jsonArray = new JSONArray();
		if(getAccommodationDetailsById != null && !getAccommodationDetailsById.isEmpty()) {
			for(Accommodation accommodation: getAccommodationDetailsById) {
				JSONObject item = new JSONObject()
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
	
	//總價
//	@GetMapping("/{accommodationId}/calculateTotalPrice")
//	public String calculateTotalPrice(@PathVariable Integer accommodationId,
//	                                  @RequestParam("checkInDate")  Date checkInDate,
//	                                  @RequestParam("checkOutDate")  Date checkOutDate,
//	                                  @RequestParam("roomTypeName") String roomTypeName) {
//	    JSONObject responseJson = new JSONObject();
//	    
//	    try {
//	        Integer totalPrice = accommodationSearchService.calculateTotalPrice(accommodationId, checkInDate, checkOutDate, roomTypeName);
//	        responseJson.put("totalPrice", totalPrice);
//	    } catch (Exception e) {
//	        responseJson.put("error", "Unable to calculate total price");
//	    }
//	    
//	    return responseJson.toString();
//	}
	@PostMapping("/{accommodationId}/calculateTotalPrice")
	public ResponseEntity<String> calculateTotalPrice(
	        @PathVariable Integer accommodationId,
	        @RequestParam("checkInDate") String checkInDateStr,
	        @RequestParam("checkOutDate") String checkOutDateStr,
	        @RequestBody List<RoomSelection> roomSelection) {
	    // 解析日期
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date checkInDate;
	    Date checkOutDate;

	    try {
	        checkInDate = new java.sql.Date(dateFormat.parse(checkInDateStr).getTime());
	        checkOutDate = new java.sql.Date(dateFormat.parse(checkOutDateStr).getTime());
	    } catch (ParseException e) {
	        throw new IllegalArgumentException("Invalid date format");
	    }

	    try {
	        Map<String, Integer> totalPriceMap = accommodationSearchService.calculateTotalPrice(accommodationId, checkInDate, checkOutDate, roomSelection);
	        
	        // Calculate total price considering the date range
	        int totalDays = (int) ((checkOutDate.getTime() - checkInDate.getTime()) / (1000 * 60 * 60 * 24)) + 1;
	        if (totalDays < 1) {
	            totalDays = 1;  // 最少計算一天的價格
	        }
	        
	        for (Map.Entry<String, Integer> entry : totalPriceMap.entrySet()) {
//	            String roomTypeName = entry.getKey();
	            int roomPricePerDay = entry.getValue();
	            int totalPrice = roomPricePerDay * totalDays;
	            entry.setValue(totalPrice);
	        }
	        
	        
	        // Create a response map
	        Map<String, Object> response = new HashMap<>();
	        response.put("accommodationId", accommodationId);
	        response.put("checkInDate", checkInDateStr);
	        response.put("checkOutDate", checkOutDateStr);
	        response.put("totalPriceMap", totalPriceMap);
	        System.out.println("totalPriceMap=" + totalPriceMap);
	        // Convert the response to JSON
	        ObjectMapper objectMapper = new ObjectMapper();
	        String jsonResponse = objectMapper.writeValueAsString(response);

	        return ResponseEntity.ok(jsonResponse);
	    } catch (Exception e) {
	        throw new RuntimeException("Unable to calculate total price", e);
	    }
	}

	
	
	
//	@PostMapping("/{accommodationId}/calculateTotalPrice")
//	public ResponseEntity<Map<String, Integer>> calculateTotalPrice(
//            @PathVariable Integer accommodationId,
//            @RequestParam("checkInDate") String checkInDateStr,
//            @RequestParam("checkOutDate") String checkOutDateStr,
//            @RequestBody List<RoomSelection> roomSelection) {
//		//解析日期
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date checkInDate;
//        Date checkOutDate;
//
//        try {
//            checkInDate = new java.sql.Date(dateFormat.parse(checkInDateStr).getTime());
//            checkOutDate = new java.sql.Date(dateFormat.parse(checkOutDateStr).getTime());
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Invalid date format");
//        }
//        List<RoomSelection> roomSelections = new ArrayList<>();
//        roomSelections.add(roomSelection);
//        try {
//            Map<String, Integer> totalPriceMap =
//            		accommodationSearchService.calculateTotalPrice(accommodationId, checkInDate, checkOutDate, roomSelection);
//
//            return ResponseEntity.ok(totalPriceMap);
//            Map<String, Object> response = new HashMap<>();
//            response.put("accommodationId", accommodationId);
//            response.put("checkInDate", checkInDateStr);
//            response.put("checkOutDate", checkOutDateStr);
//            response.put("roomSelection", roomSelection);
//
//            // Convert the response to JSON
//            ObjectMapper objectMapper = new ObjectMapper();
//            String jsonResponse = objectMapper.writeValueAsString(response);
//
//            return ResponseEntity.ok(jsonResponse);
//        } catch (Exception e) {
//            throw new RuntimeException("Unable to calculate total price", e);
//        }
//    }
//	public String calculateTotalPrice(@PathVariable Integer accommodationId,
//	                                  @RequestParam("checkInDate")  String checkInDateStr,
//	                                  @RequestParam("checkOutDate")  String checkOutDateStr,
//	                                  @RequestParam("rooms") List<RoomSelection> rooms) {
//		 // Parse dates from string to java.sql.Date
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date checkInDate;
//        Date checkOutDate;
//
//        try {
//            checkInDate = new java.sql.Date(dateFormat.parse(checkInDateStr).getTime());
//            checkOutDate = new java.sql.Date(dateFormat.parse(checkOutDateStr).getTime());
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Invalid date format");
//        }
//		
//		List<RoomTotalPrice> totalPriceList = new ArrayList<>();
//
//	    try {
//	        for (RoomSelection room : rooms) {
//	            String roomTypeName = room.getRoomTypeName();
//	            Integer totalRooms = room.getTotalRooms();
//
//	            // 修改计算总价的逻辑，根据传入的房型、数量等计算总价格
//	            Integer totalPrice = accommodationSearchService.calculateTotalPrice(accommodationId, checkInDate, checkOutDate, roomTypeName, totalRooms);
//	            
//	            RoomTotalPrice roomTotalPrice = new RoomTotalPrice();
//	            roomTotalPrice.setRoomTypeName(roomTypeName);
//	            roomTotalPrice.setTotalRooms(totalRooms);
//	            roomTotalPrice.setTotalPrice(totalPrice);
//
//	            
//	            totalPriceList.add(roomTotalPrice);
//	        }
//
//	        // Convert the totalPriceList to JSON string
//	        ObjectMapper objectMapper = new ObjectMapper();
//	        String jsonString = objectMapper.writeValueAsString(totalPriceList);
//	        return jsonString;
//	    } catch (Exception e) {
//	        throw new RuntimeException("Unable to calculate total price", e);
//	    }
//	}

	
	@GetMapping(path = {"/findAllmain"})
    public List<SelectAccommodationPhotosPriceView> getAllAccommodationsInfo() {
        return accommodationSearchService.getAllAccommodationsInfo();
    }
	
	@GetMapping("/{accommodationId}/allphotos")
	public String getAllAccommodationPhotos(@PathVariable Integer accommodationId){
		JSONObject responseJson = new JSONObject();
		List<AccommodationPhotos> selectAllPhotos = accommodationSearchService.selectAllPhotos(accommodationId);
		JSONArray jsonArray = new JSONArray();
		
		for(AccommodationPhotos photo: selectAllPhotos) {
			JSONObject item = new JSONObject().put("photoUrl", photo.getPhotoUrl());
			jsonArray.put(item);
		}
		
		responseJson.put("photos", jsonArray);
		return responseJson.toString();
	}
	
	@GetMapping("/{accommodationId}/photos")
	public ResponseEntity<String> getAccommodationPhotos(@PathVariable Integer accommodationId) {
	    try {
	        String photoUrl = accommodationSearchService.getPhotoUrlByAccommodationId(accommodationId);

	        JSONObject jsonResponse = new JSONObject();
	        jsonResponse.put("photoUrl", photoUrl);

	        return ResponseEntity.ok(jsonResponse.toString());
	    } catch (Exception e) {
	        JSONObject errorResponse = new JSONObject();
	        errorResponse.put("error", "Error retrieving photo URL.");
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse.toString());
	    }
	}
	
	
	@GetMapping(path = {"/search/keyword"})
	public String searchAccommodations(
			@RequestParam(value = "keyword", required = false) String keyword){
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		// Check if the keyword is null or empty
		if(keyword == null || keyword.isEmpty()) {
			responseJson.put("error", "搜索欄不能為空");
			return responseJson.toString();
		}
		
		
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
		}else {
				// 如果找不到資料，設定特定訊息
				responseJson.put("error", "查無此資料");
			}
		responseJson.put("list", jsonArray);
		return responseJson.toString();
	}
	
	@GetMapping(path = "/search/top5")	//Top5
	public String selectTop5() {
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<SelectAccommodationInventoryRoomtypePriceView> top5 = accommodationRepository.selectTop5();
		if(top5 != null && !top5.isEmpty()) {
			for(SelectAccommodationInventoryRoomtypePriceView accommodation: top5) {
				JSONObject item = new JSONObject()
						.put("accommodationName", accommodation.getAccommodationName())
						.put("weekdayPrice", accommodation.getWeekdayPrice())
						.put("timesPurchased", accommodation.getTimesPurchased());
				jsonArray = jsonArray.put(item);
			}
		}
		responseJson.put("list", jsonArray);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/search/checkInOutDate/{accommodationId}"})
	public String findByAvailabilityDateBetween(
			@PathVariable("accommodationId")Integer accommodationId,
			@RequestParam(value = "checkInDate") String checkInDate,
			@RequestParam(value = "checkOutDate") String checkOutDate){
		
		System.out.println("Received checkInDate as string: " + checkInDate);
	    System.out.println("Received checkOutDate as string: " + checkOutDate);

	    // Convert the checkInDate from string to java.sql.Date
	    java.sql.Date sqlCheckInDate = java.sql.Date.valueOf(checkInDate);
	    java.sql.Date sqlCheckOutDate = java.sql.Date.valueOf(checkOutDate);

	    System.out.println("Converted checkInDate to java.sql.Date: " + sqlCheckInDate);
	    System.out.println("Converted checkOutDate to java.sql.Date: " + sqlCheckOutDate);

	    System.out.println("Received checkinDate in the repository: " + sqlCheckInDate);
	    System.out.println("Received checkoutDate in the repository: " + sqlCheckOutDate);
	    
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		
		List<SelectAccommodationInventoryRoomtypePriceView> inventoryByDate = accommodationSearchService.findByAccommodationIdAndAvailabilityDateBetween(accommodationId, sqlCheckInDate, sqlCheckOutDate);
		if(inventoryByDate != null && !inventoryByDate.isEmpty()) {
			for(SelectAccommodationInventoryRoomtypePriceView accommodationInventory: inventoryByDate) {
				JSONObject item = new JSONObject()
				.put("accommodationName", accommodationInventory.getAccommodationName())
				.put("roomTypeName", accommodationInventory.getRoomTypeName())
				.put("availabilityDate", accommodationInventory.getAvailabilityDate())
				.put("availableRooms", accommodationInventory.getAvailableRooms())
				.put("weekdayPrice", accommodationInventory.getWeekdayPrice());
				
				 jsonArray = jsonArray.put(item);
			}
		}
		responseJson.put("list", jsonArray);
		return responseJson.toString();
	}
//	@GetMapping(path = {"/search/checkInOutDate"})
//	public String findByAvailabilityDateBetween(
//			@RequestParam(value = "checkInDate") String checkInDate,
//			@RequestParam(value = "checkOutDate") String checkOutDate){
//		
//		System.out.println("Received checkInDate as string: " + checkInDate);
//		System.out.println("Received checkOutDate as string: " + checkOutDate);
//		
//		// Convert the checkInDate from string to java.sql.Date
//		java.sql.Date sqlCheckInDate = java.sql.Date.valueOf(checkInDate);
//		java.sql.Date sqlCheckOutDate = java.sql.Date.valueOf(checkOutDate);
//		
//		System.out.println("Converted checkInDate to java.sql.Date: " + sqlCheckInDate);
//		System.out.println("Converted checkOutDate to java.sql.Date: " + sqlCheckOutDate);
//		
//		System.out.println("Received checkinDate in the repository: " + sqlCheckInDate);
//		System.out.println("Received checkoutDate in the repository: " + sqlCheckOutDate);
//		
//		JSONObject responseJson = new JSONObject();
//		JSONArray jsonArray = new JSONArray();
//		
//		
//		List<SelectAccommodationInventoryRoomtypePriceView> inventoryByDate = accommodationSearchService.findByAvailabilityDateBetween(sqlCheckInDate, sqlCheckOutDate);
//		if(inventoryByDate != null && !inventoryByDate.isEmpty()) {
//			for(SelectAccommodationInventoryRoomtypePriceView accommodationInventory: inventoryByDate) {
//				JSONObject item = new JSONObject()
//						.put("accommodationName", accommodationInventory.getAccommodationName())
//						.put("roomTypeName", accommodationInventory.getRoomTypeName())
//						.put("availabilityDate", accommodationInventory.getAvailabilityDate())
//						.put("availableRooms", accommodationInventory.getAvailableRooms());
//				
//				jsonArray = jsonArray.put(item);
//			}
//		}
//		responseJson.put("list", jsonArray);
//		return responseJson.toString();
//	}
//	@GetMapping(path = {"/search/checkInOutDate"})
//	public String searchAccommodationsByCriteria(
//			@RequestParam(value = "checkInDate") String checkInDate,
//			@RequestParam(value = "checkOutDate") String checkOutDate){
//		JSONObject responseJson = new JSONObject();
//		JSONArray jsonArray = new JSONArray();
//		
//		// Convert the checkInDate from string to java.sql.Date
//		java.sql.Date sqlCheckInDate = java.sql.Date.valueOf(checkInDate);
//		java.sql.Date sqlCheckOutDate = java.sql.Date.valueOf(checkOutDate);
//		List<AccommodationInventory> inventoryByDate = accommodationSearchService.findByAvailabilityDateBetween(sqlCheckInDate, sqlCheckOutDate);
//		if(inventoryByDate != null && !inventoryByDate.isEmpty()) {
//			for(AccommodationInventory accommodationInventory: inventoryByDate) {
//				JSONObject item = new JSONObject()
//				.put("accommodationId", accommodationInventory.getAccommodationId())
//				.put("roomTypeId", accommodationInventory.getRoomTypeId())
//				.put("availabilityDate", accommodationInventory.getAvailabilityDate())
//				.put("availableRooms", accommodationInventory.getAvailableRooms());
//				
//				 jsonArray = jsonArray.put(item);
//			}
//		}
//		responseJson.put("list", jsonArray);
//		return responseJson.toString();
//	}
	
	@PostMapping("/{accommodationId}/photos")
    public ResponseEntity<String> uploadImage(@PathVariable Integer accommodationId, @RequestParam("file") MultipartFile file) {
        try {
            // Convert the image to Base64
            String base64Image = ImageToBase64Converter.convertToBase64(file);

            // Save the base64Image to the accommodation_photo table
            AccommodationPhotos accommodationPhotos = new AccommodationPhotos();
            accommodationPhotos.setAccommodationId(accommodationId);
            accommodationPhotos.setPhotoUrl(base64Image);
            accommodationPhotosRepository.save(accommodationPhotos);

            // For simplicity, just return the base64 image
            return ResponseEntity.ok("Base64 image: " + base64Image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed: " + e.getMessage());
        }
    }

	@GetMapping("/photos/{photoId}")
	public String getPhotoUrl(@PathVariable Integer photoId) {
	    String photoUrlById = accommodationSearchService.getPhotoUrlById(photoId);

	    JSONObject responseJson = new JSONObject();
	    responseJson.put("photoUrl", photoUrlById);

	    return responseJson.toString();
	}
	
	@GetMapping(path = {"/search/requiredRooms"})
	public String searchAccommodationsByCriteria(
			@RequestParam(value = "requiredRooms") Integer requiredRooms){
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		
		// Convert the checkInDate from string to java.sql.Date
		List<AccommodationInventory> inventoryByRooms = accommodationSearchService.findAccommodationsByRooms(requiredRooms);
		if(inventoryByRooms != null && !inventoryByRooms.isEmpty()) {
			for(AccommodationInventory accommodationInventory: inventoryByRooms) {
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
	
	 @GetMapping(path = {"/search/roomTypes/{accommodationId}"})
	    public String getRoomTypes(
	    		@PathVariable("accommodationId") int accommodationId,
	            @RequestParam("totalGuests") int totalGuests,
	            @RequestParam("requiredRooms") int requiredRooms) {
		 JSONObject responseJson = new JSONObject();
		 JSONArray jsonArray = new JSONArray();
		 
	     List<List<AccommodationRoomType>> roomTypeCombinations = accommodationSearchService.findRoomCombinations(accommodationId, totalGuests, requiredRooms);
	     if(roomTypeCombinations != null && !roomTypeCombinations.isEmpty()) {
	    	 for (List<AccommodationRoomType> roomTypeList : roomTypeCombinations) {
	    		    for (AccommodationRoomType accommodationRoomType : roomTypeList) {
	    		        // Access individual AccommodationRoomType properties and process them as needed
	    		        JSONObject item = new JSONObject()
	    		                .put("accommodationId", accommodationRoomType.getAccommodationId())
	    		                .put("roomTypeName", accommodationRoomType.getRoomTypeName())
	    		                .put("capacity", accommodationRoomType.getCapacity())
	    		                .put("bedsAmount", accommodationRoomType.getBedsAmount());

	    		        jsonArray.put(item);
	    		    }
	    		}

	     	responseJson.put("list", jsonArray);
	    }
	     return responseJson.toString();
	 }
//	 @GetMapping(path = {"/search/roomTypes"})
//	 public String getRoomTypes(
//			 @RequestParam("totalGuests") int totalGuests,
//			 @RequestParam("requiredRooms") int requiredRooms) {
//		 JSONObject responseJson = new JSONObject();
//		 JSONArray jsonArray = new JSONArray();
//		 
//		 List<List<AccommodationRoomType>> roomTypeCombinations = accommodationSearchService.findRoomCombinations(totalGuests, requiredRooms);
//		 if(roomTypeCombinations != null && !roomTypeCombinations.isEmpty()) {
//			 for (List<AccommodationRoomType> roomTypeList : roomTypeCombinations) {
//				 for (AccommodationRoomType accommodationRoomType : roomTypeList) {
//					 // Access individual AccommodationRoomType properties and process them as needed
//					 JSONObject item = new JSONObject()
//							 .put("accommodationId", accommodationRoomType.getAccommodationId())
//							 .put("roomTypeName", accommodationRoomType.getRoomTypeName())
//							 .put("capacity", accommodationRoomType.getCapacity())
//							 .put("bedsAmount", accommodationRoomType.getBedsAmount());
//					 
//					 jsonArray.put(item);
//				 }
//			 }
//			 
//			 responseJson.put("list", jsonArray);
//		 }
//		 return responseJson.toString();
//	 }

//	 @GetMapping(path = {"/search/PriceRange"})
//	 public String searchAccommodationPrices(
//			 @RequestParam("minWeekdayPrice") Integer minWeekdayPrice,
//	            @RequestParam("maxWeekdayPrice") Integer maxWeekdayPrice,
//	            @RequestParam("minWeekendPrice") Integer minWeekendPrice,
//	            @RequestParam("maxWeekendPrice") Integer maxWeekendPrice) {
//		 JSONObject responseJson = new JSONObject();
//		 JSONArray jsonArray = new JSONArray();
//		 System.out.println(minWeekdayPrice);
//		 System.out.println(maxWeekdayPrice);
//		 System.out.println(minWeekendPrice);
//		 System.out.println(maxWeekendPrice);
//		 System.out.println("1");
//		 List<SelectAccommodationInventoryRoomtypePriceView> prices = accommodationSearchService.getPricesInPriceRangeForWeekdaysAndWeekends(minWeekdayPrice, maxWeekdayPrice, minWeekendPrice, maxWeekendPrice);
////		 if (prices.isEmpty()) {
////			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("無此範圍之結果");
////		 } 
//		 System.out.println("2");
//		   if (prices != null && !prices.isEmpty()) {
//			   System.out.println("3");
//			   for(SelectAccommodationInventoryRoomtypePriceView accommodationPrice: prices) {
//				   System.out.println("4");
//				   JSONObject item = new JSONObject()
//						   .put("accommodationId", accommodationPrice.getAccommodationName())
//						   .put("roomTypeId",  accommodationPrice.getRoomTypeName())
//						   .put("weekdayPrice", accommodationPrice.getWeekdayPrice())
//						   .put("weekendPrice", accommodationPrice.getWeekendPrice());
//				   System.out.println("5");
//				   jsonArray = jsonArray.put(item);
//			   }
//		}
//		   responseJson.put("list", jsonArray);   
//		return responseJson.toString();
//	 }
	 @GetMapping(path = {"/search/weekdaypricerange"})
		public String findAllByWeekdayPriceRange(
				@RequestParam ("minWeekdayPrice") Integer minPrice,
				@RequestParam ("maxWeekdayPrice") Integer maxPrice
				) {
			JSONObject responseJson = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceRange = accommodationSearchService.findAllByWeekdayPriceRange(minPrice, maxPrice);
			
			if(findAllByWeekdayPriceRange != null && !findAllByWeekdayPriceRange.isEmpty()) {
				for(SelectAccommodationInventoryRoomtypePriceView weekdayPriceRange: findAllByWeekdayPriceRange) {
					JSONObject item = new JSONObject()
							.put("accommodationName", weekdayPriceRange.getAccommodationName())
							.put("roomTypeName", weekdayPriceRange.getRoomTypeName())
							.put("weekdayPrice", weekdayPriceRange.getWeekdayPrice())
							.put("availableRooms", weekdayPriceRange.getAvailableRooms())
							;
					jsonArray = jsonArray.put(item);
				}
			}
			responseJson.put("list", jsonArray);
			return responseJson.toString();
		}
		@GetMapping(path = {"/search/weekendpricerange"})
		public String findAllByWeekendPriceRange(
				@RequestParam ("minWeekendPrice") Integer minPrice,
				@RequestParam ("maxWeekendPrice") Integer maxPrice
				) {
			JSONObject responseJson = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceRange = accommodationSearchService.findAllByWeekendPriceRange(minPrice, maxPrice);
			
			if(findAllByWeekendPriceRange != null && !findAllByWeekendPriceRange.isEmpty()) {
				for(SelectAccommodationInventoryRoomtypePriceView weekdayPriceRange: findAllByWeekendPriceRange) {
					JSONObject item = new JSONObject()
							.put("accommodationName", weekdayPriceRange.getAccommodationName())
							.put("roomTypeName", weekdayPriceRange.getRoomTypeName())
							.put("weekdayPrice", weekdayPriceRange.getWeekdayPrice())
							.put("availableRooms", weekdayPriceRange.getAvailableRooms())
							;
					jsonArray = jsonArray.put(item);
				}
			}
			responseJson.put("list", jsonArray);
			return responseJson.toString();
		}
	 @GetMapping(path = {"/search/byweekdaypricedesc"}) //以價格搜尋(高-低)
	 public String findAllByWeekdayPriceDesc() {
		 JSONObject responseJson = new JSONObject();
		 JSONArray jsonArray = new JSONArray();
		 
		 List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceDesc = accommodationSearchService.findAllByWeekdayPriceDesc();
		
		 
		 if(findAllByWeekdayPriceDesc != null && !findAllByWeekdayPriceDesc.isEmpty()) {
			 for(SelectAccommodationInventoryRoomtypePriceView accommodationPrice: findAllByWeekdayPriceDesc) {
				 JSONObject item = new JSONObject()
						 .put("accommodationName", accommodationPrice.getAccommodationName())
						 .put("roomTypeName", accommodationPrice.getRoomTypeName())
						 .put("weekdayPrice", accommodationPrice.getWeekdayPrice())
//						 .put("weekendPrice", accommodationPrice.getWeekendPrice())
						 ;
				 
				 jsonArray = jsonArray.put(item);	 
			 }
		 }
		 responseJson.put("list", jsonArray);
		 return responseJson.toString();
	 }
	 
	 @GetMapping(path = {"/search/byweekendpricedesc"}) //以價格搜尋(高-低)
	 public String findAllByWeekendPriceDesc() {
		 JSONObject responseJson = new JSONObject();
		 JSONArray jsonArray = new JSONArray();
		 
		 List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceDesc = accommodationSearchService.findAllByWeekendPriceDesc();
		
		 
		 if(findAllByWeekendPriceDesc != null && !findAllByWeekendPriceDesc.isEmpty()) {
			 for(SelectAccommodationInventoryRoomtypePriceView accommodationPrice: findAllByWeekendPriceDesc) {
				 JSONObject item = new JSONObject()
						 .put("accommodationName", accommodationPrice.getAccommodationName())
						 .put("roomTypeName", accommodationPrice.getRoomTypeName())
//						 .put("weekdayPrice", accommodationPrice.getWeekdayPrice())
						 .put("weekendPrice", accommodationPrice.getWeekendPrice())
						 ;
				 
				 jsonArray = jsonArray.put(item);	 
			 }
		 }
		 responseJson.put("list", jsonArray);
		 return responseJson.toString();
	 }
	 
	 @GetMapping(path = {"/search/byweekdaypriceasc"}) //以價格搜尋(高-低)
	 public String findAllByWeekdayPriceAsc() {
		 JSONObject responseJson = new JSONObject();
		 JSONArray jsonArray = new JSONArray();
		 
		 List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceAsc = accommodationSearchService.findAllByWeekdayPriceAsc();
		 
		 
		 if(findAllByWeekdayPriceAsc != null && !findAllByWeekdayPriceAsc.isEmpty()) {
			 for(SelectAccommodationInventoryRoomtypePriceView accommodationPrice: findAllByWeekdayPriceAsc) {
				 JSONObject item = new JSONObject()
						 .put("accommodationName", accommodationPrice.getAccommodationName())
						 .put("roomTypeName", accommodationPrice.getRoomTypeName())
						 .put("weekdayPrice", accommodationPrice.getWeekdayPrice())
//						 .put("weekendPrice", accommodationPrice.getWeekendPrice())
						 ;
				 
				 jsonArray = jsonArray.put(item);	 
			 }
		 }
		 responseJson.put("list", jsonArray);
		 return responseJson.toString();
	 }
	 
	 @GetMapping(path = {"/search/byweekendpriceasc"}) //以價格搜尋(高-低)
	 public String findAllByWeekendPriceAsc() {
		 JSONObject responseJson = new JSONObject();
		 JSONArray jsonArray = new JSONArray();
		 
		 List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceAsc = accommodationSearchService.findAllByWeekendPriceAsc();
		
		 
		 if(findAllByWeekendPriceAsc != null && !findAllByWeekendPriceAsc.isEmpty()) {
			 for(SelectAccommodationInventoryRoomtypePriceView accommodationPrice: findAllByWeekendPriceAsc) {
				 JSONObject item = new JSONObject()
						 .put("accommodationName", accommodationPrice.getAccommodationName())
						 .put("roomTypeName", accommodationPrice.getRoomTypeName())
//						 .put("weekdayPrice", accommodationPrice.getWeekdayPrice())
						 .put("weekendPrice", accommodationPrice.getWeekendPrice())
						 ;
				 
				 jsonArray = jsonArray.put(item);	 
			 }
		 }
		 responseJson.put("list", jsonArray);
		 return responseJson.toString();
	 }
	 
//	 @GetMapping(path = {"/searchByCriteria/checkInOutDate"})
//		public ResponseEntity<List<AccommodationInventory>> searchAccommodationsByCriteria(
//				@RequestParam(value = "checkInDate") String checkInDate,
//				@RequestParam(value = "checkOutDate") String checkOutDate){
//			
//			// Convert the checkInDate from string to java.sql.Date
//			java.sql.Date sqlCheckInDate = java.sql.Date.valueOf(checkInDate);
//			java.sql.Date sqlCheckOutDate = java.sql.Date.valueOf(checkOutDate);
//			List<AccommodationInventory> inventoryByDate = accommodationSearchService.findAccommodationsByDate(sqlCheckInDate, sqlCheckOutDate);
//			return ResponseEntity.ok(inventoryByDate);
//		}
	 
//	 @GetMapping(path = {"/searchByCriteria/roomTypes"})
//	    public ResponseEntity<List<List<AccommodationRoomType>>> getRoomTypes(
//	            @RequestParam("totalGuests") int totalGuests,
//	            @RequestParam("requiredRooms") int requiredRooms) {
//
//	        List<List<AccommodationRoomType>> roomTypeCombinations = accommodationSearchService.findRoomCombinations(totalGuests, requiredRooms);
//
//	        return ResponseEntity.ok().body(roomTypeCombinations);
//	    }	 
	 
	 
	 
	 
	 
//	 @GetMapping("/searchPriceRange")
//	 public ResponseEntity<?> searchAccommodationPrices(
//			 @RequestParam("minWeekdayPrice") int minWeekdayPrice,
//	            @RequestParam("maxWeekdayPrice") int maxWeekdayPrice,
//	            @RequestParam("minWeekendPrice") int minWeekendPrice,
//	            @RequestParam("maxWeekendPrice") int maxWeekendPrice) {
//		 List<AccommodationPrice> prices = accommodationSearchService.getPricesInPriceRangeForWeekdaysAndWeekends(minWeekdayPrice, maxWeekdayPrice, minWeekendPrice, maxWeekendPrice);
//		   if (prices.isEmpty()) {
//		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("無此範圍之結果");
//		    }
//		 return ResponseEntity.ok(prices);
//	 }
//	 
	 
//	@GetMapping("/searchByCriteria/roomTypes")
//	public ResponseEntity<List<AccommodationRoomType>> getRoomTypes() {
//		// 獲取房型列表，按照"max occupancy"（即"capacity"）降序排列
//		List<AccommodationRoomType> roomTypesCapacityDesc = accommodationRoomTypeRepository.findAllByOrderByCapacityDesc();
//		
//		// 返回視圖名稱（假設視圖名稱為"roomTypes"）
//		return ResponseEntity.ok().body(roomTypesCapacityDesc);
//	}
}
