/**
 * 
 */
package com.ireslab.emc.serviceImpl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ireslab.emc.model.MailData;
import com.ireslab.emc.service.EmailService;

/**
 * @author Akhilesh
 *
 */
@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;
	
	
    public void sendMailTo(MailData data) {
		
		try {
			MimeMessage message = emailSender.createMimeMessage();

			// Enable the multipart flag!
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom("noreply.electra@gmail.com", "Electra Support");
			helper.setTo(data.getSendTo());
			helper.setSubject(data.getSubject());
            helper.setText(data.getBody(), true);
			
            emailSender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ireslab.emc.service.EmailService#sendEmail(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void sendEmail(String to, String subject, String text) {
	 	MimeMessage message = emailSender.createMimeMessage();
 		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setFrom("noreply.electra@gmail.com", "Electra Support");
			helper.setTo(to);
 			helper.setText(text, true);	
			helper.setSubject(subject);
			emailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	
}