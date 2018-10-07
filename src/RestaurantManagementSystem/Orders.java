package RestaurantManagementSystem;

import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Orders extends Db implements ScreenController,Initializable{
	
	@FXML
	private TableView<Order_Table> productListTable;
	
	@FXML
	private TableColumn<Order_Table, String> productId,productName,productPrice,productQuantity,order_date,user_full_name,customer_mobile;
	
	@FXML
	private Pane order_details_panel;
	
	@FXML
	private Label dis_or_total,dis_or_date,dis_or_p_quantity,dis_or_p_unit_price,dis_or_product_name,dis_c_email,dis_c_phone,dis_c_name;
	@FXML 
	private TextField dis_order_id;
	@FXML 
	private Button orderCompleteBtn;
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
	public ObservableList<Order_Table> getAllOrders(){
		ArrayList allCustomers = this.getAllCustomer();
		ArrayList AllProducts = this.getAllProducts();
		ObservableList orderList = FXCollections.observableArrayList();
		this.queryResult = this.getResult("select * from orders order by id desc");
		try {
			while(this.queryResult.next()){
				//String id,String user_id,String product_id,String product_quantity,String price, String order_date,String is_served
				String price = this.queryResult.getString("price");
				String id = this.queryResult.getString("id");
				String quantity = this.queryResult.getString("product_quantity");
				String name = this.queryResult.getString("product_id");
				String order_date = this.queryResult.getString("oreder_date");
				String is_served = this.queryResult.getString("is_served");
				String user_id = this.queryResult.getString("user_id");
				String product_id = this.queryResult.getString("product_id");
				String customer_mobile = "",user_full_name = "";
				for(int i = 0; i<allCustomers.size(); i++){
					ArrayList<String> cusomterArray = (ArrayList) allCustomers.get(i);
					if(cusomterArray.get(0).equals(user_id)){
						customer_mobile = cusomterArray.get(4);
						user_full_name = cusomterArray.get(2);
					}
					
				}
				String productIdSingle = this.queryResult.getString("product_id");
				for(int i = 0; i<AllProducts.size(); i++){
					ArrayList<String> productTemp = (ArrayList) AllProducts.get(i);
					if(productTemp.get(0).equals(productIdSingle)){
						name = productTemp.get(1);
						price = productTemp.get(3);
					}
				}
				orderList.add(new Order_Table(id,user_id,name,quantity,price,order_date,is_served,customer_mobile,user_full_name));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}
	public void search(ActionEvent event){
		
	}
	public void displayOrders(){
		//productId,productName,productPrice,productQuantity
		productId.setCellValueFactory(new PropertyValueFactory<Order_Table,String>("id"));
		productName.setCellValueFactory(new PropertyValueFactory<Order_Table,String>("product_id"));
		productPrice.setCellValueFactory(new PropertyValueFactory<Order_Table,String>("price"));
		productQuantity.setCellValueFactory(new PropertyValueFactory<Order_Table,String>("product_quantity"));
		order_date.setCellValueFactory(new PropertyValueFactory<Order_Table,String>("oreder_date"));
		customer_mobile.setCellValueFactory(new PropertyValueFactory<Order_Table,String>("customer_mobile"));
		user_full_name.setCellValueFactory(new PropertyValueFactory<Order_Table,String>("user_full_name"));
		productListTable.setItems(this.getAllOrders());
	}
	
	public void setEventInTableRow(){
		productListTable.setOnMouseClicked(new EventHandler<Event>() {
			ArrayList allCustomers = getAllCustomer();
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Order_Table singleOrder = new Order_Table();
				singleOrder = productListTable.getItems().get(productListTable.getSelectionModel().getSelectedIndex());
				//dis_or_total,dis_or_date,dis_or_p_quantity,dis_or_p_unit_price,dis_or_product_name,dis_c_email,dis_c_phone,dis_c_name
				dis_or_date.setText(singleOrder.getOreder_date());
				dis_or_p_unit_price.setText(singleOrder.getPrice());
				dis_or_product_name.setText(singleOrder.getProduct_id());
				dis_or_p_quantity.setText(singleOrder.getProduct_quantity());
				String pductSinglePrice = singleOrder.getPrice();
				double totalOrderPrice = 0;
				totalOrderPrice = Double.parseDouble(singleOrder.getProduct_quantity()) * Double.parseDouble(pductSinglePrice);
				dis_or_total.setText(String.valueOf(totalOrderPrice)+" TK");
				
				int i = 0;
				for(i=0; i<getAllCustomer().size(); i++){
					ArrayList<String> temArrayList = (ArrayList<String>) allCustomers.get(i);
					if(temArrayList.get(0).equals(singleOrder.getUser_id()) ){
						dis_c_email.setText(temArrayList.get(3));
					}
				}
				dis_c_phone.setText(singleOrder.getCustomer_mobile());
				dis_c_name.setText(singleOrder.getUser_full_name());
				order_details_panel.setVisible(true);
				dis_order_id.setText(singleOrder.getId());
				if(singleOrder.getIs_served().equals("0")){
					orderCompleteBtn.setVisible(true);
				}else{
					orderCompleteBtn.setVisible(false);
				}
			}
		});
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		displayOrders();
		order_details_panel.setVisible(false);
		dis_order_id.setVisible(false);
		setEventInTableRow();
	}
	
	
	
	public void CompleteOrder(ActionEvent event){
		String OrderId = dis_order_id.getText();
		queryStringRs = "UPDATE orders SET is_served = 1 WHERE id="+OrderId;
		try {
			this.sqlStatement.executeUpdate(queryStringRs);
			orderCompleteBtn.setVisible(false);
			displayOrders();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void DeleteOrder(ActionEvent event){
		String productId = dis_order_id.getText();
		if(productId.length()<=0)
			return;
		queryStringRs = "DELETE from orders where id = "+productId;
		try {
			this.sqlStatement.executeUpdate(queryStringRs);
			order_details_panel.setVisible(false);
			displayOrders();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList getAllCustomer(){
		ArrayList<ArrayList<String>> customers = new ArrayList<ArrayList<String>>();
		this.queryResult = this.getResult("select * from users where rules = 'customer'");
		try {
			int i = 0;
			while(this.queryResult.next()){
				ArrayList<String> tempList = new ArrayList<String>();
				tempList.add(this.queryResult.getString("id"));
				tempList.add(this.queryResult.getString("user_name"));
				tempList.add(this.queryResult.getString("full_name"));
				tempList.add(this.queryResult.getString("email"));
				tempList.add(this.queryResult.getString("mobile"));
				customers.add(i,tempList);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}
	
	public ArrayList getAllProducts(){
		ArrayList<ArrayList<String>> products = new ArrayList<ArrayList<String>>();
		this.queryResult = this.getResult("select * from products");
		try {
			int i = 0;
			while(this.queryResult.next()){
				ArrayList<String> tempList = new ArrayList<String>();
				tempList.add(this.queryResult.getString("id"));
				tempList.add(this.queryResult.getString("name"));
				tempList.add(this.queryResult.getString("quantity"));
				tempList.add(this.queryResult.getString("unit_price"));
				products.add(i,tempList);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
	
	
}
