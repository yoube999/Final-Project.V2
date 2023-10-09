package tw.com.eeit168.helpdesk.service;

import org.hibernate.Session;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.helpdesk.repository.HelpDeskUpdateRecordRepository;
import tw.com.eeit168.products.RecordBean;
import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.model.AccommodationOrder;
import tw.com.eeit168.products.accommodation.repository.AccommodationOrderRepository;
import tw.com.eeit168.products.accommodation.repository.AccommodationRepository;
import tw.com.eeit168.products.attraction.model.ReservationAttractionBean;
import tw.com.eeit168.products.attraction.repository.ReservationAttractionRepository;
import tw.com.eeit168.products.restaurant.model.ReservationRestuarantBean;
import tw.com.eeit168.products.restaurant.repository.ReservationRestuarantRepository;

@Service // 註解類別處理運算邏輯(企業邏輯)
@Transactional
public class HelpDeskUpdateRecordService {

	@Autowired
	private HelpDeskUpdateRecordRepository helpDeskUpdateRecordRepository;

	@Autowired
	private AccommodationOrderRepository accommodationOrderRepository;

	@Autowired
	private ReservationAttractionRepository reservationAttractionRepository;

	@Autowired
	private ReservationRestuarantRepository reservationRestuarantRepository;

	@PersistenceContext // 允許Spring自動管理JPA或Hibernate，並將其注入到Spring管理的bean中，以便您可以輕鬆地進行數據庫操作
	private Session session;

	public Session getSession() {
		return session;
	}

	/**
	 * 更新會員主訂單審查狀態及其子訂單狀態
	 * 
	 * @param json JSON格式的請求，包含以下參數： { "record_id": 訂單ID, "newStatus": 欲更新的狀態（
	 *             value：結單、退貨完成） }
	 * @return 全部更新成功回傳true，反之false
	 */
	public boolean updateRecord(String json) {

		try {
			JSONObject updateInfo = new JSONObject(json);
			Integer record_id = updateInfo.getInt("record_id");
			String newStatus = updateInfo.getString("newStatus");

			// 更新 Record
			RecordBean record = helpDeskUpdateRecordRepository.findById(record_id).orElse(null);

			if (record != null) {
				record.setRecord_status(newStatus);
				helpDeskUpdateRecordRepository.save(record);
			} else {
				return false; // 如果查不到ID，回傳false
			}

			// 更新 accommodation_order
			AccommodationOrder accommodationRecord = accommodationOrderRepository.findById(record_id).orElse(null);

			if (accommodationRecord != null) {
				accommodationRecord.setRecordAccommodationStatus(newStatus);
				accommodationOrderRepository.save(accommodationRecord);
			} else {
				return false; // 如果查不到ID，回傳false
			}

			// 更新 reservation_attractions
			ReservationAttractionBean reservationRecord = reservationAttractionRepository.findById(record_id)
					.orElse(null);

			if (reservationRecord != null) {
				reservationRecord.setRecordAttractionsStatus(newStatus);
				reservationAttractionRepository.save(reservationRecord);
			} else {
				return false; // 如果查不到ID，回傳false
			}

			// 更新 reservation_restuarant
			ReservationRestuarantBean restuarantRecord = reservationRestuarantRepository.findById(record_id)
					.orElse(null);

			if (restuarantRecord != null) {
				restuarantRecord.setRecordRestuarantStatus(newStatus);
				reservationRestuarantRepository.save(restuarantRecord);
			} else {
				return false; // 如果查不到ID，回傳false
			}

			return true; // 如果全部更新成功，返回 true
		} catch (JSONException e) {
			// 如果出現異常，執行Rollback
			e.printStackTrace();
			return false;
		}
	}

}
