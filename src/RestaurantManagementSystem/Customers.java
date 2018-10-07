package RestaurantManagementSystem;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Customers extends Db implements ScreenController,Initializable {
	private String[] full_name;
	private String[] email;
	private String[] phone;
	private String[] username;
	private String[] password;
	@FXML
	private TextField custrmer_full_name,custrmer_email,custrmer_phone,custrmer_username,custrmer_password;
	@FXML
	private Label messageBox;
	@FXML
	private TableView<CustomerList> customerLIstTable;
	@FXML
	private javafx.scene.control.TableColumn<CustomerList, String> customerName,customerPhone,customerEmail,customerUserName;
	@FXML
	private javafx.scene.control.TableColumn<CustomerList, String>customerId;
	
	public void Customers(){
		this.con();
	}
	public void add(){
		String full_name = custrmer_full_name.getText();
		String email = custrmer_email.getText();
		String phone = custrmer_phone.getText();
		String username = custrmer_username.getText();
		String password = custrmer_password.getText();
		String queryStringRs = "insert into users(user_name,pass,full_name,email,mobile,rules) values('"+username+"','"+password+"','"+full_name+"','"+email+"','"+phone+"','customer')";
		try{
			this.sqlStatement.executeUpdate(queryStringRs);
			messageBox.setText("Customer Data Save");
		}catch(Exception e){
			System.out.println(e);
		}
		displayTableData();
	}
	@Override
	public void showCustomers(ActionEvent event) {
		this.load(event, "Customers.fxml");
	}
	@Override
	public void showProducts(ActionEvent event) {
		this.load(event, "Products.fxml");
		
	}
	@Override
	public void showDashboard(ActionEvent event) {
		this.load(event, "Welcome.fxml");
		
	}
	@Override
	public void load(ActionEvent event, String st) {
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
	/*public ArrayList getAllCustomers(){
		
	}*/
	public ObservableList<CustomerList> getCustomersTableData(){
		ObservableList<CustomerList> customersListObAr = FXCollections.observableArrayList();
		Db db = new Db();
		try {
			db.queryResult = db.sqlStatement.executeQuery("select * from users where rules='customer'");
			while(db.queryResult.next()){
				customersListObAr.add(new CustomerList(db.queryResult.getString("id"),db.queryResult.getString("full_name"),db.queryResult.getString("mobile"),db.queryResult.getString("email"),db.queryResult.getString("user_name")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customersListObAr;
	}
	
	public void displayTableData(){
		customerName.setCellValueFactory(new PropertyValueFactory<CustomerList, String>("customerName"));
		customerPhone.setCellValueFactory(new PropertyValueFactory<CustomerList, String>("customerPhone"));
		customerEmail.setCellValueFactory(new PropertyValueFactory<CustomerList, String>("customerEmail"));
		customerUserName.setCellValueFactory(new PropertyValueFactory<CustomerList, String>("customerUserName"));
		customerId.setCellValueFactory(new PropertyValueFactory<CustomerList, String>("customerId"));
		customerLIstTable.setItems(getCustomersTableData());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		displayTableData();
		customerLIstTable.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				CustomerList customer = customerLIstTable.getItems().get(customerLIstTable.getSelectionModel().getSelectedIndex());
				custrmer_full_name.setText(customer.getCustomerName());
				custrmer_email.setText(customer.getCustomerEmail());
				custrmer_phone.setText(customer.getCustomerPhone());
				custrmer_username.setText(customer.getCustomerUserName());
				custrmer_username.setEditable(false);
				custrmer_username.setDisable(true);
			}
		});
	}
	
	
}
