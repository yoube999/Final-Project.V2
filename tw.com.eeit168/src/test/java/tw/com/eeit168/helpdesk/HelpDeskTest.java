package tw.com.eeit168.helpdesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.helpdesk.service.HelpDeskProductsService;
import tw.com.eeit168.helpdesk.service.HelpDeskService;

@SpringBootTest
@Transactional
public class HelpDeskTest {

	@Autowired
	private HelpDeskProductsService helpDeskProductsService;
	
	@Autowired
	private HelpDeskService helpDeskService;
	

//	@Test
//	public void testConvertCsvToJson() {
//		String content = "restaurant_name,restaurant_address,contact_number,price,times_purchased,descriptions\n"
//                + "Restaurant1,Address1,123456,100,5,Description1\n"
//                + "Restaurant2,Address2,789012,200,10,Description2";
//        MockMultipartFile csvFile = new MockMultipartFile("csvFile", "test.csv", "text/csv", content.getBytes());
//
//        String jsonResult = helpDeskProductsService.convertCsvToJson(csvFile);
//        
//		System.out.println(jsonResult);
//
//	}
//
//	@Test
//	public void testinsertRestaurantProducts(){
//		
//		String content = "restaurant_name,restaurant_address,contact_number,price,times_purchased,descriptions\n"
//                + "Restaurant1,Address1,123456,100,5,Description1\n"
//                + "Restaurant2,Address2,789012,200,10,Description2";
//        MockMultipartFile csvFile = new MockMultipartFile("csvFile", "test.csv", "text/csv", content.getBytes());
//
//        String jsonResult = helpDeskProductsService.convertCsvToJson(csvFile);
//		String restaurantData = jsonResult;
//		System.out.println(restaurantData);
//		
//		boolean result = helpDeskProductsService.insertRestaurantProducts(restaurantData);
//		System.out.println(result);
//	}
	
    
	
	
}
