package tw.com.eeit168.products.restaurant.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.restaurant.model.ReservationRestuarantBean;

@SpringBootTest
@Transactional //執行完以後rollback
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
		bean.setRecordId(1);
		bean.setRestaurantId(1);
		LocalDate date = LocalDate.of(2023, 9, 18); //利用LocalDate就可以輸入自己想要的日期
		bean.setReservationDate(java.sql.Date.valueOf(date)); //利用java.sql.Date將想要的日期insert進去
		bean.setTotalCount(5);
		bean.setTotalPrice(3000);
		bean.setRecordRestuarantStatus("dgsewf");
		ReservationRestuarantBean insert = reservationRestuarantDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		ReservationRestuarantBean bean = new ReservationRestuarantBean();
		bean.setReservationRestuarantId(8);
		bean.setRecordId(2);
		bean.setRestaurantId(2);
		LocalDate date = LocalDate.of(2023, 9, 18); //利用LocalDate就可以輸入自己想要的日期
		bean.setReservationDate(java.sql.Date.valueOf(date)); //利用java.sql.Date將想要的日期insert進去
		bean.setTotalCount(5);
		bean.setTotalPrice(3000);
		bean.setRecordRestuarantStatus("dgsewf");
		ReservationRestuarantBean update = reservationRestuarantDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = reservationRestuarantDAO.delete(1);
		System.out.println(delete);
	}
	
}
