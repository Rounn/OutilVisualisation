package servlets.timelineServlets;

import javax.servlet.http.HttpServlet;

public class PauseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// TODO Changer en doPost
	/*public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.getWriter().println("<HTML><BODY>");
		JSONObject obj = new JSONObject();
		State state = State.getInstance();
		if (state.getState() == 1) {
			state.changeState(); //mise à zero(arret)
			
			try {
				
				obj = services.TimelineServicesWithPause.saveStep();
				resp.getWriter().println(obj.toString());
				
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.getWriter().println(e.getMessage());
			}		
		}
		
		resp.getWriter().println("</BODY></HTML>");
	}*/
}