package RestaurantManagementSystem;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Welcome implements Initializable {
	@FXML
	private Label order_count;
	private Db db;
	private boolean beepPlay;
	private ResultSet resultSet;
	Clip clip;
	@FXML
	private Button employersBtn;
	public void Welcome(){
		this.setOrderMiniCount(false);
		
	}
	public void showCustomers(ActionEvent event){
		this.loadScreen(event, "Customers.fxml");
	}
	public void showProducts(ActionEvent event){
		this.loadScreen(event, "Products.fxml");
	}
	public void showOrders(ActionEvent event){
		this.loadScreen(event, "Orders.fxml");
	}

	public void showAccount(ActionEvent event){
		this.loadScreen(event, "Account.fxml");
	}
	
	private void loadScreen(ActionEvent event, String st){
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
	
	public void setOrderMiniCount(boolean soundPlay){
		String currentOrders = this.order_count.getText();
		this.db = new Db();
		this.resultSet = db.getResult("SELECT * FROM orders where is_served = 0");
		try {
			int rsultCountOfOrder = 0;
			while(this.resultSet.next()){
				rsultCountOfOrder++;
			}
			String totalCount = Integer.toString(rsultCountOfOrder);
			this.order_count.setText(totalCount);
			if(!currentOrders.equals(totalCount) && soundPlay){
				playBeep();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.order_count.setText("");
		}
	}
	public void playBeep(){
		File beepFile = new File("F://BUBT/7th/beep.wav");
		try {
			if(beepFile.exists()){
				clip.open(AudioSystem.getAudioInputStream(beepFile));
				if(!this.beepPlay){
					clip.start();
					this.beepPlay = true;
				}else{
					clip.stop();
					this.beepPlay = false;
				}
				
			}else{
				System.out.println("file Not found");
			}
			
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Sound Playing");
	}
	public void stopBeep(){
		if(clip.isOpen()){
			clip.stop();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.beepPlay = false;
		this.setOrderMiniCount(true);
		// TODO Auto-generated method stub
		Timer timer = new Timer(true);
		TimerTask task = new TimerTask(){
			public void run(){
				//setOrderMiniCount(true);
				//System.out.println("Test ");
			}
		};
		timer.schedule(task, 0l,100l);
		employersBtn.setVisible(false);
	}

	public void welcomeDestroy(){
		this.stopBeep();
	}
}
