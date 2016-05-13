package tools.communication;

import java.util.HashMap;

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
		HashMap<String,String> hargs=new HashMap<String,String>();
		hargs.put("database", "tiny");
		hargs.put("collection", "cascades_1");
		hargs.put("nbDims", "2");
		
		mlp.setOptions(hargs);
		
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
}
