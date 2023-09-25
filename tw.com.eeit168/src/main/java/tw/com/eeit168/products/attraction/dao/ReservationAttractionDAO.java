package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import tw.com.eeit168.products.attraction.model.ReservationAttractionBean;

public interface ReservationAttractionDAO {
	
	ReservationAttractionBean select(Integer id);
	
	List<ReservationAttractionBean> selectAll();
	
	ReservationAttractionBean insert(ReservationAttractionBean bean);
	
	ReservationAttractionBean update(ReservationAttractionBean bean);
	
	boolean delete(Integer id);
 
}