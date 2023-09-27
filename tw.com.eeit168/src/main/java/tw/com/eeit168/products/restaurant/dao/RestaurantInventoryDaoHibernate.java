package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantInventoryBean;

@Repository //此annotation表示這個類別交由spring來控管
public class RestaurantInventoryDaoHibernate implements RestaurantInventoryDAO{

	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public RestaurantInventoryBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(RestaurantInventoryBean.class, id); //select使用Hibernate的get
		}
		return null;
	}
	
	@Override
	public List<RestaurantInventoryBean> selectAll() {
		return this.getSession().createQuery("from RestaurantInventoryBean", RestaurantInventoryBean.class).list(); //查詢多筆使用Hibernate裡HQL的createQuery
	}
	
	@Override
	public RestaurantInventoryBean insert(RestaurantInventoryBean bean) {
		this.getSession().persist(bean); //insert使用Hibernate的persist
		return bean;
	}
	
	@Override
	public RestaurantInventoryBean update(RestaurantInventoryBean bean) {
		if(bean != null && bean.getRestaurant_Inventory_id() != null) {
			RestaurantInventoryBean temp = this.getSession().get(RestaurantInventoryBean.class, bean.getRestaurant_Inventory_id());
			if(temp != null) {
				return (RestaurantInventoryBean)this.getSession().merge(bean); //update使用Hibernate的merge
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			RestaurantInventoryBean temp = this.getSession().get(RestaurantInventoryBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp); //update使用Hibernate的merge
				return true;
			}
		}
		return false;
	}
	
}
