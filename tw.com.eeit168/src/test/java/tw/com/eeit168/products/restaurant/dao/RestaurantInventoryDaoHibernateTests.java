package tw.com.eeit168.products.restaurant.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.restaurant.model.RestaurantInventoryBean;

@SpringBootTest
@Transactional
public class RestaurantInventoryDaoHibernateTests {
	
	@Autowired
	private RestaurantInventoryDAO restaurantInventoryDAO;
	
//	@Test
	public void testSelect() {
		RestaurantInventoryBean select = restaurantInventoryDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<RestaurantInventoryBean> selectAll = restaurantInventoryDAO.selectAll();
		System.out.println(selectAll);
	}
	
//	@Test
	public void testInsert() {
		RestaurantInventoryBean bean = new RestaurantInventoryBean();
		bean.setRestaurant_id(1);
		LocalDate date = LocalDate.of(2023, 8, 20);
		bean.setAvailability_date(java.sql.Date.valueOf(date));
		bean.setLunch(50);
		bean.setDinner(60);
		RestaurantInventoryBean insert = restaurantInventoryDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		RestaurantInventoryBean bean = new RestaurantInventoryBean();
		bean.setRestaurant_Inventory_id(10);
		bean.setRestaurant_id(10);
		bean.setAvailability_date(null);
		bean.setLunch(20);
		bean.setDinner(30);
		RestaurantInventoryBean update = restaurantInventoryDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = restaurantInventoryDAO.delete(22);
		System.out.println(delete);
	}
	
}
