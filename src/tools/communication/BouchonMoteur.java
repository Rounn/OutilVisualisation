package tools.communication;

import java.util.HashMap;
import java.util.Random;

public class BouchonMoteur implements EmbeddingsModel{
	
	
	public BouchonMoteur () {
		super ();
	}

	@Override
	public HashMap<String, String[]> getOptions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOptions(HashMap<String, String> options) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, float[]> getEmbeddings() {
		
		HashMap<String, float[]> randomEmbeddings = new HashMap<String, float[]>();
		Random rnd = new Random();
		randomEmbeddings.put("Point1", new float[]{rnd.nextFloat(),rnd.nextFloat(),rnd.nextFloat()});
		randomEmbeddings.put("Point2", new float[]{rnd.nextFloat(),rnd.nextFloat(),rnd.nextFloat()});
		randomEmbeddings.put("Point3", new float[]{rnd.nextFloat(),rnd.nextFloat(),rnd.nextFloat()});
		randomEmbeddings.put("Point4", new float[]{rnd.nextFloat(),rnd.nextFloat(),rnd.nextFloat()});
		randomEmbeddings.put("Point5", new float[]{rnd.nextFloat(),rnd.nextFloat(),rnd.nextFloat()});
		
		return randomEmbeddings;
	}

	@Override
	public void setWeights(HashMap<String, Float> weights) {
		// weights set.
		
	}

	@Override
	public void optimizeNext(int nb) {
		//It's optimizing...
	}

	@Override
	public double getGlobalLoss() {
		Random rnd = new Random();
		return rnd.nextDouble();
	}

	@Override
	public double getLoss() {
		Random rnd = new Random();
		return rnd.nextDouble();
	}

	@Override
	public void reinitLoss() {
		// reinitializing loss
		
	}

	@Override
	public HashMap<String, Float> getSims(String referer) {
		Random rnd = new Random();
		HashMap<String, Float> randomAttractivities = new HashMap<String, Float>();
		
		randomAttractivities.put("Point1", rnd.nextFloat());
		randomAttractivities.put("Point2", rnd.nextFloat());
		return randomAttractivities;
	}

	@Override
	public HashMap<String, Float> getAttractivities(String referer) {
		Random rnd = new Random();
		HashMap<String, Float> randomAttractivities = new HashMap<String, Float>();
		
		randomAttractivities.put("Point1", rnd.nextFloat());
		randomAttractivities.put("Point2", rnd.nextFloat());
		return randomAttractivities;
	}

	@Override
	public void setLine(double line) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getLastLine() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setLineDecreasingFactor(double dec) {
		// TODO Auto-generated method stub
		
	}

	

//	@Override
//	// OKKK
//	public boolean setWeight(ArrayList<ArrayList<Double>> points, double weight) {
//		return true;
//	}
//	@Override
//	// OKKKK
//	public ArrayList<ArrayList<Double>> next(int nbPas) {
//		ArrayList<ArrayList<Double>> points = new ArrayList<ArrayList<Double>>();
//		ArrayList<Double> p1 = new ArrayList<Double>();
//		ArrayList<Double> p2 = new ArrayList<Double>();
//		ArrayList<Double> p3 = new ArrayList<Double>();
//		ArrayList<Double> p4 = new ArrayList<Double>();
//		ArrayList<Double> p5 = new ArrayList<Double>();
//		Random rnd = new Random();
//		p1.add(rnd.nextDouble()); p1.add(rnd.nextDouble()); p1.add(rnd.nextDouble());
//		p2.add(rnd.nextDouble()); p2.add(rnd.nextDouble()); p2.add(rnd.nextDouble());
//		p3.add(rnd.nextDouble()); p3.add(rnd.nextDouble()); p3.add(rnd.nextDouble());
//		p4.add(rnd.nextDouble()); p4.add(rnd.nextDouble()); p4.add(rnd.nextDouble());
//		p5.add(rnd.nextDouble()); p5.add(rnd.nextDouble()); p5.add(rnd.nextDouble());
//		points.add(p1); points.add(p2); points.add(p3);
//		points.add(p4); points.add(p5);
//		
//		
//		
//		return points;
//	}
//
//	@Override
//	//OKKKK
//	public ArrayList<ArrayList<Double>> pause() {
//		ArrayList<ArrayList<Double>> points = new ArrayList<ArrayList<Double>>();
//		ArrayList<Double> p1 = new ArrayList<Double>();
//		ArrayList<Double> p2 = new ArrayList<Double>();
//		ArrayList<Double> p3 = new ArrayList<Double>();
//		ArrayList<Double> p4 = new ArrayList<Double>();
//		ArrayList<Double> p5 = new ArrayList<Double>();
//		Random rnd = new Random();
//		p1.add(rnd.nextDouble()); p1.add(rnd.nextDouble()); p1.add(rnd.nextDouble());
//		p2.add(rnd.nextDouble()); p2.add(rnd.nextDouble()); p2.add(rnd.nextDouble());
//		p3.add(rnd.nextDouble()); p3.add(rnd.nextDouble()); p3.add(rnd.nextDouble());
//		p4.add(rnd.nextDouble()); p4.add(rnd.nextDouble()); p4.add(rnd.nextDouble());
//		p5.add(rnd.nextDouble()); p5.add(rnd.nextDouble()); p5.add(rnd.nextDouble());
//		points.add(p1); points.add(p2); points.add(p3);
//		points.add(p4); points.add(p5);
//		
//		return points;
//	}
//
}
