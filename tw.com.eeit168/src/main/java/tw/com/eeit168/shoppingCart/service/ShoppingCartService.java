package tw.com.eeit168.shoppingCart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.eeit168.shoppingCart.dao.ShoppingCartDAO;
import tw.com.eeit168.shoppingCart.model.ShoppingCartItem;

@Service
public class ShoppingCartService {
	
	@Autowired
	private ShoppingCartDAO shoppingCartDAO;
	
	//取得購物車裡的商品
	public List<ShoppingCartItem> getCartItems() {
		return shoppingCartDAO.getCartItems();
	}
	
	//新增商品到購物車
	public void addItem(ShoppingCartItem item) {
		shoppingCartDAO.addItem(item);
	}
		
	//刪除購物車裡的商品
	public void removeItem(Integer itemId) {
		shoppingCartDAO.removeItem(itemId);
	}	
	
	//更新購物車裡的商品
	public void updateItem(Integer itemId, Integer newQuantity) {
		shoppingCartDAO.updateItem(itemId, newQuantity);
	}
	
	public List<ShoppingCartItem> getCartItemFromCookie(HttpServletRequest request) {
		return shoppingCartDAO.getCartItemFromCookie(null);
	}
	
	public List<ShoppingCartItem> parseCartCookie(String cartCookieValue) {
		return shoppingCartDAO.parseCartCookie(cartCookieValue);
	}
	
	public String serializeCartItems(List<ShoppingCartItem> cartItems) {
		return shoppingCartDAO.serializeCartItems(cartItems);
	}
	
	public void saveCartToCookie(HttpServletResponse response, List<ShoppingCartItem> cartItems) {
		shoppingCartDAO.saveCartToCookie(response, cartItems);
	}
	
//	private List<ShoppingCartItem> items = new ArrayList<>();
//	
//	public void addItem(ShoppingCartItem item) {
//		items.add(item);
//	}
//	
//	public void removeItem(Integer id) {
//		items.removeIf(item -> item.getItemId().equals(id));
//	}
//	
//	public void clearCart() {
//		items.clear();
//	}
//	
//	public List<ShoppingCartItem> getItem() {
//		return items;
//	}
//	
//	public double getTotalPrice() {
//		return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
//	}
	
	
	
}
