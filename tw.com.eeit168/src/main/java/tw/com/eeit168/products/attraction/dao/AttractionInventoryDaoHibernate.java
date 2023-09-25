package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.AttractionInventoryBean;

@Repository
public class AttractionInventoryDaoHibernate implements AttractionInventoryDAO{

	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public AttractionInventoryBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(AttractionInventoryBean.class, id);
		}
		return null;
	}
	
	@Override
	public List<AttractionInventoryBean> selectAll(){
		return this.getSession().createQuery("from AttractionInventoryBean", AttractionInventoryBean.class).list();
	}
	
	@Override
	public AttractionInventoryBean insert(AttractionInventoryBean bean) {
		if(bean != null && bean.getAttractions_inventory_id() != null) {
			AttractionInventoryBean temp = this.getSession().get(AttractionInventoryBean.class, bean.getAttractions_inventory_id());
			if(temp == null) {
				this.getSession().persist(bean);
				return bean;
			}
		}
		return null;
	}
	
	@Override
	public AttractionInventoryBean update(AttractionInventoryBean bean) {
		if(bean != null && bean.getAttractions_inventory_id() != null) {
			AttractionInventoryBean temp = this.getSession().get(AttractionInventoryBean.class, bean.getAttractions_inventory_id());
			if(temp != null) {
				return (AttractionInventoryBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			AttractionInventoryBean temp = this.getSession().get(AttractionInventoryBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
	
}
