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
public class AccommodationDAOHibernate implements AccommodationDAO {
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
	@Transactional
	public List<Accommodation> searchByKeyword(String keyword) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			String hql = "FROM Accommodation WHERE accommodationName LIKE :keyword OR accommodationAddress LIKE :keyword";
			Query<Accommodation> query = getSession().createQuery(hql, Accommodation.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.list();
		}
		return null;

//		return entityManager.createNamedQuery("Accommodation.searchByKeyword", Accommodation.class)
//                .setParameter("keyword", "%" + keyword + "%")
//                .getResultList();

	}

	@Override
	@Transactional
	public List<Accommodation> selectAll() {
		return this.getSession().createQuery("from Accommodation", Accommodation.class).list();

	}
	
	@Override
	@Transactional
	public Accommodation insert(Accommodation bean) {
		
		if (bean.getAccommodationId() == null) {
	        // Set accommodationId to null to let Hibernate generate it
	        bean.setAccommodationId(null);
	    }

	    // Save the accommodation
	    this.getSession().persist(bean);
	    return bean;
//		if(bean != null) {
//			Accommodation temp = this.getSession().get(Accommodation.class, bean.getAccommodationId());
//			if(temp != null) {
//				this.getSession().persist(bean);
//				return bean;
//			}
//		}
		
//		return null;
		
	}
	@Override
	@Transactional
	public Accommodation update(Accommodation bean) {
		if(bean != null && bean.getAccommodationId()!= null) {
//		   bean != null && bean.getAccommodationId() != null && this.getSession().get(Accommodation.class, bean.getAccommodationId()) != null
			Accommodation temp = this.getSession().get(Accommodation.class, bean.getAccommodationId());
			if(temp != null) {
				return (Accommodation)this.getSession().merge(bean);
			}
		}
		return null;
	}
	@Override
	@Transactional
	public boolean delete(Integer id) {
		if(id != null) {
			Accommodation temp = this.getSession().get(Accommodation.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
}
