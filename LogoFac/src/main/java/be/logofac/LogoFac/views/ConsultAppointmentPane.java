package be.logofac.LogoFac.views;

import be.logofac.LogoFac.Controllers.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ConsultAppointmentPane extends NavigationPane {


	public ConsultAppointmentPane(NavigationPane parentPane) {
		super(parentPane);
	}

	@Override
	protected void showInitialPane() {
		 try {
			    FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/ConsultAppointment.fxml"));
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
	protected void showPane() {
		// TODO Auto-generated method stub
		
	}

	
}
