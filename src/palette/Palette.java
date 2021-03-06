package palette;

import java.util.ArrayList;
import java.util.List;

import munsell.MunsellColor;

public class Palette
{
	private List<MunsellColor> colorList;
	
	/**
	 * Default constructor.
	 */
	public Palette()
	{
		colorList = new ArrayList<MunsellColor>();
		
	}
	
	/**
	 * Constructor that takes in a list of MunsellColors.
	 * @param list MunsellColor list
	 */
	public Palette(List<MunsellColor> list)
	{
		this.colorList = list;
		
	}
	
	/**
	 * Returns an array of Munsell colors. Used for color mixing.
	 * @param targetColor Target color
	 * @return An array of Munsell colors
	 */
	public MunsellColor[] getComponentsForTargetColor(MunsellColor targetColor) 
	{
		return null;
	}

	/**
	 * Get color list.
	 * @return The colorList in use
	 */
	public List<MunsellColor> getColorList()
	{
		return colorList;
	}

	/**
	 * Set color list.
	 * @param colorList The colorlist to be set
	 */
	public void setColorList(List<MunsellColor> colorList)
	{
		this.colorList = colorList;
	}
	
	/**
	 * Empties the user palette of colors.
	 */
	public void emptyPalette()
	{
		colorList.clear();
	}
	
	/**
	 * Adds a Munsell color to the palette.
	 * @param munColorToAdd Color to add 
	 */
	public void addColor(MunsellColor munColorToAdd) 
	{
		colorList.add(munColorToAdd);
	}
	
	/**
	 * Deletes the first instance of a color.
	 * @param munColorToDelete Color to delete
	 */
	public void removeFirstColor(MunsellColor munColorToDelete) 
	{
		colorList.remove(munColorToDelete);
	}
	
	/**
	 * Deletes a color at a specific index.
	 * @param index Color to delete
	 * @return MunsellColor 
	 */
	public MunsellColor removeSpecificColor(int index) 
	{
		return colorList.remove(index);
	}

}
