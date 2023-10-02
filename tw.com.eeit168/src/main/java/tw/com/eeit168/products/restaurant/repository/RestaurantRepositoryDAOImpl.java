package tw.com.eeit168.products.restaurant.repository;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;

public class RestaurantRepositoryDAOImpl implements RestaurantRepositoryDAO {
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	//Top5
	public List<RestaurantBean> selectTop5() {
		String hql = "from RestaurantBean order by times_purchased desc";
		return this.getSession().createQuery(hql, RestaurantBean.class).setMaxResults(5).list();
	}
	
	//以被購買次數搜尋(多-少)
	public List<RestaurantBean> findAllByPurchasedDesc() {
		String hql = "from RestaurantBean order by times_purchased desc";
		return this.getSession().createQuery(hql, RestaurantBean.class).list();
	}
	
	//以價格搜尋(高-低)
	public List<RestaurantBean> findAllByPriceDesc() {
		String hql = "from RestaurantBean order by price desc";
		return this.getSession().createQuery(hql, RestaurantBean.class).list();
	}
	
	//以價格搜尋(低-高)
	public List<RestaurantBean> findAllByPriceAsc() {
		String hql = "from RestaurantBean order by price asc";
		return this.getSession().createQuery(hql, RestaurantBean.class).list();
	}
	
	//餐廳名稱及地點的模糊搜尋
	public List<SelectRestaurantInventoryView> blurFind(String keyword) {
		if(keyword != null && !keyword.isEmpty()) {
			String hql = "from SelectRestaurantInventoryView where restaurant_name like :keyword or restaurant_address like :keyword";
			Query<SelectRestaurantInventoryView> result = this.getSession().createQuery(hql, SelectRestaurantInventoryView.class);
			result.setParameter("keyword", "%" + keyword + "%");
			return result.list();
		}
		return Collections.emptyList();
	}
	
	//餐廳日期的模糊搜尋
	public List<SelectRestaurantInventoryView> blurDateFind(java.sql.Date checkInDate, java.sql.Date checkOutDate) {
		String hql = "from SelectRestaurantInventoryView where availability_date between :checkInDate and :checkOutDate";
		Query<SelectRestaurantInventoryView> result = this.getSession().createQuery(hql, SelectRestaurantInventoryView.class);
		result.setParameter("checkInDate", checkInDate);
		result.setParameter("checkOutDate", checkOutDate);
		return result.list();
	}	

}
