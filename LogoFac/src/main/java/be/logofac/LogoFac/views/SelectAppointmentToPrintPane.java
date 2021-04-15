package be.logofac.LogoFac.views;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.Controllers.SelectPersonController;
import be.logofac.LogoFac.Controllers.ViewController;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.Seance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SelectAppointmentToPrintPane extends NavigationPane {

	private ObservableList<Seance> seanceObsist;
	
	public SelectAppointmentToPrintPane(NavigationPane parentPane) {
		super(parentPane);
		
	}

	@Override
	protected void showInitialPane() {
		 // then display the first main menu
		fillSeanceList();
		 try {
			 	FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/AppointmentSelectionToPrint.fxml"));
	            AnchorPane selectSeance = (AnchorPane) fxmlLoader.load();
	            rootLayout.setCenter(selectSeance);
	            
	            ViewController controller = fxmlLoader.getController();
	            controller.setPane(this);
	            controller.loadControllerLogic();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }		

	}
	
	public void fillSeanceList() {
		seanceObsist = FXCollections.observableList(FrontApp.serviceCatalog.getSeanceService().findAllByPatient(this.getCacheData().getPatient()));
		
	}

	public ObservableList<Seance> getPatientObsist() {
		return seanceObsist;
	}



	@Override
	protected void showPane() {
		// TODO Auto-generated method stub
		
	}
	
	

}
