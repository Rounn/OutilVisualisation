package servlets.optionsServlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class SetLineServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// TODO Changer en doPost
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		Map <String, String[]> pars = req.getParameterMap();
		resp.setContentType("application/json");
		JSONObject obj = new JSONObject();

		if (pars.containsKey("line")) {
			try {
				System.out.println("Test req" + Double.valueOf(req.getParameter("line")));
				obj = services.OptionsServices.setLine(Double.valueOf(req.getParameter("line")));
				System.out.println(obj.toString());
				resp.getWriter().print(obj.toString());
				resp.getWriter().close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}
