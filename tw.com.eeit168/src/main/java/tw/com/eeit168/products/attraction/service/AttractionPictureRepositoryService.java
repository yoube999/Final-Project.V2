package tw.com.eeit168.products.attraction.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import tw.com.eeit168.products.attraction.model.AttractionPictureBean;
import tw.com.eeit168.products.attraction.repository.AttractionPictureRepository;
import tw.com.eeit168.products.attraction.util.ImageConvert;

@Service
public class AttractionPictureRepositoryService {

	@Autowired
	private AttractionPictureRepository attractionPictureRepository;
	
	//新增照片
	public AttractionPictureBean create(String json, MultipartFile file) {
		try {
			JSONObject object = new JSONObject(json);
			Integer attractionsId = object.isNull("attractionsId") ? null : object.getInt("attractionsId");
			if(file != null) {
				AttractionPictureBean insert = new AttractionPictureBean();
				String base64 = ImageConvert.convertToBase64(file);
				insert.setAttractionsId(attractionsId);
				insert.setUrlImage(base64);
				return attractionPictureRepository.save(insert);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//以id搜尋
	public AttractionPictureBean findById(Integer id) {
		Optional<AttractionPictureBean> result = attractionPictureRepository.findById(id);
		if(result != null && result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
}
