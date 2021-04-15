package be.logofac.LogoFac.Controllers;



import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.views.SelectPersonPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SelectPersonController extends ViewController {
	
	private Patient selectedPatient;
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> idColumn;
    @FXML
    private TableColumn<Patient, String> nameColumn;
    
	public SelectPersonController() {
		super();
	}

	public void loadControllerLogic() {
		
		patientTable.setItems(((SelectPersonPane) pane).getPatientObsist() );		
		idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPatientId())));
		nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName() +" "+ cellData.getValue().getLastName() ));
		// Listen for selection changes and show the person details when changed.
		patientTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> updateSelectedPatient(newValue));
    }
	
	private void updateSelectedPatient(Patient newValue) {
		selectedPatient = newValue;
	}

	@FXML
	private void selectPatient() {
		if(selectedPatient != null) {
			//add the patient to the cache
			pane.getCacheData().setPatient(selectedPatient);
			//Change the value of the header
			pane.getHeaderController().loadControllerLogic();
			//Return to the previous screen
			pane.returnBack();
		}
			
	}

}
