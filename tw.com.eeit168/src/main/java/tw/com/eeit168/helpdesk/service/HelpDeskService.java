package tw.com.eeit168.helpdesk.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tw.com.eeit168.helpdesk.dao.HelpDeskInterFace;
import tw.com.eeit168.helpdesk.model.HelpDeskBean;

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskService {

	@Autowired
	private HelpDeskInterFace helpDeskInterFace = null;

	// 宣告存放圖片的實體路徑
	private static final String IMAGE_UPLOAD_PATH = "C:\\Final-Project-workspace\\images\\";

	// 寫入案件單，需再加上圖片上傳功能
	public HelpDeskBean createTicket(String json) {

		try {
			// 使用JSON格式包案件內容
			JSONObject obj = new JSONObject(json);

			// 後端收到Null防呆處理
			String customer_name = obj.isNull("customer_name") ? "訪客" : obj.getString("customer_name");
			Integer record_id = obj.isNull("record_id") ? null : obj.getInt("record_id");
			String subject_line = obj.isNull("subject_line") ? "未填寫主旨" : obj.getString("subject_line");
			String descriptions = obj.isNull("descriptions") ? "未填寫內容" : obj.getString("descriptions");
			String contact_number = obj.isNull("contact_number") ? "未填寫電話號碼" : obj.getString("contact_number");
			String email = obj.isNull("email") ? "未填寫email" : obj.getString("email");
			String way_to_contact = obj.isNull("way_to_contact") ? "電子信箱" : obj.getString("way_to_contact");

			HelpDeskBean insert = new HelpDeskBean();
			insert.setCustomer_name(customer_name);
			insert.setRecord_id(record_id);
			insert.setSubject_line(subject_line);
			insert.setDescriptions(descriptions);
			insert.setContact_number(contact_number);
			insert.setEmail(email);
			insert.setWay_to_contact(way_to_contact);
			// 建立案件時客服人員固定為null
			insert.setMember_profile_id(null);
			// 建立案件時狀態固定為未處理
			insert.setHelpdesk_status("未處理");
			// 建立案件時取得Loacl當下時間
			insert.setCreatetime(new java.util.Date());

			return helpDeskInterFace.insert(insert);

		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

	// 寫入案件單，使用多載方式，當有上傳圖片時走此邏輯
	public HelpDeskBean createTicket(String json, MultipartFile image) {

		try {
			// 使用JSON格式包案件內容
			JSONObject obj = new JSONObject(json);

			// 後端收到Null防呆處理
			String customer_name = obj.isNull("customer_name") ? "訪客" : obj.getString("customer_name");
			Integer record_id = obj.isNull("record_id") ? null : obj.getInt("record_id");
			String subject_line = obj.isNull("subject_line") ? "未填寫主旨" : obj.getString("subject_line");
			String descriptions = obj.isNull("descriptions") ? "未填寫內容" : obj.getString("descriptions");
			String contact_number = obj.isNull("contact_number") ? "未填寫電話號碼" : obj.getString("contact_number");
			String email = obj.isNull("email") ? "未填寫email" : obj.getString("email");
			String way_to_contact = obj.isNull("way_to_contact") ? "電子信箱" : obj.getString("way_to_contact");

			HelpDeskBean insert = new HelpDeskBean();
			insert.setCustomer_name(customer_name);
			insert.setRecord_id(record_id);
			insert.setSubject_line(subject_line);
			insert.setDescriptions(descriptions);
			insert.setContact_number(contact_number);
			insert.setEmail(email);
			insert.setWay_to_contact(way_to_contact);
			// 實現此方法以將圖片儲存到資料庫並返回 URL
			if (image != null && !image.isEmpty()) {
				String imageUrl = saveImages(image);
				insert.setAttachment(imageUrl);
			}
			// 建立案件時客服人員固定為null
			insert.setMember_profile_id(null);
			// 建立案件時狀態固定為未處理
			insert.setHelpdesk_status("未處理");
			// 建立案件時取得Loacl當下時間
			insert.setCreatetime(new java.util.Date());

			return helpDeskInterFace.insert(insert);

		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

	// 上傳圖片邏輯
	private String saveImages(MultipartFile image) {

		try {
			// 取得上傳路徑並new File物件
			File uploadDir = new File(IMAGE_UPLOAD_PATH);
			if (!uploadDir.exists()) {
				// 如果目錄不存在，則創建它
				uploadDir.mkdirs();
			}

			// 獲取圖片的原始文件名
			String originalFilename = image.getOriginalFilename();

			// 產生一個唯一的文件名，以避免命名衝突
			String uniqueFilename = UUID.randomUUID().toString() + "_" + originalFilename;

			// 組合最終的文件路徑
			String filePath = IMAGE_UPLOAD_PATH + uniqueFilename;

			File dest = new File(filePath);
			image.transferTo(dest);

			// 返回圖片的 URL
			return "file://" + filePath;
		} catch (IllegalStateException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		} catch (IOException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

	// 查詢未處理案件
	public List<HelpDeskBean> selectTicket(String json) {
		JSONObject obj = new JSONObject(json);
		
		// 後端收到Null防呆處理
		String helpdesk_status = obj.isNull("helpdesk_status") ? "未處理" : obj.getString("helpdesk_status");
		
		
		
		
		
		
		
		
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
