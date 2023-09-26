package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tw.com.eeit168.products.accommodation.dao.AccommodationPriceDAO;
import tw.com.eeit168.products.accommodation.model.AccommodationPrice;

@SpringBootTest
//@Transactional
public class AccommodationPriceDAOHibernateTests {
	@Autowired
	private AccommodationPriceDAO accommodationPriceDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	@Test
	public void testSelect() {
		AccommodationPrice select = accommodationPriceDAO.select(1);
		System.out.println("select=" + select);
	}
	
//	@Test
	public void testSelectAll() {
		List<AccommodationPrice> selectAll = accommodationPriceDAO.selectAll();
		System.out.println("selectAll" +selectAll);
	}
	
//	@Test
	public void testInsert() {
		AccommodationPrice accommodationPriceInsert = new AccommodationPrice();
		accommodationPriceInsert.setAccommodationId(10);
		accommodationPriceInsert.setRoomTypeId(30);
		accommodationPriceInsert.setWeekdayPrice(150);
		accommodationPriceInsert.setWeekendPrice(200);
		
		AccommodationPrice result = accommodationPriceDAO.insert(accommodationPriceInsert);
		System.out.println("新增資料為" + result);
	}
	
//	@Test
	public void testUpdate() {
	AccommodationPrice accommodationPriceUpdate = new AccommodationPrice();
	accommodationPriceUpdate.setPriceId(32);
	accommodationPriceUpdate.setAccommodationId(10);
	accommodationPriceUpdate.setRoomTypeId(30);
	accommodationPriceUpdate.setWeekdayPrice(2000);
	accommodationPriceUpdate.setWeekendPrice(5000);
	
	AccommodationPrice update = accommodationPriceDAO.update(accommodationPriceUpdate);
	System.out.println("修改資料為" + update);
	
	}
	
	@Test
	public void testDelete() {
		boolean delete = accommodationPriceDAO.delete(32);
		System.out.println("刪除資料" + delete);
	}
}
