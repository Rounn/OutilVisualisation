package tools.options;

import org.json.JSONException;
import org.json.JSONObject;

public class Losses {

	private static volatile Losses instance = null;

	private final String name;
	private double loss;
	private double globalLoss;

	private Losses() {
		super();
		this.name = "Losses";
		this.loss = 0;
		this.globalLoss = 0;		
	}

	public final static Losses getInstance() {
		if (Losses.instance == null) {
			synchronized(Losses.class) {
				if (Losses.instance == null) {
					Losses.instance = new Losses();
				}
			}
		}
		return Losses.instance;
	}

	public double getLoss() {
		return loss;
	}

	public void setLoss(double loss) {
		this.loss = loss;
	}

	public double getGlobalLoss() {
		return globalLoss;
	}

	public void setGlobalLoss(double globalLoss) {
		this.globalLoss = globalLoss;
	}
	
	public JSONObject toJSON() throws JSONException{
		JSONObject obj = new JSONObject();
		obj.put("loss", this.loss);
		obj.put("globalLoss", this.globalLoss);
		return obj;
	}
	
	public void setLosses(double loss, double globalLoss) {
		this.loss = loss;
		this.globalLoss = globalLoss;
	}

	@Override
	public String toString() {
		return "Losses [name=" + name + ", loss=" + loss + ", globalLoss=" + globalLoss + "]";
	}
	
	
}
