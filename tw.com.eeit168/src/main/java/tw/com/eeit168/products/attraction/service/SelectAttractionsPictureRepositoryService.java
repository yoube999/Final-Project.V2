package tw.com.eeit168.products.attraction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit168.products.attraction.model.SelectAttractionsPictureView;
import tw.com.eeit168.products.attraction.repository.SelectAttractionsPictureRepository;

@Service
public class SelectAttractionsPictureRepositoryService {
	
	@Autowired
	private SelectAttractionsPictureRepository selectAttractionsPictureRepository;
	
	public List<SelectAttractionsPictureView> findAll() {
		return selectAttractionsPictureRepository.findAll();
	}

}
