import java.util.Arrays;
import java.util.HashMap;

import core.Env;
import mlp.EmbeddingsModel;
import propagationModels.MLPproj;
public class Test {

	
	public static void main(String[] args){
		
		// Exemple d'initialisation d'un embedding model 
		Env.setVerbose(0);                          
		
		MLPproj mlp=new MLPproj(""); 
		HashMap<String,String> hargs=new HashMap<String,String>();
		hargs.put("database", "tiny");
		hargs.put("collection", "cascades_1");
		hargs.put("nbDims", "2");
		
		mlp.setOptions(hargs);
		
		EmbeddingsModel mod=mlp;
		
		
		// Pour modifier les parametres line et decFactor (permettent de gerer le pas d'apprentissage)
		// Exemples :
		mod.setLine(0.1f);
		mod.setLineDecreasingFactor(0.9999f);
		
		// Exemple d'optimisation du modele pendant 100 x 100 iterations avec affichage du loss a chaque tour de boucle
		for(int i=0;i<100;i++){
			mod.optimizeNext(100);
			System.out.println(mod.getGlobalLoss()+" "+mod.getLoss());
			System.out.println("line = "+mod.getLastLine());
			mod.reinitLoss();
		}
		
		
		
		// Recuperation des embeddings (utile pour afficher les points) :
		HashMap<String,float[]> points=mod.getEmbeddings();
		
		points.forEach((k,v) -> System.out.println(k+":"+Arrays.toString(v)));
		
		
		
		// Pour le reste regardez ce qui est defini dans EmbeddingsModel
		
		
	}
}