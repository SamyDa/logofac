package be.logofac.LogoFac;

import java.io.FileNotFoundException;

import javax.swing.text.StyleConstants.ColorConstants;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.kernel.color.*;

@Service
public class TestDocument {
	
	public void createDocument() {
		testTable();
		createBasic();
		aligntext();
	}

	private void testTable() {
		 // Creating a PdfDocument object   
	      String dest = "testTable.pdf";   
	     PdfWriter writer;
		try {
			writer = new PdfWriter(dest);
		
	         
	      // Creating a PdfDocument object      
	      PdfDocument pdf = new PdfDocument(writer);                  
	      
	      // Creating a Document object       
	      Document doc = new Document(pdf);                       
	         
	      // Creating a table       
	      float [] pointColumnWidths = {150F, 200F, 100F};   
	      Table table = new Table(pointColumnWidths);    
	      
	      // Adding cells to the table       
	      table.addCell(new Cell().add("Name"));       
	      table.addCell(new Cell().add("Raju"));       
	      table.addCell(new Cell().add("Id"));       
	      table.addCell(new Cell().add("1001"));       
	      table.addCell(new Cell().add("Designation"));       
	      table.addCell(new Cell().add("Programmer"));                 
	         
	      // Adding Table to document        
	      doc.add(table);                  
	         
	      // Closing the document       
	      doc.close();
	      System.out.println("Table created successfully..");   
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
		
	}

	private void aligntext() {
		 // Creating a PdfWriter       
	      String dest = "Align.pdf";       
	      PdfWriter writer;
		try {
			  writer = new PdfWriter(dest);
			 // Creating a PdfDocument       
		      PdfDocument pdfDoc = new PdfDocument(writer);      
		   // Adding a new page 
		      pdfDoc.addNewPage();   
		      // Creating a Document        
		      Document document = new Document(pdfDoc); 
		      float [] pointColumnWidths = {200F, 150F, 150F};   
		      Table table = new Table(pointColumnWidths);    
		      table.addCell(getCell("Text to the left", TextAlignment.LEFT));
		      table.addCell(getCell("Text in the middle", TextAlignment.LEFT));
//		      table.addCell(getCell("Text in the middle", TextAlignment.CENTER));
//		      table.addCell(getCell("Text to the right", TextAlignment.RIGHT));
		      table.addCell(getCell("Text to the right", TextAlignment.LEFT));
		      document.add(table);
		      
		      
		      // Closing the document    
		      document.close();              
		      System.out.println("PDF Created");    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private void createBasic() {
		 // Creating a PdfWriter       
	      String dest = "sample.pdf";       
	      PdfWriter writer;
		try {
			  writer = new PdfWriter(dest);
			 // Creating a PdfDocument       
		      PdfDocument pdfDoc = new PdfDocument(writer);              
		   
		      // Adding a new page 
		      pdfDoc.addNewPage();               
		   
		      // Creating a Document        
		      Document document = new Document(pdfDoc);               
		   
		      // Closing the document    
		      document.close();              
		      System.out.println("PDF Created");    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public Cell getCell(String text, TextAlignment alignment) {
	    Cell cell = new Cell().add(new Paragraph(text));
	    cell.setPadding(0);
	    cell.setTextAlignment(alignment);
	    cell.setBorder(Border.NO_BORDER);
	    return cell;
	}

}
