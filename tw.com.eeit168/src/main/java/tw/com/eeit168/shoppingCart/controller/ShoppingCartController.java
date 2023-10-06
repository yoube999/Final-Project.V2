package tw.com.eeit168.shoppingCart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.com.eeit168.shoppingCart.model.ShoppingCartItem;
import tw.com.eeit168.shoppingCart.service.ShoppingCartService;

@RestController
@RequestMapping(path = {"/shoppingcart"})
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping(path = {"/cart"})
	public String viewCart(HttpServletRequest request, Model model) {
		List<ShoppingCartItem> itemFromCookie = shoppingCartService.getCartItemFromCookie(request);
		model.addAttribute("cartItems", itemFromCookie);
		return "cart";
	}
	
	@PostMapping(path = {"/addItem"})
	public String addItem(HttpServletRequest request, HttpServletResponse response, @RequestBody ShoppingCartItem item) {
		shoppingCartService.addItem(item);
		shoppingCartService.saveCartToCookie(response, shoppingCartService.getCartItems());
		return "redirect:/cart";
	}
	
	@DeleteMapping(path = {"/removeItem/{itemId}"})
	public String removeItem(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer itemId) {
		shoppingCartService.removeItem(itemId);
		shoppingCartService.saveCartToCookie(response, shoppingCartService.getCartItems());
		return "redirect:/cart";
	}
	
}
