package tools;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;

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
		this.uri = /*"../RepServeur/PLDAC";*/   "/Users/Rani/Desktop/PLDACSave/";

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

	public String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, Charset.forName("UTF-8"));
	}

	public void load(String path) throws IOException, JSONException {
		JSONObject obj = new JSONObject(this.readFile(path));

		//Nbr steps
		this.ns.setSteps(obj.getInt("nbrSteps"));

		//Embeddings
		JSONObject jsonPoints = obj.getJSONObject("points");
		HashMap<String, float[]> embs = new HashMap<String, float[]>();
		Iterator<?> keys = jsonPoints.keys();
		while( keys.hasNext() ) {
			String key = (String)keys.next();
			if ( jsonPoints.get(key) instanceof JSONObject ) {
				embs.put(key, (float[]) jsonPoints.get(key));
			}
		}

		//Dimensions
		this.dims.setX(obj.getJSONObject("dimensions").getInt("x"));
		this.dims.setY(obj.getJSONObject("dimensions").getInt("y"));
		this.dims.setTotalDimensions(obj.getJSONObject("dimensions").getInt("totalDimensions"));

		//Losses
		this.losses.setLosses(obj.getJSONObject("losses").getDouble("loss"), obj.getJSONObject("losses").getDouble("loss"));

		//Lines
		this.line.setLine(obj.getJSONObject("line").getDouble("line"));
		this.line.setDecreasingFactor(obj.getJSONObject("line").getDouble("decreasingFactor"));

		//Attractivities
		JSONObject jsonattrs = obj.getJSONObject("attractivities").getJSONObject("Attractivities");
		HashMap<String, Float> att = new HashMap<String, Float>();
		Iterator<?> keys2 = jsonattrs.keys();
		while( keys2.hasNext() ) {
			String key = (String)keys2.next();
			if ( jsonattrs.get(key) instanceof JSONObject ) {
				att.put(key, Float.valueOf(jsonattrs.getString(key)));
			}
		}
		this.attrs.setReferer(obj.getJSONObject("attractivities").getString("referrer"));
		this.attrs.setAttractivities(att);

		//Similarities
		JSONObject jsonsims = obj.getJSONObject("similarities").getJSONObject("Similarities");
		HashMap<String, Float> sim = new HashMap<String, Float>();
		Iterator<?> keyssims = jsonsims.keys();
		while( keyssims.hasNext() ) {
			String key = (String)keyssims.next();
			if ( jsonsims.get(key) instanceof JSONObject ) {
				att.put(key, Float.valueOf(jsonsims.getString(key)));
			}
		}
		this.sims.setReferer(obj.getJSONObject("similarities").getString("referrer"));
		this.sims.setSimilarities(sim);
		
		//Weighted points
		JSONObject jsonwp = obj.getJSONObject("weightedPoints");
		HashMap<String, Float> weip = new HashMap<String, Float>();
		Iterator<?> keyswp = jsonwp.keys();
		while( keyswp.hasNext() ) {
			String key = (String)keyswp.next();
			if ( jsonwp.get(key) instanceof JSONObject ) {
				weip.put(key, Float.valueOf(jsonwp.getString(key)));
			}
		}
		this.pw.addWeights(weip);
		
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
		step.put("attractivities", attractivities);
		step.put("similarities", similarities);
		step.put("losses", losses);
		step.put("line", line);

		return step;
	}

	public void save () throws IOException, JSONException {
		FileWriter file = new FileWriter(this.uri+this.name+this.id+".json");
		file.write(this.toJSON().toString());
		file.close();
	}
}
