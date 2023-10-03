package tw.com.eeit168.helpdesk.dao;

import java.util.List;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import tw.com.eeit168.helpdesk.model.HelpDeskRecordBean;
import tw.com.eeit168.member.model.MemberProfileBean;
import tw.com.eeit168.products.RecordBean;

@Repository
public class HelpDeskRecordDAO implements HelpDeskRecordInterFace {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	/**
	 * 前端載入審核訂單面時，審核中訂單查詢API
	 * 
	 * 
	 */
	@Override
	public List<HelpDeskRecordBean> selectRecordByStatus(JSONObject obj) {

		// 後端收到查詢條件相關Nulll防呆處理
		String sortType = obj.isNull("sortType") ? null : obj.getString("sortType"); // 排序欄位
		String sortOrder = obj.isNull("sortOrder") ? null : obj.getString("sortOrder"); // 查詢排序

		// 這是 Criteria API 的一個關鍵介面，它允許您建立各種查詢條件和表達式，獲取了一個用於建立查詢的 CriteriaBuilder 實例 by
		// ChatGPT
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		// 這是描述 JPA 查詢的主要介面。通過 criteriaBuilder.createQuery(HelpDeskBean.class)。這個
		// MemberProfileBean.class 告訴查詢你要查詢哪種類型的實體 by ChatGPT
		CriteriaQuery<HelpDeskRecordBean> criteriaQuery = criteriaBuilder.createQuery(HelpDeskRecordBean.class);

		// from record
		Root<HelpDeskRecordBean> root = criteriaQuery.from(HelpDeskRecordBean.class);

		if (sortType != null) {
			if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
				Order order = criteriaBuilder.desc(root.get(sortType));
				criteriaQuery = criteriaQuery.orderBy(order);
			} else {
				Order order = criteriaBuilder.asc(root.get(sortType));
				criteriaQuery = criteriaQuery.orderBy(order);
			}
		}

		// 這段程式碼的目的是創建一個TypedQuery<MemberProfileBean>對象，用於執行基於標準的 JPA 查詢 by ChatGPT
		TypedQuery<HelpDeskRecordBean> typedQuery = this.getSession().createQuery(criteriaQuery);

		List<HelpDeskRecordBean> result = typedQuery.getResultList();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}

	}

	/**
	 * 取得特定會員資料
	 * 
	 * 
	 */
	@Override
	public MemberProfileBean selectUserById(Integer member_profile_id) {
		if (member_profile_id != null) {
			return this.getSession().get(MemberProfileBean.class, member_profile_id);
		}

		return null;
	}

	/**
	 * 取得特定訂單資料
	 * 
	 * 
	 */
	@Override
	public RecordBean selectRecordById(Integer record_id) {
		if (record_id != null) {
			return this.getSession().get(RecordBean.class, record_id);
		}

		return null;
	}

}
