package be.logofac.LogoFac.Controllers;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.Utils.ViewNavigator;
import be.logofac.LogoFac.domain.enums.Options;
import be.logofac.LogoFac.views.AddAppointmentPane;
import be.logofac.LogoFac.views.ConsultAppointmentPane;
import be.logofac.LogoFac.views.ListInvoicePane;
import be.logofac.LogoFac.views.NavigationPane;
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
	
	@FXML
	private void regenerateInvoice() {
		pane.setNavigatedPane( new ListInvoicePane(pane));
		
	}
	

	
	@Override
	public void loadControllerLogic() {
		if(FrontApp.serviceCatalog.getProfessionnelService().findAllPro().size() == 0) {
			NavigationPane pane  = (new ViewNavigator()).getViewFromInputOption(Options.ProManagement);
			pane.setParentPane(this.pane);
			pane.showPane();
		}
			
	}
	
}
