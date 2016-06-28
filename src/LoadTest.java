import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

import tools.Step;

public final class LoadTest {
	public static String readFile(String path, Charset encoding)throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	
	public static void main(String[] args) throws IOException, JSONException {
		//Charset cs = new 
		/*try {
			String str = readFile("/Users/Rani/Desktop/PLDACSave/Step170.json", Charset.forName("UTF-8"));
			System.out.println(str);
			JSONObject obj = new JSONObject(str);
			System.out.println(obj);
			//System.out.println(obj.get("name"));
		} catch (IOException|JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Step curr = Step.getInstance();
		curr.load("1");
		
		System.out.println(curr.toJSON());
		
	}
}


