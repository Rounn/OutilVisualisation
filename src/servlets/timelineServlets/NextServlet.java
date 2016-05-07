package servlets.timelineServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import services.TimelineServices;

public class NextServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// TODO Changer en doPost
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.getWriter().println("<HTML><BODY>");
		JSONObject obj = new JSONObject();
		try {
			obj = TimelineServices.next();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				obj.put("Error next", e.getMessage());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		resp.getWriter().println(obj.toString());
		resp.getWriter().println("</BODY></HTML>");
	}

}