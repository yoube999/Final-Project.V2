package tw.com.eeit168.products;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = {"/record"})
public class RecordAjaxController {
	
	@Autowired
	private RecordRepositorySerivce recordRepositorySerivce;
	
	@GetMapping(path = {"/findbyid/{record_id}"})
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
					.put("return_date", result.getReturnDate());
			array = array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	@GetMapping(path = {"/findall"})
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
						.put("return_date", record.getReturnDate());
				array = array.put(item);			
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	@PostMapping(path = {"/insert"})
	public String create(@RequestBody String body) {
		JSONObject responseJson = new JSONObject();
		JSONObject object = new JSONObject(body);
		Integer recordId = object.isNull("recordId") ? null : object.getInt("recordId");
		if(recordRepositorySerivce.exists(recordId)) {
			responseJson.put("message", "id已存在，新增失敗");
			responseJson.put("success", false);
		} else {
			RecordBean result = recordRepositorySerivce.create(body);
			if(result == null) {
				responseJson.put("message", "新增失敗");
				responseJson.put("success", false);
			} else {
				responseJson.put("message", "新增成功");
				responseJson.put("success", true);
			}
		}
		return null;
	}
	
	@GetMapping(path = {"/findbymemberid/{memberId}"})
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
						.put("return_date", record.getReturnDate());
				array = array.put(item);			
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
}
