package tw.com.eeit168.products.accommodation.dao;

import java.util.List;

import tw.com.eeit168.products.accommodation.model.AccommodationInventory;

public interface AccommodationInventoryDAO {
	public abstract AccommodationInventory select (Integer id);
	
	public abstract List<AccommodationInventory> selectAll();
	
	public abstract  AccommodationInventory insert(AccommodationInventory bean);
	
	public abstract AccommodationInventory update(AccommodationInventory bean);
	
	public abstract boolean delete(Integer id);
}
