package tw.com.eeit168.products;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;

@Service //註解類別處理運算邏輯(企業邏輯)
public class RecordRepositorySerivce {
	
	@Autowired
	private RecordRepository recordRepository;
	
	@PersistenceContext //允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;
	
	public Session getSession() {
		return session;
	}
	
	//以id搜尋
	public RecordBean findById(Integer id) {
		Optional<RecordBean> result = recordRepository.findById(id);
		if(result != null && result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	//搜尋全部
	public List<RecordBean> findAll() {
		return recordRepository.findAll();
	}
	
	//新增
	public RecordBean create(String json) {
		try {
			JSONObject object = new JSONObject(json);
			Integer memberProfileId = object.isNull("memberProfileId") ? null : object.getInt("memberProfileId");
			String recordStatus = object.isNull("recordStatus") ? null : object.getString("recordStatus");
			Integer totalPrice = object.isNull("totalPrice") ? null : object.getInt("totalPrice");
			RecordBean insert = new RecordBean();
			insert.setMemberProfileId(memberProfileId);
			insert.setRecordStatus(recordStatus);
			insert.setTotalPrice(totalPrice);
			return recordRepository.save(insert);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	//判斷id是否存在
	public boolean exists(Integer id) {
		return recordRepository.existsById(id);
	}
	
	//用member id去搜尋
	public List<RecordBean> findByMemberId(Integer id) {
		return recordRepository.findByMemberId(id);
	}
	
}
