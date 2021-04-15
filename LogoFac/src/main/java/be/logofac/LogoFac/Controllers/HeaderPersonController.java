package be.logofac.LogoFac.Controllers;

import be.logofac.LogoFac.views.SelectPersonPane;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeaderPersonController extends ViewController {

	
	@FXML
	private Label patientName ;
	@FXML
	private Label patientFirstName ;
	
	
	
	public HeaderPersonController() {
		super();
	}
	
	@FXML
	private void selectPatient() {
		pane.setNavigatedPane(new SelectPersonPane(pane));
	}
	
	public void loadControllerLogic() {
		if(pane.getCacheData().getPatient()!= null) {
			patientName.setText( pane.getCacheData().getPatient().getLastName());
			patientFirstName.setText(pane.getCacheData().getPatient().getFirstName());
		}		
	}

	
}
