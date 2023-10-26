package tw.com.eeit168.products;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = {"/project/record"})
public class RecordAjaxController {
	
	@Autowired
	private RecordRepositorySerivce recordRepositorySerivce;
	
	@GetMapping(path = {"/findbyid/{record_id}"}) //以id搜尋
	public String findById(@PathVariable(name = "record_id") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		RecordBean result = recordRepositorySerivce.findById(id);
		if(result != null) {
			JSONObject item = new JSONObject()
					.put("record_id", result.getRecordId())
					.put("member_profile_id", result.getMemberProfileId())
					.put("created_at", result.getCreatedAt())
					.put("record_status", result.getRecordStatus())
					.put("return_title", result.getReturnTitle())
					.put("return_description", result.getReturnDescription())
					.put("return_date", result.getReturnDate())
					.put("total_price", result.getTotalPrice());
			array = array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/findall"}) //搜尋全部
	public String findAll() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<RecordBean> result = recordRepositorySerivce.findAll();
		if(result != null && !result.isEmpty()) {
			for(RecordBean record : result) {
				JSONObject item = new JSONObject()
						.put("record_id", record.getRecordId())
						.put("member_profile_id", record.getMemberProfileId())
						.put("created_at", record.getCreatedAt())
						.put("record_status", record.getRecordStatus())
						.put("return_title", record.getReturnTitle())
						.put("return_description", record.getReturnDescription())
						.put("return_date", record.getReturnDate())
						.put("total_price", record.getTotalPrice());
				array = array.put(item);			
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	@PostMapping(path = {"/insert"}) //新增
	public String create(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
			RecordBean result = recordRepositorySerivce.create(body);
			if(result == null) {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
				responseJson.put("memberProfileId", result.getMemberProfileId());
				responseJson.put("recordStatus", result.getRecordStatus());
				responseJson.put("totalPrice", result.getTotalPrice());
			}
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/findbymemberid/{memberId}"}) //以memberId搜尋
	public String findbymemberid(@PathVariable(name = "memberId") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<RecordBean> result = recordRepositorySerivce.findByMemberId(id);
		if(result != null) {
			for(RecordBean record : result) {
				JSONObject item = new JSONObject()
						.put("record_id", record.getRecordId())
						.put("member_profile_id", record.getMemberProfileId())
						.put("created_at", record.getCreatedAt())
						.put("record_status", record.getRecordStatus())
						.put("return_title", record.getReturnTitle())
						.put("return_description", record.getReturnDescription())
						.put("return_date", record.getReturnDate())
						.put("total_price", record.getTotalPrice());
				array = array.put(item);			
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	 @PostMapping("/update/{recordId}")
	    public ResponseEntity<String> updateOrderRecord(
	            @PathVariable(name = "recordId") Integer recordId,
	            @RequestBody Map<String, String> updateData) {
	        String message;
	        HttpStatus status;
	        
	        RecordBean existingRecord = recordRepositorySerivce.findById(recordId);
	        
	        if (existingRecord != null) {
	            // 更新订单记录的退货信息
	            existingRecord.setReturnTitle(updateData.get("return_title"));
	            existingRecord.setReturnDescription(updateData.get("return_description"));
	            existingRecord.setRecordStatus(updateData.get("record_status"));

	            // 设置当前日期
	            // 设置当前日期
	            LocalDateTime now = LocalDateTime.now();
//	            LocalDate now = LocalDate.now();
//	            LocalTime time = LocalTime.now();  // 设置时分秒为 00:00:00
//	            LocalDateTime dateTime = now.atTime(time);
	            // 格式化为日期字符串
//	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//	            String formattedDateTime = now.format(formatter);
//	            // 将时间字符串转换为 Timestamp
//	            Timestamp timestamp = Timestamp.valueOf(formattedDateTime);
//	            // 将 Timestamp 转换为 Date
//	            Date date = new Date(timestamp.getTime());
//	            // 设置退货时间
	            existingRecord.setReturnDate(new java.util.Date());
	            // 调用 Service 更新订单记录
	            recordRepositorySerivce.updateRecord(existingRecord);

	            
	            message = "订单记录更新成功";
	            status = HttpStatus.OK;
	        } else {
	            message = "未找到订单记录";
	            status = HttpStatus.NOT_FOUND;
	        }

	        return new ResponseEntity<>(message, status);
	    }
}
