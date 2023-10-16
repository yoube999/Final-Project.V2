package tw.com.eeit168.products.accommodation.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.SelectAccommodationInventoryRoomtypePriceView;

public interface SelectAccommodationInventoryRoomtypePriceViewRepository extends JpaRepository<SelectAccommodationInventoryRoomtypePriceView, Integer>{
	 List<SelectAccommodationInventoryRoomtypePriceView> findByAvailabilityDateBetweenAndRoomTypeName(
	            Date checkInDate, Date checkOutDate, String roomTypeName);
}
