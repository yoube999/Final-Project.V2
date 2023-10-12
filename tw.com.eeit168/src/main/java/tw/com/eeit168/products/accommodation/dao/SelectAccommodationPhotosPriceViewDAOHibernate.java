package tw.com.eeit168.products.accommodation.dao;

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
import tw.com.eeit168.products.accommodation.model.SelectAccommodationPhotosPriceView;

@Repository
public class SelectAccommodationPhotosPriceViewDAOHibernate implements SelectAccommodationPhotosPriceViewDAO {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	@Override
	public List<SelectAccommodationPhotosPriceView> selectAll(JSONObject obj) {

		// 後端收到查詢條件相關Null防呆處理
		
		
		
		
		int start = obj.isNull("start") ? 0 : obj.getInt("start");// 起始分頁
		int row = obj.isNull("row") ? 10 : obj.getInt("row");// 分頁案件數量
		String sortType = obj.isNull("sortType") ? null : obj.getString("sortType");// 排序欄位
		String sortOrder = obj.isNull("sortOrder") ? null : obj.getString("sortOrder");// 查詢排序

		// 這是 Criteria API 的一個關鍵介面，它允許您建立各種查詢條件和表達式，獲取了一個用於建立查詢的 CriteriaBuilder 實例 by
		CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();

		// 這是描述 JPA 查詢的主要介面。通過 criteriaBuilder.createQuery(HelpDeskBean.class)。這個
		// ProductBean.class 告訴查詢你要查詢哪種類型的實體
		CriteriaQuery<SelectAccommodationPhotosPriceView> criteriaQuery = criteriaBuilder
				.createQuery(SelectAccommodationPhotosPriceView.class);

		// from SelectAccommodationPhotosPriceView
		Root<SelectAccommodationPhotosPriceView> root = criteriaQuery.from(SelectAccommodationPhotosPriceView.class);

		if (sortType != null) {
			if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
				Order order = criteriaBuilder.desc(root.get(sortType));
				criteriaQuery = criteriaQuery.orderBy(order);
			} else {
				Order order = criteriaBuilder.asc(root.get(sortType));
				criteriaQuery = criteriaQuery.orderBy(order);
			}
		}

		TypedQuery<SelectAccommodationPhotosPriceView> typedQuery = this.getSession().createQuery(criteriaQuery)
				.setFetchSize(start * row);
		if (start != 0) {
			typedQuery = typedQuery.setFirstResult(start);
		}
		if (row != 0) {
			typedQuery = typedQuery.setMaxResults(row);
		}

		List<SelectAccommodationPhotosPriceView> result = typedQuery.getResultList();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}

	}

}
