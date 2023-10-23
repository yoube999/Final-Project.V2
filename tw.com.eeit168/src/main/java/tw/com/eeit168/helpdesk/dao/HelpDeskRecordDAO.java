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
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import tw.com.eeit168.helpdesk.model.HelpDeskProcessBean;
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
		int start = obj.isNull("start") ? 0 : obj.getInt("start"); // 起始分頁
		int row = obj.isNull("row") ? 10 : obj.getInt("row"); // 分頁案件數量
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
		if (start != 0) {
			typedQuery = typedQuery.setFirstResult(start);
		}
		if (row != 0) {
			typedQuery = typedQuery.setMaxResults(row);
		}

		List<HelpDeskRecordBean> result = typedQuery.getResultList();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}

	}
	
	/**
	 * 查詢訂單，回傳資料總數量做分頁用
	 * 
	 * 
	 */
	@Override
	public long recordsTotal(JSONObject obj) {
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		// 建立一個 CriteriaQuery 對象，該對象用於創建查詢的元數據結構，這裡我們想要返回 Long 類型的結果，即案件的總數量。
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		Root<HelpDeskRecordBean> root = countQuery.from(HelpDeskRecordBean.class);

		// 使用 criteriaBuilder.count 方法，指定您要計算匹配查詢條件的實體總數。
		countQuery.select(criteriaBuilder.count(root));

		// 如果需要添加條件，可以透過 Predicate 來添加
		if (obj != null && !obj.isEmpty()) {
			String status = obj.optString("record_status", "審核中"); // 從 JSONObject 中獲取狀態欄位的值

			// 用於指定一個查詢條件，即確保 HelpDeskBean 實體的 helpdesk_process_id 屬性（或欄位）等於特定的值 ID。
			Predicate statusPredicate = criteriaBuilder.equal(root.get("record_status"), status);
			countQuery.where(statusPredicate);
		}

		// 使用 createQuery 方法創建查詢，並使用 getSingleResult 方法來執行查詢，並返回一個 Long 值
		return this.getSession().createQuery(countQuery).getSingleResult();
		
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
	public HelpDeskRecordBean selectRecordById(Integer record_id) {
		if (record_id != null) {
			return this.getSession().get(HelpDeskRecordBean.class, record_id);
		}

		return null;
	}

}
