package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantInventoryBean;

@Repository
public class RestaurantInventoryDaoHibernate implements RestaurantInventoryDAO{

	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public RestaurantInventoryBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(RestaurantInventoryBean.class, id);
		}
		return null;
	}
	
	@Override
	public List<RestaurantInventoryBean> selectAll() {
		return this.getSession().createQuery("from RestaurantInventoryBean", RestaurantInventoryBean.class).list();
	}
	
	@Override
	public RestaurantInventoryBean insert(RestaurantInventoryBean bean) {
		this.getSession().persist(bean);
		return bean;
	}
	
	@Override
	public RestaurantInventoryBean update(RestaurantInventoryBean bean) {
		if(bean != null && bean.getRestaurant_Inventory_id() != null) {
			RestaurantInventoryBean temp = this.getSession().get(RestaurantInventoryBean.class, bean.getRestaurant_Inventory_id());
			if(temp != null) {
				return (RestaurantInventoryBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			RestaurantInventoryBean temp = this.getSession().get(RestaurantInventoryBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
	
}
