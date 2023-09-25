package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;


@Repository
public class AccommodationRoomTypeDAOHibernate implements AccommodationRoomTypeDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	private Session session; 
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	@Override
	public List<AccommodationRoomType> searchByKeyword(String keyword){
		if(keyword != null && !keyword.trim().isEmpty()) {
			String hql = "FROM AccommodationRoomType WHERE roomTypeName LIKE :keyword OR capacity LIKE :keyword";
			Query<AccommodationRoomType> query = getSession().createQuery(hql, AccommodationRoomType.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.list();
		}
		return null;
	}
	
	@Override
	public List<AccommodationRoomType> selectAll(){
		return this.getSession().createQuery("from AccommodationRoomType", AccommodationRoomType.class).list();
	}
	
	@Override
	public AccommodationRoomType insert(AccommodationRoomType bean) {
	    // Set roomTypeId to null to let Hibernate generate it
	    if (bean.getRoomTypeId() != null && bean.getRoomTypeId() != 0) {
	        bean.setRoomTypeId(null);
	        if (bean.getAccommodationId() == null) {
	        	// Set accommodationId to null to let Hibernate generate it
	        	bean.setAccommodationId(null);
	        }
	    }
	    // Save the accommodation room type
	    Session session = getSession();
	    session.persist(bean);

	    return bean;
	}
	
	@Override
	public AccommodationRoomType update(AccommodationRoomType bean) {
		if(bean != null && bean.getRoomTypeId() != null) {
			AccommodationRoomType temp = this.getSession().get(AccommodationRoomType.class, bean.getRoomTypeId());
			if(temp != null) {
				return (AccommodationRoomType) this.getSession().merge(bean);
			}
		}
		
		return null;
		
	}
	
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			AccommodationRoomType temp = this.getSession().get(AccommodationRoomType.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
}
