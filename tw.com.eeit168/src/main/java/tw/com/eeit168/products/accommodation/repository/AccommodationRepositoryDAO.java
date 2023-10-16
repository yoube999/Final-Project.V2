package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.model.SelectAccommodationInventoryRoomtypePriceView;

public interface AccommodationRepositoryDAO {
	public List<Accommodation> findAccommodationName(String keyword);

	public List<SelectAccommodationInventoryRoomtypePriceView> selectTop5();
	
	List<SelectAccommodationInventoryRoomtypePriceView> findByAvailabilityDateBetween(
	        java.sql.Date checkinDate, java.sql.Date checkoutDate);
	
	List<SelectAccommodationInventoryRoomtypePriceView> findByAccommodationIdAndAvailabilityDateBetween(
            Integer accommodationId, java.sql.Date checkinDate, java.sql.Date checkoutDate);
	
	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceRange(Integer minPrice,
			Integer maxPrice);

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceRange(Integer minPrice,
			Integer maxPrice);

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceDesc();

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceDesc();

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceAsc();

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceAsc();

}
