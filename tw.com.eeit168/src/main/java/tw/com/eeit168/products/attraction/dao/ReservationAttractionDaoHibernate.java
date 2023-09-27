package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.ReservationAttractionBean;

@Repository //此annotation表示這個類別交由spring來控管
public class ReservationAttractionDaoHibernate implements ReservationAttractionDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public ReservationAttractionBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(ReservationAttractionBean.class, id); //select使用Hibernate的get
		}
		return null;
	}
	
	@Override
	public List<ReservationAttractionBean> selectAll(){
		return this.getSession().createQuery("from ReservationAttractionBean", ReservationAttractionBean.class).list(); //查詢多筆使用Hibernate裡HQL的createQuery
	}
	
	@Override
	public ReservationAttractionBean insert(ReservationAttractionBean bean) {
		this.getSession().persist(bean); //insert使用Hibernate的persist
		return bean;
	}
	
	@Override
	public ReservationAttractionBean update(ReservationAttractionBean bean) {
		if(bean != null && bean.getReservation_attractions_id() != null) {
			ReservationAttractionBean temp = this.getSession().get(ReservationAttractionBean.class, bean.getReservation_attractions_id());
			if(temp != null) {
				return (ReservationAttractionBean)this.getSession().merge(bean); //update使用Hibernate的merge
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			ReservationAttractionBean temp = this.getSession().get(ReservationAttractionBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp); //delete使用Hibernate的remove
				return true;
			}
		}
		return false;
	}

}
