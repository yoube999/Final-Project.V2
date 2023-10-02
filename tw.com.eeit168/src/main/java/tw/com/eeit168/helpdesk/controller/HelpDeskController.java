package tw.com.eeit168.helpdesk.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.com.eeit168.helpdesk.model.HelpDeskBean;
import tw.com.eeit168.helpdesk.service.HelpDeskService;
import tw.com.eeit168.member.model.MemberProfileBean;
import tw.com.eeit168.products.restaurant.util.DatetimeConverter;

@RestController
@RequestMapping("/eeit168/helpdesk")

public class HelpDeskController {

	@Autowired
	private HelpDeskService helpDeskService;

	// 寫入案件，用多載方式(overloading)來判斷接收格式
	@PostMapping("/createTicket")
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

	// 查詢案件，透過前端點擊案件分類夾顯示不同狀態的案件標題
	@PostMapping("/selectTicket")
	public String selectTicket(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		List<HelpDeskBean> helpdesks = helpDeskService.selectTicket(json);
		JSONArray array = new JSONArray();
		if (helpdesks != null && !helpdesks.isEmpty()) {
			for (HelpDeskBean helpdesk : helpdesks) {
				String createtime = DatetimeConverter.toString(helpdesk.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
				JSONObject item = new JSONObject()
						// helpdesk_id為了給前端抓取使用，帶入selectTicketById，不顯示於前端頁面
						.put("helpdesk_id", helpdesk.getHelpdesk_id()).put("customer_name", helpdesk.getCustomer_name())
						.put("subject_line", helpdesk.getSubject_line()).put("createtime", createtime);
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	// 前端點擊特定案件時，透過拿到的helpdesk_id來進行搜尋
	@GetMapping("/selectTicket/{helpdesk_id}")
	public String selectTicketById(@PathVariable(name = "helpdesk_id") Integer helpdesk_id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		HelpDeskBean helpdesk = helpDeskService.selectTicketById(helpdesk_id);
		if (helpdesk != null) {

			String createtime = DatetimeConverter.toString(helpdesk.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
			JSONObject item = new JSONObject().put("customer_name", helpdesk.getCustomer_name())
					.put("record_id", helpdesk.getRecord_id()).put("subject_line", helpdesk.getSubject_line())
					.put("descriptions", helpdesk.getDescriptions()).put("contact_number", helpdesk.getContact_number())
					.put("email", helpdesk.getEmail()).put("way_to_contact", helpdesk.getWay_to_contact())
					.put("attachment", helpdesk.getAttachment())
					.put("member_profile_id", helpdesk.getMember_profile_id()).put("createtime", createtime);
			array = array.put(item);

		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	// 透過點擊前端按鈕來變更案件狀態和人員
	@PutMapping("/modifyHelpdeskStatus/{helpdesk_id}")
	public String modifyHelpdeskStatus(@PathVariable Integer helpdesk_id, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		// 若前端送的helpdesk_id找不到時需顯示錯誤訊息
		if (helpDeskService.selectTicketById(helpdesk_id) == null) {
			responseJson.put("message", "接受案件失敗，請聯絡IT人員");
			responseJson.put("success", false);
		} else {
			HelpDeskBean helpdesk = helpDeskService.modifyHelpdeskStatus(json);

			if (helpdesk != null) {
				responseJson.put("message", "接受案件成功!");
				responseJson.put("success", true);
			} else {
				// 若接受案件失敗時需顯示錯誤訊息
				responseJson.put("message", "接受案件失敗，請聯絡IT人員");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

	// 顯示案件內容時，畫面上顯示圖片URL
//	@GetMapping("/selectPicture/{helpdesk_id}")
//	// 使用 ResponseEntity<Resource> 是一種通用的方式來處理 HTTP 響應，特別是用於處理二進制數據，例如圖片文件。
//	public ResponseEntity<byte[]> selectPicture(@PathVariable Integer helpdesk_id) {
//		
//		byte[] pictureURL = helpDeskService.selectPicture(helpdesk_id);
//		
//		System.out.println(pictureURL);
//		
//		if(pictureURL != null) {
//			
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.IMAGE_JPEG);
//			
//			return new ResponseEntity<>(pictureURL, headers, HttpStatus.OK);
//		} else {
//			// 如果找不到圖片或有其他錯誤，返回 404 錯誤
//	        return ResponseEntity.notFound().build();
//		}
//	}

	// 前端點擊特定案件時，透過拿到的helpdesk_id來進行搜尋案件歷程
	@PostMapping("/selectCustomerUser")
	public String selectCustomerUser(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		List<MemberProfileBean> CustomerUsers = helpDeskService.selectCustomerUser(json);
		JSONArray array = new JSONArray();
		if (CustomerUsers != null && !CustomerUsers.isEmpty()) {
			for (MemberProfileBean CustomerUser : CustomerUsers) {
				JSONObject item = new JSONObject().put("member_profile_id", CustomerUser.getMemberProfileId())
						.put("username", CustomerUser.getUsername());

				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	// 客服人員寄送Email回覆客戶
	@PostMapping("/sendEmail")
	public String sendEmail(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		
		// 使用boolean判斷寄送信件成功失敗
		boolean sendEmail = helpDeskService.sendEmail(json);
		if (sendEmail == true) {
			// 若前端收到true時需顯示成功訊息
			responseJson.put("message", "寄送信件成功");
			responseJson.put("success", true);
		} else {
			// 若前端收到true時需顯示成功訊息
			responseJson.put("message", "寄送信件失敗，請聯繫IT人員");
			responseJson.put("success", false);
		}

		return responseJson.toString();
	}

}
