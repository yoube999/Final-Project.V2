package tw.com.eeit168.helpdesk.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import tw.com.eeit168.helpdesk.dao.HelpDeskInterFace;
import tw.com.eeit168.helpdesk.model.HelpDeskBean;

@Service
@Transactional(rollbackFor = { Exception.class })
public class PictureFactory {

	// 宣告存放圖片的實體路徑
	private static final String IMAGE_UPLOAD_PATH = "C://Final-Project-workspace//images//";

	@Autowired
	private HelpDeskInterFace helpDeskInterFace;

	/**
	 * 上傳圖片邏輯
	 * 
	 * 
	 */
	public String saveImages(MultipartFile image) {

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
			return filePath;
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
	 * 顯示案件內容時，畫面上顯示圖片url，點擊後開啟圖片
	 * 
	 * 
	 */
	public String selectPicture(Integer helpdesk_id) {

		// 先查詢該案件資料，取得附件URL
		HelpDeskBean pictureURL = helpDeskInterFace.selectTicketById(helpdesk_id);
		if (pictureURL != null && helpdesk_id != null && pictureURL.getAttachment() != null) {

			// 返回附件的URL字串
			return pictureURL.getAttachment();
		}
		return null;
	}

}
