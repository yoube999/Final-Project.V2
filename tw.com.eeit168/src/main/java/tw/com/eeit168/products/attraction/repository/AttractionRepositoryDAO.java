package tw.com.eeit168.products.attraction.repository;

import java.util.List;

import tw.com.eeit168.products.attraction.model.AttractionBean;
import tw.com.eeit168.products.attraction.model.SelectAttractionsInventoryView;
import tw.com.eeit168.products.attraction.model.SelectAttractionsPictureView;
import tw.com.eeit168.products.attraction.model.SelectAttractionsTicketView;

public interface AttractionRepositoryDAO {

	public List<AttractionBean> selectTop5();
	
	public List<AttractionBean> findAllByPurchasedDesc();
	
	public List<SelectAttractionsTicketView> findAllByPriceDesc();
	
	public List<SelectAttractionsTicketView> findAllByPriceAsc();
	
	public List<SelectAttractionsInventoryView> blurFind(String keyword);
	
	public List<SelectAttractionsInventoryView> blurDateFind(java.sql.Date orderDate);
	
	public List<SelectAttractionsPictureView> findPictureByName(String name);
	
}
