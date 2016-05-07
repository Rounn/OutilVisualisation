package servlets.optionsServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class ReinitWeightsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// TODO Changer en doPost
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("<HTML><BODY>");

		JSONObject obj;
		try {
			obj = services.OptionsServices.reinitWeights();
			resp.getWriter().println(obj.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


		resp.getWriter().println("</BODY></HTML>");
	}
}
