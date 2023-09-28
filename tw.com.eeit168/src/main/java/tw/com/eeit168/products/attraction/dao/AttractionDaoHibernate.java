package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.AttractionBean;

@Repository //註解類別作為DAO對象(Data Access Objects)，可以直接對資料表進行操作
public class AttractionDaoHibernate implements AttractionDAO{
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public AttractionBean select(Integer id) {
		return this.getSession().get(AttractionBean.class, id); //select使用Hibernate的get
	}
	
	@Override
	public List<AttractionBean> selectAll() {
		return this.getSession().createQuery("from AttractionBean", AttractionBean.class).list(); //查詢多筆使用Hibernate裡HQL的createQuery
	}
	
	@Override
	public AttractionBean insert(AttractionBean bean) {
		this.getSession().persist(bean); //insert使用Hibernate的persist
		return bean;
	}
	
	@Override
	public AttractionBean update(AttractionBean bean) {
		if(bean != null && bean.getAttractions_id() != null) {
			AttractionBean temp = this.getSession().get(AttractionBean.class, bean.getAttractions_id());
			if(temp != null) {
				return (AttractionBean)this.getSession().merge(bean); //update使用Hibernate的merge
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			AttractionBean temp = this.getSession().get(AttractionBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp); //delete使用Hibernate的remove
				return true;
			}
		}
		return false;
	}
}
