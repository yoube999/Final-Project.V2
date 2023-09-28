package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.restaurant.model.SelectRestaurantInventoryView;

@SpringBootTest
@Transactional //執行完以後rollback
public class SelectRestaurantInventoryDaoHibernateTests {

	@Autowired
	private SelectRestaurantInventoryDAO selectRestaurantInventoryDAO;
	
//	@Test
	public void testSelect() {
		SelectRestaurantInventoryView select = selectRestaurantInventoryDAO.select(1);
		System.out.println(select);
	}
	
//	@Test
	public void testSelectAll() {
		List<SelectRestaurantInventoryView> selectAll = selectRestaurantInventoryDAO.selectAll();
		System.out.println(selectAll);
	}
	
}
