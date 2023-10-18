package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.SelectAttractionsPictureView;

@Repository
public class SelectAttractionsPictureViewDAOImpl implements SelectAttractionsPictureViewDAO{

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}
	
	
	@Override
	public List<SelectAttractionsPictureView> selectAllAttractionPicture(int start, int row) {
		String hqlquery = "FROM SelectAttractionsPictureView";
		List <SelectAttractionsPictureView> attractions= session.createQuery(hqlquery, SelectAttractionsPictureView.class).setFirstResult(start * row).setMaxResults(row).list();
		return attractions; 
	}

	@Override
	public long count() {
		String countQuery = "SELECT COUNT(*) FROM SelectAttractionsPictureView";
		Long count = (Long) session.createQuery(countQuery, Long.class).uniqueResult();
		return count != null ? count : 0;
	}

}
