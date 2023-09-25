package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.AttractionBean;

@Repository
public class AttractionDaoHibernate implements AttractionDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public AttractionBean select(Integer id) {
		return this.getSession().get(AttractionBean.class, id);
	}
	
	@Override
	public List<AttractionBean> selectAll() {
		return this.getSession().createQuery("from AttractionBean", AttractionBean.class).list();
	}
	
	@Override
	public AttractionBean insert(AttractionBean bean) {
		this.getSession().persist(bean);
		return bean;
	}
	
	@Override
	public AttractionBean update(AttractionBean bean) {
		if(bean != null && bean.getAttractions_id() != null) {
			AttractionBean temp = this.getSession().get(AttractionBean.class, bean.getAttractions_id());
			if(temp != null) {
				return (AttractionBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			AttractionBean temp = this.getSession().get(AttractionBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
}
