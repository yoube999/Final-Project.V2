package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.attraction.model.AttractionInventoryBean;

@SpringBootTest
@Transactional
public class AttractionInventoryDaoHibernateTests {
	
	@Autowired
	private AttractionInventoryDAO attractionInventoryDAO;
	
//	@Test
	public void testSelect() {
		AttractionInventoryBean select = attractionInventoryDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<AttractionInventoryBean> selectAll = attractionInventoryDAO.selectAll();
		System.out.println(selectAll);
	}
	
//	@Test
	public void testInsert() {
		AttractionInventoryBean bean = new AttractionInventoryBean();
		bean.setAttractions_id(2);
		bean.setAvailability_date(null);
		bean.setAdult(50);
		bean.setChild(30);
		AttractionInventoryBean insert = attractionInventoryDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		AttractionInventoryBean bean = new AttractionInventoryBean();
		bean.setAttractions_inventory_id(21);
		bean.setAttractions_id(2);
		bean.setAvailability_date(null);
		bean.setAdult(50);
		bean.setChild(30);
		AttractionInventoryBean update = attractionInventoryDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = attractionInventoryDAO.delete(21);
		System.out.println(delete);
	}

}
