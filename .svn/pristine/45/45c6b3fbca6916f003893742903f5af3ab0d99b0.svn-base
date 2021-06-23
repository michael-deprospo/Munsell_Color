package converterTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.Color;
import java.util.zip.DataFormatException;

import org.junit.jupiter.api.Test;

import conversion.Converter;
import munsell.HueCode;
import munsell.MunsellColor;

/**
 * @author Forrest
 *
 */
class ConverterTest
{

	/**
	 * 
	 */
	@Test
	void initializeConvertertest()
	{
		assertNotNull(Converter.getConverter());

	}

	/**
	 * 
	 */
	@Test
	void singletonTest()
	{

		Converter test = Converter.getConverter();
		assertNotNull(Converter.getConverter());
		assertEquals(test, Converter.getConverter());

	}

	/**
	 * 
	 */
	@Test
	void munsellColorObjectReturn()
	{
		Converter test = Converter.getConverter();

		assertNotNull(test.munsellToRGB(new MunsellColor()));
	}

	// -----------munsellToRGBString-----------------

	/**
	 * 
	 */
	@Test
	void munsellToRGBString()
	{
		Converter test = Converter.getConverter();
		Color color = null;
		try
		{
			assertNotNull(test.munsellToRGB("2.5R-7-12"));
			color = test.munsellToRGB("2.5R-7-12");
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}

		// red
		assertEquals(255, color.getRed());

		// green
		assertEquals(130, color.getGreen());

		// blue
		assertEquals(148, color.getBlue());

	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBStringInvalidStringFAIL()
	{
		Converter test = Converter.getConverter();
		Color color = null;
		try
		{
			color = test.munsellToRGB("2.5R7-12");
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " munsellToRGB");
		}

		assertEquals(null, color);
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBStringFirstCSVEntry()
	{
		Converter test = Converter.getConverter();
		Color color = null;
		try
		{
			color = test.munsellToRGB("2.5R-1-2");
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " munsellToRGB");
		}

		// red
		assertEquals(45, color.getRed());

		// green
		assertEquals(21, color.getGreen());

		// blue
		assertEquals(31, color.getBlue());
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBStringLastCSVEntry()
	{
		Converter test = Converter.getConverter();
		Color color = null;
		try
		{
			color = test.munsellToRGB("10.0RP-10-0");
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " munsellToRGB");
		}

		// red
		assertEquals(253, color.getRed());

		// green
		assertEquals(253, color.getGreen());

		// blue
		assertEquals(253, color.getBlue());
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBStringWhite()
	{
		Converter test = Converter.getConverter();
		Color color = null;
		try
		{
			color = test.munsellToRGB("10.0RP-10-1");
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " munsellToRGB");
		}

		// red
		assertEquals(253, color.getRed());

		// green
		assertEquals(253, color.getGreen());

		// blue
		assertEquals(253, color.getBlue());
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBStringBlack()
	{
		Converter test = Converter.getConverter();
		Color color = null;
		try
		{
			color = test.munsellToRGB("10.0RP-0-2");
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " munsellToRGB");
		}

		// red
		assertEquals(0, color.getRed());

		// green
		assertEquals(0, color.getGreen());

		// blue
		assertEquals(0, color.getBlue());
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBStringBlackAllZeros()
	{
		Converter test = Converter.getConverter();
		Color color = null;
		try
		{
			color = test.munsellToRGB("0.0RP-0-0");
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " munsellToRGB");
		}

		// red
		assertEquals(0, color.getRed());

		// green
		assertEquals(0, color.getGreen());

		// blue
		assertEquals(0, color.getBlue());
	}

	// ------------------munsellStr

	/**
	 * 
	 */
	@Test
	void munsellStrAllCorrect()
	{
		try
		{
			assertEquals("2.5R-7-12", Converter.createMunsellStr(2.5f, HueCode.R, 7f, 12f));
			assertEquals("2.5R-7-12", Converter.createMunsellStr(2.3f, HueCode.R, 7f, 12f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Test
	void munsellStrAllNeedRounding()
	{
		try
		{
			assertEquals("2.5R-7-12", Converter.createMunsellStr(2.38f, HueCode.R, 7.25f, 12.2f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Test
	void munsellStrHue4D9()
	{
		try
		{
			assertEquals("5.0R-7-12", Converter.createMunsellStr(4.9f, HueCode.R, 7.0f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Test
	void munsellStrHue5D0()
	{
		try
		{
			assertEquals("5.0R-7-12", Converter.createMunsellStr(5.0f, HueCode.R, 7.0f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Test
	void munsellStrHue5D1()
	{
		try
		{
			assertEquals("7.5R-7-12", Converter.createMunsellStr(5.1f, HueCode.R, 7.0f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Test
	void munsellStrHue7D5()
	{
		try
		{
			assertEquals("7.5R-7-12", Converter.createMunsellStr(7.5f, HueCode.R, 7.0f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Test
	void munsellStrHue7D6()
	{
		try
		{
			assertEquals("10.0R-7-12", Converter.createMunsellStr(7.6f, HueCode.R, 7.0f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 
	 */
	@Test
	void munsellStrHue10D0()
	{
		try
		{
			assertEquals("10.0R-7-12", Converter.createMunsellStr(10.0f, HueCode.R, 7.0f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}
	}

//    /**
//     * 
//     */
//    @Test
//    void munsellStrChromaNotEven()
//    {
//        try
//        {
//            assertEquals("2.5R-7-12", Converter.createMunsellStr(2.38f, HueCode.R, 7.25f, 13.0f));
//        } catch (DataFormatException e)
//        {
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//    /**
//     * 
//     */
//    @Test
//    void munsellStrChromaNotEven2()
//    {
//        try
//        {
//            assertEquals("2.5R-7-12", Converter.createMunsellStr(2.38f, HueCode.R, 7.25f, 13.49f));
//        } catch (DataFormatException e)
//        {
//            System.out.println(e.getMessage());
//        }
//
//    }

	/**
	 * 
	 */
	@Test
	void munsellStrInvalidHueTooHighFAIL()
	{
		try
		{
			assertEquals("2.5R-7-12", Converter.createMunsellStr(10.1f, HueCode.R, 7.0f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " Hue Too High.");
		}

	}

	/**
	 * 
	 */
	@Test
	void munsellStrInvalidHueTooLowFAIL()
	{
		try
		{
			assertEquals("2.5R-7-12", Converter.createMunsellStr(0.99f, HueCode.R, 7.0f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " Hue Too Low.");
		}

	}

	/**
	 * 
	 */
	@Test
	void munsellStrInvalidValueTooHighFAIL()
	{
		try
		{
			assertEquals("2.5R-7-12", Converter.createMunsellStr(2.5f, HueCode.R, 10.1f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " Value Too High.");
		}

	}

	/**
	 * 
	 */
	@Test
	void munsellStrInvalidValueTooLowFAIL()
	{
		try
		{
			assertEquals("2.5R-7-12", Converter.createMunsellStr(2.5f, HueCode.R, -0.1f, 12.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + " Value Too Low.");
		}

	}

	/**
	 * 
	 */
	@Test
	void munsellStrInvalidChromaZero()
	{
		try
		{
			assertEquals("2.5R-7-0", Converter.createMunsellStr(2.5f, HueCode.R, 7, 0.0f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 */
	@Test
	void munsellStrInvalidChromaTooLowFAIL()
	{
		try
		{
			assertEquals("2.5R-7-0", Converter.createMunsellStr(2.5f, HueCode.R, 7, -0.0001f));
		} catch (DataFormatException e)
		{
			System.out.println(e.getMessage() + "Choma Too Low");
		}

	}

	// ------------------munsellToRGB(MunsellColor munsell)

	/**
	 * 
	 */
	@Test
	void munsellToRGBNull()
	{
		Converter test = Converter.getConverter();
		assertNotNull(test.munsellToRGB(new MunsellColor()));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBNullMunColor()
	{
		Converter test = Converter.getConverter();
		MunsellColor mun = null;
		assertEquals(null, test.munsellToRGB(mun));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGB000Color()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(0, 0, 0);
		MunsellColor mun = new MunsellColor();
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	// this could prove to be a problem
	/**
	 * 
	 */
	@Test
	void munsellToRGB10Hue()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(128, 44, 32);
		MunsellColor mun = new MunsellColor(10.0f, HueCode.R, 3f, 8f);
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBZeroHue()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(128, 44, 32);
		MunsellColor mun = new MunsellColor(0.0f, HueCode.R, 3f, 8f);
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBOutofBoundsHueNegative()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(128, 44, 32);
		MunsellColor mun = new MunsellColor(-0.1f, HueCode.R, 3f, 8f);
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBOutofBoundsHuePositive()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(128, 44, 32);
		MunsellColor mun = new MunsellColor(1000f, HueCode.R, 3f, 8f);
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBFractionalHue()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(129, 39, 61);
		MunsellColor mun = new MunsellColor(2.5f, HueCode.R, 3f, 8f);
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBOutofBoundsChromaHigh()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(0, 0, 0);
		MunsellColor mun = new MunsellColor(2.5f, HueCode.R, 3f, 1000f);
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBOutofBoundsChromaLow()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(0, 0, 0);
		MunsellColor mun = new MunsellColor(2.5f, HueCode.R, 3f, 1000f);
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBOutofBoundsValueHigh()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(255, 255, 255);
		MunsellColor mun = new MunsellColor(2.5f, HueCode.R, 1000f, 8f);
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	/**
	 * 
	 */
	@Test
	void munsellToRGBOutofBoundsValueLow()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(0, 0, 0);
		MunsellColor mun = new MunsellColor(2.5f, HueCode.R, -1.0f, 8f);
//        System.out.println(mun.getValue());
//        try {
//        System.out.println(Converter.createMunsellStr(mun.getHueNum(),
//                mun.getHueCode(), mun.getValue(), mun.getChroma())); 
//        }
//        catch(DataFormatException e) {
//            System.err.println(e.getMessage());
//        }
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	/**
	 * 7.5YR-3-8,7.5YR,YR,7.5,3,8,111,59,0
	 */
	@Test
	void munsellToRGBBlackTest1()
	{
		Converter test = Converter.getConverter();
		Color testColor = new Color(85, 71, 0);
		MunsellColor mun = new MunsellColor(27.5f, 3f, 20f);
		assertEquals(testColor, test.munsellToRGB(mun));
	}

	// -----------------------convertHueNum--------------

	/**
	 * 
	 */
	@Test
	void convertHueNumTestNum()
	{

		float testHue = 35.0f;
		float hueConvert = 5.0f;

		assertEquals(hueConvert, Converter.toTenHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void convertHueNumTestNum10()
	{

		float testHue = 10.0f;
		float hueConvert = 10.0f;

		assertEquals(hueConvert, Converter.toTenHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void convertHueNumTestGreaterThan10()
	{

		float testHue = 10.1f;
		float hueConvert = 10.1f % 10;

		assertEquals(hueConvert, Converter.toTenHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void convertHueNumTestZero()
	{

		float testHue = 0.0f;
		float hueConvert = 10.0f;

		assertEquals(hueConvert, Converter.toTenHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void convertHueNumTestNegative()
	{

		float testHue = -1.0f;
		float hueConvert = 0.0f;

		assertEquals(hueConvert, Converter.toTenHue(testHue));
	}

	// -------------representableHue----------------

	/**
	 * 
	 */
	@Test
	void representableHueTestZero()
	{

		float testHue = 0.0f;
		float hueConvert = 10.0f;

		assertEquals(hueConvert, Converter.getRepresentableHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void representableHueTestNegative()
	{

		float testHue = -1.0f;
		float hueConvert = 2.5f;

		assertEquals(hueConvert, Converter.getRepresentableHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void representableHueTestTen()
	{

		float testHue = 10.0f;
		float hueConvert = 10.0f;

		assertEquals(hueConvert, Converter.getRepresentableHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void representableHueTestGreaterThanTen()
	{

		float testHue = 11.0f;
		float hueConvert = 2.5f;

		assertEquals(hueConvert, Converter.getRepresentableHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void representableHueTestSevenPointFive()
	{

		float testHue = 7.5f;
		float hueConvert = 7.5f;

		assertEquals(hueConvert, Converter.getRepresentableHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void representableHueTestSevenPoint6ix()
	{

		float testHue = 7.6f;
		float hueConvert = 10.0f;

		assertEquals(hueConvert, Converter.getRepresentableHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void representableHueTestFourPointEight()
	{

		float testHue = 4.8f;
		float hueConvert = 5.0f;

		assertEquals(hueConvert, Converter.getRepresentableHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void representableHueTestFive()
	{

		float testHue = 5.0f;
		float hueConvert = 5.0f;

		assertEquals(hueConvert, Converter.getRepresentableHue(testHue));
	}

	/**
	 * 
	 */
	@Test
	void toHundredHueZero()
	{
		float testHue = 0.0f;
		HueCode hueCode = HueCode.R;
		float hueConvert = Converter.HUE_CODE_OFFSET;

		assertEquals(hueConvert, Converter.toHundredHue(hueCode, testHue));
	}

	/**
	 * 
	 */
	@Test
	void toHundredHueB()
	{
		float testHue = 0.0f;
		HueCode hueCode = HueCode.B;
		float hueConvert = 60.0f + Converter.HUE_CODE_OFFSET;

		assertEquals(hueConvert, Converter.toHundredHue(hueCode, testHue));
	}

	/**
	 * 
	 */
	@Test
	void toHundredHueRP()
	{
		float testHue = 10.0f;
		HueCode hueCode = HueCode.RP;
		float hueConvert = 100.0f;

		assertEquals(hueConvert, Converter.toHundredHue(hueCode, testHue));
	}

	/**
	 * 
	 */
	@Test
	void toHundredHueUpperBeyond()
	{
		float testHue = 10.1f;
		HueCode hueCode = HueCode.RP;
		float hueConvert = 100.0f;

		assertEquals(hueConvert, Converter.toHundredHue(hueCode, testHue));
	}

	/**
	 * 
	 */
	@Test
	void toHundredHueNegative()
	{
		float testHue = -10.1f;
		HueCode hueCode = HueCode.RP;
		float hueConvert = 90.0f + Converter.HUE_CODE_OFFSET;

		assertEquals(hueConvert, Converter.toHundredHue(hueCode, testHue));
	}

	/**
	 * 
	 */
	@Test
	void munFromStrValidStr()
	{

		String munStr = "10.0R-1-2";
		String delimit = "-";
		MunsellColor compare = new MunsellColor(10.0f, HueCode.R, 1f, 2f);
		MunsellColor color = null;
		try
		{
			color = Converter.munFromStr(munStr, delimit);
		} catch (DataFormatException e)
		{
			System.err.println(e.getMessage());
		} catch (NumberFormatException e)
		{
			System.err.println("Error Parsing For Munsell Dimensions.");
		} catch (IllegalArgumentException e)
		{
			System.err.println("Invalid HueCode.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		assertEquals(compare, color);

	}

	/**
	 * 
	 */
	@Test
	void munFromStrInvalidLength()
	{

		String munStr = "10.0R-12";
		String delimit = "-";
		// MunsellColor compare = new MunsellColor (10.0f, HueCode.R, 1f, 2f) ;
		MunsellColor color = null;
		try
		{
			color = Converter.munFromStr(munStr, delimit);
		} catch (DataFormatException e)
		{
			System.err.println(e.getMessage());
		} catch (NumberFormatException e)
		{
			System.err.println("Error Parsing For Munsell Dimensions.");
		} catch (IllegalArgumentException e)
		{
			System.err.println("Invalid HueCode.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		assertEquals(null, color);

	}

	/**
	 * 
	 */
	@Test
	void munFromStrNullMun()
	{

		String munStr = null;
		String delimit = "-";
		// MunsellColor compare = new MunsellColor (10.0f, HueCode.R, 1f, 2f) ;
		MunsellColor color = null;
		try
		{
			color = Converter.munFromStr(munStr, delimit);
		} catch (DataFormatException e)
		{
			System.err.println(e.getMessage());
		} catch (NumberFormatException e)
		{
			System.err.println("Error Parsing For Munsell Dimensions.");
		} catch (IllegalArgumentException e)
		{
			System.err.println("Invalid HueCode.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		assertEquals(null, color);

	}

	/**
	 * 
	 */
	@Test
	void munFromStrNullDelimit()
	{

		String munStr = "10.0R-12";
		String delimit = null;
		// MunsellColor compare = new MunsellColor (10.0f, HueCode.R, 1f, 2f) ;
		MunsellColor color = null;
		try
		{
			color = Converter.munFromStr(munStr, delimit);
		} catch (DataFormatException e)
		{
			System.err.println(e.getMessage());
		} catch (NumberFormatException e)
		{
			System.err.println("Error Parsing For Munsell Dimensions.");
		} catch (IllegalArgumentException e)
		{
			System.err.println("Invalid HueCode.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		assertEquals(null, color);

	}

	/**
	 * 
	 */
	@Test
	void munFromStrInvalidDelimit()
	{

		String munStr = "10.0R-1-2";
		String delimit = ".";
		// MunsellColor compare = new MunsellColor (10.0f, HueCode.R, 1f, 2f) ;
		MunsellColor color = null;
		try
		{
			color = Converter.munFromStr(munStr, delimit);
		} catch (DataFormatException e)
		{
			System.err.println(e.getMessage());
		} catch (NumberFormatException e)
		{
			System.err.println("Error Parsing For Munsell Dimensions.");
		} catch (IllegalArgumentException e)
		{
			System.err.println("Invalid HueCode.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		assertEquals(null, color);

	}

	/**
	 * 
	 */
	@Test
	void munFromStrBadFloats()
	{

		String munStr = "10.0R-1A-2";
		String delimit = "-";
		// MunsellColor compare = new MunsellColor (10.0f, HueCode.R, 1f, 2f) ;
		MunsellColor color = null;
		try
		{
			color = Converter.munFromStr(munStr, delimit);
		} catch (DataFormatException e)
		{
			System.err.println(e.getMessage());
		} catch (NumberFormatException e)
		{
			System.err.println("Error Parsing For Munsell Dimensions.");
		} catch (IllegalArgumentException e)
		{
			System.err.println("Invalid HueCode.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		assertEquals(null, color);

	}

	/**
	 * 
	 */
	@Test
	void munFromStrEmptyMunStr()
	{

		String munStr = "";
		String delimit = "-";
		// MunsellColor compare = new MunsellColor (10.0f, HueCode.R, 1f, 2f) ;
		MunsellColor color = null;
		try
		{
			color = Converter.munFromStr(munStr, delimit);
		} catch (DataFormatException e)
		{
			System.err.println(e.getMessage());
		} catch (NumberFormatException e)
		{
			System.err.println("Error Parsing For Munsell Dimensions.");
		} catch (IllegalArgumentException e)
		{
			System.err.println("Invalid HueCode.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		assertEquals(null, color);

	}

	/**
	 * 
	 */
	@Test
	void munFromStrBadHueCode()
	{

		String munStr = "10.0RD-1-2";
		String delimit = "-";
		// MunsellColor compare = new MunsellColor (10.0f, HueCode.R, 1f, 2f) ;
		MunsellColor color = null;
		try
		{
			color = Converter.munFromStr(munStr, delimit);
		} catch (DataFormatException e)
		{
			System.err.println(e.getMessage());
		} catch (NumberFormatException e)
		{
			System.err.println("Error Parsing For Munsell Dimensions.");
		} catch (IllegalArgumentException e)
		{
			System.err.println("Invalid HueCode.");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		assertEquals(null, color);

	}

}
