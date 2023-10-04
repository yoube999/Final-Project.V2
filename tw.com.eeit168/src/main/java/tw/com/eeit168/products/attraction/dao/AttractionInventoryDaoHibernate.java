package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.AttractionInventoryBean;

@Repository //註解類別作為DAO對象(Data Access Objects)，可以直接對資料表進行操作
public class AttractionInventoryDaoHibernate implements AttractionInventoryDAO{

	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public AttractionInventoryBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(AttractionInventoryBean.class, id); //select使用Hibernate的get
		}
		return null;
	}
	
	@Override
	public List<AttractionInventoryBean> selectAll(){
		return this.getSession().createQuery("from AttractionInventoryBean", AttractionInventoryBean.class).list(); //查詢多筆使用Hibernate裡HQL的createQuery
	}
	
	@Override
	public AttractionInventoryBean insert(AttractionInventoryBean bean) {
		this.getSession().persist(bean); //insert使用Hibernate的persist
		return bean;
	}
	
	@Override
	public AttractionInventoryBean update(AttractionInventoryBean bean) {
		if(bean != null && bean.getAttractionsInventoryId() != null) {
			AttractionInventoryBean temp = this.getSession().get(AttractionInventoryBean.class, bean.getAttractionsInventoryId());
			if(temp != null) {
				return (AttractionInventoryBean)this.getSession().merge(bean); //update使用Hibernate的merge
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			AttractionInventoryBean temp = this.getSession().get(AttractionInventoryBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp); //delete使用Hibernate的remove
				return true;
			}
		}
		return false;
	}
	
}
