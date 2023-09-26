package tw.com.eeit168.products.accommodation.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.Accommodation;

@Service
public class AccommodationSearchService {
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	public List<Accommodation> findAccommodationName(String keyword) {
	    if (keyword != null && !keyword.trim().isEmpty()) {
	        String hql = "FROM Accommodation WHERE accommodationName LIKE :keyword OR accommodationAddress LIKE :keyword";
	        Query<Accommodation> query = getSession().createQuery(hql, Accommodation.class);
	        query.setParameter("keyword", "%" + keyword + "%");
	        return query.list();
	    }
	    return Collections.emptyList(); // Return an empty list if the keyword is null or empty
	}
}
