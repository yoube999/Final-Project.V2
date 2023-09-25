package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.ReservationRestuarantBean;

@Repository
public class ReservationRestuarantDaoHibernate implements ReservationRestuarantDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public ReservationRestuarantBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(ReservationRestuarantBean.class, id);
		}
		return null;
	}
	
	@Override
	public List<ReservationRestuarantBean> selectAll(){
		return this.getSession().createQuery("from ReservationRestuarantBean", ReservationRestuarantBean.class).list();
	}
	
	@Override
	public ReservationRestuarantBean insert(ReservationRestuarantBean bean) {
		if(bean != null && bean.getReservation_restuarant_id() != null) {
			ReservationRestuarantBean temp = this.getSession().get(ReservationRestuarantBean.class, bean.getReservation_restuarant_id());
			if(temp == null) {
				this.getSession().persist(bean);
				return bean;
			}
		}
		return null;
	}

	@Override
	public ReservationRestuarantBean update(ReservationRestuarantBean bean) {
		if(bean != null && bean.getReservation_restuarant_id() != null) {
			ReservationRestuarantBean temp = this.getSession().get(ReservationRestuarantBean.class, bean.getReservation_restuarant_id());
			if(temp != null) {
				return (ReservationRestuarantBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			ReservationRestuarantBean temp = this.getSession().get(ReservationRestuarantBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
	
}
