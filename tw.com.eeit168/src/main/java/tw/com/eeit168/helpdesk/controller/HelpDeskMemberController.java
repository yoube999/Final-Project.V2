package tw.com.eeit168.helpdesk.controller;

import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.JsonNode;

import tw.com.eeit168.helpdesk.service.HelpDeskMemberService;
import tw.com.eeit168.member.model.MemberProfileBean;
import tw.com.eeit168.member.service.MemberProfileService;

@RestController
@RequestMapping("/eeit168/helpdeskmember")
public class HelpDeskMemberController {

	@Autowired
	private HelpDeskMemberService helpDeskMemberService;

	@Autowired
	private MemberProfileService memberProfileService;

	/**
	 * 前端點擊編輯客服人員頁面時，顯示客服人員清單
	 * 
	 * @param memberLevel: 固定查詢條件為99和100
	 * 
	 * @return List<MemberProfileBean>
	 */
	@PostMapping("/selectCustomerService")
	public String selectCustomerService(@RequestBody String json) {
		JSONObject requestJson = new JSONObject(json);

		// 提取 levels 參數，它是一個整數陣列
		List<Integer> levels = new ArrayList<>();
		JSONArray levelsArray = requestJson.getJSONArray("levels");
		for (int i = 0; i < levelsArray.length(); i++) {
			levels.add(levelsArray.getInt(i));
		}

		// 提取 start 參數和 row 參數
		int start = requestJson.getInt("start");
		int row = requestJson.getInt("row");

		List<MemberProfileBean> members = helpDeskMemberService.selectCustomerService(levels, start, row);
		JSONArray array = new JSONArray();
		JSONObject responseJson = new JSONObject();

		if (members != null && !members.isEmpty()) {
			for (MemberProfileBean member : members) {
				JSONObject item = new JSONObject().put("member_profile_id", member.getMember_profile_id())
						.put("user_account", member.getUser_account()).put("username", member.getUsername())
						.put("gender", member.getGender()).put("birthday", member.getBirthday())
						.put("phone_number", member.getPhone_number());

				array = array.put(item);
			}
			responseJson.put("list", array);
			return responseJson.toString();
		}
		// 若前端收到false時需顯示錯誤訊息
		responseJson.put("message", "查詢客服人員失敗");
		responseJson.put("success", "error");
		return responseJson.toString();
	}

	/**
	 * 前端點擊刪除客服人員
	 * 
	 * @param member_profile_id: 客服人員ID
	 * 
	 * @return boolean
	 */
	@GetMapping("/removeCustomerService/{memberProfileId}")
	public String removeCustomerService(@PathVariable("memberProfileId") Integer memberProfileId) {
		JSONObject responseJson = new JSONObject();

		boolean result = helpDeskMemberService.removeCustomerService(memberProfileId);

		if (result) {
			// 若前端收到true時需顯示成功訊息
			responseJson.put("message", "刪除客服人員成功");
			responseJson.put("success", "true");
			return responseJson.toString();
		} else {
			// 若前端收到false時需顯示錯誤訊息
			responseJson.put("message", "刪除客服人員失敗，請確認刪除是否為客服人員");
			responseJson.put("success", "false");
			return responseJson.toString();
		}
	}

	/**
	 * 編輯客服人員資料
	 * 
	 * @param member_profile_id: 客服人員ID
	 * 
	 * @return boolean
	 */
	@PostMapping("/updateCustomerService")
	public String updateCustomerService(@RequestBody JsonNode jsonNode) {
		JSONObject responseJson = new JSONObject();
		String user_account = jsonNode.get("user_account").asText();

		try {
			boolean result = memberProfileService.updateMemberInfo(user_account, jsonNode);
			if (result) {
				// 若前端收到true時需顯示成功訊息
				responseJson.put("message", "編輯客服人員資料成功");
				responseJson.put("success", true);
				return responseJson.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		responseJson.put("message", "編輯客服人員資料失敗，請聯絡IT人員");
		responseJson.put("success", false);
		return responseJson.toString();
	}

}
