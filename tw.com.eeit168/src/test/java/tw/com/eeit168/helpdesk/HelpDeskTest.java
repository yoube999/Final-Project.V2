package tw.com.eeit168.helpdesk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.helpdesk.service.HelpDeskService;

@SpringBootTest
@Transactional
public class HelpDeskTest {

	@Autowired
	private HelpDeskService helpDeskService;

	@Test
	public void testConvertCsvToJson() {
		String csvFilePath = "C://Users//User//Downloads//餐廳餐館.csv"; // 將路徑替換為您的CSV文件路徑
		String jsonResult = helpDeskService.convertCsvToJson(csvFilePath);

		System.out.println(jsonResult);

	}

}
