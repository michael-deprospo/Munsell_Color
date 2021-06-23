package munsellColorTests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import munsell.MunsellColor;

/**
 * @author Forrest
 *
 */
class HarmoniousColorsTests
{

    // ---------Complementary--------------

    /**
     * 
     */
    @Test
    void matchComplementaryRed5()
    {
        MunsellColor mun = new MunsellColor(5.0f, 1.0f, 2.0f);
        MunsellColor harm = new MunsellColor(55.0f, 1.0f, 2.0f);

        assertEquals(harm, MunsellColor.matchComplementary(mun));

    }

    /**
     * 
     */
    @Test
    void matchComplementaryOver100()
    {
        MunsellColor mun = new MunsellColor(65.0f, 1.0f, 2.0f);
        MunsellColor harm = new MunsellColor(15.0f, 1.0f, 2.0f);

        assertEquals(harm, MunsellColor.matchComplementary(mun));

    }

    /**
     * 
     */
    @Test
    void matchComplementaryNULL()
    {
        MunsellColor mun = null;
        MunsellColor harm = null;

        assertEquals(harm, MunsellColor.matchComplementary(mun));

    }

    // ------Analogous Colors ----------------

    /**
     * 
     */
    @Test
    void matchAnalogousRed5()
    {

        MunsellColor mun = new MunsellColor(5.0f, 1.0f, 2.0f);
        MunsellColor[] analogousColors = new MunsellColor[2];

        analogousColors[0] = new MunsellColor(95.0f, 1.0f, 2.0f);
        analogousColors[1] = new MunsellColor(15.0f, 1.0f, 2.0f);

        assertArrayEquals(analogousColors, MunsellColor.matchAnalogous(mun));
    }

    /**
     * 
     */
    @Test
    void matchAnalogousNULL()
    {

        MunsellColor mun = null;
        MunsellColor[] analogousColors = new MunsellColor[2];

        analogousColors[0] = new MunsellColor(95.0f, 1.0f, 2.0f);
        analogousColors[1] = new MunsellColor(15.0f, 1.0f, 2.0f);
        analogousColors = null;
        assertArrayEquals(analogousColors, MunsellColor.matchAnalogous(mun));
    }

    /**
     * 
     */
    @Test
    void matchAnalogousOver()
    {

        MunsellColor mun = new MunsellColor(90.0f, 1.0f, 2.0f);
        MunsellColor[] analogousColors = new MunsellColor[2];

        analogousColors[0] = new MunsellColor(80.0f, 1.0f, 2.0f);
        analogousColors[1] = new MunsellColor(100.0f, 1.0f, 2.0f);

        assertArrayEquals(analogousColors, MunsellColor.matchAnalogous(mun));
    }

    // ------------------Split Complementary

    /**
     * 
     */
    @Test
    void matchSplitCompRed5()
    {

        MunsellColor mun = new MunsellColor(5.0f, 1.0f, 2.0f);
        MunsellColor[] splitColors = new MunsellColor[2];

        splitColors[0] = new MunsellColor(45.0f, 1.0f, 2.0f);
        splitColors[1] = new MunsellColor(65.0f, 1.0f, 2.0f);

        assertArrayEquals(splitColors, MunsellColor.matchSplitComp(mun));
    }

    /**
     * 
     */
    @Test
    void matchSplitCompNULL()
    {

        MunsellColor mun = null;
        MunsellColor[] splitColors = null;

        assertArrayEquals(splitColors, MunsellColor.matchSplitComp(mun));
    }

    // --------Tetrad

    /**
     * 
     */
    @Test
    void matchTetradRed5Purple85()
    {

        MunsellColor mun1 = new MunsellColor(5.0f, 1.0f, 2.0f);
        MunsellColor mun2 = new MunsellColor(85.0f, 1.0f, 2.0f);
        MunsellColor[][] tetrad = new MunsellColor[2][2];
        MunsellColor[][] tetradRtr = null;

        tetrad[0][0] = new MunsellColor(5.0f, 1.0f, 2.0f);
        tetrad[0][1] = new MunsellColor(55.0f, 1.0f, 2.0f);
        tetrad[1][0] = new MunsellColor(85.0f, 1.0f, 2.0f);
        tetrad[1][1] = new MunsellColor(35.0f, 1.0f, 2.0f);

        tetradRtr = MunsellColor.matchTetrad(mun1, mun2);

        for (int i = 0; i < tetrad.length; i++)
        {
            for (int j = 0; j < tetrad[0].length; j++)
            {
                assertEquals(tetrad[i][j], tetradRtr[i][j]);
            }
        }

    }

    /**
     * 
     */
    @Test
    void matchTetradNULL()
    {

        MunsellColor[][] tetrad = null;
        MunsellColor[][] tetradRtr = null;

        tetradRtr = MunsellColor.matchTetrad(null, null);

        assertEquals(tetrad, tetradRtr);

    }

    /**
     * 
     */
    @Test
    void matchTetradIllegalArg()
    {

        MunsellColor mun1 = new MunsellColor(5.0f, 1.0f, 2.0f);
        MunsellColor mun2 = new MunsellColor(55.0f, 1.0f, 2.0f);
        MunsellColor[][] tetrad = null;
        MunsellColor[][] tetradRtr = null;

        try
        {
            tetradRtr = MunsellColor.matchTetrad(mun1, mun2);
        } catch (IllegalArgumentException e)
        {

            System.err.println(e.getMessage());
        }

        assertEquals(tetrad, tetradRtr);
    }

    // --------square ---------------

    /**
     * 
     */
    @Test
    void matchSquareRed5()
    {

        MunsellColor mun = new MunsellColor(5.0f, 1.0f, 2.0f);
        MunsellColor[] squareSet = new MunsellColor[4];

        squareSet[0] = new MunsellColor(5.0f, 1.0f, 2.0f);
        squareSet[1] = new MunsellColor(30.0f, 1.0f, 2.0f);
        squareSet[2] = new MunsellColor(55.0f, 1.0f, 2.0f);
        squareSet[3] = new MunsellColor(80.0f, 1.0f, 2.0f);
        assertArrayEquals(squareSet, MunsellColor.matchSquare(mun));

    }

    /**
     * 
     */
    @Test
    void matchSquareNULL()
    {

        MunsellColor mun = null;
        MunsellColor[] squareSet = null;

        assertArrayEquals(squareSet, MunsellColor.matchSquare(mun));

    }

    // -----------------triad-----------------------

    /**
     * 
     */
    @Test
    void matchTriadRed5()
    {

        MunsellColor mun = new MunsellColor(5.0f, 1.0f, 2.0f);
        MunsellColor[] triadSet = new MunsellColor[3];

        triadSet[0] = new MunsellColor(5.0f, 1.0f, 2.0f);
        triadSet[1] = new MunsellColor(38.33f, 1.0f, 2.0f);
        triadSet[2] = new MunsellColor(71.66f, 1.0f, 2.0f);
        assertArrayEquals(triadSet, MunsellColor.matchTriad(mun));

    }

    /**
     * 
     */
    @Test
    void matchTriadNULL()
    {

        MunsellColor mun = null;
        MunsellColor[] squareSet = null;

        assertArrayEquals(squareSet, MunsellColor.matchSquare(mun));

    }

}



