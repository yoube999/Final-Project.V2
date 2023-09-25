package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.attraction.model.ReservationAttractionBean;

@SpringBootTest
@Transactional
public class ReservationAttractionDaoHibernateTests {

	@Autowired
	private ReservationAttractionDAO reservationAttractionDAO;
	
//	@Test
	public void testSelect() {
		ReservationAttractionBean select = reservationAttractionDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<ReservationAttractionBean> selectAll = reservationAttractionDAO.selectAll();
		System.out.println(selectAll);
	}
	
//	@Test
	public void testInsert() {
		ReservationAttractionBean bean = new ReservationAttractionBean();
		bean.setReservation_attractions_id(11);
		bean.setRecord_id(1);
		bean.setAttractions_id(1);
		bean.setReservation_date(null);
		bean.setTotal_count(5);
		bean.setTotal_price(3000);
		bean.setRecord_attractions_status("dgsewf");
		ReservationAttractionBean insert = reservationAttractionDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		ReservationAttractionBean bean = new ReservationAttractionBean();
		bean.setReservation_attractions_id(5);
		bean.setRecord_id(3);
		bean.setAttractions_id(3);
		bean.setReservation_date(null);
		bean.setTotal_count(5);
		bean.setTotal_price(2000);
		bean.setRecord_attractions_status("dgsewf");
		ReservationAttractionBean update = reservationAttractionDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = reservationAttractionDAO.delete(1);
		System.out.println(delete);
	}
	
}
