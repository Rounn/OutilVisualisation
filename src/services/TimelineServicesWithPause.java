package services;

public class TimelineServicesWithPause {
	
	
	/*public static JSONObject saveStep() throws JSONException {
		
		JSONObject obj = new JSONObject();
		Step currentStep = Step.getInstance();
		try {
			currentStep.save();
			obj.put("SAVE OK", currentStep.toJSON());
			return obj;
		} catch (IOException | JSONException e) {
			e.printStackTrace();
			obj.put("SAVE Error", e.getMessage());
			return obj;
		}
		
	}*/
	
	/*public static JSONObject next() throws JSONException {
		BouchonMoteur moteur = new BouchonMoteur();
		
		Step currentStep = Step.getInstance();
		currentStep.incrementId();
		Points points = Points.getInstance();
		NbrSteps ns = NbrSteps.getInstance();
		Integer nbrSteps = ns.getSteps();
		JSONObject obj = new JSONObject();
		
		points.addPoints(moteur.next(nbrSteps));
		
		
		try {
			obj.put("Moteur Resultat", points.toJSON());
			return obj;
		} catch (JSONException e) {
			e.printStackTrace();
			obj.put("Moteur Error", e.getMessage());
			return obj;
		}
	}*/

}
