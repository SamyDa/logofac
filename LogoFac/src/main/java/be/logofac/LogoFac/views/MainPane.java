package be.logofac.LogoFac.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPane extends NavigationPane {

	public MainPane( Stage primaryStage, BorderPane rootLayout, NavigationPane parentPane) {
		super(primaryStage,rootLayout,parentPane);
	}

	
	public void addAppointment() {
		navigatedPane = new AddAppointmentPane(this);
		
	}


	@Override
	protected void showPane() {
		
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
