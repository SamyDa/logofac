package be.logofac.LogoFac.views;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.Controllers.SelectPersonController;
import be.logofac.LogoFac.Controllers.ViewController;
import be.logofac.LogoFac.domain.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SelectOptionPane extends NavigationPane {

	private ObservableList<Patient> patientObsist;
	
	public SelectOptionPane(NavigationPane parentPane) {
		super(parentPane);
		
	}

	@Override
	protected void showInitialPane() {
		 // then display the first main menu
		 try {
			 	FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/OptionList.fxml"));
	            AnchorPane optionSelection = (AnchorPane) fxmlLoader.load();
	            rootLayout.setCenter(optionSelection);
	            
	            ViewController controller = fxmlLoader.getController();
	            controller.setPane(this);
	            controller.loadControllerLogic();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }		

	}

	@Override
	public void showPane() {
		// TODO Auto-generated method stub
		
	}
	


}
