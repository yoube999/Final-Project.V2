package tw.com.eeit168.products.accommodation.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.accommodation.model.Accommodation;


@SpringBootTest
@Transactional
public class AccommodationDAOHibernateTests {
	@Autowired
	private AccommodationDAO accommodationDAO;
	
	@Test
	public void testSearchByKeyword() {
		String keyword = "Hotel A";	// 設定你想搜尋的關鍵字
		List<Accommodation> accommodations = accommodationDAO.searchByKeyword(keyword);
		// 斷言檢查是否成功搜尋到住宿資料
        assertNotNull(accommodations);
        assertFalse(accommodations.isEmpty());

        System.out.println("Number of accommodations found: " + accommodations.size());
        for (Accommodation accommodation : accommodations) {
            System.out.println(accommodation);
        }	
//		System.out.println("Number of accommodations found: " + accommodations.size());
//		for (Accommodation accommodation : accommodations) {
//		    System.out.println(accommodation);
//		}


//		 Print the accommodations found
//        if (accommodations != null) {
//            for (Accommodation accommodation : accommodations) {
//                System.out.println(accommodation);
//            }
//        } else {
//            System.out.println("No accommodations found for the keyword: " + keyword);
//        }
//		
	}
}
