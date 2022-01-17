package be.logofac.LogoFac.Controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import be.logofac.LogoFac.FrontApp;
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
import javafx.scene.control.TextField;

public class InvoiceDetailController extends ViewController {

	@FXML
	private TextField id;
	@FXML
	private TextField reference;
	@FXML
	private TextField creationDate;
	@FXML
	private TextField modificationDate;
	@FXML
	private TextField applicationDate;
	@FXML
	private TextField communication;
	
	private Facture invoice ;
	
	@Override
	public void loadControllerLogic() {
		invoice = pane.getCacheData().getSelectedInvoice();
		fillTextFields();
	}
	
	private void fillTextFields() {
		id.setText( String.valueOf(invoice.getFactureId()));
		reference.setText(invoice.getReference());
		creationDate.setText(invoice.getCreationDate().toString());
		modificationDate.setText(invoice.getModificationDate()!=null ? invoice.getModificationDate().toString() : invoice.getCreationDate().toString());
		applicationDate.setText(invoice.getApplicationDate().toString());
		communication.setText(invoice.getCommunication());
	}

	@FXML
	private void saveClicked() {
		if(ValidateDate(creationDate.getCharacters()) && ValidateDate(applicationDate.getCharacters())) {
			
			invoice.setApplicationDate(LocalDate.parse(applicationDate.getCharacters()));
			invoice.setModificationDate(LocalDate.now());
			invoice.setCommunication(communication.getText());
			invoice.setReference(reference.getText());
			invoice.setCreationDate(LocalDate.parse(creationDate.getCharacters()));
			
			FrontApp.serviceCatalog.getFactureService().save(invoice);
			
			GoBack();
			
		}
	}

	private void GoBack() {
		pane.getCacheData().setSelectedInvoice(null);
		pane.returnBack();
	}
	
	private boolean ValidateDate(CharSequence characters) {
		return Pattern.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$", applicationDate.getCharacters()) ;
	}

	@FXML
	private void cancelClicked() {
		
		GoBack();
	}
	
	@FXML
	private void back() {
		pane.returnBack();
	}
}
