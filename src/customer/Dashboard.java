package customer;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import RestaurantManagementSystem.Db;
import RestaurantManagementSystem.ProductList;
import RestaurantManagementSystem.screenLoad;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Dashboard extends screenLoad implements Initializable {
	@FXML
	Button btn_find_foods,btn_my_order;
	@FXML
	TableView<Rs_Foods> viewFoods;
	@FXML
	TableColumn<Rs_Foods, String> FoodNameColumn,PriceColumn;
	private ObservableList<Rs_Foods> allProducts;
	private ResultSet resultSet;
	private Db db;
	private Object that;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		that = this;
		db = new Db();
		DisplayProducts();
		
		
		
	}
	
	public void displayMyOrders(ActionEvent event) {
		new screenLoad().loadScreen(event, "../customer/My_orders.fxml");
	}
	public void displayFindFoods(ActionEvent event) {
		new screenLoad().loadScreen(event,"../customer/Dashboard.fxml");
	}
	
	
	
	
	
	private void DisplayProducts() {
		FoodNameColumn.setCellValueFactory(new PropertyValueFactory<Rs_Foods, String>("FoodName"));
		PriceColumn.setCellValueFactory(new PropertyValueFactory<Rs_Foods, String>("Price"));
		viewFoods.setItems(allProducts());
		viewFoods.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Rs_Foods tempFood = viewFoods.getSelectionModel().getSelectedItem();
				
				TextInputDialog dialog = new TextInputDialog("1");
				dialog.setContentText("Quantity:");
				dialog.setHeaderText(tempFood.getFoodName());
				dialog.setGraphic(null);
				dialog.setTitle("Order now");
				Optional<String> quantity = dialog.showAndWait();
				String rsTodayData = "";
				SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
				Date dateTemp = new Date();
				rsTodayData = formatDate.format(dateTemp);
				if(quantity.isPresent()) {
					String insertNewOrder = "INSERT INTO orders(user_id,product_id,product_quantity,price,oreder_date,is_served) values('"+CustomerSession.getUserId()+"','"+tempFood.getId()+"','"+quantity.get()+"','"+tempFood.getPrice()+"','"+rsTodayData+"','0')";
					try {
						db.sqlStatement.executeUpdate(insertNewOrder);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Order Confiration");
					alert.setHeaderText("Your order has been placed!");
					alert.setContentText(null);
					ButtonType displayOrders = new ButtonType("View Orders");
					ButtonType makeMoreOrders = new ButtonType("Continue Order",ButtonData.CANCEL_CLOSE);
					alert.getButtonTypes().setAll(displayOrders,makeMoreOrders);
					Optional<ButtonType> result= alert.showAndWait();
					
					if(result.get().equals(displayOrders)) {
						new screenLoad().loadScreen(event, "../customer/My_orders.fxml");
					}
				}
			}
		});
	}
	
	private ObservableList<Rs_Foods>  allProducts() {
		allProducts = FXCollections.observableArrayList();
		String sqlString = "select * from products order by id desc";
		resultSet = db.getResult(sqlString);
		try {
			while(resultSet.next()) {
				allProducts.add(new Rs_Foods(resultSet.getString("id"),resultSet.getString("name"),resultSet.getString("unit_price"),"addToCart"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allProducts;
	}
	
	
}
