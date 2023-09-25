package tw.com.eeit168.helpdesk.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.helpdesk.dao.HelpDeskInterFace;
import tw.com.eeit168.helpdesk.model.HelpDeskBean;

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskService {

	@Autowired
	private HelpDeskInterFace helpDeskInterFace = null;

	// 寫入案件單，需再加上圖片上傳功能
	public HelpDeskBean createTicket(String json) {

		try {
			// 使用JSON格式包案件內容
			JSONObject obj = new JSONObject(json);

			// 後端收到Null防呆處理
			String customer_name = obj.isNull("customer_name") ? "訪客" : obj.getString("customer_name");
			Integer record_id = obj.isNull("record_id") ? null : obj.getInt("record_id");
			String subject_line = obj.isNull("subject_line") ? "未填寫主旨" : obj.getString("subject_line");
			String descriptions = obj.isNull("descriptions") ? "未填寫內容" : obj.getString("descriptions");
			String contact_number = obj.isNull("contact_number") ? "未填寫電話號碼" : obj.getString("contact_number");
			String email = obj.isNull("email") ? "未填寫email" : obj.getString("email");
			String way_to_contact = obj.isNull("way_to_contact") ? "電子信箱" : obj.getString("way_to_contact");

			HelpDeskBean insert = new HelpDeskBean();
			insert.setCustomer_name(customer_name);
			insert.setRecord_id(record_id);
			insert.setSubject_line(subject_line);
			insert.setDescriptions(descriptions);
			insert.setContact_number(contact_number);
			insert.setEmail(email);
			insert.setWay_to_contact(way_to_contact);
			// 建立案件時狀態固定為未處理
			insert.setMember_profile_id(null);
			insert.setHelpdesk_status("未處理");

			System.out.println(insert);
			return helpDeskInterFace.insert(insert);

		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

}
