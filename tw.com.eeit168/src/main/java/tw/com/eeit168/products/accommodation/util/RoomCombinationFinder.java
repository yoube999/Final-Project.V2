package tw.com.eeit168.products.accommodation.util;

import tw.com.eeit168.products.accommodation.model.AccommodationRoomType;
import tw.com.eeit168.products.accommodation.repository.AccommodationRoomTypeRepository;

import java.util.ArrayList;
import java.util.List;

public class RoomCombinationFinder {
	private AccommodationRoomTypeRepository accommodationRoomTypeRepository;

	public RoomCombinationFinder(AccommodationRoomTypeRepository accommodationRoomTypeRepository) {
		this.accommodationRoomTypeRepository = accommodationRoomTypeRepository;
	}

	public List<List<AccommodationRoomType>> findCombinations(int totalGuests, int requiredRooms) {
		List<AccommodationRoomType> allRoomTypes = accommodationRoomTypeRepository.findAll();
		List<List<AccommodationRoomType>> combinations = new ArrayList<>();
		List<AccommodationRoomType> currentCombination = new ArrayList<>();

		backtrack(allRoomTypes, combinations, currentCombination, totalGuests, requiredRooms, 0);

		return combinations;
	}

	private void backtrack(List<AccommodationRoomType> roomTypes, List<List<AccommodationRoomType>> combinations,
			List<AccommodationRoomType> currentCombination, int remainingGuests, int remainingRooms, int start) {
		if (remainingGuests == 0 && remainingRooms == 0) {
// Check if all room types belong to the same accommodation
			boolean sameAccommodation = areAllFromSameAccommodation(currentCombination);

			if (sameAccommodation) {
				combinations.add(new ArrayList<>(currentCombination));
			}
			return;
		}

		for (int i = start; i < roomTypes.size(); i++) {
			AccommodationRoomType roomType = roomTypes.get(i);
			int roomCapacity = roomType.getCapacity();

			if (remainingGuests >= roomCapacity && remainingRooms > 0) {
				currentCombination.add(roomType);
				backtrack(roomTypes, combinations, currentCombination, remainingGuests - roomCapacity,
						remainingRooms - 1, i);
				currentCombination.remove(currentCombination.size() - 1);
			}
		}
	}

	private boolean areAllFromSameAccommodation(List<AccommodationRoomType> roomTypes) {
		if (roomTypes.isEmpty()) {
			return false;
		}

		int accommodationId = roomTypes.get(0).getAccommodationId();
		for (AccommodationRoomType roomType : roomTypes) {
			if (roomType.getAccommodationId() != accommodationId) {
				return false;
			}
		}
		return true;
	}
}
