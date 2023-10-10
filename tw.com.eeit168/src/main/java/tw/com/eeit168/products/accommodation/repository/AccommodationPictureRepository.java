package tw.com.eeit168.products.accommodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.AccommodationPhotos;

public interface AccommodationPictureRepository extends JpaRepository<AccommodationPhotos, Integer>{

}
