package be.logofac.LogoFac.Controllers;

import be.logofac.LogoFac.views.NavigationPane;
import javafx.fxml.FXML;

public abstract class ViewController {

	
	protected NavigationPane pane;
	
	public ViewController() {
		super();
	}

	public void setPane(NavigationPane pane) {
		this.pane = pane;
	}

	
	@FXML
    private void initialize() {
    }
	
	public void loadControllerLogic() {
		
	}
 
	
	
}
