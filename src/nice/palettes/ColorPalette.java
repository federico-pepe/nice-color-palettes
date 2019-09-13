package nice.palettes;

import processing.core.*;
import processing.data.*;
import java.io.*;

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
		
		System.out.println("Random seed: " + random);
		
		for(int i = 0; i < palette.size(); i++) {
			int c = PApplet.unhex("FF" + palette.getString(i));
			values = PApplet.append(values, c);
		}
		colors = values;
		return values;
	}
	
	/**
	 * return an array with a color palette defined by the user and based 
	 * on the size of the JSON. To avoid errors, if the parameter is larger 
	 * than the JSON's size, it will display an error in the console and pick a random number.
	 * 
	 * @param i number of the palette to load, maximum the JSON's size
	 * @return int[]
	 * @example ex03_getSpecificPalette
	 */
	public int[] getPalette(int i) {
		
		if(i >= json.size()) {
			System.out.println("There are only "+json.size()+" palettes available.\nTo avoid errors, a random palette has been chosen for you");
			i = PApplet.parseInt(myParent.random(json.size()));
		}
		
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
		JSONArray palettes = myParent.loadJSONArray("http://www.colourlovers.com/api/palettes/top?format=json");
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
		JSONArray palettes = myParent.loadJSONArray("http://www.colourlovers.com/api/palettes/top?format=json&numResults="+i);
		myParent.saveJSONArray(palettes, myParent.dataPath("nice-color-palettes.json"));
	}
	
	/**
	 * print a welcome message in the console when the library is loaded in the sketch
	 * 
	 */
	private void welcome() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author##\n");
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

