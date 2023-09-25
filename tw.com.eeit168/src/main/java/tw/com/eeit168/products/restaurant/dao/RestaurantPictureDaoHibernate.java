package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantPictureBean;

@Repository
public class RestaurantPictureDaoHibernate implements RestaurantPictureDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public RestaurantPictureBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(RestaurantPictureBean.class, id);
		}
		return null;
	}
	
	@Override
	public List<RestaurantPictureBean> selectAll(){
		return this.getSession().createQuery("from RestaurantPictureBean", RestaurantPictureBean.class).list();
	}
	
	@Override
	public RestaurantPictureBean insert(RestaurantPictureBean bean) {
		this.getSession().persist(bean);
		return bean;
	}
	
	@Override
	public RestaurantPictureBean update(RestaurantPictureBean bean) {
		if(bean != null && bean.getRestaurant_pictures_id() != null) {
			RestaurantPictureBean temp = this.getSession().get(RestaurantPictureBean.class, bean.getRestaurant_pictures_id());
			if(temp != null) {
				return (RestaurantPictureBean)this.getSession().merge(bean);
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			RestaurantPictureBean temp = this.getSession().get(RestaurantPictureBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp);
				return true;
			}
		}
		return false;
	}
	 
}
