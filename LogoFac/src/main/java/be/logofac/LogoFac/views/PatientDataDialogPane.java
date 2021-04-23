package be.logofac.LogoFac.views;

import com.itextpdf.io.IOException;

import be.logofac.LogoFac.Controllers.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PatientDataDialogPane extends NavigationPane {

	public PatientDataDialogPane(NavigationPane parentPane) {
		super(parentPane);
	}

	@Override
	protected void showInitialPane() {
		 try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(this.getClass().getResource("/views/PatientDataDialog.fxml"));
	        AnchorPane page;
			try {
				page = (AnchorPane) loader.load();
				// Create the dialog Stage.
		        Stage dialogStage = new Stage();
		        dialogStage.setTitle("Edit Person");
		        dialogStage.initModality(Modality.WINDOW_MODAL);
		        dialogStage.initOwner(primaryStage);
		        Scene scene = new Scene(page);
		        dialogStage.setScene(scene);
	
		        // Set the person into the controller.
		        ViewController controller = loader.getController();
		        controller.setPane(this);
		        controller.setStage(dialogStage);
	            controller.loadControllerLogic();
		        // Show the dialog and wait until the user closes it
		        dialogStage.showAndWait();
		        this.returnBack();
			} catch (java.io.IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void showPane() {
		
		showInitialPane();
	}

}
