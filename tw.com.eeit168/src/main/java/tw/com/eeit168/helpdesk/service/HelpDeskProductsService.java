package tw.com.eeit168.helpdesk.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import tw.com.eeit168.products.restaurant.model.RestaurantBean;
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
	public String convertCsvToJson(MultipartFile csvFile) {

		try {

			// 創建CSV讀取器
			// 將MultipartFile轉換為Reader
			Reader reader = new InputStreamReader(csvFile.getInputStream());
			CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();

			// 開始解析CSV數據
			String[] nextRecord;
			JSONArray array = new JSONArray();

			while ((nextRecord = csvReader.readNext()) != null) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("restaurant_name", nextRecord[0]);
					obj.put("restaurant_address", nextRecord[1]);
					obj.put("contact_number", nextRecord[2]);
					obj.put("price", Integer.parseInt(nextRecord[3]));
					obj.put("times_purchased", Integer.parseInt(nextRecord[4]));
					obj.put("descriptions", nextRecord[5]);

					array.put(obj);
				} catch (ArrayIndexOutOfBoundsException e) {
					// 上傳csv檔案內容格式錯誤
					e.printStackTrace();
					return null; // 返回錯誤消息給客戶端
				}

			}

			// 將JSON數組轉換為JSON字符串
			String jsonText = array.toString();

			return jsonText;

		} catch (IOException e) {
			// 需改成跳轉至錯誤頁面
			e.printStackTrace();
		} catch (CsvValidationException e) {
			// 需改成跳轉至錯誤頁面
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// 需改成跳轉至錯誤頁面
			e.printStackTrace();
		} catch (JSONException e) {
			// 需改成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 新增餐廳商品
	 * 
	 * @return insert成功回傳true，反之false
	 */
	public boolean insertRestaurantProducts(String restaurantData) {

		if (restaurantData != null) {
			try {
				JSONArray array = new JSONArray(restaurantData);

				// 這個for迴圈的目的是遍歷JSON數組中的每個JSON對象。在每次迭代中，它從JSON數組中獲取一個JSON對象，然後將該JSON對象轉換為您的自定義Java類型
				// RestaurantBean。
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);

					// 將JSON數據轉換為RestaurantBean對象
					RestaurantBean restaurantBean = new RestaurantBean();
					restaurantBean.setRestaurantName(obj.getString("restaurant_name"));
					restaurantBean.setRestaurantAddress(obj.getString("restaurant_address"));
					restaurantBean.setContactNumber(obj.getString("contact_number"));
					restaurantBean.setPrice(obj.getInt("price"));
					restaurantBean.setTimesPurchased(obj.getInt("times_purchased"));
					restaurantBean.setDescriptions(obj.getString("descriptions"));

					// 將RestaurantBean對象保存到數據庫
					restaurantRepository.save(restaurantBean);
				}

				return true;

			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}

	}

}
