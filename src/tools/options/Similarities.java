package tools.options;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class Similarities {
	
	private static volatile Similarities instance = null;

	private final String name;
	private HashMap<String, Float> similarities;
	private String referer;
	
	private Similarities() {
		super();
		this.name = "Similarities";
		this.similarities = new HashMap<String, Float>();
		this.referer = new String();
		
	}

	public final static Similarities getInstance() {
		if (Similarities.instance == null) {
			synchronized(Similarities.class) {
				if (Similarities.instance == null) {
					Similarities.instance = new Similarities();
				}
			}
		}
		return Similarities.instance;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}
	
	public HashMap<String, Float> getSimilarities() {
		return similarities;
	}

	public void setSimilarities(HashMap<String, Float> similarities) {
		this.similarities.clear();
		this.similarities = similarities;
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		JSONObject jsonAttr = new JSONObject();
		
		obj.put("referer", this.referer);
		for(Entry<String, Float> entry : this.similarities.entrySet()) {
			jsonAttr.put(entry.getKey(), entry.getValue());
		}
		obj.put(this.name, jsonAttr);
			
		return obj;
	}

	@Override
	public String toString() {
		return "Similarities [name=" + this.name + ", Similarities=" + this.similarities + ", referer=" + this.referer + "]";
	}
}
