package tw.com.eeit168.helpdesk;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.helpdesk.dao.HelpDeskInterFace;

@SpringBootTest
@Transactional
public class HelpDeskTest {

	@Autowired
	private HelpDeskInterFace helpDeskInterFace;
	
	@Test
	public void testConvertCsvToJson() {
        String csvFilePath = "C://Users//User//Downloads//餐廳餐館.csv"; // 將路徑替換為您的CSV文件路徑
        String jsonResult = helpDeskInterFace.convertCsvToJson(csvFilePath);

        System.out.println(jsonResult);

        // 在此添加更多斷言來驗證JSON結果是否符合您的預期
    }

}
