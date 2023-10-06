package tw.com.eeit168.products.attraction.service;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.eeit168.products.attraction.model.AttractionBean;

@SpringBootTest
public class AttractionRepositoryServiceTests {

	@Autowired
	private AttractionRepositoryService attractionRepositoryService;
	
//	@Test
	public void testFindById() {
		AttractionBean findById = attractionRepositoryService.findById(2);
		System.out.println(findById);
	}
	
//	@Test
	public void testFindAll() {
		List<AttractionBean> findAll = attractionRepositoryService.findAll();
		System.out.println(findAll);
	}
	
//	@Test
	public void testCreate() {
		JSONObject object = new JSONObject()
				.put("attractionsName", "墾丁國家公園")
				.put("attractionsAddress", "屏東縣")
				.put("descriptions", "國家公園")
				.put("openTime", "0800")
				.put("closeTime", "1700")
				.put("contactNumber", "0936271515")
				.put("timesPurchased", 702)
				.put("productStatus", true);
		AttractionBean insert = attractionRepositoryService.create(object.toString());
		System.out.println(insert);
				
	}
	
//	@Test
	public void testModify() {
		JSONObject object = new JSONObject()
				.put("attractionsId", 12)
				.put("attractionsName", "墾丁國家公園")
				.put("attractionsAddress", "屏東縣")
				.put("descriptions", "國家公園")
				.put("openTime", "0800")
				.put("closeTime", "1700")
				.put("contactNumber", "0936271515")
				.put("timesPurchased", 808)
				.put("productStatus", false);
		AttractionBean update = attractionRepositoryService.modify(object.toString());
		System.out.println(update);
		
	}
	
//	@Test
	public void testRemove() {
		boolean delete = attractionRepositoryService.remove(12);
		System.out.println(delete);
	}
	
}
