package tw.com.eeit168.helpdesk.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.helpdesk.service.HelpDeskUpdateRecordService;
import tw.com.eeit168.products.RecordBean;
import tw.com.eeit168.products.restaurant.util.DatetimeConverter;

@RestController
@RequestMapping("/eeit168/helpdeskupdaterecord")

public class HelpDeskUpdateRecordController {
	
	@Autowired
	private HelpDeskUpdateRecordService helpDeskUpdateRecordService;
	
//	@GetMapping("/selectById/{record_id}")
//	public String selectById(@PathVariable(name = "record_id") Integer record_id) {
//		JSONObject responseJson = new JSONObject();
//		JSONArray array = new JSONArray();
//		RecordBean record = helpDeskUpdateRecordService.selectById(record_id);
//		if (record != null) {
//
//			// 將時間格式轉為字串
//			String returnDate = DatetimeConverter.toString(record.getReturn_date(), "yyyy-MM-dd HH:mm:ss");
//			
//			// 將得到的資料放入array陣列中
//			JSONObject item = new JSONObject()
//					.put("return_title", record.getReturn_title())
//					.put("return_description", record.getReturn_description())
//					.put("return_date", returnDate);
//			
//			array = array.put(item);
//
//		}
//		responseJson.put("list", array);
//		return responseJson.toString();
//	}

}
