package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.helpdesk.model.HelpDeskProcessBean;

@Repository
public class HelpDeskProcessDAO implements HelpDeskProcessInterFace {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	// 寫入案件歷程至資料庫
	@Override
	public HelpDeskProcessBean insertComment(HelpDeskProcessBean bean) {
		if (bean != null) {
			this.getSession().persist(bean);
			return bean;

		}

		return null;
	}

	@Override
	public List<HelpDeskProcessBean> selectTicketCommentById(JSONObject obj) {
		
		// 後端收到查詢條件相關Null防呆處理
		int start = obj.isNull("start") ? 0 : obj.getInt("start"); // 起始分頁
		int row = obj.isNull("row") ? 5 : obj.getInt("row"); // 分頁歷程數量
		String sortType = obj.isNull("sortType") ? null : obj.getString("sortType"); // 排序欄位
		String sortOrder = obj.isNull("sortOrder") ? null : obj.getString("sortOrder"); // 查詢排序
		
		
		
		
		
		
		
		
		
		
		return null;
	}

}
