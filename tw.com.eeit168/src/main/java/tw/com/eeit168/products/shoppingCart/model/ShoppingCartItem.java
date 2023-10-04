package tw.com.eeit168.products.shoppingCart.model;

public class ShoppingCartItem {
	
	private Integer itemId;
	
	private String itemName;
	
	private Integer quantity;
	
	private double price;

	
	public ShoppingCartItem() {
		
	}

	public ShoppingCartItem(Integer itemId, String itemName, Integer quantity, double price) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.quantity = quantity;
		this.price = price;
	}
	
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}	
	
	
	public double getTotalPrice() {
		return price * quantity;
	}
	
}
