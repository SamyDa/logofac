package be.logofac.LogoFac.Controllers;

import java.util.ArrayList;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.domain.AppParameterGeneral;
import be.logofac.LogoFac.domain.Seance;
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

public class OptionParametersManagementController extends ViewController {

	
	@FXML
	private TableView<AppParameterGeneral> parameterTable;
	
	@FXML
	private TableColumn<AppParameterGeneral, String> descriptionColumn;
	
	@FXML 
	private TableColumn<AppParameterGeneral, String> valueColumn;
	
	
	
	
	ObservableList< AppParameterGeneral> obsAppParamList;
	ArrayList<AppParameterGeneral> appParamList  ;
	
	
	
	@Override
	public void loadControllerLogic() {
		fillList();
		parameterTable.setItems(obsAppParamList);
		parameterTable.setEditable(true);
		descriptionColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getParameterReference().getName()));
		valueColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().toString()));
		valueColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		valueColumn.setOnEditCommit((TableColumn.CellEditEvent<AppParameterGeneral, String> event)->{
            AppParameterGeneral app= event.getTableView ().getItems().get(event.getTablePosition ().getRow());
            if(app.getParameterReference().getInstance() instanceof Integer) {
	            try {
	            
	            	Integer newValue = Integer.valueOf(event.getNewValue());
	            	 app.setValue(newValue);
	            }
	            catch(NumberFormatException e) {
	            	Alert alert = new Alert(AlertType.ERROR);
	    			alert.setTitle("Erreur : valeur " + event.getNewValue() +" est incorrect");
	    	        alert.setContentText("Seul des nombres entiers peuvent être insérés");
	    	        alert.showAndWait();
	    			return;
	            }
            }
            else
            	app.setValue(event.getNewValue());
        });
		
		
	}
	
	private void fillList() {
		System.out.println("List of parameters : " + FrontApp.serviceCatalog.getParameterService().findAllGeneral().size());
		appParamList = (ArrayList<AppParameterGeneral>) FrontApp.serviceCatalog.getParameterService().findAllGeneral();
		obsAppParamList= FXCollections.observableList(appParamList);
	}

	@FXML
	private void save() {
		System.out.println("Save , value of the parameters : ");
		parameterTable.getItems().forEach(n ->{ System.out.println("value -> " + n.toString()); FrontApp.serviceCatalog.getParameterService().save(n);});
		pane.returnBack();
		
	}
	
}
