package be.logofac.LogoFac.Controllers;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.views.SelectPersonPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ConsultAppointmentController extends ViewController {

    @FXML
    private TableView<Seance> appointmentTable;
    @FXML
    private TableColumn<Seance, String> dateColumn;
    @FXML
    private TableColumn<Seance, String> durationColumn;
    @FXML
    private TableColumn<Seance, String> invoiceColumn;
    


	public ConsultAppointmentController() {
		// TODO Auto-generated constructor stub
	}


	public void loadControllerLogic() {
		// if the patient has not been selected, the controller navigate to that pane
		if(pane.getCacheData().getPatient() == null)
					pane.setNavigatedPane(new SelectPersonPane(pane));
		List<Seance> seanceList = FrontApp.serviceCatalog.getSeanceService().findAllByPatient(pane.getCacheData().getPatient());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		seanceList.sort((s1,s2) -> {if(s1.getHourFrom().isBefore(s2.getHourFrom()))return 1;else return -1;});
		System.out.println("Nbr of seance for the patient : " + seanceList.size());
		
		appointmentTable.setItems(FXCollections.observableList(seanceList));
		dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getHourFrom().format(formatter))));
		durationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getHourNumber().getDuration())));
		invoiceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf("")));

    }
	
	
	@FXML
	private void clickOk() {
		//return to the previous pane
		
		pane.returnBack();
		
	}

}
