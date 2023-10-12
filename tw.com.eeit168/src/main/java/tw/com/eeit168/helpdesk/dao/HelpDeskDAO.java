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
import tw.com.eeit168.helpdesk.model.HelpDeskBean;
import tw.com.eeit168.member.model.MemberProfileBean;

@Repository
public class HelpDeskDAO implements HelpDeskInterFace {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	/**
	 * 寫入案件單至資料庫
	 * 
	 * 
	 */
	@Override
	public HelpDeskBean insert(HelpDeskBean bean) {
		if (bean != null) {
			this.getSession().persist(bean);
			return bean;
		}

		return null;
	}

	/**
	 * 查詢案件，使用List實作，前端收到List再從裡面取得資訊顯示
	 * 
	 * 
	 */
	@Override
	public List<HelpDeskBean> selectTicket(JSONObject obj) {

		// 後端收到查詢條件相關Null防呆處理
		int start = obj.isNull("start") ? 0 : obj.getInt("start"); // 起始分頁
		int row = obj.isNull("row") ? 10 : obj.getInt("row"); // 分頁案件數量
		String sortType = obj.isNull("sortType") ? null : obj.getString("sortType"); // 排序欄位
		String sortOrder = obj.isNull("sortOrder") ? null : obj.getString("sortOrder"); // 查詢排序

		// 後端收到案件狀態Null防呆處理
		String helpdesk_status = obj.isNull("helpdesk_status") ? "未處理" : obj.getString("helpdesk_status"); // 查詢案件，使用案件狀態分類

		// 這是 Criteria API 的一個關鍵介面，它允許您建立各種查詢條件和表達式，獲取了一個用於建立查詢的 CriteriaBuilder 實例 by
		// ChatGPT
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		// 這是描述 JPA 查詢的主要介面。通過 criteriaBuilder.createQuery(HelpDeskBean.class)。這個
		// ProductBean.class 告訴查詢你要查詢哪種類型的實體 by ChatGPT
		CriteriaQuery<HelpDeskBean> criteriaQuery = criteriaBuilder.createQuery(HelpDeskBean.class);

		// from helpdesk
		Root<HelpDeskBean> root = criteriaQuery.from(HelpDeskBean.class);

		// where
		List<Predicate> predicates = new ArrayList<>();

		if (helpdesk_status != null && helpdesk_status.length() != 0) {
			predicates.add(criteriaBuilder.equal(root.get("helpdesk_status"), helpdesk_status));
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

		TypedQuery<HelpDeskBean> typedQuery = this.getSession().createQuery(criteriaQuery).setFetchSize(start * row);
		if (start != 0) {
	        typedQuery = typedQuery.setFirstResult(start);
	    }
		if (row != 0) {
			typedQuery = typedQuery.setMaxResults(row);
		}

		List<HelpDeskBean> result = typedQuery.getResultList();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}

	}

	/**
	 * 前端點擊特定案件時，透過拿到的helpdesk_id來進行搜尋
	 * 
	 * 
	 */
	@Override
	public HelpDeskBean selectTicketById(Integer helpdesk_id) {
		if (helpdesk_id != null) {
			return this.getSession().get(HelpDeskBean.class, helpdesk_id);
		}
		return null;
	}

	/**
	 * 透過點擊前端按鈕來變更案件狀態和人員
	 * 
	 * 
	 */
	@Override
	public HelpDeskBean modifyHelpdeskStatus(HelpDeskBean bean) {
		if (bean != null) {
			// 使用 merge 更新 bean
			HelpDeskBean updatedBean = (HelpDeskBean) this.getSession().merge(bean);
			return updatedBean; // 返回更新後的 bean
		}
		return null;
	}

	/**
	 * 案件頁面，客服人員文字輸入框中選擇客服人員下拉選單查詢API
	 * 
	 * 
	 */
	@Override
	public List<MemberProfileBean> selectCustomerUser(JSONObject obj) {

		// 後端收到查詢條件相關Null防呆處理
		String sortType = obj.isNull("sortType") ? null : obj.getString("sortType"); // 排序欄位
		String sortOrder = obj.isNull("sortOrder") ? null : obj.getString("sortOrder"); // 查詢排序

		// 後端收到人員等級Null防呆處理
		Integer member_level = obj.isNull("member_level") ? 99 : obj.getInt("member_level"); // 查詢案件歷程，使用人員等級

		// 這是 Criteria API 的一個關鍵介面，它允許您建立各種查詢條件和表達式，獲取了一個用於建立查詢的 CriteriaBuilder 實例 by
		// ChatGPT
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
		// 這是描述 JPA 查詢的主要介面。通過 criteriaBuilder.createQuery(HelpDeskBean.class)。這個
		// MemberProfileBean.class 告訴查詢你要查詢哪種類型的實體 by ChatGPT
		CriteriaQuery<MemberProfileBean> criteriaQuery = criteriaBuilder.createQuery(MemberProfileBean.class);

		// from member_profile
		Root<MemberProfileBean> root = criteriaQuery.from(MemberProfileBean.class);

		// where
		List<Predicate> predicates = new ArrayList<>();

		if (member_level != null) {
			predicates.add(criteriaBuilder.equal(root.get("member_level"), member_level));
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

		// 這段程式碼的目的是創建一個TypedQuery<MemberProfileBean>對象，用於執行基於標準的 JPA 查詢 by ChatGPT
		TypedQuery<MemberProfileBean> typedQuery = this.getSession().createQuery(criteriaQuery);

		List<MemberProfileBean> result = typedQuery.getResultList();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}

	}

	

}
