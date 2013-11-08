package de.homelabs.moonrat.base.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MoonratDispatcherServlet extends DispatcherServlet {

	private static final long serialVersionUID = 1L;
	
	public MoonratDispatcherServlet() {
		super();
	}

	public MoonratDispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
	}

	@Override
	protected void noHandlerFound(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//super.noHandlerFound(request, response);
		response.getOutputStream().println("Fehler!");
		//response.sendRedirect("index.html");
	}

	
}
