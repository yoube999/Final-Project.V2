package tw.com.eeit168.products.accommodation.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tw.com.eeit168.products.accommodation.model.AccommodationOrder;

@SpringBootTest
//@Transactional
public class AccommodationOrderDAOHibernateTests {
	@Autowired
	private AccommodationOrderDAO accommodationOrderDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	@Test
	public void testSelect() {
		AccommodationOrder select = accommodationOrderDAO.select(6);
		System.out.println("select" +select);
	}
	
//	@Test
	public void testSelectAll() {
		List<AccommodationOrder> selectAll = accommodationOrderDAO.selectAll();
		System.out.println("selectAll" + selectAll);
	}
	
//	@Test
	public void testInsert() {
		AccommodationOrder accommodationOrderInsert = new AccommodationOrder();
		accommodationOrderInsert.setRecordId(4);
		accommodationOrderInsert.setAccommodationId(1);
		accommodationOrderInsert.setRoomTypeName("四人房");
		LocalDate checkedInDate = LocalDate.of(2023, 9, 30);
		accommodationOrderInsert.setCheckedInDate(java.sql.Date.valueOf(checkedInDate) );
		LocalDate checkedOutDate = LocalDate.of(2023, 10, 2);
		accommodationOrderInsert.setCheckedOutDate(java.sql.Date.valueOf(checkedOutDate));
		accommodationOrderInsert.setTotalPrice(10000);
		accommodationOrderInsert.setRecordAccommodationStatus("審核");
		
		AccommodationOrder result = accommodationOrderDAO.insert(accommodationOrderInsert);
		System.out.println("新增資料為" + result);
	}
	
//	@Test
	public void testUpdate() {
		AccommodationOrder accommodationOrderUpdate = new AccommodationOrder();
		accommodationOrderUpdate.setOrderId(6);
		accommodationOrderUpdate.setRecordId(4);
		accommodationOrderUpdate.setAccommodationId(3);
		accommodationOrderUpdate.setRoomTypeName("雙人房");
		LocalDate checkedInUpdated = LocalDate.of(2023, 10, 10);
		accommodationOrderUpdate.setCheckedInDate(java.sql.Date.valueOf(checkedInUpdated));
		LocalDate checkedOutUpdated = LocalDate.of(2023, 10, 14);
		accommodationOrderUpdate.setCheckedOutDate(java.sql.Date.valueOf(checkedOutUpdated));
		accommodationOrderUpdate.setTotalPrice(20000);
		accommodationOrderUpdate.setRecordAccommodationStatus("結案");
		
		AccommodationOrder update = accommodationOrderDAO.update(accommodationOrderUpdate);
		System.out.println("修改資料為" + update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = accommodationOrderDAO.delete(8);
		System.out.println("刪除資料" + delete);
	}
}
