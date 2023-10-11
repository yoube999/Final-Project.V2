package tw.com.eeit168.products.accommodation.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.PersistenceContext;
import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.model.AccommodationInventory;
import tw.com.eeit168.products.accommodation.model.AccommodationPhotos;
import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;
import tw.com.eeit168.products.accommodation.model.SelectAccommodationInventoryRoomtypePriceView;
import tw.com.eeit168.products.accommodation.model.SelectAccommodationPhotosPriceView;
import tw.com.eeit168.products.accommodation.repository.AccommodationInventoryRepository;
import tw.com.eeit168.products.accommodation.repository.AccommodationPictureRepository;
import tw.com.eeit168.products.accommodation.repository.AccommodationRepository;
import tw.com.eeit168.products.accommodation.repository.AccommodationRoomTypeRepository;
import tw.com.eeit168.products.accommodation.repository.SelectAccommodationPhotosPriceViewRepository;
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
	private AccommodationPictureRepository accommodationPictureRepository;

//	@Autowired
//	private AccommodationPriceRepository accommodationPriceRepository;

//	@Autowired
//	private AccommodationRepositoryDAOImpl accommodationRepositoryDAOImpl;

//	@Autowired
//	private AccommodationInventoryRepositoryDAOImpl accommodationInventoryRepositoryDAOImpl;

	@Autowired
	private AccommodationRepository accommodationRepository;
	
	@Autowired
	private SelectAccommodationPhotosPriceViewRepository selectAccommodationPhotosPriceViewRepository;
	
//	@Autowired
//	private SelectAccommodationInventoryRoomtypePriceViewRepository selectAccommodationInventoryRoomtypePriceViewRepository;
	
	//findAll Accommodation
	public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }
	
	//findAll SelectAccommodationPhotosPriceViewRepository
	public List<SelectAccommodationPhotosPriceView> getAllAccommodationsInfo(){
		return selectAccommodationPhotosPriceViewRepository.findAll();
	}
	
	public String getPhotoUrlByAccommodationId(Integer accommodationId) {
        // 根據 accommodationId 查詢相應的圖片 URL
        // 這裡假設 AccommodationPhotoRepository 可以根據 accommodationId 查詢相關圖片
        String photoUrl = accommodationPictureRepository.findPhotoUrlByAccommodationId(accommodationId);
        return photoUrl;
    }
	
	
	public String getPhotoUrlById(Integer photoId) {
		// Here, you would fetch the photo URL from your database based on the photoId
        // Example assuming Photo has a field named "photoUrl"
        // Replace this with actual logic to fetch the photo URL based on photoId
		AccommodationPhotos photo = accommodationPictureRepository.findById(photoId).orElse(null);
		return (photo != null)? photo.getPhotoUrl():null;
	}
	
	public List<Accommodation> findAccommodationName(String keyword) {
		return accommodationRepository.findAccommodationName(keyword);
	}

	public List<SelectAccommodationInventoryRoomtypePriceView> selectTop5() {
		return accommodationRepository.selectTop5();
	}

	public List<SelectAccommodationInventoryRoomtypePriceView> findByAvailabilityDateBetween(java.sql.Date checkinDate,
			java.sql.Date checkoutDate) {
				return accommodationRepository.findByAvailabilityDateBetween(checkinDate, checkoutDate);

	}

//	public List<AccommodationInventory> findByAvailabilityDateBetween(java.sql.Date checkInDate,
//			java.sql.Date checkOutDate) {
//		return accommodationInventoryRepositoryDAOImpl.findByAvailabilityDateBetween(checkInDate, checkOutDate);
//	}

	public List<AccommodationInventory> findAccommodationsByRooms(Integer requiredRooms) {
		return accommodationInventoryRepository.findByAvailableRoomsGreaterThanEqual(requiredRooms);
	}

	// 在 AccommodationSearchService 類別中的程式碼替換
//	public List<List<SelectAccommodationInventoryRoomtypePriceView>> findRoomCombinations(int totalGuests, int requiredRooms) {
//	    RoomCombinationFinder combinationFinder = new RoomCombinationFinder(selectAccommodationInventoryRoomtypePriceViewRepository);
//	    // 改為使用 SelectAccommodationInventoryRoomtypePriceView
//	    return combinationFinder.findCombinations(totalGuests, requiredRooms);
//	}
	
	public List<List<AccommodationRoomType>> findRoomCombinations(int totalGuests, int requiredRooms) {
		RoomCombinationFinder combinationFinder = new RoomCombinationFinder(accommodationRoomTypeRepository);
		return combinationFinder.findCombinations(totalGuests, requiredRooms);
	}
//	public List<List<AccommodationRoomType>> findRoomCombinations(int totalGuests, int requiredRooms) {
//		RoomCombinationFinder combinationFinder = new RoomCombinationFinder(accommodationRoomTypeRepository);
//		return combinationFinder.findCombinations(totalGuests, requiredRooms);
//	}

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceRange(Integer minPrice,
			Integer maxPrice) {
		return accommodationRepository.findAllByWeekdayPriceRange(minPrice, maxPrice);
	}

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceRange(Integer minPrice,
			Integer maxPrice) {
		return accommodationRepository.findAllByWeekendPriceRange(minPrice, maxPrice);
	}
//	public List<SelectAccommodationInventoryRoomtypePriceView> getPricesInPriceRangeForWeekdaysAndWeekends(Integer minWeekdayPrice,
//			Integer maxWeekdayPrice, Integer minWeekendPrice, Integer maxWeekendPrice) {
//		System.out.println("6");
//		
//		return accommodationPriceRepository.findByWeekdayPriceBetweenAndWeekendPriceBetween(minWeekdayPrice,
//				maxWeekdayPrice, minWeekendPrice, maxWeekendPrice);
//	}

	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceDesc() {

		return accommodationRepository.findAllByWeekdayPriceDesc();
	}
	
	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceDesc() {
		return accommodationRepository.findAllByWeekendPriceDesc();
	}
	
	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekdayPriceAsc() {
		return accommodationRepository.findAllByWeekdayPriceAsc();
	}
	
	public List<SelectAccommodationInventoryRoomtypePriceView> findAllByWeekendPriceAsc() {
		return accommodationRepository.findAllByWeekendPriceAsc();
	}
	
	
//	public List<Accommodation> findAccommodationName(String keyword) {
//		if (keyword != null && !keyword.trim().isEmpty()) {
//			String hql = "FROM Accommodation WHERE accommodationName LIKE :keyword OR accommodationAddress LIKE :keyword";
//			Query<Accommodation> query = getSession().createQuery(hql, Accommodation.class);
//			query.setParameter("keyword", "%" + keyword + "%");
//			return query.list();
//		}
//		return Collections.emptyList(); // Return an empty list if the keyword is null or empty
//	}

//	public List<AccommodationInventory> findAccommodationsByDate(java.sql.Date checkInDate,
//			java.sql.Date checkOutDate) {
//		String hql = "FROM AccommodationInventory WHERE availabilityDate BETWEEN :checkInDate AND :checkOutDate";
//		Query<AccommodationInventory> query = getSession().createQuery(hql, AccommodationInventory.class);
//		query.setParameter("checkInDate", checkInDate);
//		query.setParameter("checkOutDate", checkOutDate);
//
//		return query.list();
//	}

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
