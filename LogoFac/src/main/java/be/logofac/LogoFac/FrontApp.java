package be.logofac.LogoFac;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.views.MainPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Service
public class FrontApp extends Application {

	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		System.out.println("This is started bitches !!!");
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Logofac");
		
		initRootLayout();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void initRootLayout() {
		
		MainPane mainPane = new MainPane(primaryStage , rootLayout, null);
		

		
	}
}
