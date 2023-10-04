package tw.com.eeit168.helpdesk.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import tw.com.eeit168.products.restaurant.repository.RestaurantRepository;

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskProductsService {

	// 調用Restaurant的JPARepository
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	
	
	/**
	 * 將csv檔案轉成json字串
	 * 
	 * 
	 */
	public String convertCsvToJson(String csvFilePath) {

		try {

			// 創建CSV讀取器
			FileReader reader = new FileReader(csvFilePath);
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

			// 開始解析CSV數據
			String[] nextRecord;
			JSONObject jsonObject = new JSONObject();
			int recordCount = 0;

			while ((nextRecord = csvReader.readNext()) != null) {
				String recordKey = "record" + recordCount;
				jsonObject.put(recordKey, nextRecord);
				recordCount++;
			}
			
			// 將JSON對象轉換為JSON字符串
            String jsonText = jsonObject.toString();

            return jsonText;
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 新增餐廳商品
	 * 
	 * @return 全部更新成功回傳true，反之false
	 */
	public boolean insertRestaurantProducts(String jsonText) {
		
		
		
		
		return false;
	}
	
	
	
	
}
