package tw.com.eeit168.helpdesk.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.helpdesk.dao.HelpDeskProcessDAO;
import tw.com.eeit168.helpdesk.model.HelpDeskProcessBean;
import tw.com.eeit168.helpdesk.model.HelpDeskProcessWithNameBean;
import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.repository.AccommodationRepository;

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskProcessService {

	@Autowired
	private HelpDeskProcessDAO helpDeskProcessDAO;

	/**
	 * 客服人員送出留言，建立歷程
	 * 
	 * 
	 */
	public HelpDeskProcessBean insertComment(String json) {

		try {
			// 使用JSON格式包留言內容
			JSONObject obj = new JSONObject(json);

			Integer helpdesk_id = obj.getInt("helpdesk_id");
			String process_description = obj.isNull("process_description") ? "未填寫內容"
					: obj.getString("process_description"); // 前端防呆失效且未傳送留言內容時處理
			String attachment = obj.isNull("attachment") ? null : obj.getString("attachment");
			Integer member_profile_id = obj.getInt("member_profile_id");

			HelpDeskProcessBean insert = new HelpDeskProcessBean();
			insert.setHelpdesk_id(helpdesk_id);
			insert.setProcess_description(process_description);
			insert.setAttachment(attachment);
			insert.setMember_profile_id(member_profile_id);
			// 送出留言時取得Loacl當下時間
			insert.setCreatetime(new java.util.Date());

			return helpDeskProcessDAO.insertComment(insert);
		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 前端點擊特定案件時，透過拿到的helpdesk_id來進行搜尋案件歷程
	 * 
	 * 
	 */
	public List<HelpDeskProcessWithNameBean> selectTicketCommentById(String json) {

		try {
			JSONObject obj = new JSONObject(json);
			return helpDeskProcessDAO.selectTicketCommentById(obj);
		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * 查詢案件，回傳總數量
	 * 
	 * 
	 */
	public long ticketCommentsTotal(String json) {

		try {
			JSONObject obj = new JSONObject(json);
			return helpDeskProcessDAO.ticketCommentsTotal(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

}
