package servlets.optionsServlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class SetNbrStepsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// TODO Changer en doPost
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map <String, String[]> pars = req.getParameterMap();
		resp.setContentType("application/json");
		JSONObject obj;
		
		if (pars.containsKey("nbrSteps")) {
			Integer nbrSteps = Integer.valueOf(req.getParameter("nbrSteps"));
			try {
				obj = services.OptionsServices.setSteps(nbrSteps);
				resp.getWriter().print(obj.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
	}
}