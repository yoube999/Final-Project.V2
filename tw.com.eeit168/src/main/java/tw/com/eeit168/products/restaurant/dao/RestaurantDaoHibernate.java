package tw.com.eeit168.products.restaurant.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
	
}
