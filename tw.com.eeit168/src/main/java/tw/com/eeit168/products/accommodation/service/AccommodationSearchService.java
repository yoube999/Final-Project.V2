package tw.com.eeit168.products.accommodation.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.model.AccommodationInventory;
import tw.com.eeit168.products.accommodation.repository.AccommodationInventoryRepository;

@Service
public class AccommodationSearchService {
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Autowired
	private AccommodationInventoryRepository accommodationInventoryRepository;
	
	public List<Accommodation> findAccommodationName(String keyword) {
		if (keyword != null && !keyword.trim().isEmpty()) {
	        String hql = "FROM Accommodation WHERE accommodationName LIKE :keyword OR accommodationAddress LIKE :keyword";
	        Query<Accommodation> query = getSession().createQuery(hql, Accommodation.class);
	        query.setParameter("keyword", "%" + keyword + "%");
	        return query.list();
	    }
	    return Collections.emptyList(); // Return an empty list if the keyword is null or empty
	}
	
	public List<AccommodationInventory> findAccommodationsByDate(java.sql.Date checkInDate, java.sql.Date checkOutDate){
		String hql = "FROM AccommodationInventory WHERE availabilityDate BETWEEN :checkInDate AND :checkOutDate";
		Query<AccommodationInventory> query = getSession().createQuery(hql, AccommodationInventory.class);
		query.setParameter("checkInDate", checkInDate);
		query.setParameter("checkOutDate", checkOutDate);
		
		return query.list();
	}
	
	public List<AccommodationInventory> findAccommodationsByRooms(Integer requiredRooms){
		return accommodationInventoryRepository.findByAvailableRooms(requiredRooms);
	}
//	public List<AccommodationInventory> findAccommodationsByCriteria(java.sql.Date checkInDate, int requiredRooms){
//			String hql = "FROM AccommodationInventory WHERE availabilityDate = :checkInDate AND availableRooms >= :requiredRooms ";
//			Query<AccommodationInventory> query = getSession().createQuery(hql, AccommodationInventory.class);
//			query.setParameter("checkInDate", checkInDate);
//			query.setParameter("requiredRooms", requiredRooms);
//			return null;
//	}
	

}
