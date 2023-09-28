package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;

@Repository //註解類別作為DAO對象(Data Access Objects)，可以直接對資料表進行操作
public class SelectRestaurantInventoryDaoHibernate implements SelectRestaurantInventoryDAO{

	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	@Override
	public SelectRestaurantInventoryView select(Integer id) {
		if(id != null) {
			return this.getSession().get(SelectRestaurantInventoryView.class, id); //select使用Hibernate的get
		}
		return null;
	}
	
	@Override
	public List<SelectRestaurantInventoryView> selectAll() {
		return this.getSession().createQuery("from SelectRestaurantInventoryView", SelectRestaurantInventoryView.class).list(); //查詢多筆使用Hibernate裡HQL的createQuery
	}
	
}
