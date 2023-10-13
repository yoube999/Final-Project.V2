package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.AccommodationPhotos;

public interface AccommodationPhotosRepository extends JpaRepository<AccommodationPhotos, Integer>{
	List<AccommodationPhotos> findByAccommodationId(Integer accommodationId);
}
