package com.assignment.core.serviceImpl;




import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

import com.assignment.core.service.SendEmailService;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;


@Component(service=SendEmailService.class,immediate=true)
@Designate(ocd = SendEmailServiceImpl.Config.class)
public class SendEmailServiceImpl implements SendEmailService  {

	@Reference
	MessageGatewayService messageGatewayService;
	
	private String from;
	private String to;
	
	@ObjectClassDefinition(name = "Email config", description = "Email details config")
	public static @interface Config{
		@AttributeDefinition(name = "From mail id")
		String fromEmailId() default "pranavlarry111@gmail.com";
		
		@AttributeDefinition(name = "To mail id")
		String toEmailId() default "pranavlari.mec@gmail.com";
		
	}
	
	@Override
	public void sendMail(String msg) {

		 	MessageGateway<Email> messageGateway; 
         
		    //Set up the Email message
		    Email email = new SimpleEmail();
		          
		    //Set the mail values
		    String emailToRecipients = this.to; 
		      
		    try {
				email.addTo(emailToRecipients);
			    email.setSubject("New application alert");
			    email.setFrom(this.from); 
			    email.setMsg(msg);
			    messageGateway = messageGatewayService.getGateway(Email.class);
			    
			    // Check the logs to see that messageGateway is not null
			    messageGateway.send((Email) email);
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}
	
	@Activate
	protected void active(final Config config) {
		this.from=config.fromEmailId();
		this.to=config.toEmailId();
	}

}
