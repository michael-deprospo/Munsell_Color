package setCreator;

import munsell.MunsellColor;
//import setCreator.MunDimension;

/**
 * Creates sets of Munsell Colors based on an increment for each Dimension of
 * the Munsell Color space.
 * 
 * @author Michael DeProspo
 * @author Forrest Halterman
 */
public class ColorSetCreator
{

    // recommended default increment value
    public static final float CHROMA_INCR = 2.0f;
    public static final float VALUE_INCR = 1.0f;
    public static final float HUE_INCR = 2.5f;

    // recommended default limits

    public static final float CHROMA_LIMIT = 40.0f;
    public static final float VALUE_LIMIT = 10.0f;
    public static final float HUE_LIMIT = 10.0f;

    // the Munsell Dimension increments ( what is added to the base dimensional
    // values)
    // these can any number within the limit
    // FIXME: ZERO increments throw an error when their dimension is chosen as a
    private float hueIncrement;
    private float valueIncrement;
    private float chromaIncrement;

    // the limits or boundaries for the Munsell Dimensions
    private float chromaLimit; // 0-infinity(anything past the high thirties can't be displayed)
    private float valueLimit; // 0-10
    private float hueLimit; // 0-100

    /**
     * Creates a color set.
     */
    public ColorSetCreator()
    {

        this.hueIncrement = ColorSetCreator.HUE_INCR;
        this.valueIncrement = ColorSetCreator.VALUE_INCR;
        this.chromaIncrement = ColorSetCreator.CHROMA_INCR;
        this.hueLimit = ColorSetCreator.HUE_LIMIT;
        this.valueLimit = ColorSetCreator.VALUE_LIMIT;
        this.chromaLimit = ColorSetCreator.CHROMA_LIMIT;

    }

    /**
     * Constructs a ColorSetCreator with the default increments and limits for the
     * munsell dimensions.
     * 
     * @param hueIncrement    Default hue increment.
     * @param valueIncrement  Default value increment.
     * @param chromaIncrement Default chroma increment.
     */
    public ColorSetCreator(float hueIncrement, float valueIncrement, float chromaIncrement)
    {
        this.hueIncrement = hueIncrement;
        this.valueIncrement = valueIncrement;
        this.chromaIncrement = chromaIncrement;
        this.hueLimit = ColorSetCreator.HUE_LIMIT;
        this.valueLimit = ColorSetCreator.VALUE_LIMIT;
        this.chromaLimit = ColorSetCreator.CHROMA_LIMIT;
    }

    /*
     * TODO: add overload methods for 1d and 2d methods that has array sizes defined
     * by the params not required, but might make things easier and less error prone
     */

    /**
     * Creates a list or 1dimensional set of MunsellColors.
     * 
     * @param munD       the munsell dimension to increment
     * @param startColor the base MunsellColor
     * @return set of MunsellColors
     */
    public MunsellColor[] create1DSet(MunDimension munD, MunsellColor startColor)
    {
        MunsellColor[] mun1dSet = null; // 1d set of MunsellColors
        if (munD == MunDimension.HUE)
        {
            mun1dSet = new MunsellColor[dimensionSize(munD, startColor)];// fixed or should be
            int k = 0;

            for (float i = startColor.getHueNum(); i <= hueLimit; i += hueIncrement)
            {

                mun1dSet[k] = new MunsellColor(i, startColor.getHueCode(), startColor.getValue(),
                        startColor.getChroma());
                k++;
            }
        } else if (munD == MunDimension.VALUE)
        {

            mun1dSet = new MunsellColor[dimensionSize(munD, startColor)];// fixed or should be
            int k = 0;

            for (float i = startColor.getValue(); i <= valueLimit; i += valueIncrement)
            {

                mun1dSet[k] = new MunsellColor(startColor.getHueNum(), startColor.getHueCode(), i,
                        startColor.getChroma());
                k++;
            }
        } else if (munD == MunDimension.CHROMA)
        {
            mun1dSet = new MunsellColor[dimensionSize(munD, startColor)];// fixed or should be
            int k = 0;

            for (float i = startColor.getChroma(); i <= chromaLimit; i += chromaIncrement)
            {

                mun1dSet[k] = new MunsellColor(startColor.getHueNum(), startColor.getHueCode(),
                        startColor.getValue(), i);
                k++;
            }

        }

        return mun1dSet;

    }

    /**
     * Creates a 2d array with to rows and columns referring to incrementing
     * dimensions of munD and MunD2.
     * 
     * @param munD       row dimension
     * @param munD2      column dimension
     * @param startColor the initial base color to start from; this is always
     *                   inputed into the array at index [0][0]
     * @return the 2dimensional set of MunsellColors
     */
    public MunsellColor[][] create2DSet(MunDimension munD, MunDimension munD2,
            MunsellColor startColor)
    {

        if (munD == null || munD2 == null || startColor == null)
        {

            return null;
        }

        int xD = dimensionSize(munD, startColor);
        int yD = dimensionSize(munD2, startColor);

        float initialHue = startColor.getHueNum();
        float initialValue = startColor.getValue();
        float initialChroma = startColor.getChroma();

        MunsellColor newColor = null;

        MunsellColor[][] mun2dSet = new MunsellColor[xD][yD];

        for (int i = 0; i < mun2dSet.length; i++)
        {

            for (int j = 0; j < mun2dSet[0].length; j++)
            {

                newColor = new MunsellColor(startColor.getHueNum(), startColor.getHueCode(),
                        startColor.getValue(), startColor.getChroma());

                switch (munD)
                {

                    case HUE:

                    // FIXME: needs to handle the HueCode; shouldn't matter this sprint
                        newColor.setHueNum(initialHue + (hueIncrement * (float) i));
                        break;
                    case VALUE:

                        newColor.setValue(initialValue + (valueIncrement * (float) i));
                        break;

                    case CHROMA:
                        newColor.setChroma(initialChroma + (chromaIncrement * (float) i));
                        break;

                    default:
                        break;
                }

                switch (munD2)
                {

                    case HUE:

                        // FIXME: needs to handle the HueCode; shouldn't matter this sprint
                        newColor.setHueNum(initialHue + (hueIncrement * (float) j));
                        break;
                    case VALUE:

                        newColor.setValue(initialValue + (valueIncrement * (float) j));
                        break;

                    case CHROMA:
                        newColor.setChroma(initialChroma + (chromaIncrement * (float) j));
                        break;

                    default:
                        break;
                }

                mun2dSet[i][j] = newColor;

            }

        }

        return mun2dSet;

    }

    /**
     * Calculates dimension size ( for the arrays ).
     * 
     * @param munD       munsell dimension (Hue, Value, Chroma)
     * @param startColor the initial color base
     * @return the number of MunsellColor objects to be made.
     */
    private int dimensionSize(MunDimension munD, MunsellColor startColor)
    {

        int dimensionSize = 0;
        switch (munD)
        {

            case HUE:
                dimensionSize = (int) Math.ceil((hueLimit - startColor.getHueNum()) / hueIncrement);
                break;
            case VALUE:
                dimensionSize = (int) Math.ceil((valueLimit - startColor.getValue())
                        / valueIncrement);
                break;

            case CHROMA:
                dimensionSize = (int) Math.ceil((chromaLimit - startColor.getChroma())
                        / chromaIncrement);
                break;

            default:
                break;
        }
        // + 1 for the startColor
        return dimensionSize + 1;

    }

//public MunsellColor[][][] create3dSet(MunsellColor startColor) {
    // return new MunsellColor[1][1][1];
//}
    /**
     * @return hueIncrement
     */
    public float getHueIncrement()
    {
        return hueIncrement;
    }

    /**
     * @param hueIncrement The number by which to increment the hue.
     */
    public void setHueIncrement(float hueIncrement)
    {
        this.hueIncrement = hueIncrement;
    }

    /**
     * @return valueIncrement
     */
    public float getValueIncrement()
    {
        return valueIncrement;
    }

    /**
     * @param valueIncrement The number by which to increment the value.
     */
    public void setValueIncrement(float valueIncrement)
    {
        this.valueIncrement = valueIncrement;
    }

    /**
     * @return chromaIncrement
     */
    public float getChromaIncrement()
    {
        return chromaIncrement;
    }

    /**
     * @param chromaIncrement The number by which to increment the chroma.
     */
    public void setChromaIncrement(float chromaIncrement)
    {
        this.chromaIncrement = chromaIncrement;
    }

    /**
     * @return chromaLimit
     */
    public float getChromaLimit()
    {
        return chromaLimit;
    }

    /**
     * @return valueLimit
     */
    public float getValueLimit()
    {
        return valueLimit;
    }

    /**
     * @param valueLimit The limit of the value.
     */
    public void setValueLimit(float valueLimit)
    {
        this.valueLimit = valueLimit;
    }

    /**
     * @return hueLimit
     */
    public float getHueLimit()
    {
        return hueLimit;
    }

    /**
     * @param hueLimit The limit of the hue.
     */
    public void setHueLimit(float hueLimit)
    {
        this.hueLimit = hueLimit;
    }

    /**
     * @param chromaLimit The limit of the chroma.
     */
    public void setChromaLimit(float chromaLimit)
    {
        this.chromaLimit = chromaLimit;
    }
}

