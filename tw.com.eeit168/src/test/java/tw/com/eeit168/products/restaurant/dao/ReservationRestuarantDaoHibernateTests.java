package tw.com.eeit168.products.restaurant.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.restaurant.model.ReservationRestuarantBean;

@SpringBootTest
@Transactional
public class ReservationRestuarantDaoHibernateTests {

	@Autowired
	private ReservationRestuarantDAO reservationRestuarantDAO;
	
//	@Test
	public void testSelect() {
		ReservationRestuarantBean select = reservationRestuarantDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<ReservationRestuarantBean> selectAll = reservationRestuarantDAO.selectAll();
		System.out.println(selectAll);
	}
	
//	@Test
	public void testInsert() {
		ReservationRestuarantBean bean = new ReservationRestuarantBean();
		bean.setRecord_id(1);
		bean.setRestaurant_id(1);
		LocalDate date = LocalDate.of(2023, 9, 18);
		bean.setReservation_date(java.sql.Date.valueOf(date));
		bean.setTotal_count(5);
		bean.setTotal_price(3000);
		bean.setRecord_restuarant_status("dgsewf");
		ReservationRestuarantBean insert = reservationRestuarantDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		ReservationRestuarantBean bean = new ReservationRestuarantBean();
		bean.setReservation_restuarant_id(8);
		bean.setRecord_id(2);
		bean.setRestaurant_id(2);
		bean.setReservation_date(null);
		bean.setTotal_count(5);
		bean.setTotal_price(3000);
		bean.setRecord_restuarant_status("dgsewf");
		ReservationRestuarantBean update = reservationRestuarantDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = reservationRestuarantDAO.delete(1);
		System.out.println(delete);
	}
	
}
