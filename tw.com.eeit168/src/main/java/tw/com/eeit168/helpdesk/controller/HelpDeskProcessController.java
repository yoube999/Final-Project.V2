package tw.com.eeit168.helpdesk.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit168.helpdesk.model.HelpDeskProcessBean;
import tw.com.eeit168.helpdesk.service.HelpDeskProcessService;
import tw.com.eeit168.products.restaurant.util.DatetimeConverter;

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

	// 前端點擊特定案件時，透過拿到的helpdesk_id來進行搜尋案件歷程
	@PostMapping("/selectTicketCommentById")
	public String selectTicketCommentById(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		List<HelpDeskProcessBean> helpdeskprocess = helpDeskProcessService.selectTicketCommentById(json);
		JSONArray array = new JSONArray();
		if (helpdeskprocess != null && !helpdeskprocess.isEmpty()) {
			for (HelpDeskProcessBean helpdeskproces : helpdeskprocess) {
				String createtime = DatetimeConverter.toString(helpdeskproces.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
				JSONObject item = new JSONObject()
						.put("process_description", helpdeskproces.getProcess_description())
						.put("member_profile_id", helpdeskproces.getMember_profile_id())
						.put("createtime", createtime);

				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

}
