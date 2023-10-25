package tw.com.eeit168.products.accommodation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.dao.AccommodationDAO;
import tw.com.eeit168.products.accommodation.service.AccommodationSearchService;

@SpringBootTest
@Transactional
public class AccommodationAjaxControllerTest {
	@Autowired
	private AccommodationDAO accommodationDAO;
	
	@Autowired
	private AccommodationSearchService accommodationSearchService;
	
	 @PersistenceContext
	    private EntityManager entityManager;
	 
	 @Test
	 public void testCalculateTotalPrice() {
		 String itemType = accommodationSearchService.findItemTypeByAccommodationId(1);
		 System.out.println("itemType" + itemType);
	        
	 }
}
