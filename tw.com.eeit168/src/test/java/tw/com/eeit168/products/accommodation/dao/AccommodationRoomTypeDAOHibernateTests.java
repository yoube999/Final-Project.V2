package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;

@SpringBootTest
//@Transactional
public class AccommodationRoomTypeDAOHibernateTests {
	@Autowired
	private AccommodationRoomTypeDAO accommodationRoomTypeDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	@Test
	public void testSearchByKeyword() {
		String keyword = "4人";
		List<AccommodationRoomType> accommodationRoomTypes = accommodationRoomTypeDAO.searchByKeyword(keyword);
		if(accommodationRoomTypes != null) {
			for(AccommodationRoomType accommodationRoomType: accommodationRoomTypes) {
				System.out.println(accommodationRoomType);
			}
		}else {
			System.out.println("No accommodationRoomTypes found for the keyword: " +keyword);
		}
	}
	
//	@Test
	public void testSelectAll() {
		List<AccommodationRoomType> selectAll = accommodationRoomTypeDAO.selectAll();
		System.out.println("selectAll" + selectAll);
	}
	
//	@Test	
	public void testInsert() {
		AccommodationRoomType accommodationRoomTypeInsert = new AccommodationRoomType();
		accommodationRoomTypeInsert.setAccommodationId(10);
		accommodationRoomTypeInsert.setRoomTypeName("測試房");
		accommodationRoomTypeInsert.setCapacity(4);
		accommodationRoomTypeInsert.setBedsAmount(4);
		
		AccommodationRoomType result = accommodationRoomTypeDAO.insert(accommodationRoomTypeInsert);
//		entityManager.flush();
		System.out.println("新增資料為" +result);
	}
	
//	@Test
	public void testUpdate() {
		AccommodationRoomType AccommodationRoomTypeUpdate = new AccommodationRoomType();
		AccommodationRoomTypeUpdate.setRoomTypeId(11);
		AccommodationRoomTypeUpdate.setAccommodationId(10);
		AccommodationRoomTypeUpdate.setRoomTypeName("修改測試");
		AccommodationRoomTypeUpdate.setCapacity(4);
		AccommodationRoomTypeUpdate.setBedsAmount(4);
		
		AccommodationRoomType update = accommodationRoomTypeDAO.update(AccommodationRoomTypeUpdate);
		System.out.println("修改資料為" + update);
	}
	
	@Test
	public void testDelete() {
		boolean delete = accommodationRoomTypeDAO.delete(31);
		System.out.println("刪除資料" + delete);
	}
}
