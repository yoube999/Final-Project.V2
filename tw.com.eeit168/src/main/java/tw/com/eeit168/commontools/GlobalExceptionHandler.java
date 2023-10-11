package tw.com.eeit168.commontools;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// 此類別為處理全域發生Exception異常處理時，該回傳甚麼行為
@ControllerAdvice
public class GlobalExceptionHandler {

	// MethodArgumentTypeMismatchException時代表輸入型態錯誤，無法轉換
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		JSONObject responseJson = new JSONObject();
		responseJson.put("message", "輸入參數型態錯誤，請確認參數是否傳送正確型態");
		responseJson.put("success", "error");
		return ResponseEntity.badRequest().body(responseJson.toString());
	}

	// HttpMessageNotReadableException時代表輸入型態錯誤，無法轉換
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		JSONObject responseJson = new JSONObject();
		responseJson.put("message", "輸入參數型態錯誤，請確認參數是否傳送正確型態");
		responseJson.put("success", "error");
		return ResponseEntity.badRequest().body(responseJson.toString());
	}
}
