package tw.com.eeit168.products.accommodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.com.eeit168.products.accommodation.model.AccommodationPhotos;

public interface AccommodationPictureRepository extends JpaRepository<AccommodationPhotos, Integer>{
	// 自定義方法，根據 accommodationId 查詢相應的 photoUrl
    @Query("SELECT ap.photoUrl FROM AccommodationPhotos ap WHERE ap.accommodationId = :accommodationId")
    String findPhotoUrlByAccommodationId(@Param("accommodationId") Integer accommodationId);
}
