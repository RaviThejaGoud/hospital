package com.urt.sms.webapp.listener;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationProcessingFilter;
import org.springframework.security.web.context.HttpSessionContextIntegrationFilter;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.urt.persistence.model.user.User;
import com.urt.service.manager.interfaces.user.UserManager;


/**
 * UserCounterListener class used to count the current number
 * of active users for the applications.  Does this by counting
 * how many user objects are stuffed into the session.  It Also grabs
 * these users and exposes them in the servlet context.
 *
 *
 */
public class UserCounterListener implements ServletContextListener, HttpSessionAttributeListener {
	
	private static final Log log = LogFactory.getLog(UserCounterListener.class);
    /**
     * Name of user counter variable
     */
    public static final String COUNT_KEY = "userCounter";
    /**
     * Name of users Set in the ServletContext
     */
    public static final String USERS_KEY = "userNames";
    /**
     * The default event we're looking to trap.
     */
    public static final String EVENT_KEY = HttpSessionContextIntegrationFilter.SPRING_SECURITY_CONTEXT_KEY;
    private transient ServletContext servletContext;
    private int counter;
    private Set<User> accounts;

    /**
     * Initialize the context
     * @param sce the event
     */
    public synchronized void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));
    }

    /**
     * Set the servletContext, users and counter to null
     * @param event The servletContextEvent
     */
    public synchronized void contextDestroyed(ServletContextEvent event) {
        servletContext = null;
        accounts = null;
        counter = 0;
    }

    synchronized void incrementUserCounter() {
        counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
        counter++;
        servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));
    }

    synchronized void decrementUserCounter() {
        int counter = Integer.parseInt((String) servletContext.getAttribute(COUNT_KEY));
        counter--;

        if (counter < 0) {
            counter = 0;
        }

        servletContext.setAttribute(COUNT_KEY, Integer.toString(counter));
    }

    @SuppressWarnings("unchecked")
    synchronized void addUsername(User account) {
    	accounts = (Set) servletContext.getAttribute(USERS_KEY);
    	//account.setLastAccessDate(new Date());
        if (accounts == null) {
        	accounts = new LinkedHashSet<User>();
        }
        if(account.isUserOnlineNow())
        {
	        if (accounts.contains(account)) {
	        	accounts.remove(account);
	        }	
        	accounts.add(account);
            servletContext.setAttribute(USERS_KEY, accounts);
            incrementUserCounter();
        }
    }

    synchronized void removeUsername(User account, HttpSession session) {
    	accounts = (Set) servletContext.getAttribute(USERS_KEY);
        if (accounts != null) {
        	ServletContext context=session.getServletContext();
        	ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		    UserManager userMgr= (UserManager) ctx.getBean("userManager");
		    session.setMaxInactiveInterval(500);		  
		    //userMgr.updateUserLoginStatus(account.getUsername());
		    log.debug("Changed user status to inactive....."+ account.getUsername());
		    accounts.remove(account);
        }

        servletContext.setAttribute(USERS_KEY, accounts);
        decrementUserCounter();
    }

    /**
     * This method is designed to catch when user's login and record their name
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeAdded(javax.servlet.http.HttpSessionBindingEvent)
     * @param event the event to process
     */
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().equals(EVENT_KEY) && !isAnonymous()) {
            SecurityContext securityContext = (SecurityContext) event.getValue();
            if (securityContext.getAuthentication().getPrincipal() instanceof User) {
            	User account = (User) securityContext.getAuthentication().getPrincipal();
                addUsername(account);
                log.debug("Changed user status to active....." + account.getUsername());
            }
        // Workaround for Jetty bug (http://www.nabble.com/current-user-count-incorrect-tf3550268.html#a9919134)
        } else if (event.getName().equals(AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY)) {
            String username = (String) event.getValue();
            User account = new User(username);
            addUsername(account);
        }
    }

    private boolean isAnonymous() {
        AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx != null) {
            Authentication auth = ctx.getAuthentication();
            return resolver.isAnonymous(auth);
        }
        return true;
    }

    /**
     * When user's logout, remove their name from the hashMap
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeRemoved(javax.servlet.http.HttpSessionBindingEvent)
     * @param event the session binding event
     */
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals(EVENT_KEY) && !isAnonymous()) {
            SecurityContext securityContext = (SecurityContext) event.getValue();
            Authentication auth = securityContext.getAuthentication();
            if (auth != null && auth.getPrincipal() instanceof User) {
            	User account = (User) auth.getPrincipal();
                removeUsername(account, event.getSession());
            }
        }
    }

    /**
     * Needed for Acegi Security 1.0, as it adds an anonymous user to the session and
     * then replaces it after authentication. http://forum.springframework.org/showthread.php?p=63593
     * @see javax.servlet.http.HttpSessionAttributeListener#attributeReplaced(javax.servlet.http.HttpSessionBindingEvent)
     * @param event the session binding event
     */
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getName().equals(EVENT_KEY) && !isAnonymous()) {
            SecurityContext securityContext = (SecurityContext) event.getValue();
            if (securityContext.getAuthentication() != null) {
                if (securityContext.getAuthentication().getPrincipal() instanceof User) {
                	User account = (User) securityContext.getAuthentication().getPrincipal();
                    addUsername(account);
                }
            }
        }
    }
}
