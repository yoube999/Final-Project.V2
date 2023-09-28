package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.attraction.model.SelectAttractionsInventoryView;

@SpringBootTest
@Transactional //執行完以後rollback
public class SelectAttractionsInventoryDaoHibernateTests {

	@Autowired
	private SelectAttractionsInventoryDAO selectAttractionsInventoryDAO;
	
//	@Test
	public void testSelect() {
		SelectAttractionsInventoryView select = selectAttractionsInventoryDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<SelectAttractionsInventoryView> selectAll = selectAttractionsInventoryDAO.selectAll();
		System.out.println(selectAll);
	}
	
}
