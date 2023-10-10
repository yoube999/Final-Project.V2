package tw.com.eeit168.products.accommodation.util;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

public class ImageToBase64Converter {

	public static String convertToBase64(MultipartFile file) {
		try {
			String base64Encoder = null;
			if(file != null) {
				byte[] bytes = file.getBytes();
				base64Encoder = Base64.getEncoder().encodeToString(bytes);
				base64Encoder.replace("\\s+", "");
				base64Encoder = "data:image/jpeg;base64," + base64Encoder;
			}
			return base64Encoder;
		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
