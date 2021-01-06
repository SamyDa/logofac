package be.logofac.LogoFac;

import org.springframework.stereotype.Service;

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
		
		try {
			 // Load root layout from fxml file.
			FXMLLoader fxmlLoader = new FXMLLoader();
			//System.out.println("Location -> " + MainApp.class.getResource("views/RootLayout.fxml"));
			
			fxmlLoader.setLocation( this.getClass().getResource("/views/RootLayout.fxml"));
			rootLayout = (BorderPane) fxmlLoader.load();
			
			 // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
		} catch (Exception e) {
			 e.printStackTrace();
		} 
	}
}
