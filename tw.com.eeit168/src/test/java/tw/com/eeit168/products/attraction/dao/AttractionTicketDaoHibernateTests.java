package tw.com.eeit168.products.attraction.dao;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.attraction.model.AttractionTicketBean;

@SpringBootTest
@Transactional
public class AttractionTicketDaoHibernateTests {
	
	@Autowired
	private AttractionTicketDAO attractionTicketDAO;
	
//	@Test
	public void testSelect() {
		AttractionTicketBean select = attractionTicketDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<AttractionTicketBean> selectAll = attractionTicketDAO.selectAll();
		System.out.println(selectAll);
	}
	
//	@Test
	public void testInsert() {
		AttractionTicketBean bean = new AttractionTicketBean();
		bean.setAttractions_id(1);
		LocalDate date = LocalDate.of(2023, 8, 22); //利用LocalDate就可以輸入自己想要的日期
		bean.setValid_date(java.sql.Date.valueOf(date)); //利用java.sql.Date將想要的日期insert進去
		bean.setAdult_price(599);
		bean.setChild_price(399);
		AttractionTicketBean insert = attractionTicketDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		AttractionTicketBean bean = new AttractionTicketBean();
		bean.setAttractions_ticket_id(21);
		bean.setAttractions_id(1);
		LocalDate date = LocalDate.of(2023, 8, 22); //利用LocalDate就可以輸入自己想要的日期
		bean.setValid_date(java.sql.Date.valueOf(date)); //利用java.sql.Date將想要的日期insert進去
		bean.setAdult_price(599);
		bean.setChild_price(399);
		AttractionTicketBean update = attractionTicketDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = attractionTicketDAO.delete(26);
		System.out.println(delete);
	}

}
