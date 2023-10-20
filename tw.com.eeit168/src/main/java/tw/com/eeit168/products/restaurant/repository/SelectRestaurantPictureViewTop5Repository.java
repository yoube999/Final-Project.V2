package tw.com.eeit168.products.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit168.products.restaurant.model.SelectRestaurantPictureViewTop5;

public interface SelectRestaurantPictureViewTop5Repository extends JpaRepository<SelectRestaurantPictureViewTop5, Integer>{
	List<SelectRestaurantPictureViewTop5> findTop5ByOrderByTimesPurchasedDesc();
}
