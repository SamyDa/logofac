package be.logofac.LogoFac.views;

import be.logofac.LogoFac.Controllers.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class InvoiceDetailPane extends NavigationPane {

	

	public InvoiceDetailPane() {
		super(null);
	}

	
	public InvoiceDetailPane(NavigationPane parentPane) {
		super(parentPane);
	}

	@Override
	protected void showInitialPane() {
	 // then display the first main menu
		try {
		 	FXMLLoader fxmlLoader = new FXMLLoader();
		 	fxmlLoader = new FXMLLoader();
		 	fxmlLoader.setLocation(this.getClass().getResource("/views/InvoiceDataDialog.fxml"));
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
		showInitialPane();
	}

}
