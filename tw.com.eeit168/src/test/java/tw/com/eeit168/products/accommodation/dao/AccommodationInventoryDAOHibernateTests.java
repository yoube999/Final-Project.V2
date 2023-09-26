package tw.com.eeit168.products.accommodation.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tw.com.eeit168.products.accommodation.model.AccommodationInventory;

@SpringBootTest
//@Transactional
public class AccommodationInventoryDAOHibernateTests {
	@Autowired
	private AccommodationInventoryDAO accommodationInventoryDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	@Test
	public void testSelect() {
		AccommodationInventory select = accommodationInventoryDAO.select(5);
		System.out.println("select=" +select);
	}
	
//	@Test
	public void testSelectAll() {
		List<AccommodationInventory> selectAll = accommodationInventoryDAO.selectAll();
		System.out.println("selectAll" + selectAll);
	}
	
//	@Test
	public void testInsert() {
		AccommodationInventory accommodationInventoryInsert = new AccommodationInventory();
		accommodationInventoryInsert.setAccommodationId(10);
		accommodationInventoryInsert.setRoomTypeId(30);
		LocalDate date = LocalDate.of(2023, 8, 23);
		accommodationInventoryInsert.setAvailabilityDate(java.sql.Date.valueOf(date));
		accommodationInventoryInsert.setAvailableRooms(15);
		
		AccommodationInventory result = accommodationInventoryDAO.insert(accommodationInventoryInsert);
		System.out.println("新增資料為" + result);
	}
	
//	@Test
	public void testUpdate() {
		AccommodationInventory accommodationInventoryUpdate = new AccommodationInventory();
		accommodationInventoryUpdate.setInventoryId(31);
		accommodationInventoryUpdate.setAccommodationId(10);
		accommodationInventoryUpdate.setRoomTypeId(30);
		LocalDate dateUpdated = LocalDate.of(2023, 9, 26);
		accommodationInventoryUpdate.setAvailabilityDate(java.sql.Date.valueOf(dateUpdated));
		accommodationInventoryUpdate.setAvailableRooms(30);
		
		AccommodationInventory update = accommodationInventoryDAO.update(accommodationInventoryUpdate);
		System.out.println("修改資料為" + update);
	}
	
	@Test
	public void testDelete() {
		boolean delete = accommodationInventoryDAO.delete(31);
		System.out.println("刪除資料" + delete);
	}
}
