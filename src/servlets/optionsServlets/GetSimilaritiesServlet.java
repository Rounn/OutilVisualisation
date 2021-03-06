package servlets.optionsServlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class GetSimilaritiesServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map <String, String[]> pars = req.getParameterMap();
		resp.setContentType("application/json");
		JSONObject obj = new JSONObject();
		
		if (pars.containsKey("simsReferrer")) {
			String referrer = req.getParameter("simsReferrer");
			try {
				obj = services.OptionsServices.getSimilarities(referrer);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		resp.getWriter().print(obj.toString());
	}

}
