package setCreatorTests;

import setCreator.ColorSetCreator;
import setCreator.MunDimension;
import static org.junit.jupiter.api.Assertions.*;
import munsell.MunsellColor;
import munsell.HueCode;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Michael DeProspo
 *
 */
class ColorSetCreatorTest
{
    ColorSetCreator cSC = new ColorSetCreator();
    float hueInc = 2;
    float chromaInc = 3;
    float valueInc = 1;
    float hueLimit = 8;
    float valueLimit = 3;
    float chromaLimit = 9;
    MunsellColor start = new MunsellColor(2, HueCode.B, 1, 3);
    MunsellColor val2 = new MunsellColor(2, HueCode.B, 1, 6);
    MunsellColor val3 = new MunsellColor(2, HueCode.B, 1, 9);
    MunsellColor chroma2 = new MunsellColor(2, HueCode.B, 2, 3);
    MunsellColor chroma3 = new MunsellColor(2, HueCode.B, 3, 3);
    MunsellColor[] v = {start, val2, val3};
    MunsellColor[] c = {start, chroma2, chroma3};
//  MunsellColor[] vc = {   {start, val2, val3},
    // {start, chroma2, chroma3} };

    /**
     * 
     */
    @Test
    final void testColorSetCreator()
    {
        assertNotNull(cSC);
    }

    /**
     * 
     */
    @Test
    final void testColorSetCreatorFloatFloatFloat()
    {

        cSC = new ColorSetCreator(hueInc, valueInc, chromaInc);
        assertEquals(hueInc, cSC.getHueIncrement());
        assertEquals(valueInc, cSC.getValueIncrement());
        assertEquals(chromaInc, cSC.getChromaIncrement());
    }

    /**
     * 
     */
    @Test
    final void testGetHueIncrement()
    {
        float hueInc = 3;
        cSC.setHueIncrement(hueInc);
        assertEquals(hueInc, cSC.getHueIncrement());
    }

    /**
     * 
     */
    @Test
    final void testSetHueIncrement()
    {
        float original;
        ColorSetCreator setMkr = new ColorSetCreator(); 
        original = setMkr.getHueIncrement(); 
        setMkr.setHueIncrement(original + 1.0f);
        assertEquals(original + 1.0f, setMkr.getHueIncrement());
    }

    /**
     * 
     */
    @Test
    final void testGetValueIncrement()
    {
        float valueInc = 4;
        cSC.setValueIncrement(valueInc);
        assertEquals(valueInc, cSC.getValueIncrement());
    }

    /**
     * 
     */
    @Test
    final void testSetValueIncrement()
    {
        float original;
        ColorSetCreator setMkr = new ColorSetCreator(); 
        original = setMkr.getValueIncrement(); 
        setMkr.setValueIncrement(original + 1.0f);
        assertEquals(original + 1.0f, setMkr.getValueIncrement());
    }

    /**
     * 
     */
    @Test
    final void testGetChromaIncrement()
    {
        float chromaIncrement = 3;
        cSC.setChromaIncrement(chromaIncrement);
        assertEquals(chromaIncrement, cSC.getChromaIncrement());
    }

    /**
     * 
     */
    @Test
    final void testSetChromaIncrement()
    {
        float original;
        ColorSetCreator setMkr = new ColorSetCreator(); 
        original = setMkr.getChromaIncrement(); 
        setMkr.setChromaIncrement(original + 1.0f);
        assertEquals(original + 1.0f, setMkr.getChromaIncrement());
    }

    /**
     * 
     */
    @Test
    final void testGetChromaLimit()
    {
        float chromaLimit = 30;
        cSC.setChromaLimit(chromaLimit);
        assertEquals(chromaLimit, cSC.getChromaLimit());
    }

    /**
     * 
     */
    @Test
    final void testGetValueLimit()
    {
        float valueLimit = 10;
        cSC.setValueLimit(valueLimit);
        assertEquals(valueLimit, cSC.getValueLimit());
    }

    /**
     * 
     */
    @Test
    final void testSetValueLimit()
    {
        float original;
        ColorSetCreator setMkr = new ColorSetCreator(); 
        original = setMkr.getValueLimit(); 
        setMkr.setValueLimit(original + 1.0f);
        assertEquals(original + 1.0f, setMkr.getValueLimit());
    }

    /**
     * 
     */
    @Test
    final void testGetHueLimit()
    {
        float hueLimit = 50;
        cSC.setHueLimit(hueLimit);
        assertEquals(hueLimit, cSC.getHueLimit());
    }

    /**
     * 
     */
    @Test
    final void testSetHueLimit()
    {
        float original ;
        ColorSetCreator setMkr = new ColorSetCreator(); 
        original = setMkr.getHueLimit(); 
        setMkr.setHueLimit(original + 1.0f);
        assertEquals(original + 1.0f, setMkr.getHueLimit());
    }

//    @Test
//    final void testCreate1DSet()
//    {
//        chromaLimit = 9;
//        valueLimit = 6;
//        chromaInc = 3;
//        valueInc = 1;
//        C.setChromaIncrement(chromaInc);
//        C.setValueIncrement(valueInc);
//        C.setChromaLimit(chromaLimit);
//        C.setValueLimit(valueLimit);
//        assertEquals(v, C.create1DSet(MunDimension.VALUE, start));
//        assertEquals(c, C.create1DSet(MunDimension.CHROMA, start));
//
//    }

    /**
     * 
     */
    @Test
    final void testCreate2DSetTest1()
    {
        MunsellColor start = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        ColorSetCreator setMkr = new ColorSetCreator(2.5f, 1.0f, 2.0f);
        setMkr.setValueLimit(2.0f);
        setMkr.setChromaLimit(4);

        MunsellColor[][] array = new MunsellColor[2][2];

        array[0][0] = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        array[0][1] = new MunsellColor(2.5f, HueCode.R, 1.0f, 4.0f);
        array[1][0] = new MunsellColor(2.5f, HueCode.R, 2.0f, 2.0f);
        array[1][1] = new MunsellColor(2.5f, HueCode.R, 2.0f, 4.0f);

        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {

                System.out.println(" " + array[i][j].getHueNum() + " " + array[i][j].getHueCode()
                		+ " " + array[i][j].getValue() + " " + array[i][j].getChroma());

            }
        }
        MunsellColor[][] createdSet = setMkr.create2DSet(MunDimension.VALUE,
        		MunDimension.CHROMA, start);
        for (int i = 0; i < createdSet.length; i++)
        {
            for (int j = 0; j < createdSet[0].length; j++)
            {

                System.out.println(" " + array[i][j].getHueNum() + " " + array[i][j].getHueCode()
                		+ " " + array[i][j].getValue() + " " + array[i][j].getChroma());
                System.out.println("CreatedSet " + createdSet[i][j].getHueNum() + " " 
                		+ createdSet[i][j].getHueCode()
                        + " " + createdSet[i][j].getValue() + " " + array[i][j].getChroma());
                assertEquals(createdSet[i][j], array[i][j]);
                
            }
        }

    }

    /**
     * 
     */
    @Test
    final void testCreate2DSetTest2()
    {
        MunsellColor start = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        ColorSetCreator setMkr = new ColorSetCreator(2.5f, 1.0f, 2.0f);
        setMkr.setValueLimit(2.0f);
        setMkr.setChromaLimit(4.0f);

        MunsellColor[][] array = new MunsellColor[2][2];

        array[0][0] = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        array[0][1] = new MunsellColor(2.5f, HueCode.R, 1.0f, 4.0f);
        array[1][0] = new MunsellColor(2.5f, HueCode.R, 2.0f, 2.0f);
        array[1][1] = new MunsellColor(2.5f, HueCode.R, 2.0f, 4.0f);
        

        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {

                System.out.println(" " + array[i][j].getHueNum() + " " 
                		+ array[i][j].getHueCode() + " "
                        + array[i][j].getValue() + " " + array[i][j].getChroma());

            }
        }
        MunsellColor[][] createdSet = setMkr.create2DSet(MunDimension.VALUE, 
        		MunDimension.CHROMA, start);
        for (int i = 0; i < createdSet.length; i++)
        {
            for (int j = 0; j < createdSet[0].length; j++)
            {

                System.out.println(" " + array[i][j].getHueNum() + " " + array[i][j].getHueCode()
                		+ " "
                        + array[i][j].getValue() + " " + array[i][j].getChroma());
                System.out.println("CreatedSet " + createdSet[i][j].getHueNum() + " " 
                        + createdSet[i][j].getHueCode()
                        + " " + createdSet[i][j].getValue() + " " + array[i][j].getChroma());
                assertArrayEquals(createdSet,array);
            }
        }
        
        
      

    }

    
    /**
     * 
     */
    @Test
    final void testCreate2DSetTest3()
    {
        MunsellColor start = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        ColorSetCreator setMkr = new ColorSetCreator(2.5f, 1.0f, 2.0f);
        
        setMkr.setChromaLimit(4.0f);
        setMkr.setHueLimit(5.0f);
        

        MunsellColor[][] array = new MunsellColor[2][2];
        array[0][0] = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        array[0][1] = new MunsellColor(2.5f, HueCode.R, 1.0f, 4.0f);
        array[1][0] = new MunsellColor(5.0f, HueCode.R, 1.0f, 2.0f);
        array[1][1] = new MunsellColor(5.0f, HueCode.R, 1.0f, 4.0f);

        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[0].length; j++)
            {

                System.out.println(" " + array[i][j].getHueNum() + " " + array[i][j].getHueCode()
                		+ " "
                        + array[i][j].getValue() + " " + array[i][j].getChroma());

            }
        }
        MunsellColor[][] createdSet = setMkr.create2DSet(MunDimension.HUE,
        		MunDimension.CHROMA, start);
        for (int i = 0; i < createdSet.length; i++)
        {
            for (int j = 0; j < createdSet[0].length; j++)
            {

                System.out.println(" " + array[i][j].getHueNum() + " " + array[i][j].getHueCode()
                		+ " "
                        + array[i][j].getValue() + " " + array[i][j].getChroma());
                System.out.println("CreatedSet " + createdSet[i][j].getHueNum() + " "
                        + createdSet[i][j].getHueCode()
                        + " " + createdSet[i][j].getValue() + " " + array[i][j].getChroma());
                assertArrayEquals(createdSet,array);
            }
        }
        
    }
    /**
     * 
     */
    @Test
    final void testCreate1DSetReInitialized()
    {
        MunsellColor start = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        ColorSetCreator setMkr = new ColorSetCreator(2.5f, 1.0f, 2.0f);

        setMkr.setChromaLimit(4.0f);

        MunsellColor[] array = new MunsellColor[2];
        MunsellColor[] createdSet = setMkr.create1DSet(MunDimension.CHROMA, start);

        array[0] = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        array[1] = new MunsellColor(2.5f, HueCode.R, 1.0f, 4.0f);

        for (int i = 0; i < createdSet.length; i++)
        {

            System.out.println(" " + array[i].getHueNum() + " " + array[i].getHueCode() 
            		+ " " + array[i].getValue()
                    + " " + array[i].getChroma());
            System.out.println("CreatedSet " + createdSet[i].getHueNum() + " " 
                    + createdSet[i].getHueCode() + " "
                    + createdSet[i].getValue() + " " + array[i].getChroma());
            assertEquals(createdSet[i], array[i]);

        }

    }
    
    /**
     * 
     */
    @Test
    final void testCreate1DSetReInitialized2()
    {
        MunsellColor start = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        ColorSetCreator setMkr = new ColorSetCreator(2.5f, 1.0f, 2.0f);

        setMkr.setChromaLimit(4.0f);

        MunsellColor[] array = new MunsellColor[2];
        MunsellColor[] createdSet = setMkr.create1DSet(MunDimension.CHROMA, start);

        array[0] = new MunsellColor(2.5f, HueCode.R, 1.0f, 2.0f);
        array[1] = new MunsellColor(2.5f, HueCode.R, 1.0f, 4.0f);

        for (int i = 0; i < createdSet.length; i++)
        {

            System.out.println(" " + array[i].getHueNum() + " " + array[i].getHueCode() 
            		+ " " + array[i].getValue()
                    + " " + array[i].getChroma());
            System.out.println("CreatedSet " + createdSet[i].getHueNum() + " " 
                    + createdSet[i].getHueCode() + " "
                    + createdSet[i].getValue() + " " + array[i].getChroma());
            assertArrayEquals(createdSet, array);

        }
    }
    
    
    /**
     * 
     */
    @Test
    final void testCreate1DSetReInitialized3()
    {
        MunsellColor start = new MunsellColor(2.5f, HueCode.R, 2.0f, 2.0f);
        ColorSetCreator setMkr = new ColorSetCreator(2.5f, 2.0f, 2.0f);

        setMkr.setValueLimit(6.0f);

        MunsellColor[] array = new MunsellColor[3];
        MunsellColor[] createdSet = setMkr.create1DSet(MunDimension.VALUE, start);

        array[0] = new MunsellColor(2.5f, HueCode.R, 2.0f, 2.0f);
        array[1] = new MunsellColor(2.5f, HueCode.R, 4.0f, 2.0f);
        array[2] = new MunsellColor(2.5f, HueCode.R, 6.0f, 2.0f);
        for (int i = 0; i < createdSet.length; i++)
        {

            System.out.println(" " + array[i].getHueNum() + " " + array[i].getHueCode() + " " 
            		+ array[i].getValue()
                    + " " + array[i].getChroma());
            System.out.println("CreatedSet " + createdSet[i].getHueNum() + " " 
                    + createdSet[i].getHueCode() + " "
                    + createdSet[i].getValue() + " " + array[i].getChroma());
            assertArrayEquals(createdSet, array);

        }

    }
    
    /**
     * 
     */
    @Test
    final void testCreate1DSetReInitialized4()
    {
        MunsellColor start = new MunsellColor(2.0f, HueCode.R, 2.0f, 2.0f);
        ColorSetCreator setMkr = new ColorSetCreator(2.0f, 2.0f, 2.0f);

        setMkr.setHueLimit(6.0f);

        MunsellColor[] array = new MunsellColor[3];
        MunsellColor[] createdSet = setMkr.create1DSet(MunDimension.HUE, start);

        array[0] = new MunsellColor(2.0f, HueCode.R, 2.0f, 2.0f);
        array[1] = new MunsellColor(4.0f, HueCode.R, 2.0f, 2.0f);
        array[2] = new MunsellColor(6.0f, HueCode.R, 2.0f, 2.0f);
        for (int i = 0; i < createdSet.length; i++)
        {

            System.out.println(" " + array[i].getHueNum() + " " + array[i].getHueCode() 
            		+ " " + array[i].getValue()
                    + " " + array[i].getChroma());
            System.out.println("CreatedSet " + createdSet[i].getHueNum() + " " 
                    + createdSet[i].getHueCode() + " "
                    + createdSet[i].getValue() + " " + array[i].getChroma());
            assertArrayEquals(createdSet, array);

        }

    }
}






































