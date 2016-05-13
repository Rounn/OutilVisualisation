package tools;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.options.Attractivities;
import tools.options.Dimensions;
import tools.options.Line;
import tools.options.Losses;
import tools.options.NbrSteps;
import tools.options.PointsWeight;
import tools.options.Similarities;

public class Step {
	private static volatile Step instance = null;

	private final String name;
	private int id;
	private String uri;
	private Points points;
	private NbrSteps ns;
	private Dimensions dims;
	private PointsWeight pw;
	private Attractivities attrs;
	private Similarities sims;
	private Losses losses;
	private Line line;
	

	private Step() {
		super();
		this.name = "Step";
		this.id = 0;
		this.points = Points.getInstance();
		this.ns = NbrSteps.getInstance();
		this.dims = Dimensions.getInstance();
		this.pw = PointsWeight.getInstance();
		this.attrs = Attractivities.getInstance();
		this.sims = Similarities.getInstance();
		this.losses = Losses.getInstance();
		this.line = Line.getInstance();
		this.uri = "../RepServeur/PLDAC";  // "/Users/Rani/Desktop/PLDACSave/"
	
	}
	
	
	public void setURI(String uri) {
		this.uri = uri;
	}
	
	public final static Step getInstance() {
		if (Step.instance == null) {
			synchronized(Step.class) {
				if (Step.instance == null) {
					Step.instance = new Step();
				}
			}
		}
		return Step.instance;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void incrementId() {
		this.ns.incrementTotalStepsDone();
		this.id = this.ns.getTotalStepsDone();
	}

	public JSONObject toJSON() throws JSONException {
		JSONObject step = new JSONObject();
		JSONObject points = this.points.toJSON();
		JSONObject nbrSteps = this.ns.toJSON();
		JSONObject weightedPoints = this.pw.toJSON();
		JSONObject dimensions = this.dims.toJSON();
		JSONObject attractivities = this.attrs.toJSON();
		JSONObject similarities = this.sims.toJSON();
		JSONObject losses = this.losses.toJSON();
		JSONObject line = this.line.toJSON();
		
		step.put("id", this.id);
		step.put("points", points);
		step.put("nbrSteps", nbrSteps);
		step.put("dimensions", dimensions);
		step.put("weightedPoints", weightedPoints);
		step.put("attracitivities", attractivities);
		step.put("similarities", similarities);
		step.put("losses", losses);
		step.put("line", line);

		return step;
	}
	
	public void save () throws IOException, JSONException {
		FileWriter file = new FileWriter(this.uri+this.name+this.id+".txt");
		file.write(this.toJSON().toString());
		file.close();
	}
}
