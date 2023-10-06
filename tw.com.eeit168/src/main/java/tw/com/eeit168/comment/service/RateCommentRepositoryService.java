package tw.com.eeit168.comment.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit168.comment.model.RateCommentBean;
import tw.com.eeit168.comment.repository.RateCommentRepository;
import tw.com.eeit168.comment.util.DatetimeConverter;

@Service
public class RateCommentRepositoryService {

	@Autowired
	private RateCommentRepository rateCommentRepository;
	
	//以id搜尋
	public RateCommentBean findById(Integer id) {
		Optional<RateCommentBean> result = rateCommentRepository.findById(id);
		if(result != null && result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	//搜尋全部
	public List<RateCommentBean> findAll() {
		return rateCommentRepository.findAll();
	}
	
	//新增
	public RateCommentBean create(String json) {
		try {
			JSONObject object = new JSONObject(json);
			Integer itemTypeId = object.isNull("itemTypeId") ? null : object.getInt("itemTypeId");
			Integer memberProfileId = object.isNull("memberProfileId") ? null : object.getInt("memberProfileId");
			Integer itemId = object.isNull("itemId") ? null : object.getInt("itemId");
			double rate = object.isNull("rate") ? null : object.getDouble("rate");
			String comments = object.isNull("comments") ? null : object.getString("comments");
			RateCommentBean insert = new RateCommentBean();
			insert.setItemTypeId(itemTypeId);
			insert.setMemberProfileId(memberProfileId);
			insert.setItemId(itemId);
			insert.setRate(rate);
			insert.setComments(comments);
			return rateCommentRepository.save(insert);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//修改
	public RateCommentBean modify(String json) {
		try {
			JSONObject object = new JSONObject(json);
			Integer rateCommentId = object.isNull("rateCommentId") ? null : object.getInt("rateCommentId");
			Integer itemTypeId = object.isNull("itemTypeId") ? null : object.getInt("itemTypeId");
			Integer memberProfileId = object.isNull("memberProfileId") ? null : object.getInt("memberProfileId");
			Integer itemId = object.isNull("itemId") ? null : object.getInt("itemId");
			double rate = object.isNull("rate") ? null : object.getDouble("rate");
			String comments = object.isNull("comments") ? null : object.getString("comments");
			String createAt = object.isNull("createAt") ? null : object.getString("createAt");
			Optional<RateCommentBean> result = rateCommentRepository.findById(rateCommentId);
			if(result != null && result.isPresent()) {
				RateCommentBean update = new RateCommentBean();
				update.setRateCommentId(rateCommentId);
				update.setItemTypeId(itemTypeId);
				update.setMemberProfileId(memberProfileId);
				update.setItemId(itemId);
				update.setRate(rate);
				update.setComments(comments);
				update.setCreateAt(DatetimeConverter.parse(createAt, "yyyy-MM-dd"));
				return rateCommentRepository.save(update);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//刪除
	public boolean remove(Integer id) {
		try {
			if(rateCommentRepository.existsById(id)) {
				rateCommentRepository.deleteById(id);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
