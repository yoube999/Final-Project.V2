package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;

public interface AccommodationRoomTypeRepository extends JpaRepository<AccommodationRoomType, Integer>{
	List<AccommodationRoomType> findAllByOrderByCapacityDesc();
}
