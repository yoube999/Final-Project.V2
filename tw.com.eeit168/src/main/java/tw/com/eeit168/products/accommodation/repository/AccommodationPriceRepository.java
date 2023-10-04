package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.AccommodationPrice;
import tw.com.eeit168.products.accommodation.model.SelectAccommodationInventoryRoomtypePriceView;

public interface AccommodationPriceRepository extends JpaRepository<AccommodationPrice, Integer>, AccommodationPriceRepositoryDAO{
	public List<SelectAccommodationInventoryRoomtypePriceView> findByWeekdayPriceBetweenAndWeekendPriceBetween(Integer minWeekdayPrice, Integer maxWeekdayPrice, Integer minWeekendPrice, Integer maxWeekendPrice);
	
}
