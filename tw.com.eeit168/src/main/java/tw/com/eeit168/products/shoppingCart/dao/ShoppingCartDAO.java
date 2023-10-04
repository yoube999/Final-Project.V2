package tw.com.eeit168.products.shoppingCart.dao;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.eeit168.products.shoppingCart.model.ShoppingCartItem;

public interface ShoppingCartDAO {
	
	public List<ShoppingCartItem> getCartItems();
	
	public void addItem(ShoppingCartItem item);
	
	public void removeItem(Integer itemId);
	
	public void updateItem(Integer itemId, Integer newQuantity);
	
	public double getTotalPrice();
	
	public List<ShoppingCartItem> getCartItemFromCookie(HttpServletRequest request);
	
	public List<ShoppingCartItem> parseCartCookie(String cartCookieValue);
	
	public String serializeCartItems(List<ShoppingCartItem> cartItems);
	
	public void saveCartToCookie(HttpServletResponse response, List<ShoppingCartItem> cartItems);

}
