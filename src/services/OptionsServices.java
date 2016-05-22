package services;

import java.util.HashMap;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import mlp.EmbeddingsModel;
import tools.options.Attractivities;
import tools.options.Dimensions;
import tools.options.Line;
import tools.options.Losses;
import tools.options.NbrSteps;
import tools.options.PointsWeight;
import tools.options.Similarities;

public class OptionsServices {
	
	
	/* WEIGHTS Services */
	
	public static JSONObject setWeights (HashMap<String, Float> weights) throws JSONException{
		JSONObject obj = new JSONObject();
		
		//Set in the visualization tool
		PointsWeight pw = PointsWeight.getInstance();
		
		// TODO CHange all of this!
		Random rnd = new Random();
		weights.put("Point1", rnd.nextFloat());
		weights.put("Point2", rnd.nextFloat());
		weights.put("Point3", rnd.nextFloat());
		
		pw.addWeights(weights);
		
		//Set in the learning motor
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		moteur.setWeights(pw.getWeights());
		obj = pw.toJSON();
		//obj.put("moteur", "OK");
		//obj.put("weights", pw.toJSON());
		 
		return obj;
	}
	
	public static JSONObject reinitWeights() throws JSONException {
		PointsWeight pw = PointsWeight.getInstance();
		pw.reinitWeights();
		//BouchonMoteur  moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		moteur.setWeights(pw.getWeights());
		return pw.toJSON();
	}
	
	
	/* NBRSTEPS Services */
	
	public static JSONObject setSteps (Integer steps) throws JSONException {
		JSONObject obj = new JSONObject();
		
		NbrSteps ns = NbrSteps.getInstance();
		
		ns.setSteps(steps);
		
		obj.put("response", "OK");
		obj.put("steps", ns.toJSON());
		
		return obj;
	}
	
	public static Integer getSteps () {
		return NbrSteps.getInstance().getSteps();
	}
	

	/* DIMENSIONS Services */
	
	public static void setX(int x) {
		Dimensions dims = Dimensions.getInstance();
		dims.setX(x);
	}
	
	public static void setY(int y) {
		Dimensions dims = Dimensions.getInstance();
		dims.setY(y);
	}
	
	public static int getX() {
		Dimensions dims = Dimensions.getInstance();
		return dims.getX();
	}
	
	public static int getY() {
		Dimensions dims = Dimensions.getInstance();
		return dims.getY();
	}
	
	public static int getTotalNbrDimensions() {
		Dimensions dims = Dimensions.getInstance();
		return dims.getTotalDimensions();
	}
	
	
	/* ATTRACTIVITIES Services */
	
	public static JSONObject getAttractivities(String referrer) throws JSONException {
		Attractivities att = Attractivities.getInstance();
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		
		att.setReferer(referrer);
		att.setAttractivities(moteur.getAttractivities(referrer));
		return att.toJSON();
	}
	
	public static void setAttractivityReferer(String referer) {
		Attractivities attRef = Attractivities.getInstance();
		attRef.setReferer(referer);
	}
	
	public static JSONObject getAttractivityReferer() throws JSONException {
		Attractivities attRef = Attractivities.getInstance();
		return attRef.toJSON();
	}
	
	
	/* LOSS Services */
	
	public static JSONObject getLoss() throws JSONException {
		Losses l = Losses.getInstance();
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		JSONObject obj = new JSONObject();
		
		l.setLoss(moteur.getLoss());
		obj.put("loss", l.getLoss());
		return obj;
	}
	
	public static JSONObject getGlobalLoss() throws JSONException {
		Losses l = Losses.getInstance();
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		JSONObject obj = new JSONObject();
		
		l.setGlobalLoss(moteur.getGlobalLoss());
		obj.put("globalLoss", l.getGlobalLoss());
		return obj;
	}
	
	public static JSONObject getLosses() throws JSONException {
		Losses l = Losses.getInstance();
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		JSONObject obj = new JSONObject();
		
		l.setLoss(moteur.getGlobalLoss());
		l.setGlobalLoss(moteur.getGlobalLoss());
		obj = l.toJSON();
		return obj;
	}
	
	public static JSONObject reinitLoss() throws JSONException {
		Losses l = Losses.getInstance();
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		JSONObject obj = new JSONObject();
		
		moteur.reinitLoss();
		l.setLoss(0.0);
		l.setGlobalLoss(0.0);
		obj = l.toJSON();
		return obj;
	}
	
	
	/* SIMILARIITIES Services */
	
	public static JSONObject getSimilarities(String referrer) throws JSONException {
		Similarities sims = Similarities.getInstance();
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		
		sims.setReferer(referrer);
		sims.setSimilarities(moteur.getSims(referrer));
		return sims.toJSON();
	}
	
	public static void setSimilarityReferer(String referer) {
		Attractivities attRef = Attractivities.getInstance();
		attRef.setReferer(referer);
	}
	
	public static JSONObject getSimilarityReferer() throws JSONException {
		Attractivities attRef = Attractivities.getInstance();
		return attRef.toJSON();
	}
	
	
	/* LINE Services */
	
	public static JSONObject getLastLine() throws JSONException {
		//Line l = Line.getInstance();
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		JSONObject obj = new JSONObject();
		
		//l.setLine(moteur.getLastLine());
		obj.put("line", moteur.getLastLine());
		return obj;
	}
	
	public static JSONObject setLine(double line) throws JSONException {
		Line l = Line.getInstance();
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		JSONObject obj = new JSONObject();
		
		l.setLine(line);
		moteur.setLine(line);
		obj.put("line", l.getLine());
		return obj;
	}
	
	public static JSONObject setLineDecreasingFactor(double decreasingFactor) throws JSONException {
		Line l = Line.getInstance();
		//BouchonMoteur moteur = new BouchonMoteur();
		EmbeddingsModel moteur = services.TimelineServices.getMod();
		JSONObject obj = new JSONObject();
		
		l.setDecreasingFactor(decreasingFactor);
		moteur.setLineDecreasingFactor(decreasingFactor);
		obj.put("decreasingFactor", l.getDecreasingFactor());
		return obj;
	}
	
	public static JSONObject getLineDecreasingFactor() throws JSONException {
		Line l = Line.getInstance();
		JSONObject obj = new JSONObject();
		
		obj.put("decreasingFactor", l.getDecreasingFactor());
		return obj;		
	}
	
}
