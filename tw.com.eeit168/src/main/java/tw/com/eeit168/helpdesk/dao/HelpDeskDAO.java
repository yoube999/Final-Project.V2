package tw.com.eeit168.helpdesk.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.helpdesk.model.HelpDeskBean;

@Repository
public class HelpDeskDAO implements HelpDeskInterFace {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	// 上傳圖片

	// 寫入案件單至資料庫
	@Override
	public HelpDeskBean insert(HelpDeskBean bean) {
		System.out.println(bean);
		if (bean != null) {
			this.getSession().persist(bean);
			return bean;
		}

		return null;
	}

}
