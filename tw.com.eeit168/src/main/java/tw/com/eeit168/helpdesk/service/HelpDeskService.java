package tw.com.eeit168.helpdesk.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import tw.com.eeit168.helpdesk.dao.HelpDeskDAO;
import tw.com.eeit168.helpdesk.model.HelpDeskBean;
import tw.com.eeit168.member.model.MemberProfileBean;

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskService {

	private JavaMailSender javaMailSender;

	@Autowired
	public void JavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Autowired
	private HelpDeskDAO helpDeskDAO;

	// 宣告存放圖片的實體路徑
	private static final String IMAGE_UPLOAD_PATH = "C:\\Final-Project-workspace\\images\\";

	/**
	 * 寫入案件單，需再加上圖片上傳功能
	 * 
	 * 
	 */
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

			return helpDeskDAO.insert(insert);

		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 寫入案件單，使用多載方式，當有上傳圖片時走此邏輯
	 * 
	 * 
	 */
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

			return helpDeskDAO.insert(insert);

		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 上傳圖片邏輯
	 * 
	 * 
	 */
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

	/**
	 * 查詢案件
	 * 
	 * 
	 */
	public List<HelpDeskBean> selectTicket(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return helpDeskDAO.selectTicket(obj);
		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 查詢特定案件
	 * 
	 * 
	 */
	public HelpDeskBean selectTicketById(Integer helpdesk_id) {
		return helpDeskDAO.selectTicketById(helpdesk_id);
	}

	/**
	 * 透過點擊前端按鈕來變更案件狀態和人員
	 * 
	 * 
	 */
	public HelpDeskBean modifyHelpdeskStatus(String json) {

		JSONObject obj = new JSONObject(json);
		Integer helpdesk_id = obj.getInt("helpdesk_id");
		String helpdesk_status = obj.getString("helpdesk_status");
		Integer member_profile_id = obj.getInt("member_profile_id");

		HelpDeskBean update = helpDeskDAO.selectTicketById(helpdesk_id);
		// 判斷收到參數皆不為null
		if (update != null && helpdesk_status != null && member_profile_id != null) {
			update.setHelpdesk_status(helpdesk_status);
			update.setMember_profile_id(member_profile_id);

			return helpDeskDAO.modifyHelpdeskStatus(update);
		}
		return null;
	}

//	/**
//	 * 顯示案件內容時，畫面上顯示圖片url，點擊後開啟圖片
//	 * 
//	 * 
//	 */
//	public byte[] selectPicture(Integer helpdesk_id) {
//		
//		// 先查詢該案件資料，取得附件URL
//		HelpDeskBean pictureURL = helpDeskInterFace.selectTicketById(helpdesk_id);
//		if(pictureURL != null && helpdesk_id != null && pictureURL.getAttachment() != null) {
//			
//			try {
//				// 將字串轉換為位元組數組
//				byte[] imageData = pictureURL.getAttachment().getBytes(StandardCharsets.UTF_8);
//				return imageData;
//			} catch (Exception e) {
//				// 需改寫成跳轉至錯誤頁面
//				e.printStackTrace();
//				return null;
//			}
//		}
//		return null;
//	}

	/**
	 * 前端載入處理中/結案案件詳細頁面時，處理人員下拉選單查詢API
	 * 
	 * 
	 */
	public List<MemberProfileBean> selectCustomerUser(String json) {

		try {
			JSONObject obj = new JSONObject(json);
			return helpDeskDAO.selectCustomerUser(obj);
		} catch (JSONException e) {
			// 需改寫成跳轉至錯誤頁面
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 客服人員寄送Email回覆客戶
	 * 
	 * 
	 */
	public boolean sendEmail(String json) {

		JSONObject obj = new JSONObject(json);
		// Email欄位收到null時防呆處理
		String email = obj.isNull("email") ? null : obj.getString("email");
		String Subject = obj.isNull("Subject") ? null : obj.getString("Subject");
		String Text = obj.isNull("Text") ? null : obj.getString("Text");

		try {
			// 呼叫Email API
			MimeMessage message = javaMailSender.createMimeMessage();
			// 寫入Email主旨、內容
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			// Eamil、主旨和內容若為空防呆
			if (email != null && Subject != null && Text != null) {
				helper.setTo(email);
				helper.setSubject(Subject);
				helper.setText(Text);

				javaMailSender.send(message);
				return true;
			}

			// 未寄送成功回傳false
			return false;
		} catch (MessagingException e) {
			// 處理異常，需再加上錯誤處理跳轉頁面
			e.printStackTrace();
			return false;
		}

	}

}
