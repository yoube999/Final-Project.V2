package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import tw.com.eeit168.products.attraction.model.AttractionPictureBean;

public interface AttractionPictureDAO {

	AttractionPictureBean select(Integer id);
	
	List<AttractionPictureBean> selectAll();
	
	AttractionPictureBean insert(AttractionPictureBean bean);
	
	AttractionPictureBean update(AttractionPictureBean bean);
	
	boolean delete(Integer id);
	
}
