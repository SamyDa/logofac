package be.logofac.LogoFac;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.Professionnel;
import be.logofac.LogoFac.domain.Seance;
import be.logofac.LogoFac.domain.enums.Description;
import be.logofac.LogoFac.domain.enums.Mois;
import be.logofac.LogoFac.domain.enums.SeanceDuration;
import be.logofac.LogoFac.domain.enums.SeanceType;
import be.logofac.LogoFac.service.AdresseService;
import be.logofac.LogoFac.service.FactureService;
import be.logofac.LogoFac.service.PatientService;
import be.logofac.LogoFac.service.PrixService;
import be.logofac.LogoFac.service.ProfessionnelService;
import be.logofac.LogoFac.service.SeanceService;

@Service
public class DocumentProcess {

	private static final String BREAK_LINE = "\n";

	public void loadDocumentData(Facture facture) {
		
		System.out.println("Load documents ");
		createDocument(facture);
		
	}

	private void createDocument(Facture facture) {
	    String dest = "Invoice.pdf";     
	    
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
		      document.add(new Paragraph(new Text("Date : " + facture.getApplicationDate())));
		      addBreakLine(document);
		      //add the seances and price
		      addDetailedTable(document, facture);
		      // Closing the document    
		      document.close();              
		      System.out.println("PDF Created");    
		      facture.setPrinted(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	private void addDetailedTable(Document document, Facture facture) {
		float [] pointColumnWidths = {300F, 300F};   
		Table table = new Table(pointColumnWidths);
		table.addHeaderCell(getCell("  Description", TextAlignment.CENTER).setBold().setFontSize(14).setBorder(new SolidBorder(1)).setBackgroundColor(Color.LIGHT_GRAY));
		table.addHeaderCell(getCell("  Montant", TextAlignment.CENTER).setBold().setFontSize(14).setBorder(new SolidBorder(1)).setBackgroundColor(Color.LIGHT_GRAY));
		table.addCell(getDescriptionCell(facture));
		table.addCell(getAmountCell(facture));
		document.add(table);
	}

	private Cell getAmountCell(Facture facture) {
		Cell cell = new Cell();
		
		return cell;
	}

	private Cell getDescriptionCell(Facture facture) {
		Cell cell = new Cell();
		Mois moisFacture = null ;
		
		for (Mois mois : Mois.values())
		{
			if(mois.checkMois(facture.getCreationDate().getMonthValue())) {
				moisFacture = mois;
				break;
			}
		}
		cell.add(new Paragraph().add( new Text(Description.Honoraires.getDescription(moisFacture) + "\n\n")));
		
		String text = "";
		//Need to separate the description between hours and 
		
		for(SeanceDuration seanceDuration : SeanceDuration.values()) {
			
			List<Seance> filteredSeances = facture.getSeances().stream().filter(n-> n.getHourNumber() == seanceDuration).collect(Collectors.toList()) ;
			
			for(SeanceType seanceType :  SeanceType.values()) {
				List<Seance> secondFilteredSeances = facture.getSeances().stream().filter(n-> n.getSeanceType()== seanceType).collect(Collectors.toList()) ;
				if(secondFilteredSeances.size() > 0 ) {
					if(secondFilteredSeances.size() == 1) {
						String duration = "";
						if (seanceDuration.getDescrption().toLowerCase().toCharArray()[0] == 'u') 
							duration = "d'" + seanceDuration.getDescrption();
						else
							duration = "de " + seanceDuration.getDescrption();
						text = 	"1 séance " + duration + " " + seanceType.getSeanceString() + "\n";
					}
					else
					{
						text = secondFilteredSeances.size() + " séances " + seanceDuration.getDescrption() + " " + seanceType.getSeanceString() + "\n";
					}
				}
			}
			
		}
		
		cell.add(new Paragraph().add(new Text(text)));
		
		return cell;
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


	public Cell getCell(String text, TextAlignment alignment) {
	    Cell cell = new Cell().add(new Paragraph(text));
	    cell.setPadding(0);
	    cell.setTextAlignment(alignment);
	    cell.setBorder(Border.NO_BORDER);
	    return cell;
	}
}
