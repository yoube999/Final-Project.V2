package tw.com.eeit168.products.accommodation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.model.AccommodationInventory;
import tw.com.eeit168.products.accommodation.service.AccommodationSearchService;

@RestController
@RequestMapping("/accommodation")
public class AccommodationController {
	
	@Autowired
	private AccommodationSearchService accommodationSearchService;
	
	@GetMapping("/search")
	public ResponseEntity<List<Accommodation>> searchAccommodations(
			@RequestParam(value = "keyword", required = false) String keyword){
		List<Accommodation> accommodations = accommodationSearchService.findAccommodationName(keyword);
		return ResponseEntity.ok(accommodations);
	}
	
	@GetMapping("/searchByCriteria/checkInOutDate")
	public ResponseEntity<List<AccommodationInventory>> searchAccommodationsByCriteria(
			@RequestParam(value = "checkInDate") String checkInDate,
			@RequestParam(value = "checkOutDate") String checkOutDate){
		
		// Convert the checkInDate from string to java.sql.Date
		java.sql.Date sqlCheckInDate = java.sql.Date.valueOf(checkInDate);
		java.sql.Date sqlCheckOutDate = java.sql.Date.valueOf(checkOutDate);
		List<AccommodationInventory> inventoryByDate = accommodationSearchService.findAccommodationsByDate(sqlCheckInDate, sqlCheckOutDate);
		return ResponseEntity.ok(inventoryByDate);
	}
	@GetMapping("/searchByCriteria/requiredRooms")
	public ResponseEntity<List<AccommodationInventory>> searchAccommodationsByCriteria(
			@RequestParam(value = "requiredRooms") Integer requiredRooms){
		
		// Convert the checkInDate from string to java.sql.Date
		List<AccommodationInventory> inventoryByRooms = accommodationSearchService.findAccommodationsByRooms(requiredRooms);
		return ResponseEntity.ok(inventoryByRooms);
	}
}
