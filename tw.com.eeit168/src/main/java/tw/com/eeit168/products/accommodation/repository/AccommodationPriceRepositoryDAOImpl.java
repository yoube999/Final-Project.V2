package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.hibernate.Session;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.AccommodationPrice;

public class AccommodationPriceRepositoryDAOImpl implements AccommodationPriceRepositoryDAO {
//	@PersistenceContext
//	private Session session;
//
//	public Session getSession() {
//		return session;
//	}
//
//	public List<AccommodationPrice> findAllByWeekdayPriceDesc() {
//		String hql = "from AccommodationPrice order by weekdayPrice desc";
//		return this.getSession().createQuery(hql, AccommodationPrice.class).list();
//	}
//
//	public List<AccommodationPrice> findAllByWeekendPriceDesc() {
//		String hql = "from AccommodationPrice order by weekendPrice desc";
//		return this.getSession().createQuery(hql, AccommodationPrice.class).list();
//	}
//
//	public List<AccommodationPrice> findAllByWeekdayPriceAsc() {
//		String hql = "from AccommodationPrice order by weekdayPrice asc";
//		return this.getSession().createQuery(hql, AccommodationPrice.class).list();
//	}
//
//	public List<AccommodationPrice> findAllByWeekendPriceAsc() {
//		String hql = "from AccommodationPrice order by weekendPrice asc";
//		return this.getSession().createQuery(hql, AccommodationPrice.class).list();
//	}

}
