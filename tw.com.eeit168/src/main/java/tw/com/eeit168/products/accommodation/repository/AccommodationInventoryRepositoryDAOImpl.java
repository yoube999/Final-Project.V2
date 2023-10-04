package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.AccommodationInventory;
@Repository
public class AccommodationInventoryRepositoryDAOImpl implements AccommodationInventoryRepositoryDAO{
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	
	
	public List<AccommodationInventory> findByAvailabilityDateBetween(java.sql.Date checkInDate,
			java.sql.Date checkOutDate) {
		String hql = "FROM AccommodationInventory WHERE availabilityDate BETWEEN :checkInDate AND :checkOutDate";
		Query<AccommodationInventory> query = this.getSession().createQuery(hql, AccommodationInventory.class);
		query.setParameter("checkInDate", checkInDate);
		query.setParameter("checkOutDate", checkOutDate);

		return query.list();
	}
	
}
