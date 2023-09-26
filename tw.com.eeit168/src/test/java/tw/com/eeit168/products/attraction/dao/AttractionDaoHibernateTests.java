package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.attraction.model.AttractionBean;

@SpringBootTest
@Transactional
public class AttractionDaoHibernateTests {
	
	@Autowired
	private AttractionDAO attractionDAO;
	
//	@Test
	public void testSelect() {
		AttractionBean select = attractionDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<AttractionBean> selectAll = attractionDAO.selectAll();
		System.out.println(selectAll);
	}
	
//	@Test
	public void testInsert() {
		AttractionBean bean = new AttractionBean();
		bean.setAttractions_name("skdkrhekhre");
		bean.setAttractions_address("dsjvjvoejro");
		bean.setDescriptions("vfnvkners");
		bean.setOpen_time("0830");
		bean.setClose_time("1800");
		bean.setContact_number("389375932");
		bean.setTimes_purchased(100);
		AttractionBean insert = attractionDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		AttractionBean bean = new AttractionBean();
		bean.setAttractions_id(10);
		bean.setAttractions_name("aaaaaaaaaaaaa");
		bean.setAttractions_address("dsjvjvoejro");
		bean.setDescriptions("vfnvkners");
		bean.setOpen_time("0830");
		bean.setClose_time("1800");
		bean.setContact_number("389375932");
		bean.setTimes_purchased(100);
		AttractionBean update = attractionDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = attractionDAO.delete(16);
		System.out.println(delete);
	}
	
}
