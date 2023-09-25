package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;

@SpringBootTest
@Transactional
public class RestaurantDaoHibernateTests {
	
	@Autowired
	private RestaurantDAO restaurantDAO;
	
//	@Test
	public void testSelect() {
		RestaurantBean select = restaurantDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<RestaurantBean> selectAll = restaurantDAO.selectAll();
		System.out.println(selectAll);
	}
	
//	@Test
	public void testInsert() {
		RestaurantBean bean = new RestaurantBean();
		bean.setRestaurant_id(11);
		bean.setRestaurant_name("aaa");
		bean.setRestaurant_address("hefdkghsk");
		bean.setContact_number("2345567");
		bean.setPrice(699);
		bean.setRating("4.6");
		bean.setTimes_purchased(346);
		RestaurantBean insert = restaurantDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		RestaurantBean bean = new RestaurantBean();
		bean.setRestaurant_id(10);
		bean.setRestaurant_name("鼎王");
		bean.setRestaurant_address("grbrebr");
		bean.setContact_number("2345567");
		bean.setPrice(599);
		bean.setRating("4.6");
		bean.setTimes_purchased(346);
		RestaurantBean update = restaurantDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = restaurantDAO.delete(10);
		System.out.println(delete);
	}
	
}
