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
  
  // Print an array of integers of the color palette
  // Calling getPalette() with parameters will always return the same palette
  printArray(palette.getPalette(5));
}

void draw() {
}
