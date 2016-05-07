package services;

import org.json.JSONException;
import org.json.JSONObject;

public class TestServices {
	
	/*public static String writeTest () {
		return "Test";
	}*/
	
	public static JSONObject jsonTest() throws JSONException {
		 JSONObject obj = new JSONObject();
		 obj.put("name", "roun");
		 System.out.println("in service");
		 return obj;

	}

}
