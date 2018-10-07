package RestaurantManagementSystem;
	
import java.awt.geom.Rectangle2D;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Main extends Application implements Initializable{
	@FXML
	private Button rs_forgot_pass;
	@FXML
	private Pane login_section_box;
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
			//Parent root = FXMLLoader.load(getClass().getResource("Customers.fxml"));
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Restaurant Management System");
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			
			Products product = new Products();
			product.productList();
			
			primaryStage.show();
			//login_section_box.setBackground();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		rs_forgot_pass.setVisible(false);
	}
}
