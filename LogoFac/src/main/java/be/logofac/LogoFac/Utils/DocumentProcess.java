package be.logofac.LogoFac.Utils;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import be.logofac.LogoFac.FrontApp;
import be.logofac.LogoFac.domain.AppParameterAmount;
import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.Professionnel;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.domain.enums.Description;
import be.logofac.LogoFac.domain.enums.Mois;
import be.logofac.LogoFac.domain.enums.ParameterReference;
import be.logofac.LogoFac.domain.enums.SeanceDuration;
import be.logofac.LogoFac.domain.enums.SeanceType;

@Service
public class DocumentProcess {

	private static final String BREAK_LINE = "\n";
	private double total = 0 ;
	private boolean sendEmail = false;
	
	public void loadDocumentData(Facture facture) {
		
		System.out.println("Load documents ");
		createDocument(facture);
		
	}
	
	public boolean isSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(boolean sendEmail) {
		this.sendEmail = sendEmail;
	}

	private void createDocument(Facture facture) {
		
		
	    String dest = "";
	    if(FrontApp.serviceCatalog.getParameterService().findGeneralParameter(ParameterReference.FOLDER_LOCATION) != null ) {
	    	
	    	dest = FrontApp.serviceCatalog.getParameterService().findGeneralParameter(ParameterReference.FOLDER_LOCATION).getValue().toString();
	    }
	    
	    
	    if(!dest.endsWith("\\"))
	    	dest = dest.concat("\\");
	    dest = dest +  getFileName(facture) ;    
	    
	    System.out.println("destination = " + dest);
		try {
			  PdfWriter writer = new PdfWriter(dest);
			  // Creating a PdfDocument       
		      PdfDocument pdfDoc = new PdfDocument(writer);      
		      
		      // Adding a new page 
		      pdfDoc.addNewPage();   
		      // Creating a Document        
		      Document document = new Document(pdfDoc); 
		      addHeaderPro(document,facture.getProfessionnel());
		      addHeaderPatient(document,facture.getPatient());
		      addBreakLine(document);
		      //add the invoice reference
		      document.add(new Paragraph(new Text("Facture N° " + facture.getReference())));
		      //add the date
		      document.add(new Paragraph(new Text("Date : " + facture.getCreationDate().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")))));
		      addBreakLine(document);
		      //add the seances and price
		      addDetailedTable(document, facture);
		      addBreakLine(document, 2);
		      
		      //add the seances and price
		      addFooter(document, facture);
		      
		      // Closing the document    
		      document.close();              
		      System.out.println("PDF Created");    
		      facture.setPrinted(true);
		      
		      if(sendEmail && dest !=null && !dest.isEmpty()) {
		    	  
		    	  String title = "Facture honoraires séances logopédiques ";
		    	  for(Mois mois : Mois.values()) {
		    		  if(mois.getMois() == facture.getApplicationDate().getMonthValue()) {
		    			  	title = title + mois;
		    			  
		    		  }
		    		  
		    	  }  
		    	  
		    	  String messageText = "Bonjour,\n"
		    	  		+ "\n"
		    	  		+ "Ci-joint, vous trouverez la facture des honoraires du mois ";
		    	  
		    	  for(Mois mois : Mois.values()) {
		    		  
		    		  if(mois.getMois() == facture.getApplicationDate().getMonthValue()) {
		    			  
		    			  if(String.valueOf(mois).toLowerCase().charAt(0) == 'a' || String.valueOf(mois).toLowerCase().charAt(0) == 'o')
		    				  messageText = messageText + "d'" + mois;
		    			  else
		    				  messageText = messageText + "de " + mois;
		    			  
		    			  break;
		    			  
		    		  }
		    	  }
		    	  messageText = messageText+ " . \n"
		    	  		+ "\n"
		    	  		+ "En vous en souhaitant bonne réception, \n"
		    	  		+ "Cordialement,\n"
		    	  		+ "\n"
		    	  		+ facture.getProfessionnel().getFirstName() + " " + facture.getProfessionnel().getLastName() +"\n"
		    	  		+ facture.getProfessionnel().getProfession() + "\n"
		    	  		//+ addColor("Ceci est un mail automatique, merci de ne pas y répondre. En cas de besoin, contactez-moi à l'adresse mail suivante : ", java.awt.Color.RED) + facture.getProfessionnel().getEmail();
		    	  		+ "Ceci est un mail automatique, merci de ne pas y répondre. En cas de besoin, contactez-moi à l'adresse mail suivante : " + facture.getProfessionnel().getEmail();
		    	  
		    	  MailUtil mailUtil = new MailUtil(title, messageText, dest);
		    	  mailUtil.setCcAdresse(facture.getProfessionnel().getEmail());
		    	  mailUtil.setRecipient(facture.getPatient().getEmail());
		    	  mailUtil.setFileName(getFileName(facture) + ".pdf");
		    	  mailUtil.sendEmail();
		    	  
		      }
		      
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
	
	private String getFileName(Facture facture) {
		String moisStr ="";
		
		for(Mois mois : Mois.values()) {
			
			if(mois.getMois() == facture.getApplicationDate().getMonthValue()) {
				moisStr = String.valueOf(mois);
			
			}
				
		}
		return facture.getReference() + "_" + moisStr+"_"+facture.getPatient().getFirstName()+"_"+facture.getPatient().getLastName()+".pdf";
	}

	private void addBreakLine(Document document, int i) {
		
		for(int j = 0 ; j<i; j++)
			addBreakLine(document);
		
	}

	private void addFooter(Document document, Facture facture) {
		document.add(new Paragraph(new Text("Facture payable au comptant par virement bancaire sur le compte : " ).setBold()).add("\n\t\tIBAN : " + facture.getProfessionnel().getIbanAccount() + " " + "\n\tBIC : " + facture.getProfessionnel().getBicAccount() ));
		document.add(new Paragraph(new Text("Communication sur le virement : " ).setBold()).add(facture.getCommunication()));
		document.add(new Paragraph(new Text("Paiement dès réception." ).setBold()));
		
		
	}

	private void addDetailedTable(Document document, Facture facture) {
		float [] pointColumnWidths = {350F, 200F};   
		Table table = new Table(pointColumnWidths);
		table.addHeaderCell(getCell("  Description", TextAlignment.CENTER).setBold().setFontSize(14).setBorder(new SolidBorder(1)).setBackgroundColor(Color.LIGHT_GRAY));
		table.addHeaderCell(getCell("  Montant", TextAlignment.CENTER).setBold().setFontSize(14).setBorder(new SolidBorder(1)).setBackgroundColor(Color.LIGHT_GRAY));
		addTableFirstCell(table, facture);
		addSeanceAndAmounts(table, facture);
		table.addFooterCell(getCell("Total", TextAlignment.LEFT).setBold().setBorder(new SolidBorder(1)).setMarginLeft(7));
		table.addFooterCell(getCell(String.valueOf(((double) Math.round(total*100))/100) + "€", TextAlignment.LEFT).setBold().setBorder(new SolidBorder(1)).setMarginLeft(7));
		table.setBorder(new SolidBorder(2));
		document.add(table);
	}

	private void addSeanceAndAmounts(Table table, Facture facture) {
		
		List<Seance> seanceList =  facture.getSeances().stream().filter(n-> !n.getIsCancelled()).collect(Collectors.toList()) ;
		AddCommonSeancesAndAddTotal(table,  facture);
		
		table.addCell(getCell("\n" , TextAlignment.LEFT));
		table.addCell(getCell("\n" , TextAlignment.LEFT).setBorderLeft(new SolidBorder(1)));
	}

	private double AddCommonSeancesAndAddTotal(Table table, Facture facture) {
		String text = "";
		for(SeanceDuration seanceDuration : SeanceDuration.values()) {
			
			List<Seance> filteredSeances = facture.getSeances().stream().filter(n-> n.getHourNumber() == seanceDuration).collect(Collectors.toList()) ;
			text = "";
			if(filteredSeances.size() == 0 )
				continue;
			
			for(SeanceType seanceType :  SeanceType.values()) {
				List<Seance> secondFilteredSeances = filteredSeances.stream().filter(n-> n.getSeanceType()== seanceType).collect(Collectors.toList()) ;
				if (secondFilteredSeances.size() == 0)
					continue;
				text = "";
				if(secondFilteredSeances.size() > 0 ) {
					if(secondFilteredSeances.size() == 1) {
						String duration = "";
						if (seanceDuration.getDescrption().toLowerCase().toCharArray()[0] == 'u') 
							duration = "d'" + seanceDuration.getDescrption();
						else
							duration = "de " + seanceDuration.getDescrption();
						text = 	text + "1 séance " + duration + " " + seanceType.getSeanceString() + "\n" ;
					}
					else
					{
						text = text + secondFilteredSeances.size() + " séances " + seanceDuration.getDescrption() + " " + seanceType.getSeanceString() + "\n";
					}
					
					text = text + listSeanceDateText(secondFilteredSeances);
					table.addCell(getCell("\n"+ text, TextAlignment.LEFT,3));
					
					//Add the amount 
					AppParameterAmount appParameterAmount = new AppParameterAmount(seanceDuration, seanceType);
					AppParameterAmount retrievedParameterAmount =(AppParameterAmount) (new ParamRetriever()).retrieveParameter(appParameterAmount);
					text = "";
					if(retrievedParameterAmount != null) {
						double localTotal = (double )secondFilteredSeances.size() * retrievedParameterAmount.getAmount();
						total = total + ((double) Math.round(localTotal*100))/100;
						text = text + secondFilteredSeances.size() + "x" + retrievedParameterAmount.getAmount() +" = "  +((double) Math.round(localTotal*100))/100 + "€";
						table.addCell(getCell("\n" + text, TextAlignment.LEFT,3).setBorderLeft(new SolidBorder(1)));
					}
				}
			}
		}
		return 0;
	}

	private void addTableFirstCell(Table table, Facture facture) {
		Cell cell = new Cell();
		Mois moisFacture = null ;
		
		for (Mois mois : Mois.values())
		{
			if(mois.checkMois(facture.getApplicationDate().getMonthValue())) {
				moisFacture = mois;
				break;
			}
		}
		
		String textBilan = getTextBilan(facture);
		
		table.addCell(getCell(new Paragraph().add( new Text("\n" + Description.Honoraires.getDescription("Honoraires ".concat( textBilan), moisFacture) + "\n")).setUnderline(),  TextAlignment.LEFT,3));
		table.addCell(getCell(" " , TextAlignment.LEFT).setBorderLeft(new SolidBorder(1)));
		
	}

	private String getTextBilan( Facture facture) {
			if(facture.isBilan())
				return "pour le bilan initial"; 
			
			return "";
	}

	private String listSeanceDateText(List<Seance> seanceList) {

		String text = "";
		
		if(seanceList.size()> 0) {
			text = "(";
			
			if (seanceList.size()== 1)
			{
				text = text + seanceList.get(0).getHourFrom().format( DateTimeFormatter.ofPattern("dd/MM"));
			}
			else {
				for(int i = 0 ; i < seanceList.size() ; i++) {
					
					text = text + seanceList.get(i).getHourFrom().format( DateTimeFormatter.ofPattern("dd/MM"));
					
					if(i < seanceList.size() -2) {
						text = text + ", ";
					}else if (i < seanceList.size()-1)
					{
						text= text + " et ";
					}
				}
			}
			
			text = text + ")";
		
		}
		return text;
		
		
	}

	private void addBreakLine(Document document) {
		document.add(new Paragraph().add(new Text("\n")));
	}


	private void addHeaderPatient(Document document, Patient patient) {
		float [] pointColumnWidths = {200F, 150, 200F};   
	      Table table = new Table(pointColumnWidths);    
	     
	      table.addCell(getCell("", TextAlignment.LEFT));
	      table.addCell(getCell("", TextAlignment.LEFT));
	      table.addCell(getCell(retrievePatientInfo(patient), TextAlignment.LEFT));
	      document.add(table);
	}



	private String retrievePatientInfo(Patient patient) {
		
		return patient.getFirstName() + " " + patient.getLastName() + BREAK_LINE  
		     + patient.getAdresse().getStreet()+ ", " + patient.getAdresse().getHouseNumber() + BREAK_LINE
		     + patient.getAdresse().getPostalCode() + " " + patient.getAdresse().getCity();
	}


	public void addHeaderPro(Document document , Professionnel pro ) {
		float [] pointColumnWidths = {200F, 200F, 150F};   
	      Table table = new Table(pointColumnWidths);    
	      table.addCell(getCell(retrieveProInfoText(pro), TextAlignment.LEFT));
	      table.addCell(getCell(retrieveProAdressText(pro), TextAlignment.LEFT));
	      table.addCell(getCell(retrieveCommunicationInamiText(pro), TextAlignment.LEFT));
	      document.add(table);
	      addBreakLine(document);
	}
	
	private String retrieveCommunicationInamiText(Professionnel pro) {
		return  pro.getPhoneNumber() + BREAK_LINE
			  + pro.getEmail() + BREAK_LINE
			  + "N° INAMI : " + pro.getInamiNumber();
	}
	


	private String retrieveProAdressText(Professionnel pro) {
		return pro.getAdresse().getStreet() + ", " + pro.getAdresse().getHouseNumber() + BREAK_LINE
			+ pro.getAdresse().getPostalCode() + " " + pro.getAdresse().getCity();
	}


	private String retrieveProInfoText(Professionnel pro) {
		String text =  pro.getFirstName()  + " " + pro.getLastName() + BREAK_LINE
			     + pro.getProfession() + BREAK_LINE;
		if(!pro.getBCENumber().isBlank() )
			text = text + "N° BCE : " + pro.getBCENumber();
			
		return text;
	}


	public Cell getCell(String text, TextAlignment alignment ) {
	    return getCell(text, alignment, 0 ); 
	}
	public Cell getCell(Paragraph text, TextAlignment alignment  ) {
		return getCell(text, alignment, 0 );
	}
	public Cell getCell(String text, TextAlignment alignment, float margin) {
	    Cell cell = new Cell().add(new Paragraph(text));
	    cell.setPadding(0);
	    cell.setTextAlignment(alignment);
	    cell.setBorder(Border.NO_BORDER);
	    cell.setMarginLeft(margin);
	    return cell;
	}
	public Cell getCell(Paragraph text, TextAlignment alignment , float margin) {
		Cell cell = new Cell().add(text);
		cell.setPadding(0);
		cell.setTextAlignment(alignment);
		cell.setBorder(Border.NO_BORDER);
		cell.setMarginLeft(margin);
		return cell;
	}
	
	private String addColor(String msg, java.awt.Color color) {
	    String hexColor = String.format("#%06X",  (0xFFFFFF & color.getRGB()));
	    String colorMsg = " <font color=\"#" + hexColor + "\">" + msg + "</font>";
	    return colorMsg;
	}
}
