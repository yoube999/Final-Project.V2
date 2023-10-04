package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.ReservationRestuarantBean;

@Repository //註解類別作為DAO對象(Data Access Objects)，可以直接對資料表進行操作
public class ReservationRestuarantDaoHibernate implements ReservationRestuarantDAO{
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public ReservationRestuarantBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(ReservationRestuarantBean.class, id); //select使用Hibernate的get
		}
		return null;
	}
	
	@Override
	public List<ReservationRestuarantBean> selectAll(){
		return this.getSession().createQuery("from ReservationRestuarantBean", ReservationRestuarantBean.class).list(); //查詢多筆使用Hibernate裡HQL的createQuery
	}
	
	@Override
	public ReservationRestuarantBean insert(ReservationRestuarantBean bean) {
		this.getSession().persist(bean); //insert使用Hibernate的persist
		return bean;
	}

	@Override
	public ReservationRestuarantBean update(ReservationRestuarantBean bean) {
		if(bean != null && bean.getReservationRestuarantId() != null) {
			ReservationRestuarantBean temp = this.getSession().get(ReservationRestuarantBean.class, bean.getReservationRestuarantId());
			if(temp != null) {
				return (ReservationRestuarantBean)this.getSession().merge(bean); //update使用Hibernate的merge
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			ReservationRestuarantBean temp = this.getSession().get(ReservationRestuarantBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp); //update使用Hibernate的merge
				return true;
			}
		}
		return false;
	}
	
}
