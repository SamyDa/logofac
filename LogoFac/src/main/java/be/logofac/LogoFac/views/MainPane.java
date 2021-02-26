package be.logofac.LogoFac.views;

import be.logofac.LogoFac.Controllers.FirstMenuController;
import be.logofac.LogoFac.Controllers.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPane extends NavigationPane {

	public MainPane( Stage primaryStage, BorderPane rootLayout, NavigationPane parentPane) {
		super(primaryStage,rootLayout,parentPane);
	}


	@Override
	protected void showPane() {
		
		initializeRootLayout();
		displayPersonHeader();
		displayMainMenu();
	}


	private void displayMainMenu() {

		 // then display the first main menu
		 try {
			 	FXMLLoader fxmlLoader = new FXMLLoader();
			 	
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/FirstMenu.fxml"));
	            AnchorPane firstMenu = (AnchorPane) fxmlLoader.load();
	            rootLayout.setCenter(firstMenu);
	            ViewController  controller = fxmlLoader.getController();
	            controller.setPane(this);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	}


	private void displayPersonHeader() {
		 // then display the first main menu
		 try {
			 	FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/HeaderPerson.fxml"));
	            AnchorPane personHeader = (AnchorPane) fxmlLoader.load();
	            rootLayout.setTop(personHeader);
	            
	            ViewController controller = fxmlLoader.getController();
	            controller.setPane(this);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }		
	}


	private void initializeRootLayout() {
		// first create the root layout 
				try {
					FXMLLoader fxmlLoader = new FXMLLoader();
				   // Load root layout from fxml file.
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
