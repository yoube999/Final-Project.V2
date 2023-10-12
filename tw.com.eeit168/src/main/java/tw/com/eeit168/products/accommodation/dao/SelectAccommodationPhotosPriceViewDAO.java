package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import org.json.JSONObject;

import tw.com.eeit168.products.accommodation.model.SelectAccommodationPhotosPriceView;

public interface SelectAccommodationPhotosPriceViewDAO {
	public abstract List<SelectAccommodationPhotosPriceView> selectAll(JSONObject obj);
}
