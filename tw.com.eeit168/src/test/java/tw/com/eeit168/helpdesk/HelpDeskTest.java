package tw.com.eeit168.helpdesk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.helpdesk.service.HelpDeskProductsService;

@SpringBootTest
@Transactional
public class HelpDeskTest {

	@Autowired
	private HelpDeskProductsService helpDeskProductsService;

	@Test
	public void testConvertCsvToJson() {
		String csvFilePath = "C://Users//User//Downloads//期末餐館測試資料.csv"; // 將路徑替換為您的CSV文件路徑
		String jsonResult = helpDeskProductsService.convertCsvToJson(csvFilePath);

		System.out.println(jsonResult);

	}

}
