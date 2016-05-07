package tools.options;

import org.json.JSONException;
import org.json.JSONObject;

public class Line {
	
	private static volatile Line instance = null;

	private final String name;
	private double line;
	private double decreasingFactor;

	private Line() {
		super();
		this.name = "Line";
		this.line = 0;
	}

	public final static Line getInstance() {
		if (Line.instance == null) {
			synchronized(Line.class) {
				if (Line.instance == null) {
					Line.instance = new Line();
				}
			}
		}
		return Line.instance;
	}

	public double getLine() {
		return line;
	}

	public void setLine(double line) {
		this.line = line;
	}
	
	public double getDecreasingFactor() {
		return decreasingFactor;
	}

	public void setDecreasingFactor(double decreasingFactor) {
		this.decreasingFactor = decreasingFactor;
	}
	
	public JSONObject toJSON() throws JSONException {
		JSONObject obj = new JSONObject();
		obj.put("line", this.line);
		obj.put("decreasingFactor", this.decreasingFactor);
		return obj;
	}

	@Override
	public String toString() {
		return "Line [name=" + this.name + ", line=" + this.line + ", decreasingFactor=" + this.decreasingFactor + "]";
	}

	

}
