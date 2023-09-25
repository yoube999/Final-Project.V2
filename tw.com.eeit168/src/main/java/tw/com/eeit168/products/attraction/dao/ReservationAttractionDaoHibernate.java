package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.ReservationAttractionBean;

@Repository
public class ReservationAttractionDaoHibernate implements ReservationAttractionDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public ReservationAttractionBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(ReservationAttractionBean.class, id);
		}
		return null;
	}
	
	@Override
	public List<ReservationAttractionBean> selectAll(){
		return this.getSession().createQuery("from ReservationAttractionBean", ReservationAttractionBean.class).list();
	}
	
	@Override
	public ReservationAttractionBean insert(ReservationAttractionBean bean) {
		this.getSession().persist(bean);
		return bean;
	}
	
	@Override
	public ReservationAttractionBean update(ReservationAttractionBean bean) {
		if(bean != null && bean.getReservation_attractions_id() != null) {
			ReservationAttractionBean temp = this.getSession().get(ReservationAttractionBean.class, bean.getReservation_attractions_id());
			if(temp != null) {
				return (ReservationAttractionBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			ReservationAttractionBean temp = this.getSession().get(ReservationAttractionBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}

}
