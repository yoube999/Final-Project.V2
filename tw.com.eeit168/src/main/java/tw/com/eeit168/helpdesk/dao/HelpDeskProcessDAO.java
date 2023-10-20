package tw.com.eeit168.helpdesk.dao;

import java.util.ArrayList;
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
import tw.com.eeit168.helpdesk.model.HelpDeskProcessWithNameBean;

@Repository
public class HelpDeskProcessDAO implements HelpDeskProcessInterFace {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	/**
	 * 寫入案件歷程至資料庫
	 * 
	 * 
	 */
	@Override
	public HelpDeskProcessBean insertComment(HelpDeskProcessBean bean) {
		if (bean != null) {
			this.getSession().persist(bean);
			return bean;

		}

		return null;
	}

	/**
	 * 前端點擊特定案件時，透過拿到的helpdesk_id來進行搜尋案件歷程
	 * 
	 * 
	 */
	@Override
	public List<HelpDeskProcessWithNameBean> selectTicketCommentById(JSONObject obj) {

		// 後端收到查詢條件相關Null防呆處理
		int start = obj.isNull("start") ? 0 : obj.getInt("start"); // 起始分頁
		int row = obj.isNull("row") ? 5 : obj.getInt("row"); // 分頁歷程數量
		String sortType = obj.isNull("sortType") ? null : obj.getString("sortType"); // 排序欄位
		String sortOrder = obj.isNull("sortOrder") ? null : obj.getString("sortOrder"); // 查詢排序

		// 後端收到案件狀態Null防呆處理
		Integer helpdesk_id = obj.isNull("helpdesk_id") ? 0 : obj.getInt("helpdesk_id"); // 查詢案件歷程，使用案件ID

		// 這是 Criteria API 的一個關鍵介面，它允許您建立各種查詢條件和表達式，獲取了一個用於建立查詢的 CriteriaBuilder 實例 by
		// ChatGPT
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		// 這是描述 JPA 查詢的主要介面。通過 criteriaBuilder.createQuery(HelpDeskBean.class)。這個
		// ProductBean.class 告訴查詢你要查詢哪種類型的實體 by ChatGPT
		CriteriaQuery<HelpDeskProcessWithNameBean> criteriaQuery = criteriaBuilder.createQuery(HelpDeskProcessWithNameBean.class);

		// from helpdesk_process
		Root<HelpDeskProcessWithNameBean> root = criteriaQuery.from(HelpDeskProcessWithNameBean.class);

		// where
		List<Predicate> predicates = new ArrayList<>();

		if (helpdesk_id != null) {
			predicates.add(criteriaBuilder.equal(root.get("helpdesk_id"), helpdesk_id));
		}

		// 將 predicates 列表轉換為 Predicate 數組的目的是傳遞它們給 where 子句以構建查詢的一部分。
		if (predicates != null && !predicates.isEmpty()) {
			criteriaQuery = criteriaQuery.where(predicates.toArray(new Predicate[1]));
		}

		if (sortType != null) {
			if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
				Order order = criteriaBuilder.desc(root.get(sortType));
				criteriaQuery = criteriaQuery.orderBy(order);
			} else {
				Order order = criteriaBuilder.asc(root.get(sortType));
				criteriaQuery = criteriaQuery.orderBy(order);
			}
		}

		TypedQuery<HelpDeskProcessWithNameBean> typedQuery = this.getSession().createQuery(criteriaQuery)
				.setFetchSize(start * row);
		if (start != 0) {
	        typedQuery = typedQuery.setFirstResult(start);
	    }
		if (row != 0) {
			typedQuery = typedQuery.setMaxResults(row);
		}

		List<HelpDeskProcessWithNameBean> result = typedQuery.getResultList();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}

	}

}
