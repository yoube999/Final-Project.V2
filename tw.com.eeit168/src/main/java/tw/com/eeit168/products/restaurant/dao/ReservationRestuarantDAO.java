package tw.com.eeit168.products.restaurant.dao;

import java.util.List;

import tw.com.eeit168.products.restaurant.model.ReservationRestuarantBean;

public interface ReservationRestuarantDAO {
	
	ReservationRestuarantBean select(Integer id);
	
	List<ReservationRestuarantBean> selectAll();
	
	ReservationRestuarantBean insert(ReservationRestuarantBean bean);
	
	ReservationRestuarantBean update(ReservationRestuarantBean bean);
	
	boolean delete(Integer id);

}
