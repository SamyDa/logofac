package be.logofac.LogoFac.Controllers;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.Utils.ViewNavigator;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.enums.Options;
import be.logofac.LogoFac.domain.enums.Views;
import be.logofac.LogoFac.views.NavigationPane;
import be.logofac.LogoFac.views.PatientDataDialogPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class OptionPatientManagementController extends ViewController {

	@FXML
	private TableView<Patient> patientTable;
	@FXML
	private TableColumn<Patient, String> nameColumn;
	@FXML
	private TableColumn<Patient, String> firstnameColumn;
	
	@FXML
	private Label prenom;
	@FXML
	private Label nom;
	@FXML
	private Label age;
	@FXML
	private Label id;
	@FXML
	private Label rue;
	@FXML
	private Label ville;
	@FXML
	private Label pays;
	@FXML
	private Label codePostal;
	@FXML
	private Label numero;
	

	private List<Patient> patientList = new ArrayList<Patient>();
	private ObservableList<Patient> obsPatientList;
	
	
	@Override
	public void loadControllerLogic() {
		
		patientList = FrontApp.serviceCatalog.getPatientService().findAll();
		obsPatientList = FXCollections.observableList(patientList);
		patientTable.setItems(obsPatientList);
		
		nameColumn.setCellValueFactory(n -> new SimpleStringProperty(n.getValue().getLastName()));
		firstnameColumn.setCellValueFactory(n -> new SimpleStringProperty(n.getValue().getFirstName()));
		patientTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {showPatientDetails(newValue);});
		
		
	}
	
	private void showPatientDetails(Patient patient) {
		String birth = "";
		String ageStr = ""; 
		if (patient.getBirthdate() == null)
		{
			birth ="01-01-0001";
			ageStr = "0 ans";
		}
		else {
			birth = patient.getBirthdate().format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));
			
			if(patient.getBirthdate().plusYears(LocalDate.now().getYear()-patient.getBirthdate().getYear()).isAfter(LocalDate.now()))
				ageStr = String.valueOf(LocalDate.now().getYear()-patient.getBirthdate().getYear()-1) + " ans";
			else
				ageStr = String.valueOf(LocalDate.now().getYear()-patient.getBirthdate().getYear()) + " ans";
			
		}
		prenom.setText(patient.getFirstName()); 
		nom.setText(patient.getLastName()); 
		id.setText(String.valueOf(patient.patientId)); 
		age.setText(ageStr + " ("+birth+")"); 
		rue.setText(patient.getAdresse().getStreet()); 
		numero.setText(patient.getAdresse().getHouseNumber()); 
		ville.setText(patient.getAdresse().getCity()); 
		codePostal.setText(String.valueOf(patient.getAdresse().getPostalCode())); 
		pays.setText(patient.getAdresse().getCountry()); 
		 
	}

	@FXML
	private void back(){
		pane.returnBack();
	}
	
	@FXML
	private void addPatient() {
		openPatientDialog(null);
	}
	
	private void openPatientDialog(Patient patient) {
		pane.getCacheData().setSelectedPatient(patient);
		PatientDataDialogPane dataDialogPane = new PatientDataDialogPane(pane);
		
		//newPane.showPane();
				
	}

	@FXML
	private void editPatient() {
		openPatientDialog(patientTable.getSelectionModel().getSelectedItem());
		patientList = FrontApp.serviceCatalog.getPatientService().findAll();
		obsPatientList = FXCollections.observableList(patientList);
		
	}
	
	
}
