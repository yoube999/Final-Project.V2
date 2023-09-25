package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import tw.com.eeit168.products.attraction.model.AttractionTicketBean;

public interface AttractionTicketDAO {
	
	AttractionTicketBean select(Integer id);
	
	List<AttractionTicketBean> selectAll();
	
	AttractionTicketBean insert(AttractionTicketBean bean);
	
	AttractionTicketBean update(AttractionTicketBean bean);
	
	boolean delete(Integer id);

}
