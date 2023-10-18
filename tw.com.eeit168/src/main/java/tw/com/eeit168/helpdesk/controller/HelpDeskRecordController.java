package tw.com.eeit168.helpdesk.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.helpdesk.model.HelpDeskRecordBean;
import tw.com.eeit168.helpdesk.service.HelpDeskRecordService;
import tw.com.eeit168.member.model.MemberProfileBean;
import tw.com.eeit168.products.RecordBean;
import tw.com.eeit168.products.restaurant.util.DatetimeConverter;

@RestController
@RequestMapping("/eeit168/helpdeskrecord")

public class HelpDeskRecordController {
	
	@Autowired
	private HelpDeskRecordService helpDeskRecordService;
	
	/**
	 * 前端載入審核訂單面時，審核中訂單查詢API
	 * 
	 * 
	 */
	@PostMapping("/selectRecordByStatus")
	public String selectRecordByStatus(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		List<HelpDeskRecordBean> helpDeskRecords = helpDeskRecordService.selectRecordByStatus(json);
		JSONArray array = new JSONArray();
		if(helpDeskRecords != null && !helpDeskRecords.isEmpty()) {
			for(HelpDeskRecordBean helpDeskRecord : helpDeskRecords) {
				
				// 將時間格式轉為字串
				String returnDate = DatetimeConverter.toString(helpDeskRecord.getReturn_date(), "yyyy-MM-dd HH:mm:ss");
				
				// 將迴圈得到的資料放入array陣列中
				JSONObject item = new JSONObject()
						.put("record_id", helpDeskRecord.getRecord_id())
						.put("username", helpDeskRecord.getUsername())
						.put("return_title", helpDeskRecord.getReturn_title())
						.put("member_profile_id", helpDeskRecord.getMember_profile_id())
						.put("return_date", returnDate);
						
				array = array.put(item);
			}
		}
		// 將array陣列放入JSON物件中，並回傳字串
		responseJson.put("list", array);
		return responseJson.toString();
	}

	
	/**
	 * 取得特定會員資料
	 * 
	 * 
	 */
	@GetMapping("/selectUserById/{member_profile_id}")
	public String selectUserById(@PathVariable(name = "member_profile_id") Integer member_profile_id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		MemberProfileBean member = helpDeskRecordService.selectUserById(member_profile_id);
		if(member != null) {
			
			// 將時間格式轉為字串
			String birthday = DatetimeConverter.toString(member.getBirthday(), "yyyy-MM-dd");
			String registration_date = DatetimeConverter.toString(member.getRegistration_date(), "yyyy-MM-dd");
			
			// 將得到的資料放入array陣列中
			JSONObject item = new JSONObject()
					.put("username", member.getUsername())
					.put("birthday", birthday)
					.put("gender", member.getGender())
					.put("phone_number", member.getPhone_number())
					.put("registration_date", registration_date);
					
			array = array.put(item);
		}
		// 將array陣列放入JSON物件中，並回傳字串
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	
	/**
	 * 取得特定訂單資料
	 * 
	 * 
	 */
	@GetMapping("/selectRecordById/{record_id}")
	public String selectRecordById(@PathVariable(name = "record_id") Integer record_id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		RecordBean record = helpDeskRecordService.selectRecordById(record_id);
		if(record != null) {
			
			// 將時間格式轉為字串
			String returnDate = DatetimeConverter.toString(record.getReturnDate(), "yyyy-MM-dd HH:mm:ss");
			
			// 將得到的資料放入array陣列中
			JSONObject item = new JSONObject()
					.put("return_title", record.getReturnTitle())
					.put("return_description", record.getReturnDescription())
					.put("return_date", returnDate);
			
			array = array.put(item);
		}
		// 將array陣列放入JSON物件中，並回傳字串
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	
	
	
	
}
