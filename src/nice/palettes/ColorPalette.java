package nice.palettes;

import processing.core.*;
import processing.data.*;

import java.io.*;
import java.net.*;
import java.util.stream.Collectors;

/**
 * Nice Color Palette
 * 
 * A JSON of the top color palettes on ColourLovers.com for Processing
 * Inspired by https://www.npmjs.com/package/nice-color-palettes
 *
 * @example ex01_Hello_NiceColorPalettes
 */

public class ColorPalette {
	
	// myParent is a reference to the parent sketch
	PApplet myParent;
	
	public final static String VERSION = "##library.prettyVersion##";
	
	JSONArray json;
	JSONArray palette;
	
	public int[] colors;
	
	/**
	 * a Constructor, usually called in the setup() method in your sketch 
	 * to initialize and start the Library. By default it will use the color palettes 
	 * defined in the JSON attached with the library. Is it possibile to call the refresh() 
	 * function to download a new JSON. If the file exists in the data folder of the sketch, 
	 * this library will use it.
	 * 
	 * @param theParent the parent PApplet
	 */
	public ColorPalette(PApplet theParent) {
		
		myParent = theParent;
		
		welcome();
		
		// Check if the user downloaded a new version from the API
		File f = new File(myParent.dataPath("nice-color-palettes.json"));
		
		if(f.exists()) {
			json = myParent.loadJSONArray(myParent.dataPath("nice-color-palettes.json"));
		} else {
			json = myParent.loadJSONArray("data/palettes.json");
		}
	}
	
	/**
	 * return an array with a random color palette
	 * 
	 * @return int[] and array of five different colors in the same palette
	 * @example ex02_getPalette
	 */
	public int[] getPalette() {
		int[] values = new int[0];
		
		int random = PApplet.parseInt(myParent.random(json.size()));
		palette = json.getJSONObject(random).getJSONArray("colors");
				
		for(int i = 0; i < palette.size(); i++) {
			int c = PApplet.unhex("FF" + palette.getString(i));
			values = PApplet.append(values, c);
		}
		colors = values;
		return values;
	}
	
	/**
	 * return an array with a color palette defined by the user and based 
	 * on the size of the JSON.
	 * 
	 * @param i number of the palette to load, maximum the JSON's size
	 * @return int[]
	 * @example ex03_getSpecificPalette
	 */
	public int[] getPalette(int i) {
		
		int[] values = new int[0];
		
		palette = json.getJSONObject(i).getJSONArray("colors");

		for(int x = 0; x < palette.size(); x++) {
			int c = PApplet.unhex("FF" + palette.getString(x));
			values = PApplet.append(values, c);
		}
		
		colors = values;
		
		return values;
		
	}
	
	/**
	 * Download a new color palette's JSON by calling the colourlovers.com API. 
	 * Default: 20 results
	 * 
	 * @example ex04_refreshPalettes
	 */
	public void refresh() {
		JSONArray palettes = request(20);
		myParent.saveJSONArray(palettes, myParent.dataPath("nice-color-palettes.json"));
	}
	
	/**
	 * Download a new color palette's JSON by calling the colourlovers.com API. 
	 * A JSONArray will be saved in the sketch's data folder.
	 * 
	 * @param i Number of results to return from the API, maximum of 100. 
	 * @example ex04_refreshPalettes
	 */
	public void refresh(int i) {
		if(i <= 0 || i > 100) {
			i = 100;
		}
		JSONArray palettes = request(i);
		myParent.saveJSONArray(palettes, myParent.dataPath("nice-color-palettes.json"));
	}
	
	
	/**
	 * Return the number of palettes available
	 * @example  ex05_getPaletteCount
	 */
	public int getPaletteCount() {
		return json.size();
	}
	
	/**
	 * print a welcome message in the console when the library is loaded in the sketch
	 * 
	 */
	private void welcome() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author##\n");
	}
	
	
	/**
	 * 	New function to fix a 403 error while requesting new color palette to colourlovers.com
	 */
	private JSONArray request(int i){  
		
		if(i <= 0 || i > 100) {
			i = 100;
		}
		
		HttpURLConnection connection = null;
	    
		try{ 
	        // Connect to the URL adding user-agent property
	        URL url = new URL("https://www.colourlovers.com/api/palettes/top?format=json&numResults="+i);  
	        connection = (HttpURLConnection)url.openConnection();
	        connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
	        // Buffer Reader for Output
	        BufferedReader ir = new BufferedReader(new InputStreamReader((InputStream) connection.getContent()));        
	        return myParent.parseJSONArray(ir.lines().collect(Collectors.joining()));
	      } catch(Exception e){
	        System.out.println(e);
	        return null;
	      } finally {
	        connection.disconnect();
	      }
	}  
		
	
	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
}

