package tw.com.eeit168.products;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecordRepositorySerivceTests {
	
	@Autowired
	private RecordRepositorySerivce recordRepositorySerivce;
	
	@Test
	public void testCreate() {
		JSONObject object = new JSONObject()
				.put("memberProfileId", 1026)
				.put("recordStatus", "結單");
		RecordBean result = recordRepositorySerivce.create(object.toString());
		System.out.println(result);
	}

}
