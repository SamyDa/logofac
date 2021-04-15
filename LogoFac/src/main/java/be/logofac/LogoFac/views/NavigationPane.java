package be.logofac.LogoFac.views;

import be.logofac.LogoFac.Controllers.ViewController;
import be.logofac.LogoFac.Utils.CacheData;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public abstract class NavigationPane {
	
	protected ViewController headerController;
	protected NavigationPane navigatedPane;	
	protected NavigationPane parentPane;
	protected BorderPane rootLayout;
	protected Stage primaryStage;
	protected CacheData cacheData;
	
	
	public NavigationPane( Stage primaryStage,BorderPane rootLayout, NavigationPane parentPane, CacheData cacheData ) {
		super();
		this.parentPane = parentPane;
		this.rootLayout = rootLayout; 
		this.primaryStage  = primaryStage;
		this.cacheData  = cacheData;
		showInitialPane();
	}

	public NavigationPane(NavigationPane parentPane) {
		
		this.parentPane = parentPane;
		this.cacheData  = parentPane.getCacheData();
		this.primaryStage = parentPane.getPrimaryStage();
		this.rootLayout = parentPane.getRootLayout();
		this.headerController = parentPane.getHeaderController();
		showInitialPane();
	}

	public NavigationPane currentPane() {
				
		if(navigatedPane == null)
			return this;
		else 
			return navigatedPane.currentPane();
		
	}
	
	public void returnBack() {
		
		parentPane.setAsCurrentPane();
		parentPane.showInitialPane();
	}

	private void setAsCurrentPane() {
		navigatedPane = null;	
	}
	
	protected abstract void showInitialPane();
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

	public CacheData getCacheData() {
		return cacheData;
	}

	public ViewController getHeaderController() {
		return headerController;
	}

	public void setHeaderController(ViewController headerController) {
		this.headerController = headerController;
	}

	
	
	
}
