package be.logofac.LogoFac.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class AddAppointmentPane extends NavigationPane {

	public AddAppointmentPane(NavigationPane parentPane) {
		super(parentPane);
	}

	@Override
	protected void showPane() {
		 // then display the first main menu
		 try {
			    FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/AddAppointment.fxml"));
	            AnchorPane firstMenu = (AnchorPane) fxmlLoader.load();
	            rootLayout.setCenter(firstMenu);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}

}
