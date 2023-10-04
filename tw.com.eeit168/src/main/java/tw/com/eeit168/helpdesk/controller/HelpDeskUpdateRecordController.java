package tw.com.eeit168.helpdesk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
	@PutMapping("/helpdeskupdaterecord/{record_id}")
	public boolean updateRecord(@PathVariable Integer record_id, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		boolean update = helpDeskUpdateRecordService.updateRecord(json);

		return update;
	}

}
