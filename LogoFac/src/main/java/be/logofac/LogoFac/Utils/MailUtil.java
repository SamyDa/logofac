package be.logofac.LogoFac.Utils;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
class MailUtil{  
		 
	private String title; 
	private String messageText;
	private String fileLocation; 
	private String recipient;
	private String ccAdresse;
	private String fileName;
	public MailUtil() {
		super();
	}

	public MailUtil(String title, String messageText, String fileLocation) {
		super();
		this.title = title;
		this.messageText = messageText;
		this.fileLocation = fileLocation;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessageText() {
		return messageText;
	}


	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}


	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}
	

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	
	
	public String getCcAdresse() {
		return ccAdresse;
	}

	public void setCcAdresse(String ccAdresse) {
		this.ccAdresse = ccAdresse;
	}
	
	

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean sendEmail() {
	
			final String username = "info.logofac@gmail.com";
		    final String password = "naelia2power";
		
		    Properties prop = new Properties();
		    prop.put("mail.smtp.host", "smtp.gmail.com");
		    prop.put("mail.smtp.port", "587");
		    prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		    prop.put("mail.smtp.auth", "true");
		    prop.put("mail.smtp.starttls.enable", "true"); //TLS
		    prop.put("mail.smtp.ssl.trust", "*");
		    
		    Session session = Session.getInstance(prop,
		            new javax.mail.Authenticator() {
		                protected PasswordAuthentication getPasswordAuthentication() {
		                    return new PasswordAuthentication(username, password);
		                }
		            });
		
		    try {
		
		        Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress("info.logofac@gmail.com"));
		        if(recipient == null)
		        	return false;
		        String toAddress = recipient;
		        if(ccAdresse != null)
		        	toAddress = toAddress+" , " +ccAdresse;
		        message.setRecipients(
		                Message.RecipientType.TO,
		                InternetAddress.parse(toAddress)
		        );
		        message.setSubject(title);
		
		     // Create the message part
		         BodyPart messageBodyPart = new MimeBodyPart();
		     // Now set the actual message
		         messageBodyPart.setText(messageText);

		         // Create a multipar message
		         Multipart multipart = new MimeMultipart();

		         // Set text message part
		         multipart.addBodyPart(messageBodyPart);

		         // Part two is attachment
		         messageBodyPart = new MimeBodyPart();
		         if(fileLocation != null) {
			         DataSource source = new FileDataSource(fileLocation);
			         messageBodyPart.setDataHandler(new DataHandler(source));
			         if(fileName == null)
			        	 fileName = fileLocation;
			         messageBodyPart.setFileName(fileName);
			         multipart.addBodyPart(messageBodyPart);
		         }
		         // Send the complete message parts
		         message.setContent(multipart);

		         // Send message
		         Transport.send(message);

		        System.out.println("Email sent successfully");
		
		        return true;
		
		    } catch (MessagingException e) {
		        e.printStackTrace();
		    }
		    return false;
	}

}
   