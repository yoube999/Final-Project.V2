package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.restaurant.model.RestaurantPictureBean;

@SpringBootTest
@Transactional
public class RestaurantPictureDaoHibernateTests {
	
	@Autowired
	private RestaurantPictureDAO restaurantPictureDAO;
	
//	@Test
	public void testSelect() {
		RestaurantPictureBean select = restaurantPictureDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<RestaurantPictureBean> selectAll = restaurantPictureDAO.selectAll();
		System.out.println(selectAll);
	}
	
//	@Test
	public void testInsert() {
		RestaurantPictureBean bean = new RestaurantPictureBean();
		bean.setRestaurant_pictures_id(11);
		bean.setRestaurant_id(1);
		bean.setUrl_image("lnnldfreg9erh9");
		RestaurantPictureBean insert = restaurantPictureDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		RestaurantPictureBean bean = new RestaurantPictureBean();
		bean.setRestaurant_pictures_id(11);
		bean.setRestaurant_id(10);
		bean.setUrl_image("ojgojgjmo");
		RestaurantPictureBean update = restaurantPictureDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = restaurantPictureDAO.delete(11);
		System.out.println(delete);
	}
	
}
