package servlets.timelineServlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetModelServlets extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map <String, String[]> pars = req.getParameterMap();
		resp.getWriter().println("<HTML><BODY>");
		
		if (pars.containsKey("nbDims")&&pars.containsKey("collection")&&pars.containsKey("database")) {
			String nbDims = req.getParameter("nbDims");
			String collection = req.getParameter("collection");
			String database = req.getParameter("database");
			HashMap<String, String> hargs = new HashMap<String, String>();
			hargs.put("nbDims", nbDims);
			hargs.put("database", database);
			hargs.put("collection", collection);
			
			services.TimelineServices.setModel(hargs);
			
		}
		
		
		
		
		resp.getWriter().println("</BODY></HTML>");
	}
}
