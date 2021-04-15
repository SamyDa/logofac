package be.logofac.LogoFac;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.Utils.CacheData;
import be.logofac.LogoFac.service.ServiceCatalog;
import be.logofac.LogoFac.views.MainPane;
import javafx.application.Application;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

@Service
public class FrontApp extends Application {

	public static ServiceCatalog serviceCatalog;
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Logofac");
		initRootLayout();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void initRootLayout() {
		MainPane mainPane = new MainPane(primaryStage , rootLayout, null, new CacheData());
	}

}
