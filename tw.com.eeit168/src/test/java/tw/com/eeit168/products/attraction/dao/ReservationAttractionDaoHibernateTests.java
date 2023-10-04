package tw.com.eeit168.products.attraction.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.attraction.model.ReservationAttractionBean;

@SpringBootTest
@Transactional //執行完以後rollback
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
		bean.setRecordId(1);
		bean.setAttractionsId(1);
		LocalDate date = LocalDate.of(2023, 9, 20); //利用LocalDate就可以輸入自己想要的日期
		bean.setReservationDate(java.sql.Date.valueOf(date)); //利用java.sql.Date將想要的日期insert進去
		bean.setTotalCount(5);
		bean.setTotalPrice(3000);
		bean.setRecordAttractionsStatus("dgsewf");
		ReservationAttractionBean insert = reservationAttractionDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		ReservationAttractionBean bean = new ReservationAttractionBean();
		bean.setReservationAttractionsId(5);
		bean.setRecordId(3);
		bean.setAttractionsId(3);
		LocalDate date = LocalDate.of(2023, 9, 20); //利用LocalDate就可以輸入自己想要的日期
		bean.setReservationDate(java.sql.Date.valueOf(date)); //利用java.sql.Date將想要的日期insert進去
		bean.setTotalCount(5);
		bean.setTotalPrice(2000);
		bean.setRecordAttractionsStatus("dgsewf");
		ReservationAttractionBean update = reservationAttractionDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = reservationAttractionDAO.delete(1);
		System.out.println(delete);
	}
	
}
