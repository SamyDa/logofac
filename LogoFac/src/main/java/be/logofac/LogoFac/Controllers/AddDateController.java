package be.logofac.LogoFac.Controllers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.domain.Professionnel;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.domain.enums.SeanceDuration;
import be.logofac.LogoFac.domain.enums.SeanceType;
import be.logofac.LogoFac.views.SelectPersonPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AddDateController extends ViewController {

	@FXML
	private DatePicker datePicker;
	@FXML
	private ComboBox<LocalTime > availableTimeslotList;
	@FXML
	private ComboBox<String> durationList;
	@FXML
	private ComboBox<SeanceType> seanceType;
	@FXML
	private TableView<Seance> newAppointmentList;
	@FXML
	private TableColumn<Seance, String> seanceDescription;
	
	
	List<LocalTime> timeList = new ArrayList<LocalTime>();
	List<SeanceType> seanceTypeList = new ArrayList<SeanceType>();
	List<String> duration = new ArrayList<String>();
	List<Seance>seanceList = new ArrayList<Seance>();
	ObservableList<Seance> obsSeanceList;
	
	public AddDateController() {
		
		super();	
		 
		LocalTime time = LocalTime.of(8, 0);
		while(time.isBefore(LocalTime.of(19, 0))) {
			timeList.add(LocalTime.of(time.getHour(), time.getMinute()));
			time = time.plusMinutes(30);
		}
		timeList.sort((x1 , x2 ) -> {if(x1.isAfter(x2)) return -1; else return 1;});
		for(SeanceDuration seance :  SeanceDuration.values())
			duration.add(seance.getDescrption());
		
		for(SeanceType type : SeanceType.values())
			seanceTypeList.add(type);
	}
	
	@FXML
	private void selectDate() {
			
	}
	
	@FXML
	private void addSelectedDate() {
		Professionnel pro = FrontApp.serviceCatalog.getProfessionnelService().findAllPro().stream().findFirst().get();
		
		if(datePicker.getValue() == null || availableTimeslotList.getValue() == null || durationList.getValue() == null || seanceType.getValue() == null)
			return;
		
		
		SeanceDuration seanceDuration = null;
		for(SeanceDuration seanceDur: SeanceDuration.values()) {
			if(durationList.getValue().equals(seanceDur.getDescrption())){
					seanceDuration = seanceDur;
			}
		}
		
		LocalDateTime dateTime = LocalDateTime.of(datePicker.getValue(), availableTimeslotList.getValue());
		Seance seance = new Seance(pane.getCacheData().getPatient(), pro, dateTime, seanceDuration, seanceType.getValue());
		seanceList.add(seance);
		obsSeanceList = FXCollections.observableList(seanceList);
		newAppointmentList.setItems(obsSeanceList);
	
	}

	public void loadControllerLogic() {
		// if the patient has not been selected, the controller navigate to that pane
		if(pane.getCacheData().getPatient() == null)
			pane.setNavigatedPane(new SelectPersonPane(pane));
		availableTimeslotList.setItems( FXCollections.observableList(timeList));
		seanceType.setItems(FXCollections.observableList(seanceTypeList));
		durationList.setItems(FXCollections.observableList(duration));
		seanceType.setValue(SeanceType.Cabinet);
		seanceDescription.setCellValueFactory(cell ->  new SimpleStringProperty(cell.getValue().getHourFrom().format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm ")) + " ("+cell.getValue().getHourNumber().getDescrption()+" " + cell.getValue().getSeanceType().getSeanceString()+")"));
		
	}
	
	@FXML 
	private void saveList() {
		seanceList.forEach(n -> FrontApp.serviceCatalog.getSeanceService().save(n));
		pane.returnBack();
		
	}
	
	@FXML 
	private void deleteAppointment() {
		seanceList.remove(newAppointmentList.getSelectionModel().getSelectedItem());
		obsSeanceList = FXCollections.observableList(seanceList);
		
		
	}
	
}
