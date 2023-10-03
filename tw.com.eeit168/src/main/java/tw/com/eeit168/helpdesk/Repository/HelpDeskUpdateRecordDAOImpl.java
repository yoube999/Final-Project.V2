package tw.com.eeit168.helpdesk.Repository;

import org.hibernate.Session;

import jakarta.persistence.PersistenceContext;

public class HelpDeskUpdateRecordDAOImpl implements HelpDeskUpdateRecordInterFace {

	@PersistenceContext // 允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;

	public Session getSession() {
		return session;
	}

}
