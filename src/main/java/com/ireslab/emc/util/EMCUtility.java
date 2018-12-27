/**
 * 
 */
package com.ireslab.emc.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author Akhilesh
 *
 */
public class EMCUtility {
	private static EMCUtility emcUtility = new EMCUtility();

	private EMCUtility() {
	}

	public static EMCUtility getInstance() {
		return emcUtility;
	}

	public String createPaswordChars() {
		String P_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random randomNumber = new Random();
		while (salt.length() < 9) {
			int index = (int) (randomNumber.nextFloat() * P_CHARS.length());
			salt.append(P_CHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}
	
	public static String geUUID() {
	    return UUID.randomUUID().toString().replace("-", "");
	    
	}
}
