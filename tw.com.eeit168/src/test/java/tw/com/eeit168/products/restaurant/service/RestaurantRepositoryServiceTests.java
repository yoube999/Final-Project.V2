package tw.com.eeit168.products.restaurant.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;

@SpringBootTest
public class RestaurantRepositoryServiceTests {

	@Autowired
	private RestaurantRepositoryService restaurantRepositoryService;
	
//	@Test
	public void findById() {
		RestaurantBean findById = restaurantRepositoryService.findById(2);
		System.out.println(findById);
	}
	
//	@Test
	public void findAll() {
		List<RestaurantBean> findAll = restaurantRepositoryService.findAll();
		System.out.println(findAll);
	}
	
//	@Test
	public void testCreate() {
		JSONObject object = new JSONObject()
				.put("restaurantName", "鼎王")
				.put("restaurantAddress", "桃園市")
				.put("descriptions", "鼎王集團")
				.put("contactNumber", "0988172635")
				.put("price", 1599)
				.put("timesPurchased", 871)
				.put("productStatus", true);
		RestaurantBean insert = restaurantRepositoryService.create(object.toString());
		System.out.println(insert);
	}
	
//	@Test
	public void testModify() {
		JSONObject object = new JSONObject()
				.put("restaurantId", 453)
				.put("restaurantName", "鼎王")
				.put("restaurantAddress", "台南市")
				.put("descriptions", "鼎王集團")
				.put("contactNumber", "0988172635")
				.put("price", 1299)
				.put("timesPurchased", 871)
				.put("productStatus", false);
		RestaurantBean update = restaurantRepositoryService.modify(object.toString());
		System.out.println(update);
	}
	
//	@Test
	public void testRemove() {
		boolean delete = restaurantRepositoryService.remove(12);
		System.out.println(delete);
	}
	
}