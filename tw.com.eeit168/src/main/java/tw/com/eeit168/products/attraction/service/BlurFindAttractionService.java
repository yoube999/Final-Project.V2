package tw.com.eeit168.products.attraction.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.SelectAttractionsInventoryView;

@Service //註解類別處理運算邏輯(企業邏輯)
public class BlurFindAttractionService {

	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	//景點名稱及地點的模糊搜尋
	public List<SelectAttractionsInventoryView> blurFind(String keyword) {
		if(keyword != null && !keyword.isEmpty()) {
			String hql = "from SelectAttractionsInventoryView where attractions_name like :keyword or attractions_address like :keyword";
			Query<SelectAttractionsInventoryView> result = this.getSession().createQuery(hql, SelectAttractionsInventoryView.class);
			result.setParameter("keyword", "%" + keyword + "%");
			return result.list();
		}
		return Collections.emptyList();
	}
	
	//景點日期的模糊搜尋
	public List<SelectAttractionsInventoryView> blurDateFind(java.sql.Date checkInDate, java.sql.Date checkOutDate) {
		String hql = "from SelectAttractionsInventoryView where availability_date between :checkInDate and :checkOutDate";
		Query<SelectAttractionsInventoryView> result = this.getSession().createQuery(hql, SelectAttractionsInventoryView.class);
		result.setParameter("checkInDate", checkInDate);
		result.setParameter("checkOutDate", checkOutDate);
		return result.list();
	}
}