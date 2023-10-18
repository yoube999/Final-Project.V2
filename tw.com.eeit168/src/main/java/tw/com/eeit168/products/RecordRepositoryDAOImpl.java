package tw.com.eeit168.products;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.PersistenceContext;

public class RecordRepositoryDAOImpl implements RecordRepositoryDAO {
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	public List<RecordBean> findByMemberId(Integer id) {
		String hql = "from RecordBean where memberProfileId = :memberId";
		Query<RecordBean> result = this.getSession().createQuery(hql, RecordBean.class);
		result.setParameter("memberId", id);
		return result.list();
	}

}
