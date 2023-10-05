package tw.com.eeit168.products.attraction.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.AttractionBean;
import tw.com.eeit168.products.attraction.model.SelectAttractionsInventoryView;
import tw.com.eeit168.products.attraction.model.SelectAttractionsPictureView;
import tw.com.eeit168.products.attraction.model.SelectAttractionsTicketView;
import tw.com.eeit168.products.attraction.repository.AttractionRepository;

@Service //註解類別處理運算邏輯(企業邏輯)
@Transactional
public class AttractionRepositoryService {

	@Autowired
	private AttractionRepository attractionRepository;
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	//以id搜尋
	public AttractionBean findById(Integer id) {
		Optional<AttractionBean> result = attractionRepository.findById(id);
		if(result != null && result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	//搜尋全部
	public List<AttractionBean> findAll(){
		return attractionRepository.findAll();
	}
	
	//Top5
	public List<SelectAttractionsTicketView> selectTop5() {
		return attractionRepository.selectTop5();
	}
	
	//以被購買次數搜尋(多-少)
	public List<AttractionBean> findAllByPurchasedDesc() {
		return attractionRepository.findAllByPurchasedDesc();
	}
	
	//以價格搜尋(高-低)
	public List<SelectAttractionsTicketView> findAllByPriceDesc() {
		return attractionRepository.findAllByPriceDesc();
	}
	
	//以價格搜尋(低-高)
	public List<SelectAttractionsTicketView> findAllByPriceAsc() {
		return attractionRepository.findAllByPriceAsc();
	}
	
	//景點名稱及地點的模糊搜尋
	public List<SelectAttractionsInventoryView> blurFind(String keyword) {
		return attractionRepository.blurFind(keyword);
	}
	
	//景點日期的模糊搜尋
	public List<SelectAttractionsInventoryView> blurDateFind(java.sql.Date orderDate) {
		return attractionRepository.blurDateFind(orderDate);
	}
	
	//用景點名稱搜尋圖片
	public List<SelectAttractionsPictureView> findPictureByName(String name) {
		return attractionRepository.findPictureByName(name);
	}
	
}
