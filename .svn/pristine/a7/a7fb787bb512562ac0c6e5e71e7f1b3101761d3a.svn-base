package paletteTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import munsell.MunsellColor;
import palette.Palette;

/**
 * Collection of tests for Palette.java.
 * @author alanschex
 *
 */
class PaletteTests
{
	// All of these are initialized in setUp
	Palette emptyPalette;
	Palette paletteLength1;
	Palette longPalette;
	ArrayList<MunsellColor> munColorList;
	ArrayList<MunsellColor> longMunColorList;
	MunsellColor munColor;

	/**
	 * @throws Exception exception
	 */
	@BeforeEach
	void setUp() throws Exception
	{
		
		// Sets up a MunsellColor object, an empty palette,
		// and a second palette that contains that MunsellColor object
		emptyPalette = new Palette(); // empty palette
		
		// populates a munsell color arraylist of length 1
		munColorList = new ArrayList<MunsellColor>();
		munColor = new MunsellColor(0, 0, 0);
		munColorList.add(munColor);
		paletteLength1 = new Palette(munColorList);
		
		longMunColorList = new ArrayList<MunsellColor>();
		for (int i = 0; i <= 9; i++)
		{
			longMunColorList.add(new MunsellColor(i, i, i));
			
		}
		longPalette = new Palette(longMunColorList);
		
		
	}

	/**
	 * @throws Exception exception
	 */
	@AfterEach
	void tearDown() throws Exception
	{
		
	}

	/***/
	@Test
	void testPalette()
	{
		assertNotNull(emptyPalette);
	}
	
	/***/
	@Test
	void testPaletteList()
	{
		assertEquals(1, paletteLength1.getColorList().size());
		assertEquals(munColor, paletteLength1.getColorList().get(0));
	}
	
	/***/
	@Test
	void testGetColorList1()
	{
		ArrayList<MunsellColor> testList = new ArrayList<MunsellColor>();
		testList.add(munColor);
		assertEquals(testList, paletteLength1.getColorList());
	}
	
	/***/
	@Test
	void testGetColorList2()
	{
		paletteLength1.addColor(munColor);
		ArrayList<MunsellColor> testList = new ArrayList<MunsellColor>();
		testList.add(munColor);
		testList.add(munColor);
		assertEquals(testList, paletteLength1.getColorList());
	}
	
	/***/
	@Test
	void testSetColorList()
	{
		// builds a list of length 2 to be set
		ArrayList<MunsellColor> testList = new ArrayList<MunsellColor>();
		testList.add(munColor);
		MunsellColor munColor2 = new MunsellColor(2, 2, 2);
		testList.add(munColor2);
		
		emptyPalette.setColorList(testList);
		assertEquals(testList, emptyPalette.getColorList());
		assertEquals(2, emptyPalette.getColorList().size());
		
	}
	
	/***/
	@Test
	void testEmptyPalette()
	{
		paletteLength1.emptyPalette();
		assertEquals(0, paletteLength1.getColorList().size());
		
	}
	
	/***/
	@Test
	void testAddColor()
	{
		emptyPalette.addColor(munColor);
		assertEquals(1, emptyPalette.getColorList().size());
		assertEquals(munColor, emptyPalette.getColorList().get(0));
		
		
	}
	
	/***/
	@Test
	void testRemoveFirstColor()
	{
		// remove from list of size 1
		paletteLength1.removeFirstColor(munColor);
		assertEquals(0, paletteLength1.getColorList().size());
		
	}
	
	/***/
	@Test
	void testRemoveSpecificColor()
	{
		munColor = longPalette.removeSpecificColor(2);
		assertEquals(9, longPalette.getColorList().size());
		assertEquals(new MunsellColor(2,2,2), munColor);
		
		
		
	}

}
