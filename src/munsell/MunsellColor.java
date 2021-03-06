package munsell;

import java.awt.Color;

public class MunsellColor
{

	private float hueNum;
	private HueCode hueCode;
	private float value;
	private float chroma;
	private String munNotation;
	private Color rgb;

	/**
	 * Creates empty munsellColor.
	 */
	public MunsellColor()
	{

	}

	/**
	 * Creates a MunsellColor with Color value of RGB.
	 * 
	 * @param rgb the Color for munsell that will be used to get Munsell Color
	 */
	public MunsellColor(Color rgb)
	{
		this.rgb = rgb;
	}

	/**
	 * Creates a MunsellColor with hueNum, hueCode, value, chroma.
	 * 
	 * @param hueNum  num for hue, 0 to 100
	 * @param hueCode huecode, letter
	 * @param value   N0-N10 - shades of gray
	 * @param chroma  0-infinity, intensity
	 */
	public MunsellColor(float hueNum, HueCode hueCode, float value, float chroma)
	{

		setHueNum(hueNum);
		setHueCode(hueCode);
		setValue(value);
		setChroma(chroma);
	}

	/**
	 * Creates a MunsellColor with just hue, value, and chroma floats.
	 * 
	 * @param hueNum num for hue, 0 to 100
	 * @param value  0 to 10 black to white
	 * @param chroma 0 to infinity
	 */
	public MunsellColor(float hueNum, float value, float chroma)
	{
		setHueNum(hueNum);
		setValue(value);
		setChroma(chroma);
		setHueCode(MunsellColor.matchHueCode(this.hueNum));
	}

	/**
	 * Matches the hue 0 to 100 float to a valid HueCode.
	 * 
	 * @param hueNum the 0 to 100 hue float
	 * @return a valid Hue Code
	 */
	public static HueCode matchHueCode(float hueNum)
	{

		int enumIndex = (int) (hueNum / 10);
		// HueNum's tens place

		if (Float.compare(hueNum, 100.0f) >= 0 || Float.compare(hueNum, 0.0f) <= 0)
		{
			return HueCode.values()[HueCode.values().length - 1];
		}

		else if (Float.compare(hueNum % 10, 0.0f) == 0)
		{

			return HueCode.values()[enumIndex - 1];

		}

		else
		{

			return HueCode.values()[enumIndex];
		}

	}

	/**
	 * Matches a complementary color for a Munsell color.
	 * 
	 * @param mun the original munsell color
	 * @return the complementary MunsellColor ;
	 */
	public static MunsellColor matchComplementary(MunsellColor mun)
	{

		float compHue = 0; // the complement's hue

		if (mun == null)
		{

			System.err.println("Cannot Complement null MunsellColor.");
			return null;
		}

		compHue = (mun.getHueNum() + 50.0f) % 100;
		return new MunsellColor(compHue, mun.getValue(), mun.getChroma());

	}

	/**
	 * Calculates the Analogous colors for a given munsell.
	 * 
	 * @param mun the given munsell color
	 * @return an array that contains the right and left analogous colors
	 */
	public static MunsellColor[] matchAnalogous(MunsellColor mun)
	{

		final float leftAnalog = 90.0f; // analogous adding number
		final float rightAnalog = 10.0f; // analogous adding number

		// array to the left and right analogs
		MunsellColor[] analogousColors = new MunsellColor[2];

		if (mun == null)
		{

			System.err.println("There is no harmony in nothing. Argument null.");
			return null;
		}

		// left
		analogousColors[0] = new MunsellColor((mun.getHueNum() + leftAnalog) % 100, mun.getValue(), mun.getChroma());

		// right
		analogousColors[1] = new MunsellColor((mun.getHueNum() + rightAnalog) % 100, mun.getValue(), mun.getChroma());

		return analogousColors;

	}

	/**
	 * Calculate the Split-Complementary colors of a given munsell color.
	 * 
	 * @param mun given munsell color
	 * @return the split complementary colors in array
	 */
	public static MunsellColor[] matchSplitComp(MunsellColor mun)
	{

		if (mun == null)
		{

			System.err.println("There is no harmony in nothing. Argument null.");
			return null;
		}

		return MunsellColor.matchAnalogous(MunsellColor.matchComplementary(mun));
	}

	/**
	 * Calculate tetrad colors based on the given munsell colors.
	 * 
	 * @param mun1 the first given munsell
	 * @param mun2 the second given munsell
	 * @throws IllegalArgumentException
	 * @return 2d array of the 4 munsell colors that make the tetrad
	 */
	public static MunsellColor[][] matchTetrad(MunsellColor mun1, MunsellColor mun2) throws IllegalArgumentException
	{

		MunsellColor[][] tetradColors = new MunsellColor[2][2]; // tetrad pairs

		if (mun1 == null || mun2 == null)
		{

			System.err.println("There is no harmony in nothing. Argument null.");
			return null;
		}

		if (mun2.equals(MunsellColor.matchComplementary(mun1)))
		{

			throw new IllegalArgumentException("MunsellColor args cannot be compliments of each other");
		}

		tetradColors[0][0] = mun1;
		tetradColors[0][1] = MunsellColor.matchComplementary(mun1);
		tetradColors[1][0] = mun2;
		tetradColors[1][1] = MunsellColor.matchComplementary(mun2);

		return tetradColors;

	}

	/**
	 * Calculate the Square colors for a given munsell.
	 * 
	 * @param mun the given munsell color
	 * @return the square set of munsell colors
	 */
	public static MunsellColor[] matchSquare(MunsellColor mun)
	{

		MunsellColor[] squareSet = new MunsellColor[4]; // square set of munsell colors
		float increment = 0; // loop increment

		if (mun == null)
		{

			System.err.println("There is no harmony in nothing. Argument null.");
			return null;
		}

		for (int i = 0; i < squareSet.length; i++)
		{

			squareSet[i] = new MunsellColor((mun.getHueNum() + increment) % 100, mun.getValue(), mun.getChroma());
			increment += 25.0f;
		}

		return squareSet;

	}

	/**
	 * Calculate triad colors for a given munsell color.
	 * 
	 * @param mun the given munsell
	 * @return set of triad colors as an array
	 */
	public static MunsellColor[] matchTriad(MunsellColor mun)
	{

		MunsellColor[] triadSet = new MunsellColor[3]; // set of triad munsell colors
		float increment = 0; // loop increment

		if (mun == null)
		{

			System.err.println("There is no harmony in nothing. Argument null.");
			return null;
		}

		for (int i = 0; i < triadSet.length; i++)
		{

			triadSet[i] = new MunsellColor((mun.getHueNum() + increment) % 100, mun.getValue(), mun.getChroma());
			increment += 33.33f;
		}

		return triadSet;

	}

	/**
	 * Returns munNotation - munsell notation.
	 * 
	 * @return munNotation the munsell hue, value, chroma
	 */
	public String getMunNotation()
	{
		munNotation = "" + hueNum + hueCode + "-" + value + "-" + chroma;
		return munNotation;
	}

	/**
	 * Returns munsell value.
	 * 
	 * @return value
	 */
	public float getValue()
	{
		return value;
	}

	/**
	 * Returns chroma value.
	 * 
	 * @return chroma
	 */
	public float getChroma()
	{
		return chroma;
	}

	/**
	 * Returns rgb color for this munsell color.
	 * 
	 * @return rgb the color
	 */
	public Color getColor()
	{
		return rgb;
	}

	/**
	 * Gets hueNum.
	 * 
	 * @return hueNum
	 */
	public float getHueNum()
	{
		return hueNum;
	}

	/**
	 * Returns hueCode.
	 * 
	 * @return hueCode
	 */
	public HueCode getHueCode()
	{
		return hueCode;
	}

	/**
	 * Returns string representation of munsell color.
	 * 
	 * @return String of munsell color
	 */
	public String munColor()
	{
		return "Hue: " + getHueNum() + getHueCode() + ", Value: N" + getValue() + ", Chroma: " + getChroma();
	}

	/**
	 * Sets rgb color to specified color.
	 * 
	 * @param newRGB Color to set to
	 */
	public void setColor(Color newRGB)
	{
		this.rgb = newRGB;
	}

	/**
	 * Sets value to specified value.
	 * 
	 * @param value value to set to
	 */
	public void setValue(float value)
	{
		if (value < 0)
		{
			this.value = 0;
		} else if (value > 10)
		{
			this.value = 10;
		} else
		{
			this.value = value;
		}
	}

	/**
	 * Sets chroma to specified value.
	 * 
	 * @param chroma the chroma to set to
	 */
	public void setChroma(float chroma)
	{
		if (chroma < 0)
		{
			this.chroma = 0;
		} else
		{
			this.chroma = chroma;
		}
	}

	/**
	 * Sets hueCode to specified code.
	 * 
	 * @param hueCode the code to set to
	 */
	public void setHueCode(HueCode hueCode)
	{
		this.hueCode = hueCode;
	}

	/**
	 * Assigns huecode based on the value of hue(tens digit).
	 * 
	 * @param value the value of hue's 10's digit
	 */
	public void assignHueCode(int value)
	{
		switch (value)
		{
		case 0:
			this.hueCode = HueCode.R;
			break;
		case 1:
			this.hueCode = HueCode.YR;
			break;
		case 2:
			this.hueCode = HueCode.Y;
			break;
		case 3:
			this.hueCode = HueCode.GY;
			break;
		case 4:
			this.hueCode = HueCode.G;
			break;
		case 5:
			this.hueCode = HueCode.BG;
			break;
		case 6:
			this.hueCode = HueCode.B;
			break;
		case 7:
			this.hueCode = HueCode.PB;
			break;
		case 8:
			this.hueCode = HueCode.P;
			break;
		case 9:
			this.hueCode = HueCode.RP;
			break;
		default:
			break;

		}
	}

	/**
	 * Sets the hueNum to specified value.
	 * 
	 * @param hueNum value to set to
	 */
	public void setHueNum(float hueNum)
	{
		if (hueNum < 0)
		{
			this.hueNum = 0;
		} else if (hueNum > 100)
		{
			this.hueNum = 100;
		} else
		{
			this.hueNum = hueNum;
		}
	}

	/**
	 * Assigns hue of 2.5,5,or 7.5.
	 * 
	 * @param hueActual the actual hue value
	 */
	public void assignHueNum(float hueActual)
	{
		float hue = hueActual % 10;
		if (hue <= 2.5)
		{
			hueNum = (float) 2.5;
		} else if (hue <= 5)
		{
			hueNum = (float) 5;
		} else
		{
			hueNum = (float) 7.5;
		}
	}

	/**
	 * Autogenerated hashCode method based on chroma, hueNum, and Value. munNotation
	 * string isn't factored into the hash code.
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(chroma);
		result = prime * result + ((hueCode == null) ? 0 : hueCode.hashCode());
		result = prime * result + Float.floatToIntBits(hueNum % 100);
		result = prime * result + Float.floatToIntBits(value);
		return result;
	}

	/**
	 * Creates a new MunsellColor based off averages of two munsellColors.
	 * 
	 * @param m1 first color to be mixed
	 * @param m2 the second color to be mixed
	 * @return the new mixed color
	 */
	public MunsellColor mixColors(MunsellColor m1, MunsellColor m2)
	{

		double x1;
		double x2;
		double y1;
		double y2;
		double x3;
		double y3;
		double angle;
		// angle = 3.6 * (Math.abs(m1.getHueNum() - m2.getHueNum())); //this might be
		// incorrect
		// angle = 3.6 * (m2.getHueNum() - m1.getHueNum()); // this might be incorrect,
		// multiply by 3.6 since each increase
		// maybe not angle of difference but angle of each color? // of 1 in hue results
		// in 3.6 degrees
		double radians1 = Math.toRadians(m1.getHueNum());
		double radians2 = Math.toRadians(m2.getHueNum());
		x1 = m1.getChroma() * (Math.cos(radians1)); // x of first color
		y1 = m1.getChroma() * (Math.sin(radians1)); // y of first color
		x2 = m2.getChroma() * (Math.cos(radians2));
		y2 = m2.getChroma() * (Math.sin(radians2));
		x3 = (x1 + x2) / 2; // midpoint of x
		y3 = (y1 + y2) / 2; // midpoint of y
		float chroma = (float) Math.sqrt(Math.pow(y3, 2) + Math.pow(x3, 2)); // distance of point from origin
		angle = Math.toDegrees(Math.atan(y3 / x3)); // finding theta

		if (x3 == 0 && y3 > 0)
		{
			angle = 90;
		} else if (x3 < 0 && y3 < 0)
		{
			angle = 270;
		} else if (x3 < 0)
		{
			angle += 180;
		}
		float hue = (float) (angle); // divide by 3.6 degrees to go back to hue
		// radians = Math.toRadians(angle);
		// float hue = (float)(Math.atan(radians) *
		// Math.abs(m2.getHueNum()-m1.getHueNum())); //angle of theta (hue)
		// float hue = (float)Math.abs((angle/3.6)); //angle of theta (hue)
		// float hue = (float)(radians);

		// float hue = (float)Math.toDegrees(Math.atan(radians));
		float value = (m1.getValue() + m2.getValue()) / 2; // average of the two values
		MunsellColor mixed = new MunsellColor(hue, value, Math.round(chroma));// round to get whole value for hue and
																				// chroma, but not value
		// assigns proper hueCode based on tens digit
		// mixed.assignHueCode(hue);//This fixes the rounding issue
		// mixed.setHueCode(matchHueCode(hue));
		mixed.assignHueCode(Math.round(hue/10));

		mixed.assignHueNum(hue); // assigns proper hue based on ones digit
		return mixed;
	}

	/*
	 * used for comparisons between MunsellColor objects munNotation isn't factored
	 * in
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MunsellColor other = (MunsellColor) obj;
		if (Float.floatToIntBits(chroma) != Float.floatToIntBits(other.chroma))
			return false;
		if (hueCode != other.hueCode)
			return false;
		if (Float.floatToIntBits(hueNum) != Float.floatToIntBits(other.hueNum)
				&& Float.compare(hueNum % 100, other.hueNum % 100) != 0)
			return false;
		if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
			return false;
		return true;
	}
}
