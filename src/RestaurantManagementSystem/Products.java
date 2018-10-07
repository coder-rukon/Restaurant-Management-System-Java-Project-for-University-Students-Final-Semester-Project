package RestaurantManagementSystem;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Products extends Db  implements ScreenController,Initializable{
	
	@FXML
	private TextField product_quantity,product_name,unit_price,old_product_id;
	@FXML
	private Label product_save_meess;
	@FXML
	private TableView<ProductList> productListTable;
	@FXML
	private javafx.scene.control.TableColumn<ProductList, String> productName,productPrice,productId,productQuantity;
	public Products(){
		this.con();
	}
	public void add(){
		String name = product_name.getText();
		String quantity = product_quantity.getText();
		String price = unit_price.getText();
		String productOldId = old_product_id.getText();
		String queryStringRs;
		if(productOldId.length() >= 1){
			queryStringRs = "UPDATE products SET name = '"+name+"', quantity = '"+quantity+"', unit_price = '"+price+"' WHERE id = "+productOldId;
		}else{
			queryStringRs = "insert into products(name,quantity,unit_price) values('"+name+"','"+quantity+"','"+price+"')";
		}
		
		
		try{
			this.sqlStatement.executeUpdate(queryStringRs);
			product_save_meess.setText("Product Save");
		}catch(Exception e){
			System.out.println(e);
		}
		displayTable();
	}
	public void productList(){
		try{
			this.queryResult = this.sqlStatement.executeQuery("select * from products");
			while(this.queryResult.next()){
				
			}
		}catch(Exception e){
			System.out.println(e);
		}
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
	public ObservableList<ProductList> getAllProducts(){
		ObservableList<ProductList> listProducts =  FXCollections.observableArrayList();
		this.queryResult = this.getResult("select * from products order by id desc");
		try {
			while(this.queryResult.next()){
				listProducts.add(new ProductList(this.queryResult.getString("id"),this.queryResult.getString("name"),this.queryResult.getString("unit_price"),this.queryResult.getString("quantity") ) );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listProducts;
	}
	
	public void displayTable(){
		productName.setCellValueFactory(new PropertyValueFactory<ProductList, String>("productName"));
		productPrice.setCellValueFactory(new PropertyValueFactory<ProductList, String>("productPrice"));
		productId.setCellValueFactory(new PropertyValueFactory<ProductList, String>("productId"));
		productQuantity.setCellValueFactory(new PropertyValueFactory<ProductList, String>("productQuantity"));
		productListTable.setItems(getAllProducts());
		editProduct();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		displayTable();
		old_product_id.setVisible(false);
	}
	
	private void  editProduct(){
		productListTable.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				ProductList pl = productListTable.getItems().get(productListTable.getSelectionModel().getSelectedIndex());
				product_name.setText(pl.getProductName());
				product_quantity.setText(pl.getProductQuantity());
				unit_price.setText(pl.getProductPrice());
				old_product_id.setText(pl.getProductId());
			}
		});
	}
}
