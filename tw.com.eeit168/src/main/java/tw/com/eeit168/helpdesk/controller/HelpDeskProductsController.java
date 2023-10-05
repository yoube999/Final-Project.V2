package tw.com.eeit168.helpdesk.controller;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.com.eeit168.helpdesk.service.HelpDeskProductsService;

@RestController
@RequestMapping("/eeit168/helpdeskproducts")

public class HelpDeskProductsController {

	@Autowired
	private HelpDeskProductsService helpDeskProductsService;

	// 宣告獲取設置的暫存檔案目錄路徑
	String tempDirectory = "C:/Final-Project-workspace/temp/directory/";

	/**
	 * 讀取csv檔案，透過api轉成json格式再Insert to DB
	 * 
	 * 
	 */
	@PostMapping("/createProducts/restaurant")
	public String createRestaurantProducts(@RequestParam("csvFile") MultipartFile csvFile) {
		JSONObject responseJson = new JSONObject();

		if (!csvFile.isEmpty()) {

			// 檢查目錄是否存在，如果不存在，則創建它
			File directory = new File(tempDirectory);
			if (!directory.exists()) {
				directory.mkdirs(); // 創建目錄及其父目錄（如果不存在）
			}

			try {
				String file = helpDeskProductsService.convertCsvToJson(csvFile);
				
				boolean insert = helpDeskProductsService.insertRestaurantProducts(file);

				// 刪除暫存檔案
				csvFile.getInputStream().close(); // 關閉檔案流
				// 將暫存檔案從伺服器暫存路徑移至指定路徑
				csvFile.transferTo(
						new File("C:/Final-Project-workspace/temp/directory/" + csvFile.getOriginalFilename())); 

				// 刪除指定路徑的暫存檔案
				File fileToDelete = new File("C:/Final-Project-workspace/temp/directory/" + csvFile.getOriginalFilename());
				fileToDelete.delete();
				
				
				if (insert) {
					// 若前端收到true時需顯示成功訊息
					responseJson.put("message", "新增商品成功");
					responseJson.put("success", true);
				} else {
					// 若前端收到false時需顯示錯誤訊息
					responseJson.put("message", "新增商品失敗");
					responseJson.put("success", false);
				}

				return responseJson.toString();

			} catch (IllegalStateException e) {
				// 需改成跳轉至錯誤頁面
				e.printStackTrace();
				responseJson.put("message", "新增商品發生錯誤，請聯絡IT人員");
				responseJson.put("success", "error");
				return responseJson.toString();
			} catch (IOException e) {
				// 需改成跳轉至錯誤頁面
				e.printStackTrace();
				responseJson.put("message", "新增商品發生錯誤，請聯絡IT人員");
				responseJson.put("success", "error");
				return responseJson.toString();
			}

		}
		responseJson.put("message", "請選擇上傳檔案");
		responseJson.put("success", "error");
		return responseJson.toString();
	}

}
