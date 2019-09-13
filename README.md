# Nice Color Palettes for Processing
A JSON of the top color palettes on [ColourLovers.com](https://www.colourlovers.com) for Processing. Inspired by [nice-color-palettes](https://github.com/Jam3/nice-color-palettes).

![nice-color-palettes](web/nice-color-palette.png)

## How to use it
[Download](https://github.com/federico-pepe/nice-color-palettes/releases) the library and install it in the sketchbook folder inside the folder name *libraries*. If you have any trouble [read this](https://github.com/processing/processing/wiki/How-to-Install-a-Contributed-Library).

Import the library to a new sketch:
```processing
import nice.palettes.*;
```
Create an object **ColorPalette**
```processing
ColorPalette palette;
```
In the `void setup()` initialize the object
```processing
palette = new ColorPalette(this);
```

### Get a random color palette
The function `getPalette()` without parameters returns an array of five different colors that work well together.
```processing
printArray(palette.getPalette());
```

### Get a chosen color palette
When you are using a random color palette you'll see a message in the console **Random Seed: 8**. If you like that color palette you can recall those color by setting the parameter inside the function `getPalette()`.
```processing
printArray(palette.getPalette(8));
```
If the parameter is invalid an error message will be displayed in the console and a random palette will be chosen.

### Get the colors in a palette
You can get the colors of a palette directly.
```processing
palette.colors[0];
```
Remember that the array only contains **five** colors so `palette.colors[5];` will return an error.

You can also assign the colors to a variable:
```processing
colors[] c = palette.getPalette();
printArray(c);
```

### Download a new JSON from ColourLovers
You can download an updated version of the top palettes on ColourLovers by calling the function `refresh()`. The JSON file will be downloaded into the sketch's data folder and saved with the name **nice-color-palettes.json**.
```processing
palette.refresh();
```
By default the function `refresh()` will download 20 results. But you use a parameter if you need more results (max 100) `palette.refresh(100)`.
