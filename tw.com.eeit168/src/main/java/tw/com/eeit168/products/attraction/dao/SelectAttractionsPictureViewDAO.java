package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import tw.com.eeit168.products.attraction.model.SelectAttractionsPictureView;

public interface SelectAttractionsPictureViewDAO {
	public List<SelectAttractionsPictureView> selectAllAttractionPicture(int start, int row);
	
	public long count();
}
