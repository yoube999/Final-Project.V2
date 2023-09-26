package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tw.com.eeit168.products.accommodation.model.AccommodationPhotos;

@Repository
public class AccommodationPhotosDAOHibernate implements AccommodationPhotosDAO{
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	@Transactional
	public AccommodationPhotos select(Integer id) {
		if(id != null) {
			return this.getSession().get(AccommodationPhotos.class, id);
		}
		return null;
	}
	
	@Override
	@Transactional
	public List<AccommodationPhotos> selectAll() {
		return this.getSession().createQuery("from AccommodationPhotos", AccommodationPhotos.class).list();
	}
	
	@Override
	@Transactional
	public AccommodationPhotos insert(AccommodationPhotos bean) {
		if(bean.getPhotoId() != null && bean.getPhotoId()!=0) {
			bean.setPhotoId(null);
		}
		Session session = getSession();
		session.persist(bean);
		
		return bean;
	}
	
	@Override
	@Transactional
	public AccommodationPhotos update(AccommodationPhotos bean) {
		if(bean != null && bean.getPhotoId() != null) {
			AccommodationPhotos temp = this.getSession().get(AccommodationPhotos.class, bean.getPhotoId());
			if(temp != null) {
				return(AccommodationPhotos) this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	@Transactional
	public boolean delete(Integer id) {
		if (id != null) {
			AccommodationPhotos temp = this.getSession().get(AccommodationPhotos.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
}
