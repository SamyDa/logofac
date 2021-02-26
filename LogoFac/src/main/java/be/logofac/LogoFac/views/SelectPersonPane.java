package be.logofac.LogoFac.views;

import be.logofac.LogoFac.Controllers.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SelectPersonPane extends NavigationPane {

	public SelectPersonPane(NavigationPane parentPane) {
		super(parentPane);
	}

	@Override
	protected void showPane() {
		 // then display the first main menu
		 try {
			 	FXMLLoader fxmlLoader = new FXMLLoader();
			 	fxmlLoader = new FXMLLoader();
			 	fxmlLoader.setLocation(this.getClass().getResource("/views/SelectPerson.fxml"));
	            AnchorPane selectPerson = (AnchorPane) fxmlLoader.load();
	            rootLayout.setTop(selectPerson);
	            
	            ViewController controller = fxmlLoader.getController();
	            controller.setPane(this);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }		

	}

}
