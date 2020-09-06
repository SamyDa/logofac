package be.logofac.LogoFac;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import be.logofac.LogoFac.service.AdresseService;
import be.logofac.LogoFac.service.FactureService;
import be.logofac.LogoFac.service.PatientService;
import be.logofac.LogoFac.service.PrixService;
import be.logofac.LogoFac.service.ProfessionnelService;
import be.logofac.LogoFac.service.SeanceService;

@Service
public class DocumentProcess {

	private PrixService prixService;
	private ProfessionnelService professionnelService;
	private PatientService patientService;
	private SeanceService seanceService;
	private AdresseService adresseService;
	private FactureService factureService;
	
	
	
	public DocumentProcess(PrixService prixService, ProfessionnelService professionnelService,
			PatientService patientService, SeanceService seanceService, AdresseService adresseService,
			FactureService factureService) {
		super();
		this.prixService = prixService;
		this.professionnelService = professionnelService;
		this.patientService = patientService;
		this.seanceService = seanceService;
		this.adresseService = adresseService;
		this.factureService = factureService;
	}


	public void loadDocumentData() {
		
		System.out.println("Load documents ");
		System.out.println("Test Prix: ");
		prixService.getAllPrices().forEach(n -> System.out.println(n.toString()));
		
		System.out.println("Test facture");
		factureService.findAll().forEach(n-> {System.out.println(n.toString());});
		
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk("Hello World", font);
		 
		try {
			document.add(chunk);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		
	}
}
