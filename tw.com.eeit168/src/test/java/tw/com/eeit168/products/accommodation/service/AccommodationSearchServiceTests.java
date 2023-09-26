package tw.com.eeit168.products.accommodation.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AccommodationSearchServiceTests {
	@Autowired
	private AccommodationSearchService accommodationSearchService;
	
	@Test
	public void testFindAccommodationName(String keyword) {
		String findword = "";
	}
}
