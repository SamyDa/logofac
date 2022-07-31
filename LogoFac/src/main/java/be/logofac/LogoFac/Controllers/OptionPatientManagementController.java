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
import javafx.scene.control.CheckBox;
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
	private Label email;
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
	@FXML
	private CheckBox bim ; 

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
		prenom.setText(patient.getFirstName()); 
		nom.setText(patient.getLastName()); 
		id.setText(String.valueOf(patient.patientId)); 
		email.setText(patient.getEmail()); 
		rue.setText(patient.getAdresse().getStreet()); 
		numero.setText(patient.getAdresse().getHouseNumber()); 
		ville.setText(patient.getAdresse().getCity()); 
		codePostal.setText(String.valueOf(patient.getAdresse().getPostalCode())); 
		pays.setText(patient.getAdresse().getCountry()); 
		bim.setSelected(patient.isBim());
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
	}

	@FXML
	private void editPatient() {
		openPatientDialog(patientTable.getSelectionModel().getSelectedItem());
		patientList = FrontApp.serviceCatalog.getPatientService().findAll();
		obsPatientList = FXCollections.observableList(patientList);
		
	}
	
	
}
