package RestaurantManagementSystem;

public class ProductList {
	private String productName,productPrice,productQuantity,productId;
	ProductList(){
		this.productName = "";
		this.productPrice = "";
		this.productQuantity = "";
		this.productId = "";
	}
	ProductList(String id, String name,String price, String quantity){
		this.productId = id;
		this.productName = name;
		this.productPrice  = price;
		this.productQuantity = quantity;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
}
