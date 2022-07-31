package be.logofac.LogoFac.Controllers;

import java.util.ArrayList;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.domain.AppParameterAmount;
import be.logofac.LogoFac.domain.AppParameterGeneral;
import be.logofac.LogoFac.domain.Seance;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class OptionParametersAmountController extends ViewController {

	
	@FXML
	private TableView<AppParameterAmount> parameterTable;
	
	@FXML
	private TableColumn<AppParameterAmount, String> typeColumn;
	
	@FXML 
	private TableColumn<AppParameterAmount, String> durationColumn;
	@FXML 
	private TableColumn<AppParameterAmount, String> descriptionColumn;
	@FXML 
	private TableColumn<AppParameterAmount, String> amountColumn;
	
	
	
	ObservableList<AppParameterAmount> obsAppAmountList;
	ArrayList<AppParameterAmount> appAmountList  ;
	
	
	
	@Override
	public void loadControllerLogic() {
		FrontApp.serviceCatalog.getParameterService().addMissingAmountParameter();
		fillList();
		parameterTable.setItems(obsAppAmountList);
		parameterTable.setEditable(true);
		typeColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSeanceType().toString()));
		descriptionColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));
		durationColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDuration().toString()));		
		amountColumn.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().getAmount())));
		amountColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		amountColumn.setOnEditCommit((TableColumn.CellEditEvent<AppParameterAmount, String> event)->{
			AppParameterAmount app= event.getTableView ().getItems().get(event.getTablePosition ().getRow());
            try {
            
            	Double newValue = Double.valueOf(event.getNewValue());
            	app.setAmount(newValue);
            }
            catch(NumberFormatException e) {
            	Alert alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Erreur : valeur " + event.getNewValue() +" est incorrect");
    	        alert.setContentText("Seul des nombres entiers peuvent être insérés");
    	        alert.showAndWait();
    			return;
            }
        });
		
		
	}
	
	private void fillList() {
		System.out.println("List of parameters : " + FrontApp.serviceCatalog.getParameterService().findAllGeneral().size());
		appAmountList = (ArrayList<AppParameterAmount>) FrontApp.serviceCatalog.getParameterService().findAllAmounts();
		obsAppAmountList= FXCollections.observableList(appAmountList);
	}

	@FXML
	private void save() {
		System.out.println("Save , value of the parameters : ");
		parameterTable.getItems().forEach(n ->{ System.out.println("value -> " + n.toString()); FrontApp.serviceCatalog.getParameterService().save(n);});
		pane.returnBack();
		
	}
	
}
