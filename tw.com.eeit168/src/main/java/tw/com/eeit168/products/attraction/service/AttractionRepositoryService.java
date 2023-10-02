package tw.com.eeit168.products.attraction.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.attraction.model.AttractionBean;
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
	public List<AttractionBean> selectTop5() {
		String hql = "from AttractionBean order by times_purchased desc";
		return this.getSession().createQuery(hql, AttractionBean.class).setMaxResults(5).list();
	}
	
	//以被購買次數搜尋(多-少)
	public List<AttractionBean> findAllByPurchasedDesc() {
		String hql = "from AttractionBean order by times_purchased desc";
		return this.getSession().createQuery(hql, AttractionBean.class).list();
	}
	
	//以價格搜尋(高-低)
//	public List<AttractionBean> findAllByPriceDesc() {
//		String hql = "from AttractionBean order by price desc";
//		return this.getSession().createQuery(hql, AttractionBean.class).list();
//	}
	
	//以價格搜尋(低-高)
//	public List<AttractionBean> findAllByPriceAsc() {
//		String hql = "from AttractionBean order by price asc";
//		return this.getSession().createQuery(hql, AttractionBean.class).list();
//	}
	
}
