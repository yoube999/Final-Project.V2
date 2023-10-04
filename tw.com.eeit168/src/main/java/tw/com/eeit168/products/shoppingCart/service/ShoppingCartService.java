package tw.com.eeit168.products.shoppingCart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.eeit168.products.shoppingCart.dao.ShoppingCartDAO;
import tw.com.eeit168.products.shoppingCart.model.ShoppingCartItem;

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
		
	//計算價格
	public double getTotalPrice() {
		return shoppingCartDAO.getTotalPrice();
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
	
}
