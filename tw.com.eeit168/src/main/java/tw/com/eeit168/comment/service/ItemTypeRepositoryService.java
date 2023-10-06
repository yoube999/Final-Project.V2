package tw.com.eeit168.comment.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit168.comment.model.ItemTypeBean;
import tw.com.eeit168.comment.repository.ItemTypeRepository;

@Service
public class ItemTypeRepositoryService {

	@Autowired
	private ItemTypeRepository itemTypeRepository;
	
	//id搜尋
	public ItemTypeBean findById(Integer id) {
		Optional<ItemTypeBean> result = itemTypeRepository.findById(id);
		if(result != null && result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	//搜尋全部
	public List<ItemTypeBean> findAll(){
		return itemTypeRepository.findAll();
	}
	
	//新增
	public ItemTypeBean create(String json) {
		try {
			JSONObject object = new JSONObject(json);
			Integer itemTypeId = object.isNull("itemTypeId") ? null : object.getInt("itemTypeId");
			String itemTypeName = object.isNull("itemTypeName") ? null : object.getString("itemTypeName");
			ItemTypeBean insert = new ItemTypeBean();
			insert.setItemTypeId(itemTypeId);
			insert.setItemTypeName(itemTypeName);
			return itemTypeRepository.save(insert);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//修改
	public ItemTypeBean modify(String json) {
		try {
			JSONObject object = new JSONObject(json);
			Integer itemTypeId = object.isNull("itemTypeId") ? null : object.getInt("itemTypeId");
			String itemTypeName = object.isNull("itemTypeName") ? null : object.getString("itemTypeName");
			Optional<ItemTypeBean> result = itemTypeRepository.findById(itemTypeId);
			if(result != null && result.isPresent()) {
				ItemTypeBean update = new ItemTypeBean();
				update.setItemTypeId(itemTypeId);
				update.setItemTypeName(itemTypeName);
				return itemTypeRepository.save(update);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//刪除
	public boolean remove(Integer id) {
		try {
			if(itemTypeRepository.existsById(id)) {
				itemTypeRepository.deleteById(id);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
