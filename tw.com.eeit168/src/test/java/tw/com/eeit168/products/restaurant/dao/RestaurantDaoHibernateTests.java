package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;

@SpringBootTest
@Transactional //執行完以後rollback
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
		bean.setRestaurantName("aaa");
		bean.setRestaurantAddress("hefdkghsk");
		bean.setDescriptions("evevoerjovjpo");
		bean.setContactNumber("2345567");
		bean.setPrice(699);
		bean.setTimesPurchased(346);
		RestaurantBean insert = restaurantDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		RestaurantBean bean = new RestaurantBean();
		bean.setRestaurantId(11);
		bean.setRestaurantName("鼎王");
		bean.setRestaurantAddress("grbrebr");
		bean.setDescriptions("rpoeitpoerpo");
		bean.setContactNumber("2345567");
		bean.setPrice(599);
		bean.setTimesPurchased(346);
		RestaurantBean update = restaurantDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = restaurantDAO.delete(11);
		System.out.println(delete);
	}
	
}
