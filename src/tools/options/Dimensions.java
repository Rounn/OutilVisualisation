package tools.options;

import org.json.JSONException;
import org.json.JSONObject;

public class Dimensions {

	private static volatile Dimensions instance = null;

	private final String name;
	private int x; // number of the dimension chosen as X axis
	private int y; // number of the dimension chosen as Y axis
	private int totalDimensions = 0; // total number of dimensions

	private Dimensions() {
		super();
		this.x = 1; // default
		this.y = 2; // default
		this.name = "Dimensions";
		this.totalDimensions = 0;
	}

	public final static Dimensions getInstance() {
		if (Dimensions.instance == null) {
			synchronized(Dimensions.class) {
				if (Dimensions.instance == null) {
					Dimensions.instance = new Dimensions();
				}
			}
		}
		return Dimensions.instance;
	}
	
	public void setTotalDimensions(int totalDimensions) {
		this.totalDimensions = totalDimensions;
	}
	
	public int getTotalDimensions() {
		return this.totalDimensions;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public JSONObject toJSON () throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("x", this.x);
		obj.put("y", this.y);
		obj.put("totalDimensions", this.totalDimensions);
		return obj;		
	}
	@Override
	public String toString() {
		return "Dimensions [name=" + name + ", x=" + x + ", y=" + y + ", totalDimensions=" + totalDimensions + "]";
	}	
}
