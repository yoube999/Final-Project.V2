package tw.com.eeit168.products.restaurant.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.service.RestaurantService;

@SpringBootTest
@Transactional
public class RestaurantServiceTests {

	@Autowired
	private RestaurantService restaurantService;
	
//	@Test
	public void testSelectName() {
		List<RestaurantBean> selectName = restaurantService.selectName("夏慕尼");
		System.out.println(selectName);
	}
	
//	@Test
	public void testSelect() {
		List<RestaurantBean> select = restaurantService.select(4);
		System.out.println(select);
	}
	
//	@Test
	public void selectList() {
		List<RestaurantBean> selectList = restaurantService.selectList();
		System.out.println(selectList);
	}
}
