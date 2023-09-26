package tw.com.eeit168.products.restaurant.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantBean;

@Service
public class RestaurantService {

	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
		
	public List<RestaurantBean> selectName(String restaurant_name) {
		String query = "from RestaurantBean where restaurant_name = :name";
		return this.getSession().createQuery(query, RestaurantBean.class).setParameter("name", restaurant_name).list();
	}
	
	public List<RestaurantBean> select(Integer restaurant_id) {
		String query = "select new List(restaurant_name, descriptions, price)from RestaurantBean where restaurant_id = :id";
		return this.getSession().createQuery(query, RestaurantBean.class).setParameter("id", restaurant_id).list();
		
	}
	
}
