package tw.com.eeit168.helpdesk.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.eeit168.products.accommodation.model.Accommodation;
import tw.com.eeit168.products.accommodation.repository.AccommodationRepository;
import tw.com.eeit168.products.attraction.model.AttractionBean;
import tw.com.eeit168.products.attraction.repository.AttractionRepository;
import tw.com.eeit168.products.restaurant.model.RestaurantBean;
import tw.com.eeit168.products.restaurant.repository.RestaurantRepository;

@Service
@Transactional(rollbackFor = { Exception.class })
public class HelpDeskProductsService {

	// 調用Restaurant的JPARepository
	@Autowired
	private RestaurantRepository restaurantRepository;

	// 調用Accommodation的JPARepository
	@Autowired
	private AccommodationRepository accommodationRepository;

	// 調用AttractionRepository的JPARepository
	@Autowired
	private AttractionRepository attractionRepository;

	/**
	 * 新增餐廳商品
	 * 
	 * @return insert成功回傳true，反之false
	 */
	public boolean insertRestaurantProducts(String restaurantData) {

		if (restaurantData != null) {
			try {
				JSONArray array = new JSONArray(restaurantData);

				// 這個for迴圈的目的是遍歷JSON數組中的每個JSON對象。在每次迭代中，它從JSON數組中獲取一個JSON對象，然後將該JSON對象轉換為您的自定義Java類型
				// RestaurantBean。
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);

					// 將JSON數據轉換為RestaurantBean對象
					RestaurantBean restaurantBean = new RestaurantBean();
					restaurantBean.setRestaurantName(obj.getString("restaurant_name"));
					restaurantBean.setRestaurantAddress(obj.getString("restaurant_address"));
					restaurantBean.setContactNumber(obj.getString("contact_number"));
					restaurantBean.setPrice(obj.getInt("price"));
					restaurantBean.setTimesPurchased(obj.getInt("times_purchased"));
					restaurantBean.setDescriptions(obj.getString("descriptions"));
					restaurantBean.setProductStatus(true); // 前端不會送此欄位，後端ProductStatus欄位固定送true

					// 將RestaurantBean對象保存到資料庫
					restaurantRepository.save(restaurantBean);
				}

				return true;

			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}

	}

	/**
	 * 新增飯店商品
	 * 
	 * @return insert成功回傳true，反之false
	 */
	public boolean insertAccommodationProducts(String restaurantData) {

		if (restaurantData != null) {
			try {
				JSONArray array = new JSONArray(restaurantData);

				// 這個for迴圈的目的是遍歷JSON數組中的每個JSON對象。在每次迭代中，它從JSON數組中獲取一個JSON對象，然後將該JSON對象轉換為您的自定義Java類型
				// Accommodation。
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);

					// 將JSON數據轉換為Accommodation對象
					Accommodation restaurantBean = new Accommodation();
					restaurantBean.setAccommodationName(obj.getString("accommodation_name"));
					restaurantBean.setAccommodationAddress(obj.getString("accommodation_address"));
					restaurantBean.setContactNumber(obj.getString("contact_number"));
					restaurantBean.setTimesPurchased(obj.getInt("times_purchased"));
					restaurantBean.setDescriptions(obj.getString("descriptions"));
					restaurantBean.setProductStatus(true); // 前端不會送此欄位，後端ProductStatus欄位固定送true

					// 將Accommodation對象保存到資料庫
					accommodationRepository.save(restaurantBean);
				}

				return true;

			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}

	}

	/**
	 * 新增景點商品
	 * 
	 * @return insert成功回傳true，反之false
	 */
	public boolean insertAttractionProducts(String restaurantData) {

		if (restaurantData != null) {
			try {
				JSONArray array = new JSONArray(restaurantData);

				// 這個for迴圈的目的是遍歷JSON數組中的每個JSON對象。在每次迭代中，它從JSON數組中獲取一個JSON對象，然後將該JSON對象轉換為您的自定義Java類型
				// Accommodation。
				for (int i = 0; i < array.length(); i++) {
					JSONObject obj = array.getJSONObject(i);

					// 將JSON數據轉換為Accommodation對象
					AttractionBean attractionBean = new AttractionBean();
					attractionBean.setAttractionsName(obj.getString("attractions_name"));
					attractionBean.setAttractionsAddress(obj.getString("attractions_address"));
					attractionBean.setDescriptions(obj.getString("descriptions"));
					attractionBean.setOpenTime(obj.getString("open_time"));
					attractionBean.setCloseTime(obj.getString("close_time"));
					attractionBean.setContactNumber(obj.getString("contact_number"));
					attractionBean.setTimesPurchased(obj.getInt("times_purchased"));
					attractionBean.setProductStatus(true); // 前端不會送此欄位，後端ProductStatus欄位固定送true

					// 將Accommodation對象保存到資料庫
					attractionRepository.save(attractionBean);
				}

				return true;

			} catch (JSONException e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}

	}

	/**
	 * 更新餐廳商品
	 * 
	 * @param json JSON格式的請求，包含以下參數： { "restaurant_id": 餐廳ID, "restaurant_name":
	 *             餐廳名稱, "restaurant_address": 餐廳地址, "contact_number": 電話, "price":
	 *             價格, "descriptions": 商品介紹 }
	 * 
	 * @return insert成功回傳true，反之false
	 */
	public boolean modifyRestaurantProduct(String json) {

		try {

			JSONObject modifyInfo = new JSONObject(json);

			Integer restaurantId = modifyInfo.getInt("restaurant_id");
			String restaurantName = modifyInfo.getString("restaurant_name");
			String restaurantAddress = modifyInfo.getString("restaurant_address");
			String descriptions = modifyInfo.getString("descriptions");
			String contactNumber = modifyInfo.getString("contact_number");
			Integer price = modifyInfo.getInt("price");

			// 宣告一個boolean檢查values不為null，若其中有null回傳false
			boolean allValuesNotNull = restaurantId != null && restaurantName != null && restaurantAddress != null
					&& descriptions != null && contactNumber != null && price != null;

			if (allValuesNotNull) {
				RestaurantBean product = restaurantRepository.findById(restaurantId).orElse(null);
				// 判斷該ID是否有對應商品
				if (product != null) {
					product.setRestaurantName(restaurantName);
					product.setRestaurantAddress(restaurantAddress);
					product.setDescriptions(descriptions);
					product.setContactNumber(contactNumber);
					product.setPrice(price);

					restaurantRepository.save(product);
					return true;

				} else {
					return false; // 如果查不到ID，回傳false
				}

			} else {
				return false; // 前端送來的欄位其中有null回傳false
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 更新飯店商品
	 * 
	 * @param json JSON格式的請求，包含以下參數： { "accommodation_id": 飯店ID, "accommodation_name":
	 *             飯店名稱, "accommodation_address": 飯店地址, "contact_number": 電話, 
	 *             "descriptions": 商品介紹 }
	 * 
	 * @return insert成功回傳true，反之false
	 */
	public boolean modifyAccommodationProduct(String json) {

		try {

			JSONObject modifyInfo = new JSONObject(json);

			Integer accommodationId = modifyInfo.getInt("accommodation_id");
			String accommodationName = modifyInfo.getString("accommodation_name");
			String accommodationAddress = modifyInfo.getString("accommodation_address");
			String descriptions = modifyInfo.getString("descriptions");
			String contactNumber = modifyInfo.getString("contact_number");

			// 宣告一個boolean檢查values不為null，若其中有null回傳false
			boolean allValuesNotNull = accommodationId != null && accommodationName != null && accommodationAddress != null
					&& descriptions != null && contactNumber != null;

			if (allValuesNotNull) {
				Accommodation product = accommodationRepository.findById(accommodationId).orElse(null);
				// 判斷該ID是否有對應商品
				if (product != null) {
					product.setAccommodationId(accommodationId);
					product.setAccommodationName(accommodationName);
					product.setAccommodationAddress(accommodationAddress);
					product.setDescriptions(descriptions);
					product.setContactNumber(contactNumber);

					accommodationRepository.save(product);
					return true;

				} else {
					return false; // 如果查不到ID，回傳false
				}

			} else {
				return false; // 前端送來的欄位其中有null回傳false
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * 更新景點商品
	 * 
	 * @param json JSON格式的請求，包含以下參數： { "attractions_id": 景點ID, "attractions_name":
	 *             景點名稱, "attractions_address": 景點地址, "descriptions": 商品介紹, "openTime": 開門時間, 
	 *             "closeTime": 關門時間, "contact_number": 電話 }
	 * 
	 * @return insert成功回傳true，反之false
	 */
	public boolean modifyAttractionProduct(String json) {

		try {

			JSONObject modifyInfo = new JSONObject(json);

			Integer attractionsId = modifyInfo.getInt("attractions_id");
			String attractionsName = modifyInfo.getString("attractions_name");
			String attractionsAddress = modifyInfo.getString("attractions_address");
			String descriptions = modifyInfo.getString("descriptions");
			String openTime = modifyInfo.getString("open_time");
			String closeTime = modifyInfo.getString("close_time");
			String contactNumber = modifyInfo.getString("contact_number");

			// 宣告一個boolean檢查values不為null，若其中有null回傳false
			boolean allValuesNotNull = attractionsId != null && attractionsName != null && attractionsAddress != null
					&& descriptions != null && openTime != null && closeTime != null && contactNumber != null;

			if (allValuesNotNull) {
				AttractionBean product = attractionRepository.findById(attractionsId).orElse(null);
				// 判斷該ID是否有對應商品
				if (product != null) {
					product.setAttractionsName(attractionsName);
					product.setAttractionsAddress(attractionsAddress);
					product.setDescriptions(descriptions);
					product.setOpenTime(openTime);
					product.setCloseTime(closeTime);
					product.setContactNumber(contactNumber);

					attractionRepository.save(product);
					return true;

				} else {
					return false; // 如果查不到ID，回傳false
				}

			} else {
				return false; // 前端送來的欄位其中有null回傳false
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	
	
}
