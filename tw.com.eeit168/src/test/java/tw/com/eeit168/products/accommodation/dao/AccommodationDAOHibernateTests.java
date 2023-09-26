package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.Accommodation;


@SpringBootTest
//@Transactional
public class AccommodationDAOHibernateTests {
	@Autowired
	private AccommodationDAO accommodationDAO;
	
	 @PersistenceContext
	    private EntityManager entityManager;  // 注入EntityManager
	
	
//	@Test
	public void testSearchByKeyword() {
		String keyword = "市東";	// 設定你想搜尋的關鍵字
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
	
//	@Test
	public void testSelectAll() {
		List<Accommodation> selectAll = accommodationDAO.selectAll();
		System.out.println("selectAll" + selectAll);
	}

//	accommodation_name, accommodation_address, contact_number, times_purchased
//	@Test
	public void testInsert() {
		Accommodation accommodationInsert = new Accommodation();
//		accommodationInsert.setAccommodationId(null);
		accommodationInsert.setAccommodationName("資展國際");
		accommodationInsert.setAccommodationAddress("106臺北市大安區復興南路一段390號2樓");
		accommodationInsert.setDescriptions("描述測試");
		accommodationInsert.setContactNumber("(02)6631-6588");
		accommodationInsert.setTimesPurchased(26);
		
		Accommodation result = accommodationDAO.insert(accommodationInsert);
		entityManager.flush();
		System.out.println("新增資料為" +result);
	}
	
//	@Test
	public void testUpdate() {
		Accommodation accommodationUpdate = new Accommodation();
		accommodationUpdate.setAccommodationId(11);
		accommodationUpdate.setAccommodationName("資展國際修改");
		accommodationUpdate.setAccommodationAddress("106臺北市大安區復興南路一段390號3樓");
		accommodationUpdate.setDescriptions("描述修改測試");
		accommodationUpdate.setContactNumber("(02) 6612-4445");
		accommodationUpdate.setTimesPurchased(123);
		
		Accommodation update = accommodationDAO.update(accommodationUpdate);
		System.out.println("修改資料為" +update);
	}
	@Test
	public void testDelete() {
		boolean delete = accommodationDAO.delete(11);
		System.out.println("刪除資料" + delete);
	}
	}
