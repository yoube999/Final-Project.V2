package tw.com.eeit168.products.attraction.dao;

import java.util.List;
import tw.com.eeit168.products.attraction.model.AttractionBean;

public interface AttractionDAO {

	AttractionBean select(Integer id);
	
	List<AttractionBean> selectAll();
	
	AttractionBean insert(AttractionBean bean);
	
	AttractionBean update(AttractionBean bean);
	
	boolean delete(Integer id);
	
}
