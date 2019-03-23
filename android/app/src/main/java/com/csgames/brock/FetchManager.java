import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class FetchManager {

	public static Ingredients getIngredients() {
		URL url;
		try {
			url = new URL("https://mirego-csgames19.herokuapp.com/ingredients");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Team", "Brock University");

			// read the response.
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			Gson g = new Gson();
			Ingredients ingredients = g.fromJson(content.toString(), Ingredients.class);
			return ingredients;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		getIngredients();
	}
}
