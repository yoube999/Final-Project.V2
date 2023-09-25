package tw.com.eeit168.helpdesk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.com.eeit168.helpdesk.model.HelpDeskBean;
import tw.com.eeit168.helpdesk.service.HelpDeskService;

@RestController
@RequestMapping("/eeit168/helpdesk")

public class HelpDeskController {

	@Autowired
	private HelpDeskService helpDeskService;

	@PostMapping("/createTicket")
	// 寫入案件，用多載方式(overloading)來判斷接收格式，
	
	public String createTicket(@RequestBody(required = true) String json,
			@RequestPart(required = false) MultipartFile image) {
		JSONObject responseJson = new JSONObject();

		HelpDeskBean helpdesk = helpDeskService.createTicket(json);
		if (helpdesk != null) {
			// 若前端收到true時需顯示成功訊息
			responseJson.put("message", "新增案件成功");
			responseJson.put("success", true);
		} else {
			// 若前端收到false時需顯示錯誤訊息
			responseJson.put("message", "新增案件失敗");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}

	
}
