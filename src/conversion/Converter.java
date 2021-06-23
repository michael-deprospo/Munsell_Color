package conversion;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import munsell.HueCode;
import munsell.MunsellColor;

/**
 * Converts RGBs and Munsell colors. there should only ever be one of these.
 * 
 * @author Michael DeProspo
 */
public class Converter
{

	public static final float HUE_CODE_OFFSET = 0.0001f;
	public static final int CHR_LIM = 40;

	private static Converter converter = null; // singleton

	// default munsell to rbg path
	private static final String MUNSELL_2_RGB = "Munsell2RGB.csv";
	// default rbg to munsell path
	private static final String RGB_2_MUNSELL = "RGB2Munsell.csv";

	private static final String MUN_DELIMITER = ","; // delimiter to split strings from CSV

	private Scanner munToRGB; // scanner for file
	private Scanner rgbToMun; // scanner for file

	private Map<String, String> munToRGBMap; // map to store csv file input
	// map to store csv file input - will be used in the next sprint
	private Map<String, String> rgbToMunMap;

	/**
	 * Constructs the converter with default file names.
	 */
	private Converter()
	{

		FileInputStream mun = null; // munselToRGB input stream
		FileInputStream rgb = null; // rgbToMunsell input stream

		try
		{
			mun = new FileInputStream(new File(MUNSELL_2_RGB));
			rgb = new FileInputStream(new File(RGB_2_MUNSELL));
			munToRGB = new Scanner(mun);
			rgbToMun = new Scanner(rgb);
		} catch (FileNotFoundException e)
		{

			e.printStackTrace();
		}

		munToRGBMap = new HashMap<>();
		this.loadCSV(munToRGB, munToRGBMap, MUN_DELIMITER);
		rgbToMunMap = new HashMap<>();
		this.loadCSVRGB(rgbToMun, rgbToMunMap, MUN_DELIMITER);

		munToRGB.close();
		rgbToMun.close();
		if (mun != null)
		{
			try
			{
				mun.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		if (rgb != null)
		{
			try
			{
				rgb.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * loads strings into a map from a formatted text file.
	 * 
	 * @param scan      Scanner.
	 * @param map       where the key is the first string before the delimiter, and
	 *                  the rest is the value.
	 * @param delimiter the character or string that separates the key from the
	 *                  value
	 */
	private void loadCSV(Scanner scan, Map<String, String> map, String delimiter)
	{

		scan.useDelimiter(delimiter);

		do
		{

			if (scan.hasNext())
			{

				map.put(scan.next(), scan.nextLine());
			}

		} while (scan.hasNextLine());

	}

	private void loadCSVRGB(Scanner scan, Map<String, String> map, String delimiter)
	{

		String splitTemp = null;
		String value = null;
		String[] strs = null;
		do
		{
			if (scan.hasNext())
			{

				splitTemp = scan.nextLine();
				strs = splitTemp.split(delimiter);

				splitTemp = "";
				value = "";
				for (int i = 0; i < strs.length; i++)
				{

					if (i < 3)
					{
						splitTemp = splitTemp.concat(strs[i]);
					} else if (i < strs.length - 1)
					{
						value = value.concat(strs[i] + "-");
					} else
					{
						value = value.concat(strs[i]);
					}
				}

				map.put(splitTemp, value);

			}

		} while (scan.hasNextLine());

	}

	public MunsellColor rgbToMunsell(Color rgb)
	{

		String key = "";
		MunsellColor mun = null;
		Integer red = 0;
		Integer green = 0;
		Integer blue = 0;

		if (rgb == null)
		{
			return null;
		}

		red = rgb.getRed();
		green = rgb.getGreen();
		blue = rgb.getBlue();

		key = key.concat(red.toString());
		key = key.concat(green.toString());
		key = key.concat(blue.toString());

		if (this.rgbToMunMap.containsKey(key))
		{

			try
			{
				mun = munFromStr(this.rgbToMunMap.get(key), "-");
				mun = new MunsellColor(Converter.toHundredHue(mun.getHueCode(), mun.getHueNum()), mun.getValue(),
						mun.getChroma());

			}

			catch (DataFormatException e)
			{
				System.err.println(e.getMessage());
			} catch (NumberFormatException e)
			{
				System.err.println(e.getMessage());
			} catch (IllegalArgumentException e)
			{
				System.err.println(e.getMessage());
			}
		}

		return mun;

	}

	public String rgbToMunsellStr(Color rgb)
	{

		String key = "";
		String mun = null;
		Integer red = 0;
		Integer green = 0;
		Integer blue = 0;

		if (rgb == null)
		{
			return null;
		}

		red = rgb.getRed();
		green = rgb.getGreen();
		blue = rgb.getBlue();

		key = key.concat(red.toString());
		key = key.concat(green.toString());
		key = key.concat(blue.toString());

		if (this.rgbToMunMap.containsKey(key))
		{

			mun = this.rgbToMunMap.get(key);

		}

		return mun;

	}

	/**
	 * Checks to see if a Converter has already been made and then makes calls to
	 * constructor if one hasn't been made.
	 * 
	 * @return the single Converter
	 */

	public static Converter getConverter()
	{

		if (converter != null)
		{
			return converter;
		}

		converter = new Converter();
		return converter;
	}

	/**
	 * Converts a MunsellColor to Color for RGB usage.
	 * 
	 * @param munsell color
	 * @return a the Convert Color, if the Munsell conversion can't be found it
	 *         returns a Color with 0 0 0 RGB values Hue is changed from a 0 to 100
	 *         float to 0 to 10 using mod 10. It assumes the HueCode is correct and
	 *         corresponds to the float num
	 * 
	 */
	public Color munsellToRGB(MunsellColor munsell)
	{

		Color rtrColor = null; // rgb color to be made
		if (munsell != null)
		{

			try
			{
				rtrColor = munsellToRGB(createMunsellStr(Converter.getRepresentableHue(munsell.getHueNum()),
						munsell.getHueCode(), munsell.getValue(), munsell.getChroma()));

			} catch (DataFormatException e)
			{
				rtrColor = new Color(0, 0, 0);
			}

		}

		return rtrColor;

	}

	/**
	 * Converts a 0-100 Hue float to a 0 to 10 float inclusive. negatives are turned
	 * to into a 0 value 0 == 100, so 0 is considered a 10.0
	 * 
	 * @param hueNum The hue to convert.
	 * @return the hue number in the proper munsell format(ish)
	 */
	public static float toTenHue(float hueNum)
	{

		float hueProper = 0.0f; // the hue in the proper range

		if (Float.compare(hueNum % 10, 0.0f) == 0)
		{

			hueProper = 10.0f;

		} else if (Float.compare(hueNum, 0.0f) <= -1)
		{
			hueProper = 0.0f;
		} else
		{
			hueProper = hueNum % 10;
		}

		return hueProper;

	}

	/**
	 * Calculates the 0 to 100 hue number from the hue code and 0 to 10 hue number.
	 * 
	 * @param hueCode   the hue enum
	 * @param hueTenNum the 0 to 10 hue Zero hueNums are added with 0.0001 to
	 *                  preserve the interpretation that all ten divisible hue
	 *                  values are the greatest hue value in their set hueNums above
	 *                  ten are interpreted as 10.0f
	 * @return the 0 to 100 hue num
	 * 
	 */
	public static float toHundredHue(HueCode hueCode, float hueTenNum)
	{
		// to fix checkstyle errors
		float hueHundredNum = hueTenNum;

		// 10.0 > interpretation
		if (Float.compare(hueTenNum, 10.0f) >= 1)
		{
			hueHundredNum = 10.0f;
		}

		// Zero Hue interpretation
		if (Float.compare(hueTenNum, 0.0f) <= 0)
		{
			hueHundredNum = Converter.HUE_CODE_OFFSET;
		}
		return (hueHundredNum + 10 * hueCode.getValue());

	}

	/**
	 * Converts a Hue number 0 to 100 to a representable Hue ( one in the CSV
	 * files).
	 * 
	 * @param hueNum the 0 to 100 hue
	 * @return the 2.5f, 5.0f, 7.5f, or 10.0f hue
	 */
	public static float getRepresentableHue(float hueNum)
	{

		float hue = 0.0f; // the hue number converted to a representable hue

		hue = Converter.toTenHue(hueNum);

		if (Float.compare(hue, 2.5f) <= 0)
		{
			hue = 2.5f;
		} else if (Float.compare(hue, 5.0f) <= 0)
		{
			hue = 5.0f;
		} else if (Float.compare(hue, 7.5f) <= 0)
		{
			hue = 7.5f;
		} else
		{
			hue = 10.0f;
		}

		return hue;
	}

	/**
	 * Uses a formated string (see Munsell2RGB.csv) to create a Color object that
	 * can be used. with an RGB based GUI.
	 * 
	 * @param munsell the munsell formated string EX: 2.5R-1-2.
	 * @return awt color
	 * @throws DataFormatException when the given string isn't in the munToRGBMap.
	 *                             note that the rgb values start at the 5th index
	 *                             after String.split.
	 * 
	 */
	public Color munsellToRGB(String munsell) throws DataFormatException
	{

		int[] rgbVals; // array for rgb values
		MunsellColor munColor = null;
		Color rgbColor = null;
		int incr = 0; // value subtraction
		String search = null;

		try
		{

			munColor = munFromStr(munsell, "-");
		} catch (DataFormatException e)
		{
			System.err.println(e.getMessage());
		} catch (NumberFormatException e)
		{
			System.err.println("Error Parsing For Munsell Dimensions.");
		} catch (IllegalArgumentException e)
		{
			System.err.println("Invalid HueCode.");
		}

		if (munColor != null)
		{

			if (Float.compare(munColor.getValue(), 0.0f) == 0)
			{
				rgbColor = new Color(0, 0, 0);
			} else if (Float.compare(munColor.getValue(), 10.0f) == 0)
			{
				rgbColor = new Color(255, 255, 255);
			}

		}

		if (munColor != null)
		{
			search = Converter.createMunsellStr(munColor);
			while (!this.munToRGBMap.containsKey(search) && incr <= Converter.CHR_LIM)
			{
				// System.err.println(munColor.getChroma() - modIncrement);
				munColor.setChroma(munColor.getChroma() - 1);
				++incr;
				search = Converter.createMunsellStr(munColor);

			}
		}

		if (munColor != null && this.munToRGBMap.containsKey(search) && incr <= Converter.CHR_LIM)
		{
			rgbVals = this.breakIntoRGB(this.munToRGBMap.get(search));
			return new Color(rgbVals[0], rgbVals[1], rgbVals[2]);
		} else if (rgbColor != null)
		{
			return rgbColor;
		} else
		{

			throw new DataFormatException("Invalid Munsell String.");
		}

	}

	/**
	 * Makes a MunsellColor from values parsed from a formated string.
	 * 
	 * @param munsell   the munsell formatted string (7.5R-3-2 as an example)
	 * @param delimiter separates the munsell values, used to split string
	 * @return the MunsellColor object created
	 * @throws DataFormatException      if munsell string or delimiter is invalid or
	 *                                  null
	 * @throws NumberFormatException    if the parser can't find valid floats
	 * @throws IllegalArgumentException if the HueCode is invalid
	 */
	public static MunsellColor munFromStr(String munsell, String delimiter)
			throws DataFormatException, NumberFormatException, IllegalArgumentException
	{

		String[] munStrs; // split up mun string
		Float[] munDim = new Float[3]; // the mun dimensions
		String hueCodeStr = null; // the hueCode as string
		int hueCodeIndex = 0; // the hueCode's first character's index
		HueCode hueCode;

		if (munsell == null || munsell.length() <= 0)
		{
			throw new DataFormatException("Invalid Munsell String.");
		}

		if (delimiter == null || delimiter.length() <= 0)
		{
			throw new DataFormatException("Invalid Delimiter String.");
		}

		munStrs = munsell.split(delimiter);

		if (munStrs.length != 3)
		{
			throw new DataFormatException("Invalid Munsell String or Delimiter.");
		}

		/// check for hue code characters and parse for hue value
		for (int i = 0; i < munStrs[0].length(); i++)
		{

			if (Character.isLetter(munStrs[0].charAt(i)))
			{
				munDim[0] = Float.parseFloat(munStrs[0].substring(0, i));
				hueCodeIndex = i;
				break;
			}

		}

		if (munDim[0] == null)
		{

			throw new DataFormatException("Invalid Hue String.");
		}

		// get the rest of the float values
		for (int i = 1; i < munStrs.length; i++)
		{
			munDim[i] = Float.parseFloat(munStrs[i]);
		}

		// gets substring of the total hue string based upon length of the hue float
		hueCodeStr = munStrs[0].substring(hueCodeIndex);

		// get a HueCode from the string; it throws an IllegalArg error if invalid
		hueCode = HueCode.valueOf(hueCodeStr);

		return new MunsellColor(munDim[0], hueCode, munDim[1], munDim[2]);

	}

	/**
	 * Breaks down the Munsell2RGB.csv value to obtain rgb numbers.
	 * 
	 * @param str the csv string pulled from the munToRGB map
	 * @return an array of the rgb values ( ints) helper method
	 */
	private int[] breakIntoRGB(String str)
	{

		final int rgbParams = 3; // number rgb values
		String[] splitStr; // the split string
		int[] rgbVals = new int[rgbParams];

		splitStr = str.split(Converter.MUN_DELIMITER);

		// 3 is the number values ( the rgb numbers ) to get
		for (int i = 1; i <= rgbParams; i++)
		{

			rgbVals[rgbVals.length - i] = Integer.parseInt(splitStr[splitStr.length - i]);

		}

		return rgbVals;

	}

	/**
	 * Utility method that converts a Munsell's fields into a formatted string.
	 * 
	 * @param hue    valid range 0.0f to 10.0f
	 * @param code   hue's enum code
	 * @param chroma valid if greater than 0.0f
	 * @param value  valid if in the range of 0.0f to 10.0f
	 * @throws DataFormatException throws if null
	 * @return formatted key for the munToRGB map value and chroma are rounded by
	 *         Math.round(): rounds up if >= .5 Hue floats are always rounded up
	 *         based on the threshold number EX: 2.6 will be rounded to 5.0
	 */
	public static String createMunsellStr(float hue, HueCode code, float value, float chroma) throws DataFormatException
	{

		String hueStr; // hue as a string
		String codeStr; // code as a string
		String chromaStr; // chroma as a string
		String valueStr; // value as a string
		int numChroma; // rounded chroma
		float hueFlex;
		final float upLimit = 10.0f; // hue's and value's upper bound
		final float downLimit = 0.0f; // hue's lower bound
		final float valueAndChromaFloor = 0.0f; // value's and Chroma's lower bound

		DecimalFormat decFormat = new DecimalFormat("0.0");

		if (code == null)
		{
			throw new DataFormatException("Arguement Null: HueCode.");
			// hue and value shouldn't ever exceed 10.0f
		}
		if (Float.compare(hue, upLimit) >= 1 || Float.compare(value, upLimit) >= 1)
		{

			throw new DataFormatException("Arguement upper bound exceeded.");

		} else if (Float.compare(hue, downLimit) <= -1 || Float.compare(value, valueAndChromaFloor) <= -1
				|| Float.compare(chroma, valueAndChromaFloor) <= -1)
		{

			throw new DataFormatException("Argument lower bound exceeded.");
		}

		// make sure hue is the right value
		if (Float.compare(hue, 2.5f) <= 0)
		{
			hueFlex = 2.5f;
		} else if (Float.compare(hue, 5.0f) <= 0)
		{
			hueFlex = 5.0f;
		} else if (Float.compare(hue, 7.5f) <= 0)
		{
			hueFlex = 7.5f;
		} else
		{
			hueFlex = 10.0f;
		}

		hueStr = decFormat.format((double) hueFlex);
		codeStr = code.name();
		valueStr = Integer.toString(Math.round(value));
		numChroma = Math.round(chroma);

//		// changes an odd chroma to an even one so the value could possible be found in
//		// map
//		if (numChroma % 2 != 0)
//		{
//			numChroma -= 1;
//		}

		chromaStr = Integer.toString(numChroma);

		return (hueStr.concat(codeStr) + "-" + valueStr + "-" + chromaStr);

	}

	/**
	 * Creates a munsell string using munsell class.
	 * 
	 * @param mun the munsell color.
	 * @return munsell string
	 * @throws DataFormatException if invalid values.
	 */
	public static String createMunsellStr(MunsellColor mun) throws DataFormatException
	{

		float hueTen = Converter.getRepresentableHue(mun.getHueNum()); // hue as 1 to 10 number
		return createMunsellStr(hueTen, mun.getHueCode(), mun.getValue(), mun.getChroma());
	}

}
