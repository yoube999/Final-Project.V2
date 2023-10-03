package tw.com.eeit168.products.accommodation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.com.eeit168.products.accommodation.model.AccommodationPrice;
import tw.com.eeit168.products.accommodation.repository.AccommodationPriceRepository;

@Service
public class AccommodationPriceService {
	@Autowired
	private AccommodationPriceRepository accommodationPriceRepository;
	
	public List<AccommodationPrice> getAllPrices(){
		return accommodationPriceRepository.findAll();
	}
	
	public AccommodationPrice getPriceById(Integer id) {
		return accommodationPriceRepository.findById(id).orElse(null);
	}
	
	public List<AccommodationPrice> getPricesInPriceRangeForWeekdaysAndWeekends(int minWeekdayPrice, int maxWeekdayPrice, int minWeekendPrice, int maxWeekendPrice){
		return accommodationPriceRepository.findByWeekdayPriceBetweenAndWeekendPriceBetween(minWeekdayPrice, maxWeekdayPrice, minWeekendPrice, maxWeekendPrice);
	}
}
