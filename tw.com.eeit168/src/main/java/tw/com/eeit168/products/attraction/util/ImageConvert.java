package tw.com.eeit168.products.attraction.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.web.multipart.MultipartFile;

public class ImageConvert {

	//單張照片轉成base64
	public static final String convertToBase64(MultipartFile file) {
		try {
			String base64Encoder = null;
			if(file != null) {
				byte[] bytes = file.getBytes();
				base64Encoder = Base64.encodeBase64String(bytes);
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
	public static final Map<Integer, String> convertManyToBase64(MultipartFile[] files) {
		try {
			Map<Integer, String> result = new HashMap<Integer, String>();
			String base64Encoder = null;
			byte[] bytes = null;
			if(files != null) {
				Integer i = 1;
				for(MultipartFile file : files) {
					bytes = file.getBytes();
					base64Encoder = Base64.encodeBase64String(bytes);
					if(base64Encoder != null && base64Encoder.length() != 0) {
						base64Encoder.replace("[\\s*\t\n\r]", "");
						base64Encoder = "data:image/jpeg;base64," + base64Encoder;
						result.put(i, base64Encoder);
						i++;
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
