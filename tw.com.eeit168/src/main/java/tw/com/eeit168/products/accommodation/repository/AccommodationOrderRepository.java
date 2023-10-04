package tw.com.eeit168.products.accommodation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.accommodation.dao.AccommodationOrderDAO;
import tw.com.eeit168.products.accommodation.model.AccommodationOrder;

public interface AccommodationOrderRepository extends JpaRepository<AccommodationOrder, Integer>{

}
