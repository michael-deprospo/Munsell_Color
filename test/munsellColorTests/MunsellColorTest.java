package munsellColorTests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import munsell.HueCode;

import org.junit.jupiter.api.Test;
import munsell.MunsellColor;

/**
 * 
 * @author Michael DeProspo
 *
 */
class MunsellColorTest
{

	MunsellColor buns;
	Color rgb = new Color(255, 100, 0);

	float value = 13;
	float hue = 115;
	float chroma = 20;
	HueCode hueCode = HueCode.B;
	MunsellColor muns = new MunsellColor(hue, hueCode, value, chroma);

	/**
	 * Tests creating color with rgb.
	 */
	@Test
	final void testMunsellColorColor()
	{
		buns = new MunsellColor(rgb);
		assertEquals(buns.getColor(), rgb);
	}

	/**
	 * 
	 */
	@Test
	final void testMunsellColor()
	{
		buns = new MunsellColor();
		assertNotNull(buns);
	}

	/**
	 * 
	 */
	@Test
	final void testMunsellColorFloatHueCodeFloatFloat()
	{

		hue = 100;
		value = 10;
		assertEquals(hue, muns.getHueNum());
		assertEquals(HueCode.B, muns.getHueCode());
		assertEquals(value, muns.getValue());
		assertEquals(20, muns.getChroma());
	}

	/**
	 * 
	 */
	@Test
	final void testGetMunNotation()
	{
		assertEquals("100.0B-10.0-20.0", muns.getMunNotation());
	}

	/**
	 * 
	 */
	@Test
	final void testGetValue()
	{
		assertEquals(10, muns.getValue());
	}

	/**
	 * 
	 */
	@Test
	final void testGetChroma()
	{
		assertEquals(20, muns.getChroma());
	}

	/**
	 * 
	 */
	@Test
	final void testGetColor()
	{
		buns = new MunsellColor(rgb);
		assertEquals(rgb, buns.getColor());
	}

	/**
	 * 
	 */
	@Test
	final void testGetHueNum()
	{
		assertEquals(100, muns.getHueNum());
	}

	/**
	 * 
	 */
	@Test
	final void testMunColor()
	{
		assertEquals("Hue: 100.0B, Value: N10.0, Chroma: 20.0", muns.munColor());
	}

	/**
	 * 
	 */
	@Test
	final void testSetColor()
	{
		Color testColor = new Color(1, 1, 1);
		muns.setColor(testColor);
		assertEquals(testColor, muns.getColor());
	}

	/**
	 * 
	 */
	@Test
	final void testSetValue()
	{
		value = 0;
		muns.setValue(-1);
		assertEquals(value, muns.getValue());
		value = 5;
		muns.setValue(value);
		assertEquals(value, muns.getValue());
	}

	/**
	 * 
	 */
	@Test
	final void testSetChroma()
	{
		chroma = 0;
		muns.setChroma(-1);
		assertEquals(chroma, muns.getChroma());
	}

	/**
	 * 
	 */
	@Test
	final void testSetHueCode()
	{
		muns.setHueCode(HueCode.RP);
		assertEquals(HueCode.RP, muns.getHueCode());
	}

	/**
	 * 
	 */
	@Test
	final void testSetHueNum()
	{
		hue = 0;

		muns.setHueNum(-1);
		assertEquals(hue, muns.getHueNum());
		hue = 50;
		muns.setHueNum(hue);
		assertEquals(hue, muns.getHueNum());
	}

	/**
	 * 
	 */
	@Test
	void testHashEquality()
	{
		MunsellColor color = new MunsellColor();
		MunsellColor color2 = new MunsellColor();

		assertEquals(color.hashCode(), color2.hashCode());

	}

	/**
	 * 
	 */
	@Test
	void testEqualsEquality()
	{
		MunsellColor color = new MunsellColor();
		MunsellColor color2 = new MunsellColor();

		assertEquals(color.getHueNum(), color2.getHueNum());
		assertEquals(color.getHueCode(), color2.getHueCode());
		assertEquals(color.getValue(), color2.getValue());
		assertEquals(color.getChroma(), color2.getChroma());
		assertEquals(true, color.equals(color2));
	}

	/**
	 * 
	 */
	@Test
	void testEqualsEqualityFailHue()
	{
		MunsellColor color = new MunsellColor();
		MunsellColor color2 = new MunsellColor();

		color2.setHueNum(1.0f);
		assertEquals(false, color.equals(color2));
	}

	/**
	 * 
	 */
	@Test
	void testEqualsEqualityFailValue()
	{
		MunsellColor color = new MunsellColor();
		MunsellColor color2 = new MunsellColor();

		color2.setValue(1.0f);
		assertEquals(false, color.equals(color2));
	}

	/**
	 * 
	 */
	@Test
	void testEqualsEqualityFailChroma()
	{
		MunsellColor color = new MunsellColor();
		MunsellColor color2 = new MunsellColor();

		color2.setChroma(1.0f);
		assertEquals(false, color.equals(color2));
	}

	/**
	 * 
	 */
	@Test
	void testEqualsEqualityFailCode()
	{
		MunsellColor color = new MunsellColor();
		MunsellColor color2 = new MunsellColor();

		color2.setHueCode(HueCode.G);
		assertEquals(false, color.equals(color2));
	}

	/**
	 * 
	 */
	@Test
	void testEqualsEqualityFailObject()
	{
		MunsellColor color = new MunsellColor();
		Color color2 = new Color(0, 0, 0);

		assertEquals(false, color.equals(color2));
	}

	/**
	 * 
	 */
	@Test
	void testEqualsEqualityFailNull()
	{
		MunsellColor color = new MunsellColor();
		MunsellColor color2 = null;

		assertEquals(false, color.equals(color2));
	}

	/**
	 * 
	 */
	@Test
	void matchHueCodetestBasic()
	{
		assertEquals(HueCode.B, MunsellColor.matchHueCode(65.5f));
	}

	/**
	 * 
	 */
	@Test
	void matchHueCodeLowerBoundZero()
	{
		assertEquals(HueCode.RP, MunsellColor.matchHueCode(0.0f));
	}

	/**
	 * 
	 */
	@Test
	void matchHueCodeUpperBoundHundred()
	{
		assertEquals(HueCode.RP, MunsellColor.matchHueCode(100.0f));
	}

	/**
	 * 
	 */
	@Test
	void matchHueCodeUpperBoundBeyond()
	{
		assertEquals(HueCode.RP, MunsellColor.matchHueCode(101.0f));
	}

	/**
	 * 
	 */
	@Test
	void matchHueCodeLowerBoundNegative()
	{
		assertEquals(HueCode.RP, MunsellColor.matchHueCode(-1.0f));
	}

	/**
	 * 
	 */
	@Test
	void matchHueCodeR10()
	{
		assertEquals(HueCode.R, MunsellColor.matchHueCode(10.0f));
	}

	/**
	 * 
	 */
	@Test
	void matchHueCodeYR10Point1()
	{
		assertEquals(HueCode.YR, MunsellColor.matchHueCode(10.1f));
	}

	/**
	 * 
	 */
	@Test
	void matchHueCodeYR20()
	{
		assertEquals(HueCode.YR, MunsellColor.matchHueCode(20.0f));
	}

	/**
	 * 
	 */
	@Test
	void matchHueCodeB60Point01()
	{
		assertEquals(HueCode.B, MunsellColor.matchHueCode(60.01f));
	}

	@Test
	void mixColors()
	{
		float hue = 20;
		float value = 6;
		float chroma = 5;
		MunsellColor c1 = new MunsellColor(hue, value, chroma);
		MunsellColor c2 = new MunsellColor(hue + 5, value + 1, chroma + 5);
		MunsellColor c3 = c1.mixColors(c1, c2);
		assertEquals(Math.round(c3.getHueNum()), 5);
		assertEquals(c3.getChroma(), 7.0);
		assertEquals(c3.getValue(), 6.5);
	}

	@Test
	void mixColorsHigh()
	{
		float hue = 10;
		float value = 6;
		float chroma = 5;
		MunsellColor c1 = new MunsellColor(hue, value, chroma);
		MunsellColor c2 = new MunsellColor(hue + 40, value + 1, chroma + 45);
		MunsellColor c3 = c1.mixColors(c1, c2);
		assertEquals(c3.getHueNum(), 7.5);
		assertEquals(Math.round(c3.getChroma()), 27);
		assertEquals(c3.getValue(), 6.5);
	}

	@Test
	void MixColorsMedium()
	{
		float hue = 35;
		float value = 3;
		float chroma = 10;
		MunsellColor c1 = new MunsellColor(hue, value, chroma);
		MunsellColor c2 = new MunsellColor(hue + 40, value + 3, chroma + 10);
		MunsellColor c3 = c1.mixColors(c1, c2);
		assertEquals(c3.getHueNum(), 2.5);
		assertEquals(Math.round(c3.getChroma()), 14);
		assertEquals(c3.getValue(), 4.5);
	}
}
