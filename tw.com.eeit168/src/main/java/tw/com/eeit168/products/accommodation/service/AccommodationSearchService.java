package tw.com.eeit168.products.accommodation.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.model.AccommodationInventory;
import tw.com.eeit168.products.accommodation.model.AccommodationPrice;
import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;
import tw.com.eeit168.products.accommodation.repository.AccommodationInventoryRepository;
import tw.com.eeit168.products.accommodation.repository.AccommodationPriceRepository;
import tw.com.eeit168.products.accommodation.repository.AccommodationRoomTypeRepository;
import tw.com.eeit168.products.accommodation.util.RoomCombinationFinder;

@Service
public class AccommodationSearchService {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return session;
	}

	@Autowired
	private AccommodationInventoryRepository accommodationInventoryRepository;
	@Autowired
	private AccommodationRoomTypeRepository accommodationRoomTypeRepository;

	@Autowired
	public void setAccommodationRoomTypeRepository(AccommodationRoomTypeRepository accommodationRoomTypeRepository) {
		this.accommodationRoomTypeRepository = accommodationRoomTypeRepository;
	}

	@Autowired
	private AccommodationPriceRepository accommodationPriceRepository;

	public List<Accommodation> findAccommodationName(String keyword) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			String hql = "FROM Accommodation WHERE accommodationName LIKE :keyword OR accommodationAddress LIKE :keyword";
			Query<Accommodation> query = getSession().createQuery(hql, Accommodation.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.list();
		}
		return Collections.emptyList(); // Return an empty list if the keyword is null or empty
	}

	public List<AccommodationInventory> findAccommodationsByDate(java.sql.Date checkInDate,
			java.sql.Date checkOutDate) {
		String hql = "FROM AccommodationInventory WHERE availabilityDate BETWEEN :checkInDate AND :checkOutDate";
		Query<AccommodationInventory> query = getSession().createQuery(hql, AccommodationInventory.class);
		query.setParameter("checkInDate", checkInDate);
		query.setParameter("checkOutDate", checkOutDate);

		return query.list();
	}

	public List<AccommodationInventory> findAccommodationsByRooms(Integer requiredRooms) {
		return accommodationInventoryRepository.findByAvailableRoomsGreaterThanEqual(requiredRooms);
	}

	public List<List<AccommodationRoomType>> findRoomCombinations(int totalGuests, int requiredRooms) {
		RoomCombinationFinder combinationFinder = new RoomCombinationFinder(accommodationRoomTypeRepository);
		return combinationFinder.findCombinations(totalGuests, requiredRooms);
	}

	public List<AccommodationPrice> getPricesInPriceRangeForWeekdaysAndWeekends(int minWeekdayPrice,
			int maxWeekdayPrice, int minWeekendPrice, int maxWeekendPrice) {
		return accommodationPriceRepository.findByWeekdayPriceBetweenAndWeekendPriceBetween(minWeekdayPrice,
				maxWeekdayPrice, minWeekendPrice, maxWeekendPrice);
	}

//	public class RoomCombinationFinder {
//
//	    public List<List<AccommodationRoomType>> findCombinations(int totalGuests, int requiredRooms) {
//	        List<List<AccommodationRoomType>> combinations = new ArrayList<>();
//	        backtrack(new ArrayList<>(), totalGuests, requiredRooms, combinations);
//	        return combinations;
//	    }
//
//	    private void backtrack(List<AccommodationRoomType> currentCombination, int remainingGuests, int remainingRooms,
//	                           List<List<AccommodationRoomType>> combinations) {
//	        if (remainingGuests == 0 && remainingRooms == 0) {
//	            combinations.add(new ArrayList<>(currentCombination));
//	            return;
//	        }
//
//	        List<AccommodationRoomType> roomTypes = getAllRoomTypes();  // Fetch all room types
//
//	        for (AccommodationRoomType roomType : roomTypes) {
//	            int roomCapacity = roomType.getCapacity();
//
//	            if (remainingGuests >= roomCapacity && remainingRooms > 0) {
//	                currentCombination.add(roomType);
//	                backtrack(currentCombination, remainingGuests - roomCapacity, remainingRooms - 1, combinations);
//	                currentCombination.remove(currentCombination.size() - 1);
//	            }
//	        }
//	    }
//
//	    // Assume this method fetches all room types from your repository
//	    private List<AccommodationRoomType> getAllRoomTypes() {
//	        // Fetch all room types from your repository
//	        // Replace this with the actual call to your repository
//	        // For example:
//	        // return accommodationRoomTypeRepository.findAll();
//	        return new ArrayList<>();  // Placeholder, replace with actual implementation
//	    }
//	}

//	public List<AccommodationRoomType> recommendRooms(int totalGuests, int requiredRooms) {
//	    List<AccommodationRoomType> allRoomTypes = accommodationRoomTypeRepository.findAll();
//
//	    // 根據房間容量降序排列房型
//	    allRoomTypes.sort(Comparator.comparingInt(AccommodationRoomType::getCapacity).reversed());
//
//	    List<AccommodationRoomType> recommendedRoomTypes = new ArrayList<>();
//	    int remainingGuests = totalGuests;
//
//	    // 選擇房型，直到達到所需的房間數
//	    for (AccommodationRoomType roomType : allRoomTypes) {
//	        int capacity = roomType.getCapacity();
//
//	        while (recommendedRoomTypes.size() < requiredRooms && remainingGuests >= capacity) {
//	            recommendedRoomTypes.add(roomType);
//	            remainingGuests -= capacity;
//	        }
//	    }
//
//	    return recommendedRoomTypes;
//	}

//	public List<AccommodationRoomType> findAllByMaxOccupancyDesc(){
//		return accommodationRoomTypeRepository.findAllByOrderByMaxOccupancyDesc();
//	}

//	public List<AccommodationInventory> findAccommodationsByCriteria(java.sql.Date checkInDate, int requiredRooms){
//			String hql = "FROM AccommodationInventory WHERE availabilityDate = :checkInDate AND availableRooms >= :requiredRooms ";
//			Query<AccommodationInventory> query = getSession().createQuery(hql, AccommodationInventory.class);
//			query.setParameter("checkInDate", checkInDate);
//			query.setParameter("requiredRooms", requiredRooms);
//			return null;
//	}

}
