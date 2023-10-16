package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;

public interface AccommodationRoomTypeRepository extends JpaRepository<AccommodationRoomType, Integer>{
	public List<AccommodationRoomType> findAllByOrderByCapacityDesc();
	public List<AccommodationRoomType>findByAccommodationId(int accommodationId);
}
