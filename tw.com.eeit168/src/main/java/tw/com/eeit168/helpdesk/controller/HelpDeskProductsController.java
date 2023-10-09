package tw.com.eeit168.helpdesk.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.annotation.GetExchange;

import tw.com.eeit168.helpdesk.service.HelpDeskProductsService;
import tw.com.eeit168.helpdesk.util.CsvFieldMapping;

@RestController
@RequestMapping("/eeit168/helpdeskproducts")

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskProductsController {

	@Autowired
	private HelpDeskProductsService helpDeskProductsService;

	@Autowired
	CsvFieldMapping csvFieldMapping;
	private

	// 宣告獲取設置的暫存檔案目錄路徑
	String tempDirectory = "C:/Final-Project-workspace/temp/directory/";

	/**
	 * 讀取餐廳csv檔案，透過api轉成json格式再Insert to DB
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

			// 創建 CsvFieldMapping 並設置字段映射
			List<String> fieldNames = Arrays.asList("restaurant_name", "restaurant_address", "contact_number", "price",
					"times_purchased", "descriptions");
			CsvFieldMapping fieldMapping = new CsvFieldMapping(fieldNames);

			try {
				String file = csvFieldMapping.convertCsvToJson(csvFile, fieldMapping);

				boolean insert = helpDeskProductsService.insertRestaurantProducts(file);

				// 刪除暫存檔案
				csvFile.getInputStream().close(); // 關閉檔案流
				// 將暫存檔案從伺服器暫存路徑移至指定路徑
				csvFile.transferTo(
						new File("C:/Final-Project-workspace/temp/directory/" + csvFile.getOriginalFilename()));

				// 刪除指定路徑的暫存檔案
				File fileToDelete = new File(
						"C:/Final-Project-workspace/temp/directory/" + csvFile.getOriginalFilename());
				fileToDelete.delete();

				if (insert) {
					// 若前端收到true時需顯示成功訊息
					responseJson.put("message", "新增商品成功");
					responseJson.put("success", true);
				} else {
					// 若前端收到false時需顯示錯誤訊息
					responseJson.put("message", "新增商品失敗，請確認商品內容是否符合格式");
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

	/**
	 * 讀取住宿csv檔案，透過api轉成json格式再Insert to DB
	 * 
	 * 
	 */
	@PostMapping("/createProducts/accommodation")
	public String createAccommodationProducts(@RequestParam("csvFile") MultipartFile csvFile) {
		JSONObject responseJson = new JSONObject();

		if (!csvFile.isEmpty()) {

			// 檢查目錄是否存在，如果不存在，則創建它
			File directory = new File(tempDirectory);
			if (!directory.exists()) {
				directory.mkdirs(); // 創建目錄及其父目錄（如果不存在）
			}

			// 創建 CsvFieldMapping 並設置字段映射
			List<String> fieldNames = Arrays.asList("accommodation_name", "accommodation_address", "contact_number",
					"times_purchased", "descriptions");
			CsvFieldMapping fieldMapping = new CsvFieldMapping(fieldNames);

			try {
				String file = csvFieldMapping.convertCsvToJson(csvFile, fieldMapping);

				boolean insert = helpDeskProductsService.insertAccommodationProducts(file);

				// 刪除暫存檔案
				csvFile.getInputStream().close(); // 關閉檔案流
				// 將暫存檔案從伺服器暫存路徑移至指定路徑
				csvFile.transferTo(
						new File("C:/Final-Project-workspace/temp/directory/" + csvFile.getOriginalFilename()));

				// 刪除指定路徑的暫存檔案
				File fileToDelete = new File(
						"C:/Final-Project-workspace/temp/directory/" + csvFile.getOriginalFilename());
				fileToDelete.delete();

				if (insert) {
					// 若前端收到true時需顯示成功訊息
					responseJson.put("message", "新增商品成功");
					responseJson.put("success", true);
				} else {
					// 若前端收到false時需顯示錯誤訊息
					responseJson.put("message", "新增商品失敗，請確認商品內容是否符合格式");
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

	/**
	 * 讀取景點csv檔案，透過api轉成json格式再Insert to DB
	 * 
	 * 
	 */
	@PostMapping("/createProducts/attraction")
	public String createAttractionProducts(@RequestParam("csvFile") MultipartFile csvFile) {
		JSONObject responseJson = new JSONObject();

		if (!csvFile.isEmpty()) {

			// 檢查目錄是否存在，如果不存在，則創建它
			File directory = new File(tempDirectory);
			if (!directory.exists()) {
				directory.mkdirs(); // 創建目錄及其父目錄（如果不存在）
			}

			// 創建 CsvFieldMapping 並設置字段映射
			List<String> fieldNames = Arrays.asList("attractions_name", "attractions_address", "descriptions",
					"open_time", "close_time", "contact_number", "times_purchased");
			CsvFieldMapping fieldMapping = new CsvFieldMapping(fieldNames);

			try {
				String file = csvFieldMapping.convertCsvToJson(csvFile, fieldMapping);

				boolean insert = helpDeskProductsService.insertAttractionProducts(file);

				// 刪除暫存檔案
				csvFile.getInputStream().close(); // 關閉檔案流
				// 將暫存檔案從伺服器暫存路徑移至指定路徑
				csvFile.transferTo(
						new File("C:/Final-Project-workspace/temp/directory/" + csvFile.getOriginalFilename()));

				// 刪除指定路徑的暫存檔案
				File fileToDelete = new File(
						"C:/Final-Project-workspace/temp/directory/" + csvFile.getOriginalFilename());
				fileToDelete.delete();

				if (insert) {
					// 若前端收到true時需顯示成功訊息
					responseJson.put("message", "新增商品成功");
					responseJson.put("success", true);
				} else {
					// 若前端收到false時需顯示錯誤訊息
					responseJson.put("message", "新增商品失敗，請確認商品內容是否符合格式");
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

	/**
	 * 更新餐廳商品
	 * 
	 */
	@PutMapping("/modifyProduct/restaurant")
	public ResponseEntity<String> modifyRestaurantProduct(@RequestBody(required = false) String json) {

		// 判斷前端送的Header是否為JSON，需再加上跳轉錯誤頁面
		if (json == null || json.isEmpty()) {
			return ResponseEntity.badRequest().body("請提供有效的JSON數據");
		}

		boolean result = helpDeskProductsService.modifyRestaurantProduct(json);

		if (result) {
			return ResponseEntity.ok("修改商品成功");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("修改商品失敗，請確認送出編輯是否正確");
		}

	}

	/**
	 * 更新飯店商品
	 * 
	 */
	@PutMapping("/modifyProduct/accommodation")
	public ResponseEntity<String> modifyAccommodationProduct(@RequestBody(required = false) String json) {

		// 判斷前端送的Header是否為JSON，需再加上跳轉錯誤頁面
		if (json == null || json.isEmpty()) {
			return ResponseEntity.badRequest().body("請提供有效的JSON數據");
		}

		boolean result = helpDeskProductsService.modifyAccommodationProduct(json);

		if (result) {
			return ResponseEntity.ok("修改商品成功");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("修改商品失敗，請確認送出編輯是否正確");
		}

	}

	/**
	 * 更新景點商品
	 * 
	 */
	@PutMapping("/modifyProduct/attraction")
	public ResponseEntity<String> modifyAttractionProduct(@RequestBody(required = false) String json) {

		// 判斷前端送的Header是否為JSON，需再加上跳轉錯誤頁面
		if (json == null || json.isEmpty()) {
			return ResponseEntity.badRequest().body("請提供有效的JSON數據");
		}

		boolean result = helpDeskProductsService.modifyAttractionProduct(json);

		if (result) {
			return ResponseEntity.ok("修改商品成功");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("修改商品失敗，請確認送出編輯是否正確");
		}

	}

	/**
	 * 下架餐廳商品，變更商品productStatus
	 * 
	 * 
	 */
	@GetMapping("/removeProduct/restaurant/{restaurantId}")
	public String removeRestaurantProduct(@PathVariable(name = "restaurantId") Integer restaurantId) {
		JSONObject responseJson = new JSONObject();

		boolean result = helpDeskProductsService.removeRestaurantProduct(restaurantId);

		if (result) {
			responseJson.put("message", "下架商品成功");
			responseJson.put("success", "true");
			return responseJson.toString();
		} else {
			responseJson.put("message", "下架商品發生錯誤，請聯絡IT人員");
			responseJson.put("success", "false");
			return responseJson.toString();
		}

	}
	
	/**
	 * 下架飯店商品，變更商品productStatus
	 * 
	 * 
	 */
	@GetMapping("/removeProduct/accommodation/{productId}")
	public String removeAccommodationProduct(@PathVariable(name = "productId") Integer productId) {
		JSONObject responseJson = new JSONObject();

		boolean result = helpDeskProductsService.removeAccommodationProduct(productId);

		if (result) {
			responseJson.put("message", "下架商品成功");
			responseJson.put("success", "true");
			return responseJson.toString();
		} else {
			responseJson.put("message", "下架商品發生錯誤，請聯絡IT人員");
			responseJson.put("success", "false");
			return responseJson.toString();
		}

	}

	
	/**
	 * 下架飯店商品，變更商品productStatus
	 * 
	 * 
	 */
	@GetMapping("/removeProduct/attractions/{attractionsId}")
	public String removeAttractionsProduct(@PathVariable(name = "attractionsId") Integer attractionsId) {
		JSONObject responseJson = new JSONObject();

		boolean result = helpDeskProductsService.removeAccommodationProduct(attractionsId);

		if (result) {
			responseJson.put("message", "下架商品成功");
			responseJson.put("success", "true");
			return responseJson.toString();
		} else {
			responseJson.put("message", "下架商品發生錯誤，請聯絡IT人員");
			responseJson.put("success", "false");
			return responseJson.toString();
		}

	}
}
