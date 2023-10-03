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
				JSONObject item = new JSONObject()
						.put("record_id", helpDeskRecord.getRecord_id())
						.put("username", helpDeskRecord.getUsername())
						.put("return_title", helpDeskRecord.getReturn_title())
						.put("member_profile_id", helpDeskRecord.getMember_profile_id());
						
				array = array.put(item);
			}
		}
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
			
			String birthday = DatetimeConverter.toString(member.getBirthday(), "yyyy-MM-dd HH:mm:ss");
			String registration_date = DatetimeConverter.toString(member.getRegistration_date(), "yyyy-MM-dd HH:mm:ss");
			JSONObject item = new JSONObject()
					.put("username", member.getUsername())
					.put("birthday", birthday)
					.put("gender", member.getGender())
					.put("phone_number", member.getPhone_number())
					.put("registration_date", registration_date);
					
			array = array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
}
