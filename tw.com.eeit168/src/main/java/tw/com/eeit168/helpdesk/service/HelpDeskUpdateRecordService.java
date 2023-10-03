package tw.com.eeit168.helpdesk.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.helpdesk.Repository.HelpDeskUpdateRecordRepository;
import tw.com.eeit168.products.RecordBean;

@Service //註解類別處理運算邏輯(企業邏輯)
@Transactional
public class HelpDeskUpdateRecordService {

	@Autowired
	private HelpDeskUpdateRecordRepository helpDeskUpdateRecordRepository;
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
//	public RecordBean selectById(Integer record_id) {
//		
//		Optional<RecordBean> result = helpDeskUpdateRecordRepository.findById(record_id);
//		return result.get();
//		
//	}
	
	
	
	
}
