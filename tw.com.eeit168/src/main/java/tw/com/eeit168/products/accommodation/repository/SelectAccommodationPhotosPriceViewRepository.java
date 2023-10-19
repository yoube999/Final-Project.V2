package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.SelectAccommodationPhotosPriceView;

public interface SelectAccommodationPhotosPriceViewRepository extends JpaRepository<SelectAccommodationPhotosPriceView, Integer>{
	 List<SelectAccommodationPhotosPriceView> findTop5ByOrderByTimesPurchasedDesc();
}
