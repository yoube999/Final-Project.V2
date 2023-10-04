package tw.com.eeit168.helpdesk.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.helpdesk.model.HelpDeskBean;
import tw.com.eeit168.helpdesk.service.HelpDeskUpdateRecordService;
import tw.com.eeit168.products.RecordBean;

@RestController
@RequestMapping("/eeit168/helpdeskupdaterecord")

public class HelpDeskUpdateRecordController {

	@Autowired
	private HelpDeskUpdateRecordService helpDeskUpdateRecordService;

	/**
	 * 更新會員主訂單審查狀態及其子訂單狀態
	 * 
	 */
	@PutMapping("/helpdeskupdaterecord")
	public ResponseEntity<String> updateRecord(@RequestBody(required = false) String json) {
		try {

			// 判斷前端送的Header是否為JSON，需再加上跳轉錯誤頁面
			if (json == null || json.isEmpty()) {
				return ResponseEntity.badRequest().body("請提供有效的JSON數據");
			}

			JSONObject updateInfo = new JSONObject(json);

			// 檢查record_id和newStatus是否存在並且不為null
			Integer record_id = updateInfo.has("record_id") ? updateInfo.getInt("record_id") : null;
			String newStatus = updateInfo.has("newStatus") ? updateInfo.getString("newStatus") : null;

			if (record_id == null || newStatus == null) {
				return ResponseEntity.badRequest().body("程式送出格式異常，請聯絡IT人員");
			}

			boolean update = helpDeskUpdateRecordService.updateRecord(json);

			// 判斷是否審核訂單成功
			if (update == true) {
				return ResponseEntity.ok("審核訂單成功");
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("審核訂單失敗，請確認送出訂單編號是否正確");
			}

		} catch (JSONException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("無效的JSON格式");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("更新過程中出現異常，請聯絡IT人員");
		}
	}

}
