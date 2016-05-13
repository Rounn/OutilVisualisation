package services;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import mlp.EmbeddingsModel;
import tools.Points;
import tools.Step;
import tools.communication.Model;
import tools.options.Attractivities;
import tools.options.Similarities;

public class TimelineServices {

	public static JSONObject getCurrentStepId() throws JSONException {
		Step step = Step.getInstance();
		JSONObject obj = new JSONObject();
		obj.put("stepId", step.getId());
		return obj;		
	}
	
	public static JSONObject setURI(String uri) throws JSONException {
		Step step = Step.getInstance();
		JSONObject obj = new JSONObject();
		
		step.setURI(uri);
		obj.put("uri", uri);
		return obj;
	}
	
	public static EmbeddingsModel getMod() {
		Model model = Model.getInstance();
		return model.getMod();
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
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		moteur.optimizeNext(nbrSteps);
		Step step = Step.getInstance();
		Points points = Points.getInstance();
		Attractivities attr = Attractivities.getInstance();
		Similarities sims = Similarities.getInstance();
		//Losses losses = Losses.getInstance();
		
		
		step.incrementId();
		points.addPoints(moteur.getEmbeddings());
		attr.setAttractivities(moteur.getAttractivities(attr.getReferer()));
		sims.setSimilarities(moteur.getAttractivities(sims.getReferer()));
		//losses.setLosses(moteur.getLoss(), moteur.getGlobalLoss());
		
		
		return TimelineServices.saveCurrentStep();
	}
}
