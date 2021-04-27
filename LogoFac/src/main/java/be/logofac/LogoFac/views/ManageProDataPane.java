package be.logofac.LogoFac.views;

import be.logofac.LogoFac.Controllers.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

public class ManageProDataPane extends NavigationPane {

	public ManageProDataPane(NavigationPane parentPane) {
		super(parentPane);
	}
	
	public  ManageProDataPane() {
		
		super(null);
	}

	@Override
	protected void showInitialPane() {
		 // then display the first main menu
		 try {
			    FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/OptionProDataManagement.fxml"));
	            AnchorPane proDataMgt = (AnchorPane) fxmlLoader.load();
	            rootLayout.setCenter(proDataMgt);
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
