package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;

public class TestServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("<HTML><BODY>");
		//resp.getWriter().println(this + ": <br>");
		
		JSONObject obj;
		try {
			obj = services.TestServices.jsonTest();
			resp.getWriter().println(obj.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		resp.getWriter().println("</BODY></HTML>");
	}
}