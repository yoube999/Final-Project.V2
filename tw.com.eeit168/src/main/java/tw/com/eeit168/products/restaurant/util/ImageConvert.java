package tw.com.eeit168.products.restaurant.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageConvert {

	//單張照片轉成base64
	public static String convertToBase64(MultipartFile file) {
		try {
			String base64Encoder = null;
			if(file != null) {
				byte[] bytes = file.getBytes();
				base64Encoder = Base64.getEncoder().encodeToString(bytes);
				base64Encoder.replace("[\\s*\t\n\r]", "");
				base64Encoder = "data:image/jpeg;base64," + base64Encoder;
			}
			return base64Encoder;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//多張照片轉成base64
	public static List<String> convertManyToBase64(MultipartFile[] files) {
		try {
			List<String> result = new ArrayList<String>();
			String base64Encoder = null;
			byte[] bytes = null;
			if(files != null) {
				for(MultipartFile file : files) {
					bytes = file.getBytes();
					base64Encoder = Base64.getEncoder().encodeToString(bytes);
					if(base64Encoder != null && base64Encoder.length() != 0) {
						base64Encoder.replace("[\\s*\t\n\r]", "");
						base64Encoder = "data:image/jpeg;base64," + base64Encoder;
					}
				}
			}
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
