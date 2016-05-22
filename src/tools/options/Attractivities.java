package tools.options;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

public class Attractivities {
	
	private static volatile Attractivities instance = null;

	private final String name;
	private HashMap<String, Float> attractivities;
	private String referer;
	
	private Attractivities() {
		super();
		this.name = "Attractivities";
		this.attractivities = new HashMap<String, Float>();
		this.referer = new String();
		
	}

	public final static Attractivities getInstance() {
		if (Attractivities.instance == null) {
			synchronized(Attractivities.class) {
				if (Attractivities.instance == null) {
					Attractivities.instance = new Attractivities();
				}
			}
		}
		return Attractivities.instance;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}
	
	public HashMap<String, Float> getAttractivities() {
		return attractivities;
	}

	public void setAttractivities(HashMap<String, Float> attractivities) {
		this.attractivities.clear();
		this.attractivities = attractivities;
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		JSONObject jsonAttr = new JSONObject();
		
		obj.put("referrer", this.referer);
		for(Entry<String, Float> entry : this.attractivities.entrySet()) {
			jsonAttr.put(entry.getKey(), entry.getValue());
		}
		obj.put(this.name, jsonAttr);
			
		return obj;
	}

	@Override
	public String toString() {
		return "Attractivities [name=" + this.name + ", attractivities=" + this.attractivities + ", referer=" + this.referer + "]";
	}
}
