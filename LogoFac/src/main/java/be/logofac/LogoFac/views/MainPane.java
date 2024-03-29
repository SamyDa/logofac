package be.logofac.LogoFac.views;

import java.awt.Desktop;
import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import be.logofac.LogoFac.Controllers.ViewController;
import be.logofac.LogoFac.Utils.CacheData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainPane extends NavigationPane {

	public MainPane( Stage primaryStage, BorderPane rootLayout, NavigationPane parentPane, CacheData cacheData) {
		super(primaryStage,rootLayout,parentPane, cacheData);
	}


	@Override
	protected void showInitialPane() {
		initializeRootLayout();
		showPane();
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
	            controller.loadControllerLogic();
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
	            this.setHeaderController(controller);
	            controller.setPane(this);
	            controller.loadControllerLogic();
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

	@Override
	public void showPane() {
		displayPersonHeader();
		displayMainMenu();
	}

}
