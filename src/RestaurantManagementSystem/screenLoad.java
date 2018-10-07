package RestaurantManagementSystem;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class screenLoad {
	public void loadScreen(ActionEvent event, String st){
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
	public void loadScreen(Event event, String st){
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
}
