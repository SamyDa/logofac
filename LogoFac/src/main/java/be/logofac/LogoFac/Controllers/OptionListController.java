package be.logofac.LogoFac.Controllers;

import java.util.ArrayList;
import java.util.List;

import be.logofac.LogoFac.Utils.ViewNavigator;
import be.logofac.LogoFac.domain.enums.Options;
import be.logofac.LogoFac.views.NavigationPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class OptionListController extends ViewController {

	
	@FXML
	private ListView<String> optionListView;
	
	
	private ViewNavigator viewSelector = new ViewNavigator();
	
	List<String> optionList = new ArrayList<String>();
	ObservableList<String> obsOptionList;
	
	@Override
	public void loadControllerLogic() {
		
		for(Options option : Options.values()) {
			optionList.add(option.getDescription());
		}
		
		obsOptionList = FXCollections.observableList(optionList);
		
		optionListView.setItems(obsOptionList);
	}
	
	
	
	@FXML
	private void nextActionA() {
		
		
		
	}
	@FXML
	private void nextActionB() {
		
		if(optionListView.getSelectionModel().getSelectedItem() == null) 
			return;
		
		Options option = null;
		
		for(Options opt : Options.values()) {
			if(opt.getDescription().equals(optionListView.getSelectionModel().getSelectedItem()) )
				option = opt;
		}
		
		if( option != null) {
			
			NavigationPane pane = viewSelector.getViewFromInputOption(option);
			pane.setParentPane(this.pane);
			pane.showPane();
			
		}
		else {
			System.out.println("OptionListController : something went wrong with option or getSelectedItem");
		}
		
	}
	
	@FXML
	private void back() {
		pane.returnBack();
	}
	
}
