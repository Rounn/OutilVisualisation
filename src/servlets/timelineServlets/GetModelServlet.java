package servlets.timelineServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class GetModelServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// TODO Changer en doPost
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		//resp.getWriter().println("<HTML><BODY>");

		JSONObject obj;
		try {
			obj = services.TimelineServices.getModelOptions();
			resp.getWriter().println(obj);
			resp.getWriter().close();
		} catch (JSONException e) {
			e.printStackTrace();
		}


		//resp.getWriter().println("</BODY></HTML>");
	}
}
