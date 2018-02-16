package session;

import javax.servlet.ServletContext;

public class ApplicationContext {
	private static ServletContext servletContext;
	
	public static void setServletContext(ServletContext servletContext) {
		ApplicationContext.servletContext = servletContext;
	}
	
	public static ServletContext getServletContext() {
		return servletContext;
	}
}
