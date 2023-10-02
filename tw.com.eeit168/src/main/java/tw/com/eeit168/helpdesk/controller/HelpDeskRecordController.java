package tw.com.eeit168.helpdesk.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.helpdesk.model.HelpDeskRecordBean;
import tw.com.eeit168.helpdesk.service.HelpDeskRecordService;

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
						.put("username", helpDeskRecord.getUsername())
						.put("return_title", helpDeskRecord.getReturn_title());
						
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

}
