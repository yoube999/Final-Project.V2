package tw.com.eeit168.products.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tw.com.eeit168.products.accommodation.model.AccommodationInventory;

@Repository //此annotation表示這個類別交由spring來控管
public interface AccommodationInventoryRepository extends JpaRepository<AccommodationInventory, Integer>{
	List<AccommodationInventory> findByAvailabilityDateBetween(java.sql.Date startDate, java.sql.Date endDate);
	List<AccommodationInventory> findByAvailableRooms(Integer availableRooms);
	List<AccommodationInventory> findByAvailableRoomsGreaterThanEqual(Integer availableRooms);

}
//JpaRepository 是 Spring Data JPA 提供的一個介面，它提供了一組通用的資料庫操作方法，例如插入、更新、刪除和查詢