package tw.com.eeit168.products.restaurant.service;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.restaurant.model.ReservationRestuarantBean;
import tw.com.eeit168.products.restaurant.repository.ReservationRestuarantRepository;

@Service
public class ReservationRestuarantRepositoryService {
	
	@Autowired
	private ReservationRestuarantRepository reservationRestuarantRepository;
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	public ReservationRestuarantBean create(String json) {
		try {
			ReservationRestuarantBean insert = new ReservationRestuarantBean();
			insert.setRecordId(null);
			insert.setRestaurantId(null);
			insert.setReservationDate(null);
			insert.setTotalCount(null);
			insert.setTotalPrice(null);
			insert.setRecordRestuarantStatus(null);
			return reservationRestuarantRepository.save(insert);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
