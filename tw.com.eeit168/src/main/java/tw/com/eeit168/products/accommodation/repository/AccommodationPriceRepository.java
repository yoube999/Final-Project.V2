package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.AccommodationPrice;

public interface AccommodationPriceRepository extends JpaRepository<AccommodationPrice, Integer>{

	List<AccommodationPrice> findByWeekdayPriceBetweenAndWeekendPriceBetween(int minWeekdayPrice, int maxWeekdayPrice, int minWeekendPrice, int maxWeekendPrice);
}
