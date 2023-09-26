package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tw.com.eeit168.products.accommodation.model.AccommodationPrice;

@Repository
public class AccommodationPriceDAOHibernate implements AccommodationPriceDAO{
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	@Transactional
	public AccommodationPrice select(Integer id) {
		if(id != null) {
			return this.getSession().get(AccommodationPrice.class, id);
		}
		return null;
	}
	
	@Override
	@Transactional
	public List<AccommodationPrice> selectAll() {
		return this.getSession().createQuery("from AccommodationPrice", AccommodationPrice.class).list();
	}
	
	@Override
	@Transactional
	public AccommodationPrice insert(AccommodationPrice bean) {
		if(bean.getPriceId()!= null && bean.getPriceId() != 0) {
			bean.setPriceId(null);
		}
		Session session = getSession();
		session.persist(bean);
		
		return bean;
	}
	
	@Override
	@Transactional
	public AccommodationPrice update(AccommodationPrice bean) {
		if(bean != null && bean.getPriceId()!= null) {
			AccommodationPrice temp = this.getSession().get(AccommodationPrice.class, bean.getPriceId());
			if(temp != null) {
				return (AccommodationPrice) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	@Transactional
	public boolean delete(Integer id) {
		if (id != null) {
			AccommodationPrice temp = this.getSession().get(AccommodationPrice.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
}
