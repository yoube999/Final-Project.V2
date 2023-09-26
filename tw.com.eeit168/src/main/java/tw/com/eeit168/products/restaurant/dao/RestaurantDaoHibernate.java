package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantBean;

@Repository
public class RestaurantDaoHibernate implements RestaurantDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}

	@Override
	public RestaurantBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(RestaurantBean.class, id);
		}
		return null;
	}
	
	@Override
	public List<RestaurantBean> selectAll() {
		return this.getSession().createQuery("from RestaurantBean", RestaurantBean.class).list();
	}
	
	@Override
	public RestaurantBean insert(RestaurantBean bean) {
		this.getSession().persist(bean);
		return bean;
	}
	
	@Override
	public RestaurantBean update(RestaurantBean bean) {
		if(bean != null && bean.getRestaurant_id() != null) {
			RestaurantBean temp = this.getSession().get(RestaurantBean.class, bean.getRestaurant_id());
			if(temp != null) {
				return (RestaurantBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			RestaurantBean temp = this.getSession().get(RestaurantBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
	
}
