package be.logofac.LogoFac.Controllers;

import java.util.ArrayList;
import java.util.List;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.Utils.CacheData;
import be.logofac.LogoFac.Utils.DocumentProcess;
import be.logofac.LogoFac.Utils.ViewNavigator;
import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.enums.Options;
import be.logofac.LogoFac.views.InvoiceDetailPane;
import be.logofac.LogoFac.views.NavigationPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InvoiceListController extends ViewController {

	
	@FXML
	private TableView<Facture> invoiceTable;
	
	@FXML
	private TableColumn<Facture, String> invoiceReference;
	
	@FXML 
	private TableColumn<Facture, String> applicationDate;
	@FXML 
	private TableColumn<Facture, String> patientName;
	
	private Facture selectedInvoice = null;
	
	
	private List<Facture> invoiceList = new ArrayList<Facture>();
	private ObservableList<Facture> obsInvoiceList;
	
	@Override
	public void loadControllerLogic() {
		fillInvoiceList();
		fillTableView();
	}
	
	
	private void fillTableView() {
		invoiceTable.setItems(obsInvoiceList);
		invoiceTable.setEditable(true);
		invoiceReference.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getReference()));
		applicationDate.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getApplicationDate().toString()));
		patientName.setCellValueFactory(cell -> new SimpleStringProperty(getPatientNameFromInvoice(cell.getValue())));
		invoiceTable.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> updateSelectedInvoice(newValue));
		
	}

	private void updateSelectedInvoice(Facture newValue) {
		selectedInvoice  = newValue;
	}


	private String getPatientNameFromInvoice(Facture invoice) {
		
		String patientName = invoice.getPatient().getFirstName() + " " + invoice.getPatient().getLastName();	
		return patientName;
	}


	private void fillInvoiceList() {
		invoiceList = FrontApp.serviceCatalog.getFactureService().findAll();
		obsInvoiceList = FXCollections.observableList(invoiceList);
	}

	
	@FXML
	private void nextActionB() {
		if(selectedInvoice == null) return ;
		pane.getCacheData().setSelectedInvoice(selectedInvoice);
		NavigationPane pane = new InvoiceDetailPane() ; 
		pane.setParentPane(this.pane);
		pane.showPane();
		
	}
	@FXML
	private void back() {
		pane.returnBack();
	}
	
	@FXML
	private void regenerateDocument() {
		
		DocumentProcess doc = new DocumentProcess();
		if(selectedInvoice == null) return ;
		FrontApp.serviceCatalog.getFactureService().fetchLazyAttributes(selectedInvoice);
		doc.loadDocumentData(selectedInvoice);
	}
	
}
