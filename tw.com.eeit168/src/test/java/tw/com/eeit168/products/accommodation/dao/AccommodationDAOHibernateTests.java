package tw.com.eeit168.products.accommodation.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		String keyword = "ity D";	// 設定你想搜尋的關鍵字
//		System.out.println("keyword:" + keyword);
		List<Accommodation> accommodations = accommodationDAO.searchByKeyword(keyword);
		
		 // 檢查搜尋結果
//	    if (!accommodations.isEmpty()) {
//	        System.out.println("Accommodations found:");
//	        for (Accommodation accommodation : accommodations) {
//	            System.out.println(accommodation);
//	        }
//	    } else {
//	        System.out.println("No accommodations found for the keyword: Hotel A");
//	    }
		// 斷言檢查是否成功搜尋到住宿資料
//        assertNotNull(accommodations);
//        assertFalse(accommodations.isEmpty());
//		 Print the accommodations found
        if (accommodations != null) {
//        	System.out.println("Number of accommodations found: " + accommodations.size());
//        	System.out.println("Number of accommodations found: " + accommodations.size());
            for (Accommodation accommodation : accommodations) {
                System.out.println(accommodation);
            }
        } else {
            System.out.println("No accommodations found for the keyword: " + keyword);
        }
		
        // 斷言檢查是否成功搜尋到住宿資料
//		System.out.println("Number of accommodations found: " + accommodations.size());
//		for (Accommodation accommodation : accommodations) {
//			System.out.println(accommodation);
//		}
//        assertEquals(false, accommodations.isEmpty(), "Accommodations list should not be empty");
    }


	}
