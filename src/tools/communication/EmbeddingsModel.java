package tools.communication;

import java.util.HashMap;

/**
 * An abstract mlp model defining embeddings of points in a given space.
 * 
 * @author sylvain lamprier
 *
 */
public interface EmbeddingsModel {

	
	
	
	
	/**
	 * Returns the options of the model. 
	 * For each entry, if the corresponding list contains only one value, it corresponds to the default value for an open field. 
	 * If it contains several values, it is the set of possible values for a selection menu.   
	 */
	public HashMap<String, String[]> getOptions();
	
	/**
	 * Sets the options of the model and initialize the model for learning.
	 * Needs to be called before learning to initialize everything. 
	 **/
	public void setOptions(HashMap<String, String> options);
	
	
	/**
	 * Returns a map containing the embeddings of the model (indexed by the name of the point).
	 *
	 */
	public HashMap<String,float[]> getEmbeddings(); 
	
	/**
	 * Allows to give importance weights to some points.   
	 * Default weights : 1 for everybody 
	 * When using this method with a subset of points, weights of all other points are set to 1.
	 * @param  weights weights to give to the corresponding points
	 * 
	 */
	public void setWeights(HashMap<String,Float> weights);
	
	
	
	/**
	 * Launch the optimization of the model for nb steps.
	 * @param nb number of optimization steps to perform.
	 */
	public void optimizeNext(int nb);
	
	
	/**
	 * Returns the loss estimated by averaging all losses computed from the begining of the optimization.
	 * 
	 */
	public double getGlobalLoss();
	
	/**
	 * Returns the loss estimated by averaging all losses computed from the last call to the reinitLoss function (or the begining of the optimization if no reinit).
	 * 
	 */
	public double getLoss();
	
	
	/**
	 * Allows to reinit the estimated loss. 
	 */
	public void reinitLoss();
	
	
	
	
	/**
	 * Returns the similarities of points with the referer point whose name in given as parameter.
	 * @param referer point
	 * @return sims
	 */
	public HashMap<String,Float> getSims(String referer);
	
	/**
	 * Returns the tendency of attractivity / repulsion effect points have on the referer point whose name in given as parameter.
	 * @param referer point
	 * @return map containing for each point its attractivity level on the referer : values between -1 (high level of repulsion) and 1 (high level of attractivity).
	 */
	public HashMap<String,Float> getAttractivities(String referer);
	
	
	/**
	 * Allows to set a line parameter used by the optimizing process. 
	 * @param line
	 */
	public void setLine(double line);
	
	
	/**
	 * Returns the lastLine that has been applied on the data
	 */
	public double getLastLine();
	
	
	/**
	* Allows to set a line decreasing factor (at each learning step, the line parameter is multiplied by this factor).
	* @param dec a decreasing factor in ]0;1]
	*/
	public void setLineDecreasingFactor(double dec);
	
	
	///**
	// * Allows to ask to the optimizer to ignore the concerning point during a given number of learning steps.
	// * @param name
	// * @param nb
	// */
	//public void setDiscard(String name, int nb);
	
	
	
	
	///**
	// * Returns the table of discards points
	// */
	//public HashMap<String,Integer> getDiscards();
}