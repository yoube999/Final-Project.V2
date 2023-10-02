package tw.com.eeit168.helpdesk.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.helpdesk.dao.HelpDeskRecordDAO;
import tw.com.eeit168.helpdesk.model.HelpDeskRecordBean;

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

}
