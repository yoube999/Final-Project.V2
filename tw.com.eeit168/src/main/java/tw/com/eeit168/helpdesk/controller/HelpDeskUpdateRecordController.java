package tw.com.eeit168.helpdesk.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.helpdesk.service.HelpDeskUpdateRecordService;

@RestController
@CrossOrigin
@RequestMapping("/eeit168/helpdeskupdaterecord")

public class HelpDeskUpdateRecordController {

	@Autowired
	private HelpDeskUpdateRecordService helpDeskUpdateRecordService;

	/**
	 * 更新會員主訂單審查狀態及其子訂單狀態
	 * 
	 */
	@PutMapping("/helpdeskupdaterecord")
	public String updateRecord(@RequestBody(required = false) String json) {
		try {
			JSONObject responseJson = new JSONObject();

			// 判斷前端送的Header是否為JSON，需再加上跳轉錯誤頁面
			if (json == null || json.isEmpty()) {
				// 若前端收到true時需顯示成功訊息
				responseJson.put("message", "請提供有效的JSON數據");
				responseJson.put("success", "error");
				return responseJson.toString();
			}

			JSONObject updateInfo = new JSONObject(json);

			// 檢查record_id和newStatus是否存在並且不為null
			Integer record_id = updateInfo.has("record_id") ? updateInfo.getInt("record_id") : null;
			String newStatus = updateInfo.has("newStatus") ? updateInfo.getString("newStatus") : null;

			if (record_id == null || newStatus == null) {
				responseJson.put("message", "程式送出格式異常，請聯絡IT人員");
				responseJson.put("success", "error");
				return responseJson.toString();
			}

			boolean update = helpDeskUpdateRecordService.updateRecord(json);

			// 判斷是否審核訂單成功
			if (update == true) {
				responseJson.put("message", "審核訂單成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "審核訂單失敗");
				responseJson.put("success", false);
			}

			return responseJson.toString();
		} catch (Exception e) {
			JSONObject responseJson = new JSONObject();

			responseJson.put("message", "程式送出格式異常，請聯絡IT人員");
			responseJson.put("success", "error");
			return responseJson.toString();
		}
	}

}
