package com.urt.util.http;

/*
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * [Additional notices, if required by prior licensing conditions]
 *
 */

import java.io.File;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * A example that demonstrates how HttpClient APIs can be used to perform 
 * form-based logon.
 * </p>
 *
 * @author Oleg Kalnichevski
 *
 */
public class PostSOAP
{
	private static final Log log = LogFactory.getLog(PostSOAP.class);
    static final String LOGON_SITE = "http://localhost:8080/rides/rideList.do";
    static final int    LOGON_PORT = 80;

    public PostSOAP() {
        super();
    }

    public static void main(String[] args) throws Exception {

        HttpClient client = new HttpClient();
       // client.getHostConfiguration().setHost(LOGON_SITE);
        client.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
     	// 'developer.java.sun.com' has cookie compliance problems
        // Their session cookie's domain attribute is in violation of the RFC2109
        // We have to resort to using compatibility cookie policy

        GetMethod authget = new GetMethod(LOGON_SITE);

        client.executeMethod(authget);
        log.debug("Login form get: " + authget.getStatusLine().toString()); 
        // release any connection resources used by the method
        authget.releaseConnection();
        // See if we got any cookies
       /** CookieSpec cookiespec = CookiePolicy.getDefaultSpec();
        Cookie[] initcookies = cookiespec.match(
            LOGON_SITE, LOGON_PORT, "/", false, client.getState().getCookies());
        log.debug("Initial set of cookies:");    
        if (initcookies.length == 0) {
            log.debug("None");    
        } else {
            for (int i = 0; i < initcookies.length; i++) {
                log.debug("- " + initcookies[i].toString());    
            }
        }
        */
       
        PostMethod authpost = new PostMethod(LOGON_SITE);
        // Prepare login parameters
        NameValuePair action   = new NameValuePair("action", "<br />Dear fdsfds dfdsfd,<br /><br />We are sorry but we have declined to publish your ride offer on the church website. If you find this to be an error, please contact us by calling the church office during normal business hours.  <br /><br />This is an automated message from {1} website.Please do not reply.<br /><br /><strong><u>Ride Offer Details:</u></strong><br /><br />&nbsp;&nbsp;&nbsp;&nbsp;Name: fdsfds dfdsfd<br />&nbsp;&nbsp;&nbsp;&nbsp;E-mail: dfgdfd@fdsf.com<br />&nbsp;&nbsp;&nbsp;&nbsp;Phone Number: 996-665-5456<br /><br /><strong>**NOTE:</strong> This is an automated email from Your Church. Please DO NOT REPLY to this email.<br />Copyright &copy;  2008 Hyniva Consulting Services,LLC. All Rights Reserved.");
        NameValuePair url      = new NameValuePair("url", "/index.html");
        NameValuePair userid   = new NameValuePair("UserId", "userid");
        NameValuePair password = new NameValuePair("Password", "password");
        authpost.setRequestBody( 
          new NameValuePair[] {action, url, userid, password});
        
        client.executeMethod(authpost);
        log.debug("Login form post: " + authpost.getStatusLine().toString()); 
        // release any connection resources used by the method
        authpost.releaseConnection();
        // See if we got any cookies
        // The only way of telling whether logon succeeded is 
        // by finding a session cookie
        //Cookie[] logoncookies = cookiespec.match(
          //  LOGON_SITE, LOGON_PORT, "/", false, client.getState().getCookies());
        
        /**if (logoncookies.length == 0) {
            log.debug("None");    
        } else {
            for (int i = 0; i < logoncookies.length; i++) {
                log.debug("- " + logoncookies[i].toString());    
            }
        }*/
        File input = new File("C:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/rides/userfiles/yahooMapRss.xml");
        // Prepare HTTP post
        PostMethod post = new PostMethod(LOGON_SITE);
        // Request content will be retrieved directly
        // from the input stream
        RequestEntity entity = new FileRequestEntity(input, "text/xml; charset=ISO-8859-1");
        post.setRequestEntity(entity);
        // Get HTTP client
        HttpClient httpclient = new HttpClient();
        // Execute request
        try {
            int result = httpclient.executeMethod(post);
            // Display status code
            log.debug("Response status code: " + result);
            // Display response
            log.debug("Response body: ");
            log.debug(post.getResponseBodyAsString());
        } finally {
            // Release current connection to the connection pool once you are done
            post.releaseConnection();
        }

        // Usually a successful form-based login results in a redicrect to 
        // another url
        int statuscode = authget.getStatusCode();
        
        if (statuscode == HttpStatus.SC_MOVED_TEMPORARILY ||
            statuscode == HttpStatus.SC_MOVED_PERMANENTLY ||
            statuscode == HttpStatus.SC_SEE_OTHER ||
            statuscode == HttpStatus.SC_TEMPORARY_REDIRECT) {
            Header header = authget.getResponseHeader("location");
            if (header != null) {
                String newuri = header.getValue();
                if (newuri == null || newuri.equals("")) {
                    newuri = "/";
                }
                log.debug("Redirect target: " + newuri); 
                GetMethod redirect = new GetMethod(newuri);

                client.executeMethod(redirect);
                log.debug("Redirect: " + redirect.getStatusLine().toString()); 
                // release any connection resources used by the method
                redirect.releaseConnection();
            } else {
                log.debug("Invalid redirect");
                System.exit(1);
            }
        }
    }
}
