package RestaurantManagementSystem;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Account extends Db implements ScreenController,Initializable {
	Global_Helper global_helper;
	@FXML
	private Label displayTotalSale;
	
	public void Account(){
		
		
	}
	
	public void displayTotalSale(){
		double TotalSale = 0;
		this.global_helper = new Global_Helper();
		ArrayList ProductList = this.global_helper.getAllProducts();

		this.queryResult = this.getResult("select * from orders where is_served = 1");
		try {
			while(this.queryResult.next()){
				for(int i=0; i<ProductList.size(); i++){
					ArrayList tempProduct = (ArrayList) ProductList.get(i);
					if(this.queryResult.getString("product_id").equals(tempProduct.get(0))){
						String unitPrice = (String) tempProduct.get(3);
						String pQuantity = this.queryResult.getString("product_quantity");
						TotalSale += Double.parseDouble(unitPrice) * Double.parseDouble(pQuantity);
					}
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		displayTotalSale.setText(Double.toString(TotalSale) + " TK");
	}
	
	@Override
	public void showCustomers(ActionEvent event) {
		// TODO Auto-generated method stub
		this.load(event, "Customers.fxml");
	}
	@Override
	public void showProducts(ActionEvent event) {
		// TODO Auto-generated method stub
		this.load(event, "Products.fxml");
	}
	@Override
	public void showDashboard(ActionEvent event) {
		// TODO Auto-generated method stub
		this.load(event, "Welcome.fxml");
		
	}
	@Override
	public void load(ActionEvent event, String st) {
		// TODO Auto-generated method stub
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource(st));
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Restaurant Management System");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			appStage.hide();
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error");;
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		displayTotalSale();
	}
}
