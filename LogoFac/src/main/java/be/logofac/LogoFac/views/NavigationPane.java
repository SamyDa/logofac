package be.logofac.LogoFac.views;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class NavigationPane {
	
	
	protected NavigationPane navigatedPane;
	
	protected NavigationPane parentPane;
	protected BorderPane rootLayout;
	protected Stage primaryStage;
	
	
	
	
	public NavigationPane( Stage primaryStage,BorderPane rootLayout, NavigationPane parentPane ) {
		super();
		this.parentPane = parentPane;
		this.rootLayout = rootLayout; 
		this.primaryStage  = primaryStage;
		showPane();
	}

	public NavigationPane(NavigationPane parentPane) {
		
		this.parentPane = parentPane;
		this.primaryStage = parentPane.getPrimaryStage();
		this.rootLayout = parentPane.getRootLayout();
		showPane();
	}

	public NavigationPane currentPane() {
				
		if(navigatedPane == null)
			return this;
		else 
			return navigatedPane.currentPane();
		
	}
	
	public void returnBack() {
		
		parentPane.setAsCurrentPane();
	}

	private void setAsCurrentPane() {
		navigatedPane = null;	
	}
	
	protected abstract void showPane();

	public NavigationPane getNavigatedPane() {
		return navigatedPane;
	}

	public NavigationPane getParentPane() {
		return parentPane;
	}

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setNavigatedPane(NavigationPane navigatedPane) {
		this.navigatedPane = navigatedPane;
	}
	
	
	
}
