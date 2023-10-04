package tw.com.eeit168.helpdesk.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;

@Repository
public class HelpDeskProductsDAO implements HelpDeskProductsInterFace {
	
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

}
