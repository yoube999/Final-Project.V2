package tw.com.eeit168.products;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;

@Repository
public class RecordDaoHibernate {
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	public RecordBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(RecordBean.class, id);			
		}
		return null;
	}
	
	public List<RecordBean> selectAll(){
		return this.getSession().createQuery("from RecordBean", RecordBean.class).list();
	}
	
	public RecordBean insert(RecordBean bean) {
		this.getSession().merge(bean);
		return bean;
	}
	
	public RecordBean update(RecordBean bean) {
		if(bean != null && bean.getRecordId() != null) {
			RecordBean temp = this.getSession().get(RecordBean.class, bean.getRecordId());
			if(temp != null) {
				return (RecordBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	public boolean delete(Integer id) {
		if(id != null) {
			RecordBean temp = this.getSession().get(RecordBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}

}
