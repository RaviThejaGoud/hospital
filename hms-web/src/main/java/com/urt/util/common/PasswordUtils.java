/**
 * 
 */
package com.urt.util.common;

import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * @author uroomtech
 *
 */
public class PasswordUtils {
	
	public static String passwordEncoder(String plainText, String saltText) {
		ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
		SystemWideSaltSource saltSource = new SystemWideSaltSource();
		saltSource.setSystemWideSalt(saltText);
		String encodedPassword = passwordEncoder.encodePassword(plainText,saltSource.getSalt(null));
		return encodedPassword;
	}

}
