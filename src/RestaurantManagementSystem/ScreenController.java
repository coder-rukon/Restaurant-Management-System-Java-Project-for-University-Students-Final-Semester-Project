package RestaurantManagementSystem;

import javafx.event.ActionEvent;

interface ScreenController {
	
	void showCustomers(ActionEvent event);
	public void showProducts(ActionEvent event);
	public void showDashboard(ActionEvent event);
	public void load(ActionEvent event, String st);
	
}
