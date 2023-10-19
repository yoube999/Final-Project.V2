package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureView;

@Repository
public class SelectRestaurantPictureViewDAOImpl implements SelectRestaurantPictureViewDAO{
	
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	@Override
	public List<SelectRestaurantPictureView> selectAllRestaurantPicture(int start, int row) {
		String hqlquery = "FROM SelectRestaurantPictureView";
		List <SelectRestaurantPictureView> restaurants= session.createQuery(hqlquery, SelectRestaurantPictureView.class).setFirstResult(start * row).setMaxResults(row).list();
		return restaurants;
	}
	
	@Override
	public long count() {
		String countQuery = "SELECT COUNT(*) FROM SelectRestaurantPictureView";
		Long count = (Long) session.createQuery(countQuery, Long.class).uniqueResult();
		return count != null ? count : 0;
	}

}
