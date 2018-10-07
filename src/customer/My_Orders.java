package customer;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import RestaurantManagementSystem.Db;
import RestaurantManagementSystem.screenLoad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class My_Orders implements Initializable{

	@FXML
	TableView<Rs_Foods> viewOrder;
	@FXML
	TableColumn<Rs_Foods, String> order_id,product_name,order_price,order_date,order_quantity;
	private ObservableList<Rs_Foods> allProducts;
	private ResultSet resultSet;
	private Db db;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		db = new Db();
		displayAllOrders();
	}
	private void displayAllOrders() {
		order_id.setCellValueFactory(new PropertyValueFactory<Rs_Foods, String>("id"));
		product_name.setCellValueFactory(new PropertyValueFactory<Rs_Foods, String>("FoodName"));
		order_price.setCellValueFactory(new PropertyValueFactory<Rs_Foods, String>("Price"));
		order_date.setCellValueFactory(new PropertyValueFactory<Rs_Foods, String>("date"));
		order_quantity.setCellValueFactory(new PropertyValueFactory<Rs_Foods, String>("quantity"));
		viewOrder.setItems(getAllOrders());
	}
	public void displayMyOrders(ActionEvent event) {
		new screenLoad().loadScreen(event, "../customer/My_orders.fxml");
	}
	public void displayFindFoods(ActionEvent event) {
		new screenLoad().loadScreen(event,"../customer/Dashboard.fxml");
	}
	private ObservableList<Rs_Foods>  getAllOrders() {
		allProducts = FXCollections.observableArrayList();
		String sqlString = "select orders.*, products.name as pname from orders  join products on products.id = orders.product_id where user_id = '"+CustomerSession.getUserId()+"'  order by id desc";
		resultSet = db.getResult(sqlString);
		try {
			while(resultSet.next()) {
				allProducts.add(new Rs_Foods(
						resultSet.getString("pname"),
						resultSet.getString("price"),
						"",
						resultSet.getString("id"),
						resultSet.getString("oreder_date"),
						resultSet.getString("product_quantity")
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allProducts;
	}
	
}
