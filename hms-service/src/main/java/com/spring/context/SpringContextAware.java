package com.spring.context;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.churchgroup.util.object.ObjectFunctions;

@Component
public class SpringContextAware implements ApplicationContextAware,ServletContextAware{

	public static ApplicationContext springContext=null;
    private static ServletContext ctx;
	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		// TODO Auto-generated method stub
		springContext=context;
	}
	
	public static Object getBean(String beanName){
		return springContext.getBean(beanName);
	}

	public void setServletContext(ServletContext ctx) {
		this.ctx = ctx;
	}
	
	public static String getHostUrl(){
		if(ObjectFunctions.isNullOrEmpty(ctx))
			return null;
		else
			return ctx.getInitParameter("hostUrl");
	}
	public static String getRealPath(String path){
		if(ObjectFunctions.isNullOrEmpty(ctx))
			return null;
		else
			return ctx.getRealPath(path);
	}
}
