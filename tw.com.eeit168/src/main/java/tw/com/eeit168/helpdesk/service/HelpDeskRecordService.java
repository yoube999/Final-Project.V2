package tw.com.eeit168.helpdesk.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.helpdesk.dao.HelpDeskRecordDAO;
import tw.com.eeit168.helpdesk.model.HelpDeskRecordBean;
import tw.com.eeit168.member.model.MemberProfileBean;
import tw.com.eeit168.products.RecordBean;

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskRecordService {

	@Autowired
	private HelpDeskRecordDAO helpDeskRecordDAO;

	/**
	 * 前端載入審核訂單面時，審核中訂單查詢API
	 * 
	 * 
	 */
	public List<HelpDeskRecordBean> selectRecordByStatus(String json) {

		try {
			JSONObject obj = new JSONObject(json);
			return helpDeskRecordDAO.selectRecordByStatus(obj);
		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 取得特定會員資料
	 * 
	 * 
	 */
	public MemberProfileBean selectUserById(Integer member_profile_id) {
		if (member_profile_id != null) {
			return helpDeskRecordDAO.selectUserById(member_profile_id);
		}

		return null;
	}

	/**
	 * 取得特定訂單資料
	 * 
	 * 
	 */
	public RecordBean selectRecordById(Integer record_id) {
		if (record_id != null) {
			return helpDeskRecordDAO.selectRecordById(record_id);
		}

		return null;
	}
}
