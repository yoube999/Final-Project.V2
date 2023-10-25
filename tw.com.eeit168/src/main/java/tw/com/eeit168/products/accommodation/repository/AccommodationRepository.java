package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.model.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer>, AccommodationRepositoryDAO{
	// 根據 accommodationId 查找資訊
    List<Accommodation> findByAccommodationId(Integer accommodationId);
    
    String findItemTypeByAccommodationId(Integer accommodationId);
}
