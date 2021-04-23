package be.logofac.LogoFac.Controllers;

import be.logofac.LogoFac.views.AddAppointmentPane;
import be.logofac.LogoFac.views.ConsultAppointmentPane;
import be.logofac.LogoFac.views.SelectAppointmentToPrintPane;
import be.logofac.LogoFac.views.SelectOptionPane;
import javafx.fxml.FXML;

public class FirstMenuController extends ViewController {


	public FirstMenuController() {
		super();
	}
	

	@FXML
	private void addAppointment() {
		pane.setNavigatedPane( new AddAppointmentPane(pane));
		
	}
	@FXML
	private void consultAppointment() {
		pane.setNavigatedPane( new ConsultAppointmentPane(pane));
		
	}
	
	@FXML
	private void createInvoice() {
		pane.setNavigatedPane( new SelectAppointmentToPrintPane(pane));
		
	}
	@FXML
	private void manageData() {
		pane.setNavigatedPane( new SelectOptionPane(pane));
		
	}

	
}
