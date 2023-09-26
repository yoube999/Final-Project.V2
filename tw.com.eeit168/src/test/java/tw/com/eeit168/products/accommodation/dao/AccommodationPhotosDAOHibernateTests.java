package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import tw.com.eeit168.products.accommodation.model.AccommodationPhotos;

@SpringBootTest
//@Transactional
public class AccommodationPhotosDAOHibernateTests {
	@Autowired
	private AccommodationPhotosDAO accommodationPhotosDAO;
	
	@PersistenceContext
	private EntityManager entityManager;
	
//	@Test
	public void testSelect() {
		AccommodationPhotos select = accommodationPhotosDAO.select(1);
		System.out.println("select=" +select);
	}
	
//	@Test
	public void testSelectAll() {
		List<AccommodationPhotos> selectAll = accommodationPhotosDAO.selectAll();
		System.out.println("selectAll" +selectAll);
	}
	
//	@Test
	public void testInsert() {
		AccommodationPhotos accommodationPhotosInsert = new AccommodationPhotos();
		accommodationPhotosInsert.setAccommodationId(10);
		accommodationPhotosInsert.setPhotoUrl("http://insertexample.com/photoexample.jpg");
		
		AccommodationPhotos result = accommodationPhotosDAO.insert(accommodationPhotosInsert);
		System.out.println("新增資料為" +result);
	}
	
	
//	@Test
	public void testUpdate() {
		AccommodationPhotos accommodationPhotosUpdate = new AccommodationPhotos();
		accommodationPhotosUpdate.setPhotoId(12);
		accommodationPhotosUpdate.setAccommodationId(10);
		accommodationPhotosUpdate.setPhotoUrl("http://updateexample.com/photoexample.jpg");
		
		AccommodationPhotos update = accommodationPhotosDAO.update(accommodationPhotosUpdate);
		System.out.println("修改資料為" + update);
	}
	
	@Test
	public void testDelete() {
		boolean delete = accommodationPhotosDAO.delete(12);
		System.out.println("刪除資料" +delete);
	}
	
}
