package servlets.optionsServlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class WeightServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// TODO Changer en doPost
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map <String, String[]> pars = req.getParameterMap();
		resp.getWriter().println("<HTML><BODY>");
		
		JSONObject obj;
		if (pars.containsKey("weights")) {
			try {
				// TODO change to get the values as a string from the request and parse it
				HashMap <String, Float> weights = new HashMap<String, Float>();// a changer
				obj = services.OptionsServices.setWeights(weights);
				resp.getWriter().println(obj.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		
		resp.getWriter().println("</BODY></HTML>");
	}
}