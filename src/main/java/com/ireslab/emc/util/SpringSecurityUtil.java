package com.ireslab.emc.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Nitin
 *
 */
public class SpringSecurityUtil {

	/**
	 * @return
	 */
	public static String usernameFromSecurityContext() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails details = (UserDetails) authentication.getPrincipal();

		
			return details.getUsername();
		}
		

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("Pass@4321"));
	}
}