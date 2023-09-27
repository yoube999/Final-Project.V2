package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantBean;

@Repository //此annotation表示這個類別交由spring來控管
public class RestaurantDaoHibernate implements RestaurantDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}

	@Override
	public RestaurantBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(RestaurantBean.class, id); //select使用Hibernate的get
		}
		return null;
	}
	
	@Override
	public List<RestaurantBean> selectAll() {
		return this.getSession().createQuery("from RestaurantBean", RestaurantBean.class).list(); //查詢多筆使用Hibernate裡HQL的createQuery
	}
	
	@Override
	public RestaurantBean insert(RestaurantBean bean) {
		this.getSession().persist(bean); //insert使用Hibernate的persist
		return bean;
	}
	
	@Override
	public RestaurantBean update(RestaurantBean bean) {
		if(bean != null && bean.getRestaurant_id() != null) {
			RestaurantBean temp = this.getSession().get(RestaurantBean.class, bean.getRestaurant_id());
			if(temp != null) {
				return (RestaurantBean)this.getSession().merge(bean); //update使用Hibernate的merge
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			RestaurantBean temp = this.getSession().get(RestaurantBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp); //delete使用Hibernate的remove
				return true;
			}
		}
		return false;
	}
	
}
