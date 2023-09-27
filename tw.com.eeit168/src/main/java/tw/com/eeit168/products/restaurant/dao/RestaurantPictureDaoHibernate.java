package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.RestaurantPictureBean;

@Repository //此annotation表示這個類別交由spring來控管
public class RestaurantPictureDaoHibernate implements RestaurantPictureDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public RestaurantPictureBean select(Integer id) {
		if(id != null) {
			return this.getSession().get(RestaurantPictureBean.class, id); //select使用Hibernate的get
		}
		return null;
	}
	
	@Override
	public List<RestaurantPictureBean> selectAll(){
		return this.getSession().createQuery("from RestaurantPictureBean", RestaurantPictureBean.class).list(); //查詢多筆使用Hibernate裡HQL的createQuery
	}
	
	@Override
	public RestaurantPictureBean insert(RestaurantPictureBean bean) {
		this.getSession().persist(bean); //insert使用Hibernate的persist
		return bean;
	}
	
	@Override
	public RestaurantPictureBean update(RestaurantPictureBean bean) {
		if(bean != null && bean.getRestaurant_pictures_id() != null) {
			RestaurantPictureBean temp = this.getSession().get(RestaurantPictureBean.class, bean.getRestaurant_pictures_id());
			if(temp != null) {
				return (RestaurantPictureBean)this.getSession().merge(bean); //update使用Hibernate的merge
			}
		}
		return null;
	}
	
	@Override
	public boolean delete(Integer id) {
		if(id != null) {
			RestaurantPictureBean temp = this.getSession().get(RestaurantPictureBean.class, id);
			if(temp != null) {
				this.getSession().remove(temp); //update使用Hibernate的merge
				return true;
			}
		}
		return false;
	}
	 
}
