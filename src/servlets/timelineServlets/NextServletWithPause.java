package servlets.timelineServlets;

import javax.servlet.http.HttpServlet;



public class NextServletWithPause extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// TODO Changer en doPost
	/*public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.getWriter().println("<HTML><BODY>");
		JSONObject obj = new JSONObject();
		State state = State.getInstance();
		if (state.getState() == 0) {
			state.changeState();  // Mise en marche (==1)
			
			try {
				for (int i=0; i<services.OptionsServices.getSteps().intValue(); i++) {
					if (state.getState() == 1) {
						obj = services.TimelineServicesWithPause.next();
						resp.getWriter().println(obj.toString());
						resp.getWriter().println();
						obj = services.TimelineServicesWithPause.saveStep();
						resp.getWriter().println(obj.toString());
						resp.getWriter().println("\n");
					}
					else break;
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
				resp.getWriter().println(e.getMessage());
			}
			
			state.changeState();  // Remise à zero (arret)
		}
		
		resp.getWriter().println("</BODY></HTML>");
	}*/
}