package tw.com.eeit168.helpdesk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.helpdesk.model.HelpDeskProcessBean;
import tw.com.eeit168.helpdesk.service.HelpDeskProcessService;

@RestController
@RequestMapping("/eeit168/helpdeskprocess")

public class HelpDeskProcessController {

	@Autowired
	private HelpDeskProcessService helpDeskProcessService;

	// 寫入案件歷程至資料庫
	@PostMapping("/insertComment")
	public String insertComment(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		HelpDeskProcessBean helpdeskprocess = helpDeskProcessService.insertComment(json);
		if (helpdeskprocess != null) {
			// 若前端收到true時需顯示成功訊息
			responseJson.put("message", "送出留言成功");
			responseJson.put("success", true);

		} else {
			// 若前端收到false時需顯示錯誤訊息
			responseJson.put("message", "送出留言失敗");
			responseJson.put("success", false);
		}

		return responseJson.toString();
	}

}
