package be.logofac.LogoFac.Controllers;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.Utils.DocumentProcess;
import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.views.SelectAppointmentToPrintPane;
import be.logofac.LogoFac.views.SelectPersonPane;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;

public class SelectAppointmentToPrintController extends ViewController {
	
    @FXML
    private TableView<Seance> seanceTable;
    @FXML
    private TableColumn<Seance, String> idColumn;
    @FXML
    private TableColumn<Seance, Boolean> selectColumn;
    
    @FXML
    private TextField communication;
    
	public SelectAppointmentToPrintController() {
		super();
	}

	public void loadControllerLogic() {
		
		if(pane.getCacheData().getPatient() == null)
			pane.setNavigatedPane(new SelectPersonPane(pane));
		
		seanceTable.setItems(((SelectAppointmentToPrintPane) pane).getPatientObsist() );		
		idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getHourFrom())));
		
		
		selectColumn.setCellValueFactory(cell ->
		    {
		        final BooleanProperty prop = new SimpleBooleanProperty(!FrontApp.serviceCatalog.getFactureService().isSeanceInvoiced(cell.getValue()));
		        return prop;
		    });
		selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));
		
    }
	
	
	@FXML
	private void createDocument() {
		System.out.println("Document pushed");
		ArrayList<Seance> selectedSeances = new ArrayList<Seance>();
		seanceTable.getItems().forEach(n ->{  if(selectColumn.getCellObservableValue(n).getValue()) selectedSeances.add(n);} );
		
		if(selectedSeances.size() > 0) {
		
			
			DocumentProcess docProcess  = new DocumentProcess() ;
			Facture facture = createFacture(selectedSeances);
			docProcess.loadDocumentData(facture);
			facture.setPrinted(true);
			FrontApp.serviceCatalog.getFactureService().save(facture);
		}
		else {
			System.out.println("Pas de séances sélectionnées :(");
		}
		
		pane.returnBack();
			
	}

	private Facture createFacture(List<Seance> seances) {
		int year = seances.get(0).getHourFrom().getYear();
		int invoiceCount=  FrontApp.serviceCatalog.getFactureService().countInvoiceInTime(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31)) + 1;
		String reference = Facture.createReference(year ,invoiceCount);
		String communicationText = communication.getText();
		Facture facture = new Facture(reference, false, pane.getCacheData().getPatient(),  FrontApp.serviceCatalog.getProfessionnelService().findAllPro().stream().findFirst().get(), communicationText, LocalDate.now(), seances);
		FrontApp.serviceCatalog.getFactureService().save(facture);
		
		return facture;
		
	}

}
