package tw.com.eeit168.products.restaurant.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantBean;

@Service //註解類別處理運算邏輯(企業邏輯)
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
		String query = "select new List(restaurant_name, descriptions, price)from RestaurantBean where restaurant_id = :id"; //搜尋多的欄位使用 new List()包起來
		return this.getSession().createQuery(query, RestaurantBean.class).setParameter("id", restaurant_id).list();
		
	}
	
	public List<RestaurantBean> selectList(){
		String query = "select new List(restaurant_name, descriptions, price)from RestaurantBean"; //搜尋多的欄位使用 new List()包起來
		return this.getSession().createQuery(query, RestaurantBean.class).list();
	}
	
	public RestaurantBean selectId(Integer id) {
		if(id != null) {
			return this.getSession().get(RestaurantBean.class, id);
		}
		return null;
	}
	
}
