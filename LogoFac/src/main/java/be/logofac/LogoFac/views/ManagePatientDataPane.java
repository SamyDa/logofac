package be.logofac.LogoFac.views;

import be.logofac.LogoFac.Controllers.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

public class ManagePatientDataPane extends NavigationPane {

	public ManagePatientDataPane(NavigationPane parentPane) {
		super(parentPane);
	}
	
	public  ManagePatientDataPane() {
		
		super(null);
	}

	@Override
	protected void showInitialPane() {
		 // then display the first main menu
		 try {
			    FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/OptionPatientManagement.fxml"));
	            AnchorPane firstMenu = (AnchorPane) fxmlLoader.load();
	            rootLayout.setCenter(firstMenu);
	            ViewController controller = fxmlLoader.getController();
	            controller.setPane(this);
	            controller.loadControllerLogic();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public void showPane() {
		showInitialPane();
		
	}

	

}
