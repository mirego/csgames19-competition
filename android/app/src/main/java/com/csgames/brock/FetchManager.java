import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import com.google.gson.Gson;
import ServeResponse.Recipe;
import ServeResponse.DrinkReview;

public class FetchManager {
	
	public static void main(String args[]) {
		Ingredients e = getIngredients();
		System.out.println(e.getJuices().size());
		System.out.println(e.getAlcohols().size());
		System.out.println(e.getDrinks().size());
		System.out.println(e.getIngredients().size());
		for (int i = 0; i < e.getJuices().size(); i++) {
			
			System.out.println(e.getJuices().get(i).color);
		}
		
	}
	
	/*
	 * Gets all the ingredients.
	 * 
	 * AUTHENTICATION WORKS.
	 */
	public static Ingredients getIngredients() {
		URL url;
		UUID uuid = UUID.randomUUID();
		StringBuilder sb = new StringBuilder();
		String key = uuid.toString();
		try {
			url = new URL("https://mirego-csgames19.herokuapp.com/ingredients?key=" + key);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Team", "Brock University");
			String s = "csgames19-";
			Date date = new Date();
			long epocTime = date.getTime(); //epoc time in mins.
			long epocMins = epocTime/1000/60;
			System.out.println(epocTime);
			String auth = s + epocMins + "-" + key;
			
			auth = encryptThisString(auth);
			System.out.println(auth);
			con.setRequestProperty("Authorization", auth);
			

			// read the response.
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			System.out.println(content.toString());
			Gson g = new Gson();
			Ingredients ingredients = g.fromJson(content.toString(), Ingredients.class);
			return ingredients;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * POST.
	 */
	public static DrinkReview postServe(ArrayList<Recipe> recipes) {
		URL url;
		Gson g = new Gson();
		try {
			url = new URL("https://mirego-csgames19.herokuapp.com/serve");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type","application/json");  
			con.setRequestProperty("Team", "Brock University");

			// set body
			String data = g.toJson(recipes);
			byte[] outputInBytes = data.getBytes("UTF-8");
			con.setDoOutput(true);
			OutputStream os = con.getOutputStream();
			os.write(outputInBytes);
			os.close();

			// read the response.
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			
			System.out.println(content.toString());

			DrinkReview drinkReview = g.fromJson(content.toString(), DrinkReview.class);
			return drinkReview;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//----------- stolen code.
	public static String encryptThisString(String input) 
    { 
        try { 
            MessageDigest md = MessageDigest.getInstance("SHA-1"); 
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger no = new BigInteger(1, messageDigest); 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        } 
  
        // For specifying wrong message digest algorithms 
        catch (Exception e) { 
            throw new RuntimeException(e); 
        } 
    } 
}
