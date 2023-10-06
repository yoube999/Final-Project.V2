package tw.com.eeit168.helpdesk.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

@Service
@Transactional
// 用於表示 CSV 檔案中的字段映射
public class CsvFieldMapping {
	// 這是 CsvFieldMapping 類別的私有成員變數，它是一個 List 類型，用於存儲 CSV 檔案中的字段名稱。
	private List<String> fieldNames;

	//這個建構函式接受一個 List<String> 參數，該參數包含 CSV 檔案中的字段名稱。
	//建構函式將這些字段名稱初始化並設置到 fieldNames 成員變數中。
    public CsvFieldMapping(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    // 用於根據索引值（index）獲取對應的字段名稱。它接受一個整數索引值作為參數，並檢查該索引值是否在有效範圍內（即大於等於 0 且小於字段名稱列表的大小）
    // 如果索引值有效，則返回對應的字段名稱；否則返回 null。
    public String getFieldName(int index) {
        if (index >= 0 && index < fieldNames.size()) {
            return fieldNames.get(index);
        }
        return null;
    }
    // 用於獲取 CSV 檔案中的字段數量。它簡單地返回 fieldNames 列表的大小，即字段的總數。
    public int getFieldCount() {
        return fieldNames.size();
    }
    
    /**
	 * 將csv檔案轉成json字串
	 * 
	 * 
	 */
    public String convertCsvToJson(MultipartFile csvFile, CsvFieldMapping fieldMapping) {

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
					for (int i = 0; i < fieldMapping.getFieldCount(); i++) {
						String fieldName = fieldMapping.getFieldName(i);
						obj.put(fieldName, nextRecord[i]);
					}
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
}
