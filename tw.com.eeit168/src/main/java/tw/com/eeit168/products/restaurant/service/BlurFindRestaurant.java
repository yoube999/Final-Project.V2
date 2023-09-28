package tw.com.eeit168.products.restaurant.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;

@Service //註解類別處理運算邏輯(企業邏輯)
public class BlurFindRestaurant {
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	public List<SelectRestaurantInventoryView> blurFind(String keyword){
		if(keyword != null && !keyword.isEmpty()) {
			String sql = "from SelectRestaurantInventoryView where restaurant_name like :keyword or restaurant_address like :keyword";
			Query<SelectRestaurantInventoryView> query = this.getSession().createQuery(sql, SelectRestaurantInventoryView.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.list();
		}
		return Collections.emptyList();
	}

}
