package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.AttractionPictureBean;

@Repository
public class AttractionPictureDaoHibernate implements AttractionPictureDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public AttractionPictureBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(AttractionPictureBean.class, id);
		}
		return null;
	}
	
	@Override
	public List<AttractionPictureBean> selectAll(){
		return this.getSession().createQuery("from AttractionPictureBean", AttractionPictureBean.class).list();
	}

	@Override
	public AttractionPictureBean insert(AttractionPictureBean bean) {
		this.getSession().persist(bean);
		return bean;
	}
	
	@Override
	public AttractionPictureBean update(AttractionPictureBean bean) {
		if(bean != null && bean.getAttractions_pictures_id() != null) {
			AttractionPictureBean temp = this.getSession().get(AttractionPictureBean.class, bean.getAttractions_pictures_id());
			if(temp != null) {
				return (AttractionPictureBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			AttractionPictureBean temp = this.getSession().get(AttractionPictureBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
	 
}
