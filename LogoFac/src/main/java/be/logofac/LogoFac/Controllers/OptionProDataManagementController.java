package be.logofac.LogoFac.Controllers;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.domain.Adresse;
import be.logofac.LogoFac.domain.Professionnel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class OptionProDataManagementController extends ViewController {
	
	
	@FXML
	private TextField prenomField; 
	@FXML
	private TextField nomField; 
	@FXML
	private TextField professionField; 
	@FXML
	private TextField emailField; 
	@FXML
	private TextField telephoneField; 
	@FXML
	private TextField inamiField; 
	@FXML
	private TextField bceField; 
	@FXML
	private TextField ibanField; 
	@FXML
	private TextField bicField; 
	@FXML
	private TextField rueField; 
	@FXML
	private TextField numeroField; 
	@FXML
	private TextField cpField; 
	@FXML
	private TextField villeField; 
	@FXML
	private TextField paysField; 
	
	
	private Professionnel pro;
	
	
	public void loadControllerLogic() {
		System.out.println("Number of professionnel when out  :  " + FrontApp.serviceCatalog.getProfessionnelService().findAllPro().size());
		
		if(FrontApp.serviceCatalog.getProfessionnelService().findAllPro() != null && FrontApp.serviceCatalog.getProfessionnelService().findAllPro().size() > 0) {
			
			pro = FrontApp.serviceCatalog.getProfessionnelService().findAllPro().get(0); 
			System.out.println("Retrieved pro : " + pro.toString());
			displayProData();
			
		}
		else {
			pro = new Professionnel();
			pro.setAdresse(new Adresse());
		}
			
		
		
		
	}
	
	private void displayProData() {
		FrontApp.serviceCatalog.getProfessionnelService().fetchLazy(pro);
		prenomField.setText(pro.getFirstName());
		nomField.setText(pro.getLastName());
		emailField.setText(pro.getEmail());
		telephoneField.setText(pro.getPhoneNumber());
		professionField.setText(pro.getProfession());
		inamiField.setText(pro.getInamiNumber());
		bceField.setText(pro.getBCENumber());
		ibanField.setText(pro.getIbanAccount());
		bicField.setText(pro.getBicAccount());
		rueField.setText(pro.getAdresse().getStreet());
		numeroField.setText(pro.getAdresse().getHouseNumber());
		villeField.setText(pro.getAdresse().getCity());
		cpField.setText(String.valueOf(pro.getAdresse().getPostalCode()));
		paysField.setText(pro.getAdresse().getCountry());
		
	}

	@FXML
	private void save() {
		int postalCode;
		
		try {
			postalCode = Integer.parseInt(cpField.getText());
			
		}catch(Exception e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error in Postal Code");
	        alert.setContentText("Veuillez corriger le code postal");
	        alert.showAndWait();
			return;
		}
		
		pro.getAdresse().setCity(villeField.getText());;
		pro.getAdresse().setCountry(paysField.getText());;
		pro.getAdresse().setHouseNumber(numeroField.getText());;
		pro.getAdresse().setPostalCode(postalCode);;
		pro.getAdresse().setStreet(rueField.getText());;
		
		pro.setBCENumber(bceField.getText()); ; 
		pro.setBicAccount(bicField.getText()); ; 
		pro.setEmail(emailField.getText()); ; 
		pro.setFirstName(prenomField.getText()); ; 
		pro.setLastName(nomField.getText()); ; 
		pro.setIbanAccount(ibanField.getText()); ; 
		pro.setInamiNumber(inamiField.getText()); ; 
		pro.setPhoneNumber(telephoneField.getText()); ; 
		pro.setProfession(professionField.getText()); ; 
		
		
		FrontApp.serviceCatalog.getProfessionnelService().save(pro);
		
		System.out.println("Number of professionnel when out  :  " + FrontApp.serviceCatalog.getProfessionnelService().findAllPro().size());
		pane.returnBack();
		
	}

}
