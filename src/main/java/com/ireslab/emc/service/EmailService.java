/**
 * 
 */
package com.ireslab.emc.service;

import com.ireslab.emc.model.MailData;

/**
 * @author Akhilesh
 *
 */
public interface EmailService {
	void sendEmail(String to, String subject, String text);
	public void sendMailTo(MailData data);
	
}
