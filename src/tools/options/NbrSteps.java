package tools.options;

import org.json.JSONException;
import org.json.JSONObject;

public class NbrSteps {
	private static volatile NbrSteps instance = null;

	private final String name;
	private Integer steps;
	private int totalStepsDone;

	private NbrSteps() {
		super();
		this.name = "NbrSteps";
		this.steps = 1; // 1 step by default
		this.totalStepsDone = 0;
	}

	public final static NbrSteps getInstance() {
		if (NbrSteps.instance == null) {
			synchronized(NbrSteps.class) {
				if (NbrSteps.instance == null) {
					NbrSteps.instance = new NbrSteps();
				}
			}
		}
		return NbrSteps.instance;
	}
	
	public void setSteps (int steps) {
		this.steps=steps;
	}
	
	public Integer getSteps () {
		return this.steps;
	}
	
	public Integer getTotalStepsDone () {
		return this.totalStepsDone;
	}
	
	public void incrementTotalStepsDone() {
		this.totalStepsDone += this.steps;
	}
	
	public String getName() {
		return this.name;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		
		obj.put("nbrSteps", this.steps);
		return obj;
	}

	@Override
	public String toString() {
		return "NbrSteps [name=" + name + ", steps=" + steps + "]";
	}
}
