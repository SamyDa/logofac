package be.logofac.LogoFac.Controllers;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.domain.Adresse;
import be.logofac.LogoFac.domain.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PatientDataDialogController extends ViewController {

	
	@FXML
	private TextField prenomField;
	@FXML
	private TextField nomField;
	@FXML
	private TextField rueField;
	@FXML
	private TextField villeField;
	@FXML
	private TextField codePostalField;
	@FXML
	private TextField paysField;
	@FXML
	private TextField numeroField;
	@FXML
	private DatePicker birthdateField;
	
	
	@Override
	public void loadControllerLogic() {
		
		if(pane.getCacheData().getSelectedPatient() != null) {
			
			prenomField.setText(pane.getCacheData().getSelectedPatient().getFirstName());
			nomField.setText(pane.getCacheData().getSelectedPatient().getLastName());
			rueField.setText(pane.getCacheData().getSelectedPatient().getAdresse().getStreet());
			villeField.setText(pane.getCacheData().getSelectedPatient().getAdresse().getCity());
			codePostalField.setText(String.valueOf(pane.getCacheData().getSelectedPatient().getAdresse().getPostalCode()));
			paysField.setText(pane.getCacheData().getSelectedPatient().getAdresse().getCountry());
			numeroField.setText(pane.getCacheData().getSelectedPatient().getAdresse().getHouseNumber());
			birthdateField.setValue(pane.getCacheData().getSelectedPatient().getBirthdate() );
		}
	}
	
	
	@FXML
	private void cancelClicked() {
		pane.getCacheData().setSelectedPatient(null);
		stage.close();
	}
	
	@FXML
	private void okClicked() {
		
		if(invalidInput()) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Data nto correct");
	        alert.setContentText("Veuillez introduire toutes les informations requises");
	        alert.showAndWait();
			return;
		}
			
		//add the patient data 
		Patient patient = null;
		Integer postalC = null;
		try {
			postalC = Integer.parseInt(codePostalField.getText());
		}catch(Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in Postal Code");
	        alert.setContentText("Veuillez corriger le code postal");
	        alert.showAndWait();
			return;
		}
		if(pane.getCacheData().getSelectedPatient() == null){
			
			Adresse adresse = new Adresse(rueField.getText(), villeField.getText(), numeroField.getText(), postalC, paysField.getText());
			patient = new Patient(prenomField.getText(), nomField.getText(),  birthdateField.getValue(), adresse);
		}
		else {
			
			patient = pane.getCacheData().getSelectedPatient();
			patient.setBirthdate( birthdateField.getValue());
			patient.setFirstName(prenomField.getText());
			patient.setLastName(nomField.getText());
			
			patient.getAdresse().setCity(villeField.getText());
			patient.getAdresse().setCountry(paysField.getText());
			patient.getAdresse().setHouseNumber( numeroField.getText());
			patient.getAdresse().setPostalCode(postalC);
			patient.getAdresse().setStreet(rueField.getText());
		}
		
		FrontApp.serviceCatalog.getPatientService().save(patient);
		stage.close();
		
	}


	private boolean invalidInput() {
		
		if(		prenomField.getText().isEmpty() ||prenomField.getText().isBlank() || 
				nomField.getText().isEmpty() ||nomField.getText().isBlank() || 
				rueField.getText().isEmpty() ||rueField.getText().isBlank() || 
				villeField.getText().isEmpty() ||villeField.getText().isBlank() || 
				paysField.getText().isEmpty() ||paysField.getText().isBlank() || 
				numeroField.getText().isEmpty() ||numeroField.getText().isBlank() || 
				codePostalField.getText().isEmpty() ||codePostalField.getText().isBlank() || 
				birthdateField.getValue() == null)
			return true;
		else	
			return false;
	}
	
}
