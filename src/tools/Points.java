package tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tools.Points;
import tools.options.Dimensions;

public class Points {
	private static volatile Points instance = null;

	private final String name;
	private HashMap<String,float[]> embeddings;

	private Points() {
		super();
		this.name = "Points";
		this.embeddings = new HashMap<String,float[]>();
	}

	public final static Points getInstance() {
		if (Points.instance == null) {
			synchronized(Points.class) {
				if (Points.instance == null) {
					Points.instance = new Points();
				}
			}
		}
		return Points.instance;
	}
	
	public void addPoints(HashMap<String,float[]> embeddings) {
		this.embeddings.clear();
		this.embeddings = embeddings;
		
		Dimensions dims = Dimensions.getInstance();
		Iterator<float[]> i = this.embeddings.values().iterator();
		dims.setTotalDimensions(i.next().length);
	}
	
	public HashMap<String,float[]> getEmbeddings () {
		return this.embeddings;
	}
	
	public String getName() {
		return this.name;
	}
	
	public JSONObject toJSON() throws JSONException {
		Dimensions dims = Dimensions.getInstance();
		
		JSONObject jsonPoints = new JSONObject();
		//JSONArray x = new JSONArray();
		//JSONArray y = new JSONArray();
		JSONArray name = new JSONArray();
		
		// Get embeddings names
		for(Entry<String, float[]> entry : this.embeddings.entrySet()) {
			name.put(entry.getKey());
			//x.put(entry.getValue()[0]);
			//y.put(entry.getValue()[1]);
		}
		jsonPoints.put("name", name);
		
		// Get embeddings coordonates
		for (int i=0; i < dims.getTotalDimensions(); i++) {
			JSONArray coord = new JSONArray();
			for(Entry<String, float[]> entry : this.embeddings.entrySet()) {
				coord.put(entry.getValue()[i]);
			}
			jsonPoints.put(Integer.toString(i), coord);
		}
		
		//jsonPoints.put("x", x);
		//jsonPoints.put("y", y);
		
		/*for(Entry<String, float[]> entry : this.embeddings.entrySet()) {
			jsonPoints.put(entry.getKey(), entry.getValue());
		}*/
		return jsonPoints;
	}

	@Override
	public String toString() {
		return "Points [name=" + name + ", points=" + embeddings + "]";
	}
}
