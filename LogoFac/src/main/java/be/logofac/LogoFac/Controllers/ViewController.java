package be.logofac.LogoFac.Controllers;

import be.logofac.LogoFac.views.NavigationPane;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public abstract class ViewController {

	protected Stage stage;
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

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	@FXML
	private void back() {
		pane.returnBack();
	}
	
	
}
