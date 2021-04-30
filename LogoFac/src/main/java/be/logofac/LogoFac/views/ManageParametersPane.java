package be.logofac.LogoFac.views;

import be.logofac.LogoFac.Controllers.ViewController;
import be.logofac.LogoFac.Utils.CacheData;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ManageParametersPane extends NavigationPane {

	public ManageParametersPane(NavigationPane parentPane) {
		super(parentPane);
		// TODO Auto-generated constructor stub
	}

	
	
	public ManageParametersPane() {
		super(null);
	}



	@Override
	protected void showInitialPane() {
		 // then display the first main menu
		 try {
			    FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/OptionParametersManagement.fxml"));
	            AnchorPane proDataMgt = (AnchorPane) fxmlLoader.load();
	            rootLayout.setCenter(proDataMgt);
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
