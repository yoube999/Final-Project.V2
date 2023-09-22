package tw.com.eeit168.products.accommodation.dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.Accommodation;

@Repository
@Transactional
public class AccommodationDAOHibernate implements AccommodationDAO{
//	@PersistenceContext
//	private Session session;
//	
//	public Session getSession() {
//		return session;
//	}
	
	
	  @PersistenceContext
	    private EntityManager entityManager;

	    public Session getSession() {
	        return entityManager.unwrap(Session.class);
	    }
//	@Override
//	public Accommodation select(Integer id) {
//			if(id != null) {
//				return this.getSession().get(Accommodation.class, id);
//			}
//		return null;
//	}
	
	@Override
	public List<Accommodation> searchByKeyword(String keyword){
		if(keyword != null && !keyword.trim().isEmpty()) {
			String hql = "FROM Accommodation WHERE accommodationName LIKE :keyword OR accommodationAddress LIKE :keyword";
			Query<Accommodation> query = getSession().createQuery(hql, Accommodation.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.list();
		}
		return null;
		
//		return entityManager.createNamedQuery("Accommodation.searchByKeyword", Accommodation.class)
//                .setParameter("keyword", "%" + keyword + "%")
//                .getResultList();
//		
	}
	
	
}
