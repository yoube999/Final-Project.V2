package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.attraction.model.AttractionBean;

@SpringBootTest
@Transactional //執行完以後rollback
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
		bean.setAttractionsName("skdkrhekhre");
		bean.setAttractionsAddress("dsjvjvoejro");
		bean.setDescriptions("vfnvkners");
		bean.setOpenTime("0830");
		bean.setCloseTime("1800");
		bean.setContactNumber("389375932");
		bean.setTimesPurchased(100);
		AttractionBean insert = attractionDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		AttractionBean bean = new AttractionBean();
		bean.setAttractionsId(10);
		bean.setAttractionsName("aaaaaaaaaaaaa");
		bean.setAttractionsAddress("dsjvjvoejro");
		bean.setDescriptions("vfnvkners");
		bean.setOpenTime("0830");
		bean.setCloseTime("1800");
		bean.setContactNumber("389375932");
		bean.setTimesPurchased(100);
		AttractionBean update = attractionDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = attractionDAO.delete(16);
		System.out.println(delete);
	}
	
}
