package tw.com.eeit168.products.accommodation.repository;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.model.AccommodationPrice;
import tw.com.eeit168.products.accommodation.model.SelectAccommodationInventoryRoomtypePriceView;

public class AccommodationRepositoryDAOImpl implements AccommodationRepositoryDAO {
	@PersistenceContext // 允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;

	public Session getSession() {
		return session;
	}

	public List<Accommodation> findAccommodationName(String keyword) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			String hql = "FROM Accommodation WHERE accommodationName LIKE :keyword OR accommodationAddress LIKE :keyword";
			Query<Accommodation> query = this.getSession().createQuery(hql, Accommodation.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.list();
		}
		return Collections.emptyList(); // Return an empty list if the keyword is null or empty
	}

	// Top 5
	public List<SelectAccommodationInventoryRoomtypePriceView> selectTop5() {
		String hql = "from SelectAccommodationInventoryRoomtypePriceView order by timesPurchased desc";
		return this.getSession().createQuery(hql, SelectAccommodationInventoryRoomtypePriceView.class).setMaxResults(5).list();
	}
	
	@Override
	public List<SelectAccommodationInventoryRoomtypePriceView> findByAvailabilityDateBetween(Date checkinDate,
	        Date checkoutDate) {
		String hql = "FROM SelectAccommodationInventoryRoomtypePriceView "
	            + "WHERE availabilityDate BETWEEN :checkinDate AND :checkoutDate "
	            + "AND availableRooms > 0";

	    Query<SelectAccommodationInventoryRoomtypePriceView> query = this.getSession()
	            .createQuery(hql, SelectAccommodationInventoryRoomtypePriceView.class);
	    query.setParameter("checkinDate", new java.sql.Date(checkinDate.getTime()));
	    query.setParameter("checkoutDate", new java.sql.Date(checkoutDate.getTime()));

	    return query.getResultList();
	}
	

	
	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceRange(Integer minPrice,
			Integer maxPrice) {
		 String hql = "FROM SelectAccommodationInventoryRoomtypePriceView WHERE weekdayPrice BETWEEN :minPrice AND :maxPrice";
		 Query<SelectAccommodationInventoryRoomtypePriceView> query = this.getSession().createQuery(hql, SelectAccommodationInventoryRoomtypePriceView.class);
	        query.setParameter("minPrice", minPrice);
	        query.setParameter("maxPrice", maxPrice);
	        return query.list();
	}

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceRange(Integer minPrice,
			Integer maxPrice) {
		 String hql = "FROM SelectAccommodationInventoryRoomtypePriceView WHERE weekendPrice BETWEEN :minPrice AND :maxPrice";
	        Query<SelectAccommodationInventoryRoomtypePriceView> query = this.getSession().createQuery(hql, SelectAccommodationInventoryRoomtypePriceView.class);
	        query.setParameter("minPrice", minPrice);
	        query.setParameter("maxPrice", maxPrice);
	        return query.list();
	}

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceDesc() {
		String hql = "from SelectAccommodationInventoryRoomtypePriceView order by weekdayPrice desc";
		return this.getSession().createQuery(hql, SelectAccommodationInventoryRoomtypePriceView.class).list();
	}

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceDesc() {
		String hql = "from SelectAccommodationInventoryRoomtypePriceView order by weekendPrice desc";
		return this.getSession().createQuery(hql, SelectAccommodationInventoryRoomtypePriceView.class).list();
	}

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceAsc() {
		String hql = "from SelectAccommodationInventoryRoomtypePriceView order by weekdayPrice asc";
		return this.getSession().createQuery(hql, SelectAccommodationInventoryRoomtypePriceView.class).list();
	}

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceAsc() {
		String hql = "from SelectAccommodationInventoryRoomtypePriceView order by weekendPrice asc";
		return this.getSession().createQuery(hql, SelectAccommodationInventoryRoomtypePriceView.class).list();
	}
//	public List<AccommodationInventory> findByAvailabilityDateBetween(java.sql.Date checkInDate,
//			java.sql.Date checkOutDate) {
//		String hql = "FROM AccommodationInventory WHERE availabilityDate BETWEEN :checkInDate AND :checkOutDate";
//		Query<AccommodationInventory> query = this.getSession().createQuery(hql, AccommodationInventory.class);
//		query.setParameter("checkInDate", checkInDate);
//		query.setParameter("checkOutDate", checkOutDate);
//
//		return query.list();
//	}

	
}
