package services;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.Points;
import tools.Step;
import tools.communication.BouchonMoteur;

public class TimelineServices {

	public static JSONObject getCurrentStepId() throws JSONException {
		Step step = Step.getInstance();
		JSONObject obj = new JSONObject();
		obj.put("stepId", step.getId());
		return obj;		
	}
	
	public static JSONObject saveCurrentStep() throws JSONException {

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

	}

	public static JSONObject next() throws JSONException {
		
		Integer nbrSteps = services.OptionsServices.getSteps();
		BouchonMoteur moteur = new BouchonMoteur();
		moteur.optimizeNext(nbrSteps);
		Step step = Step.getInstance();
		Points points = Points.getInstance();
		//Losses losses = Losses.getInstance();
		
		
		step.incrementId();
		points.addPoints(moteur.getEmbeddings());
		//losses.setLosses(moteur.getLoss(), moteur.getGlobalLoss());
		
		
		return TimelineServices.saveCurrentStep();
	}
}
