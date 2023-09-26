package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tw.com.eeit168.products.accommodation.model.AccommodationInventory;

@Repository
public class AccommodationInventoryDAOHibernate implements AccommodationInventoryDAO{
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	@Transactional
	public AccommodationInventory select(Integer id) {
		if(id != null) {
			return this.getSession().get(AccommodationInventory.class, id);
		}
		return null;
	}
	
	@Override
	@Transactional
	public List<AccommodationInventory> selectAll(){
		return this.getSession().createQuery("from AccommodationInventory", AccommodationInventory.class).list();
	}
	
	@Override
	@Transactional
	public AccommodationInventory insert(AccommodationInventory bean) {
		if(bean.getInventoryId() != null && bean.getInventoryId() != 0) {
			bean.setInventoryId(null);
		}
		Session session = getSession();
		session.persist(bean);
		
		return bean;
	}
	
	@Override
	@Transactional
	public AccommodationInventory update(AccommodationInventory bean) {
		if(bean != null && bean.getInventoryId() != null) {
			AccommodationInventory temp = this.getSession().get(AccommodationInventory.class, bean.getInventoryId());
			if(temp != null) {
				return (AccommodationInventory) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	@Transactional
	public boolean delete(Integer id) {
		if(id != null) {
			AccommodationInventory temp = this.getSession().get(AccommodationInventory.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
}
