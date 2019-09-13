/**
 * Nice Color Palettes
 * A simple example to demonstrate basic setup and functionality. 
 * 
 * Nice Color Palettes for Processing, by Federico Pepe, 2019
 * https://github.com/federico-pepe/nice-color-palettes
 */
 
 // Import the library
import nice.palettes.*;

// Declare the main ColorPalette object
ColorPalette palette;

void setup() {
  // Initialize it, passing a reference to the current PApplet 
  palette = new ColorPalette(this);
  
  // Download a new color palette's JSON by calling the colourlovers.com API. 
  // A JSONArray will be saved in the sketch's data folder.
  // By default the API will return the 20 top results
  // You can add a parameter if you need more (max 100)
  palette.refresh();
  
  // Print an array of integers of the color palette
  // Calling getPalette() with parameters will always return the same palette
  printArray(palette.getPalette(5));
}

void draw() {
}
