package RestaurantManagementSystem;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import customer.CustomerSession;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Users extends screenLoad implements Initializable {
	private boolean login = false;
	private String userType = "customer";
	private String queryStringRs;
	private ResultSet queryResult;
	private Statement sqlStatement;
	@FXML
	private Button rs_forgot_pass,showRegView;
	@FXML
	private Button rs_display_login;
	@FXML
	private TextField rs_user_name;
	@FXML
	private TextField rs_user_pass;
	@FXML
	private Label login_message;
	// register Options
	@FXML
	private Label login_message1;
	@FXML
	private TextField reg_user_name,rs_user_pass1,rs_user_pass11,reg_full_name,reg_email,reg_mobile;
	
	@FXML
	private Pane register_box,login_section_box;
	
	public Users(){
		con();
	}
	
	public void login(ActionEvent event){
		
		String userFullName="",UserEmail="",UserId="";
	
		
		if(this.rs_user_name.getText().length()<=0  || this.rs_user_pass.getText().length() <= 0){
			login_message.setText("User name and Password Required!");
			return;
		}
		try{
			queryResult = sqlStatement.executeQuery("select * from users where user_name='"+rs_user_name.getText()+"' and pass='"+rs_user_pass.getText()+"'");
			while(queryResult.next()){
				login = true;
				this.userType = queryResult.getString("rules");
				UserId = queryResult.getString("id");
				userFullName = queryResult.getString("name");
				
			}
		}catch(Exception e){
			
		}
		if(login){
			if(!this.userType.equals("admin")) {
				CustomerSession.setUserId(UserId);
				CustomerSession.setUserName(userFullName);
				this.loadScreen(event, "../customer/Dashboard.fxml");
			}else {
				try {
					Stage primaryStage = new Stage();
					Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
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
				}
			}
			
		}else{
			login_message.setText("Username and password do not match");
		}
	}
	
	public void register(ActionEvent event){
		/*reg_user_name,
		rs_user_pass1,
		rs_user_pass11,
		reg_full_name,
		reg_email,
		reg_mobile;
		*/
		queryStringRs = "select * from users where user_name='"+reg_user_name.getText()+"'";
		if(!Is_Exist(queryStringRs)){
			queryStringRs = "INSERT INTO users(user_name,pass,full_name,email,mobile,rules) VALUES ('"+reg_user_name.getText()+"', '"+rs_user_pass1.getText()+"', '"+reg_full_name.getText()+"', '"+reg_email.getText()+"', '"+reg_mobile.getText()+"', 'customer')";
			try{
				sqlStatement.executeUpdate(queryStringRs);
				login_message1.setText("Registration Success. Please Login To access.");
			}catch(Exception e){
				System.out.println(e);
			}
			
		}else{
			
			login_message1.setText("Already Exist");
		}
	}
	public ResultSet getAllCustomers(){
		queryStringRs = "select * from users where rules='customer'";
		try{
			queryResult = sqlStatement.executeQuery(queryStringRs);
		}
		catch(Exception e){
			System.out.print(e);
		}
		return queryResult;
	}
	public boolean Is_Exist(String queryByrs){
		boolean result = false;
		try{
			queryResult = sqlStatement.executeQuery(queryByrs);
			while(queryResult.next()){
				result = true;
			}
		}catch(Exception e){
			
		}
		return result;
	}
	private void con(){
		try{
			Connection mysqlConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rms","root","");
			sqlStatement = mysqlConnection.createStatement();
		}catch(Exception e){
			
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rs_forgot_pass.setVisible(false);
		register_box.setVisible(false);
		register_box.setLayoutX(login_section_box.getLayoutX());
		showRegView.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				register_box.setVisible(true);
				login_section_box.setVisible(false);
				
			}
		});
		
		rs_display_login.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				register_box.setVisible(false);
				login_section_box.setVisible(true);
			}
			
		});
	}
	
}
