package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.AttractionTicketBean;

@Repository
public class AttractionTicketDaoHibernate implements AttractionTicketDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public AttractionTicketBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(AttractionTicketBean.class, id);
		}
		return null;
	}
	
	@Override
	public List<AttractionTicketBean> selectAll(){
		return this.getSession().createQuery("from AttractionTicketBean", AttractionTicketBean.class).list();
	}
	
	@Override
	public AttractionTicketBean insert(AttractionTicketBean bean) {
		this.getSession().persist(bean);
		return bean;
	}
	
	@Override
	public AttractionTicketBean update(AttractionTicketBean bean) {
		if(bean != null && bean.getAttractions_ticket_id() != null) {
			AttractionTicketBean temp = this.getSession().get(AttractionTicketBean.class, bean.getAttractions_ticket_id());
			if(temp != null) {
				return (AttractionTicketBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			AttractionTicketBean temp = this.getSession().get(AttractionTicketBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}

}
