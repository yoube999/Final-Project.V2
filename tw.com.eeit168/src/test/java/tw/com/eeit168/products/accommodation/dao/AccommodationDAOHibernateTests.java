package tw.com.eeit168.products.accommodation.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.accommodation.model.Accommodation;


@SpringBootTest
@Transactional
public class AccommodationDAOHibernateTests {
	@Autowired
	private AccommodationDAO accommodationDAO;
	
	@Test
	public void testSelect() {
		Accommodation select = accommodationDAO.select(10);
		System.out.println(select);
	}
}
