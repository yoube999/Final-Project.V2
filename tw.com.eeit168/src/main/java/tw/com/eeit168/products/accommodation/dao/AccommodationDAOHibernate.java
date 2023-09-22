package tw.com.eeit168.products.accommodation.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.Accommodation;

@Repository
public class AccommodationDAOHibernate implements AccommodationDAO{
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public Accommodation select(Integer id) {
			if(id != null) {
				return this.getSession().get(Accommodation.class, id);
			}
		return null;
	}
}
