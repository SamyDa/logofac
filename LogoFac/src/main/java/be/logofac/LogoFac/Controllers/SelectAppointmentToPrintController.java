package be.logofac.LogoFac.Controllers;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.Utils.DocumentProcess;
import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.domain.enums.Mois;
import be.logofac.LogoFac.domain.enums.ParameterReference;
import be.logofac.LogoFac.views.SelectPersonPane;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.util.Callback;

public class SelectAppointmentToPrintController extends ViewController {
	
    @FXML
    private TableView<Seance> seanceTable;
    @FXML
    private TableColumn<Seance, String> idColumn;
    @FXML
    private TableColumn<Seance, Boolean> selectColumn;
    
    @FXML
    private TextField communication;
    
    @FXML
    private ComboBox<Mois> listOfMonths;
    @FXML
    private ComboBox<Integer> listOfYears;
    

	private ObservableList<Seance> seanceObsist;
	private ObservableList<Mois> moisObsist;
	private ObservableList<Integer> yearObsist;
	private Mois selectedMonth;
	private int selectedYear;
	
	public SelectAppointmentToPrintController() {
		super();
	}

	public void loadControllerLogic() {
		
		if(pane.getCacheData().getPatient() == null)
			pane.setNavigatedPane(new SelectPersonPane(pane));
		else {
			
			listOfMonths.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {selectedMonth = newValue; fillSeanceList(); communication.setText("Paiment " + selectedMonth + " " + pane.getCacheData().getPatient().getFirstName() + " " + pane.getCacheData().getPatient().getLastName());});
			listOfYears.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {selectedYear = newValue; fillSeanceList();});
			selectedYear = Integer.valueOf(LocalDate.now().getYear());
			listOfYears.setValue(selectedYear);
			fillSeanceList();
			fillMonthList();
			fillYearList();
				
			idColumn.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getHourFrom().format(DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm"))));
			

			selectColumn.setCellValueFactory(new Callback<CellDataFeatures<Seance, Boolean>, ObservableValue<Boolean>>() {
 
            @Override
            public ObservableValue<Boolean> call(CellDataFeatures<Seance, Boolean> param) {
                Seance seance = param.getValue();
 
                if(seance.isToPrint() == null) {
                	seance.setToPrint(!FrontApp.serviceCatalog.getFactureService().isSeanceInvoiced(seance));
                }
                
                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(seance.isToPrint());
 
                
 
                // When "printed" column change.
                booleanProp.addListener(new ChangeListener<Boolean>() {
 
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                            Boolean newValue) {
                        seance.setToPrint(newValue);
                    }
                });
                return booleanProp;
            }
        });
 
		selectColumn.setCellFactory(new Callback<TableColumn<Seance, Boolean>, TableCell<Seance, Boolean>>() {
            @Override
            public TableCell<Seance, Boolean> call(TableColumn<Seance, Boolean> p) {
                CheckBoxTableCell<Seance, Boolean> cell = new CheckBoxTableCell<Seance, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });
		}
		
		
    }
	
	

	private void fillYearList() {
		
		List<Integer> yearList = new ArrayList<Integer>();
		int year = LocalDate.now().getYear();
		
		for(int i = 0 ; i<5; i++) {
			yearList.add(Integer.valueOf(year-i));
		}
		yearObsist = FXCollections.observableList(yearList);
		listOfYears.setItems(yearObsist);
		
	}

	private void fillMonthList() {
		//List<Mois> moisList =  new ArrayList<Mois>();
		moisObsist = FXCollections.observableArrayList(Mois.values());
		listOfMonths.setItems(moisObsist);
		
	}

	public void fillSeanceList() {
		
		if(selectedMonth != null) {
			seanceObsist = FXCollections.observableList(FrontApp.serviceCatalog.getSeanceService().findAllByPatient(pane.getCacheData().getPatient()).stream().filter(n -> (n.getHourFrom().getMonthValue() == (selectedMonth.getMois())) && (n.getHourFrom().getYear() == selectedYear)).collect(Collectors.toList()));
			seanceTable.setItems(seanceObsist);
		}
	}

	public ObservableList<Seance> getPatientObsist() {
		return seanceObsist;
	}


	@FXML
	private void test() {
		System.out.println("Test change test");
	}
	@FXML
	private void createDocument() {
		System.out.println("Document pushed");
		ArrayList<Seance> selectedSeances = new ArrayList<Seance>();
		
		seanceTable.getItems().forEach(n ->{ 
			if(n.isToPrint())
				selectedSeances.add(n);
			}
		);
		System.out.println("size of selected seances =   " + selectedSeances.size() );
		
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
		int invoiceCount=  FrontApp.serviceCatalog.getFactureService().countInvoiceInTime(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31)) + 2;
		if(FrontApp.serviceCatalog.getParameterService().findGeneralParameter(ParameterReference.INVOICE_OFFSET) != null ) {
			try {
				Integer integer =  (Integer) FrontApp.serviceCatalog.getParameterService().findGeneralParameter(ParameterReference.INVOICE_OFFSET).getValue();
				invoiceCount = invoiceCount + integer;
			}catch(Exception e) {
				
			}
		}
		String reference = Facture.createReference(year ,invoiceCount);
		String communicationText = communication.getText();
		Facture facture = new Facture(reference, false, pane.getCacheData().getPatient(),  FrontApp.serviceCatalog.getProfessionnelService().findAllPro().stream().findFirst().get(), communicationText, LocalDate.of(selectedYear, selectedMonth.getMois(), 1), LocalDate.now(), seances);
		
		
		return facture;
		
	}

}
