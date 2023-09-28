package tw.com.eeit168.helpdesk.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.helpdesk.model.HelpDeskProcessBean;

@Repository
public class HelpDeskProcessDAO implements HelpDeskProcessInterFace {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	// 寫入案件歷程至資料庫
	@Override
	public HelpDeskProcessBean insertComment(HelpDeskProcessBean bean) {
		if (bean != null) {
			this.getSession().persist(bean);
			return bean;

		}

		return null;
	}

}
