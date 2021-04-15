package be.logofac.LogoFac.views;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.Controllers.SelectPersonController;
import be.logofac.LogoFac.Controllers.ViewController;
import be.logofac.LogoFac.domain.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SelectPersonPane extends NavigationPane {

	private ObservableList<Patient> patientObsist;
	
	public SelectPersonPane(NavigationPane parentPane) {
		super(parentPane);
		
	}

	@Override
	protected void showInitialPane() {
		 // then display the first main menu
		fillPatientList();
		 try {
			 	FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/SelectPerson.fxml"));
	            AnchorPane selectPerson = (AnchorPane) fxmlLoader.load();
	            rootLayout.setCenter(selectPerson);
	            
	            ViewController controller = fxmlLoader.getController();
	            controller.setPane(this);
	            controller.loadControllerLogic();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }		

	}
	
	public void fillPatientList() {
		patientObsist = FXCollections.observableList(FrontApp.serviceCatalog.getPatientService().findAll());
		
	}

	public ObservableList<Patient> getPatientObsist() {
		return patientObsist;
	}



	@Override
	protected void showPane() {
		// TODO Auto-generated method stub
		
	}
	
	

}
