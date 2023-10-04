package tw.com.eeit168.products.accommodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer>, AccommodationRepositoryDAO{
	
}
