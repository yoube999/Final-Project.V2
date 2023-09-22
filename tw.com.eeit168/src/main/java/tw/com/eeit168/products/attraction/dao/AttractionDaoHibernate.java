package tw.com.eeit168.products.attraction.dao;

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
	
}
