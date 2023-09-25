package tw.com.eeit168.products.attraction.dao;

import java.util.List;

import tw.com.eeit168.products.attraction.model.AttractionInventoryBean;

public interface AttractionInventoryDAO {

	AttractionInventoryBean select(Integer id);
	
	List<AttractionInventoryBean> selectAll();
	
	AttractionInventoryBean insert(AttractionInventoryBean bean);
	
	AttractionInventoryBean update(AttractionInventoryBean bean);
	
	boolean delete(Integer id);
	
}
