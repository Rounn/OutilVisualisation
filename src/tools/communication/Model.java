package tools.communication;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.Env;
import mlp.EmbeddingsModel;
import propagationModels.MLPproj;

public class Model {
	
	private static volatile Model instance = null;

	private EmbeddingsModel mod;

	private Model() {
		super();
		
		Env.setVerbose(0);                          
		
		MLPproj mlp=new MLPproj(""); 
		
		//mlp.setOptions(this.hargs);
		//this.hargs = new HashMap<String, String>();
		
		this.mod=mlp;
	}

	public final static Model getInstance() {
		if (Model.instance == null) {
			synchronized(Model.class) {
				if (Model.instance == null) {
					Model.instance = new Model();
				}
			}
		}
		return Model.instance;
	}

	
	public EmbeddingsModel getMod() {
		return mod;
	}
	
	public JSONObject getModelOptions() throws JSONException {
		JSONObject obj = new JSONObject();
		HashMap<String, String[]> hargs = this.mod.getOptions();
		for(Entry<String, String[]> entry : hargs.entrySet()) {
			JSONArray array = new JSONArray();
			for(String val:entry.getValue())
				array.put(val);
			obj.put(entry.getKey(), array);
		}
		return obj;
	}
	
	public void setModel(HashMap<String,String> hargs) {
		this.getMod().setOptions(hargs);
	}
}
