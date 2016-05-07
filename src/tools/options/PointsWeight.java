package tools.options;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class PointsWeight {
	private static volatile PointsWeight instance = null;

	private final String name;
	private HashMap <String, Float> weights;

	private PointsWeight() {
		super();
		this.name = "PointsWeight";
		this.weights = new HashMap<String, Float>();
	}

	public final static PointsWeight getInstance() {
		if (PointsWeight.instance == null) {
			synchronized(PointsWeight.class) {
				if (PointsWeight.instance == null) {
					PointsWeight.instance = new PointsWeight();
				}
			}
		}
		return PointsWeight.instance;
	}

	public void addWeights(HashMap<String, Float> weights) {
		this.weights.clear();
		this.weights = weights;
	}
	
	public HashMap<String, Float> getWeights() {
		return this.weights;
	}
	
	public void reinitWeights() {
		this.weights.clear();
	}
	
	public String getName() {
		return this.name;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject obj = new JSONObject();

		for(Entry<String, Float> entry : this.weights.entrySet()) 
		    obj.put(entry.getKey(), entry.getValue());
		return obj;
	}
	
	@Override
	public String toString() {
		return "PointsWeight [name=" + name + ", weightedPoints=" + weights + "]";
	}
	
	

}
