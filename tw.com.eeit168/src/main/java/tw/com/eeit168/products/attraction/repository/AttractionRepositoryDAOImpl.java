package tw.com.eeit168.products.attraction.repository;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.AttractionBean;
import tw.com.eeit168.products.attraction.model.SelectAttractionsInventoryView;
import tw.com.eeit168.products.attraction.model.SelectAttractionsPictureView;
import tw.com.eeit168.products.attraction.model.SelectAttractionsTicketView;

public class AttractionRepositoryDAOImpl implements AttractionRepositoryDAO {
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	//Top5
	public List<AttractionBean> selectTop5() {
		String hql = "from AttractionBean order by timesPurchased desc";
		return this.getSession().createQuery(hql, AttractionBean.class).setMaxResults(5).list();
	}
	
	//以被購買次數搜尋(多-少)
	public List<AttractionBean> findAllByPurchasedDesc() {
		String hql = "from AttractionBean order by timesPurchased desc";
		return this.getSession().createQuery(hql, AttractionBean.class).list();
	}
	
	//以價格搜尋(高-低)
	public List<SelectAttractionsTicketView> findAllByPriceDesc() {
		String hql = "from SelectAttractionsTicketView order by adultPrice desc";
		return this.getSession().createQuery(hql, SelectAttractionsTicketView.class).list();
	}
	
	//以價格搜尋(低-高)
	public List<SelectAttractionsTicketView> findAllByPriceAsc() {
		String hql = "from SelectAttractionsTicketView order by adultPrice asc";
		return this.getSession().createQuery(hql, SelectAttractionsTicketView.class).list();
	}
	
	//景點名稱及地點的模糊搜尋
	public List<SelectAttractionsInventoryView> blurFind(String keyword) {
		if(keyword != null && !keyword.isEmpty()) {
			String hql = "from SelectAttractionsInventoryView where attractionsName like :keyword or attractionsAddress like :keyword";
			Query<SelectAttractionsInventoryView> result = this.getSession().createQuery(hql, SelectAttractionsInventoryView.class);
			result.setParameter("keyword", "%" + keyword + "%");
			return result.list();
		}
		return Collections.emptyList();
	}
	
	//景點日期的模糊搜尋
	public List<SelectAttractionsInventoryView> blurDateFind(java.sql.Date orderDate) {
		String hql = "from SelectAttractionsInventoryView where availabilityDate = :orderDate";
		Query<SelectAttractionsInventoryView> result = this.getSession().createQuery(hql, SelectAttractionsInventoryView.class);
		result.setParameter("orderDate", orderDate);
		return result.list();
	}
	
	//用景點名稱搜尋圖片
	public List<SelectAttractionsPictureView> findPictureByName(String name) {
		if(name != null && !name.isEmpty()) {
			String hql = "from SelectAttractionsPictureView where attractionsName = :name";
			Query<SelectAttractionsPictureView> result = this.getSession().createQuery(hql, SelectAttractionsPictureView.class);
			result.setParameter("name", name);
			return result.list();
		}
		return Collections.emptyList();
	}

}
