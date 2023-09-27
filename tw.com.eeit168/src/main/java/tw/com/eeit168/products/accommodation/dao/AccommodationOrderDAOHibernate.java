package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tw.com.eeit168.products.accommodation.model.AccommodationOrder;

@Repository
public class AccommodationOrderDAOHibernate implements AccommodationOrderDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	@Transactional
	public AccommodationOrder select(Integer id) {
		if(id != null) {
			return this.getSession().get(AccommodationOrder.class, id);
		}
		return null;
	}
	
	@Override
	@Transactional
	public List<AccommodationOrder> selectAll(){
		return this.getSession().createQuery("from AccommodationOrder", AccommodationOrder.class).list();
	}
	
	@Override
	@Transactional
	public AccommodationOrder insert(AccommodationOrder bean) {
		if(bean.getOrderId() != null && bean.getOrderId() != 0) {
			bean.setOrderId(null);
		}
		Session session = getSession();
		session.persist(bean);
		
		return bean;
	}
	
	@Override
	@Transactional
	public AccommodationOrder update(AccommodationOrder bean) {
		if(bean != null && bean.getOrderId()!= null) {
			AccommodationOrder temp = this.getSession().get(AccommodationOrder.class, bean.getOrderId());
			if(temp != null) {
				return (AccommodationOrder) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	@Transactional
	public boolean delete(Integer id) {
		if (id != null) {
			AccommodationOrder temp = this.getSession().get(AccommodationOrder.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
}
