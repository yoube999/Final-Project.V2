package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.attraction.model.AttractionPictureBean;

@SpringBootTest
@Transactional //執行完以後rollback
public class AttractionPictureDaoHibernateTests {

	@Autowired
	private AttractionPictureDAO attractionPictureDAO;
	
//	@Test
	public void testSelect() {
		AttractionPictureBean select = attractionPictureDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<AttractionPictureBean> selectAll = attractionPictureDAO.selectAll();
		System.out.println(selectAll);
	}
	
//	@Test
	public void testInsert() {
		AttractionPictureBean bean = new AttractionPictureBean();
		bean.setAttractionsId(2);
		bean.setUrlImage("lvlvlvs");
		AttractionPictureBean insert = attractionPictureDAO.insert(bean);
		System.out.println(insert);
	}
	
//	@Test
	public void testUpdate() {
		AttractionPictureBean bean = new AttractionPictureBean();
		bean.setAttractionsPicturesId(10);
		bean.setAttractionsId(4);
		bean.setUrlImage("lvlvlvs");
		AttractionPictureBean update = attractionPictureDAO.update(bean);
		System.out.println(update);
	}
	
//	@Test
	public void testDelete() {
		boolean delete = attractionPictureDAO.delete(11);
		System.out.println(delete);
	}
	
}
