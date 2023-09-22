package tw.com.eeit168.products.restaurant.dao;

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
	
	@Test
	public void testSelect() {
		RestaurantBean select = restaurantDAO.select(1);
		System.out.println(select);
	}
}
