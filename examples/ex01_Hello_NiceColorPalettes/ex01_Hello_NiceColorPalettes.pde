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
  size(500, 500);
  noStroke();
  // Initialize it, passing a reference to the current PApplet 
  palette = new ColorPalette(this);
  
  // Call the getPalette() method of the palette object
  // to load five different colors randomly
  palette.getPalette();
  
  for(int i = 0; i < 5; i++) {
    // Access che colors in the palette
    fill(palette.colors[i]);
    rect(i * width/5, 0, width/5, height);
  }
  
  noLoop();
}

void draw() {
}
