package be.logofac.LogoFac.Controllers;

import be.logofac.LogoFac.views.AddAppointmentPane;
import javafx.fxml.FXML;

public class FirstMenuController extends ViewController {


	public FirstMenuController() {
		super();
	}
	

	@FXML
	private void addAppointment() {
		pane.setNavigatedPane( new AddAppointmentPane(pane));
		
	}

	
}
