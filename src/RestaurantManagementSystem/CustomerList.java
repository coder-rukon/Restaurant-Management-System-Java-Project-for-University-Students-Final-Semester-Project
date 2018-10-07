package RestaurantManagementSystem;
public class CustomerList {
	private String customerId,customerName,customerPhone,customerEmail,customerUserName;
	CustomerList(){
		this.customerEmail = "";
		this.customerName = "";
		this.customerPhone = "";
		this.customerUserName = "";
		this.customerId = "";
	}
	CustomerList(String id,String name,String phone,String email, String username){
		this.customerId = id;
		this.customerName = name;
		this.customerPhone = phone;
		this.customerEmail = email;
		this.customerUserName = username;
	}
	public String getCustomerId(){
		return customerId;
	}

	public void setCustomerId(String id){
		this.customerId = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerUserName() {
		return customerUserName;
	}
	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}
	
	
}
