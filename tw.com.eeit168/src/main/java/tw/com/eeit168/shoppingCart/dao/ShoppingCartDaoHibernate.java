package tw.com.eeit168.shoppingCart.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.eeit168.shoppingCart.model.ShoppingCartItem;

@Repository
public class ShoppingCartDaoHibernate implements ShoppingCartDAO {
	
	private List<ShoppingCartItem> cartItems = new ArrayList<>();
	
	//取得購物車裡的商品
	public List<ShoppingCartItem> getCartItems() {
		return cartItems;
	}
	
	//新增商品到購物車
	public void addItem(ShoppingCartItem item) {
		for(ShoppingCartItem items : cartItems) {
			if(items.getItemId().equals(item.getItemId())) {
				items.setQuantity(items.getQuantity() + item.getQuantity());
				return;
			}
		}
		cartItems.add(item);
	}
	
	//刪除購物車裡的商品
	public void removeItem(Integer itemId) {
		cartItems.removeIf(item -> item.getItemId().equals(itemId));
	}
	
	//更新購物車裡的商品
	public void updateItem(Integer itemId, Integer newQuantity) {
		for(ShoppingCartItem items : cartItems) {
			if(items.getItemId().equals(itemId)) {
				items.setQuantity(newQuantity);
				return;
			}
		}
	}
	
	public List<ShoppingCartItem> getCartItemFromCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if("cart".equals(cookie.getName())) {
					String cartCookieValue = cookie.getValue();
					return parseCartCookie(cartCookieValue);
				}
			}
		}
		return new ArrayList<>();
	}
	
	public List<ShoppingCartItem> parseCartCookie(String cartCookieValue) {
		List<ShoppingCartItem> cartItems = new ArrayList<>();
		if(cartCookieValue != null && !cartCookieValue.isEmpty()) {
			String[] strings = cartCookieValue.split(",");
			for(String itemString : strings) {
				String[] itemParts = itemString.split(":");
				if(itemParts.length == 4) {
					Integer itemId = Integer.parseInt(itemParts[0]);
					String itemName = itemParts[1];
					Integer quantity = Integer.parseInt(itemParts[2]);
					double price = Double.parseDouble(itemParts[3]);
					
					ShoppingCartItem item = new ShoppingCartItem();
					item.setItemId(itemId);
					item.setItemName(itemName);
					item.setQuantity(quantity);
					item.setPrice(price);
					cartItems.add(item);		
				}
			}
		}
		return cartItems;
	}
	
	public String serializeCartItems(List<ShoppingCartItem> cartItems) {
		StringBuilder builder = new StringBuilder();
		for(ShoppingCartItem items : cartItems) {
			String itemString = items.getItemId() + ":" + items.getItemName() + ":" + items.getQuantity() + ":" + items.getPrice();
			if(builder.length() > 0) {
				builder.append(",");
			}
			builder.append(itemString);
		}
		return builder.toString();
	}
	
	
	public void saveCartToCookie(HttpServletResponse response, List<ShoppingCartItem> cartItems) {
		String cartCookieValue = serializeCartItems(cartItems);
		Cookie cartCookie = new Cookie("cart", cartCookieValue);
		cartCookie.setMaxAge(36000);
		cartCookie.setPath("/");
		response.addCookie(cartCookie);
	}

}
