package munsell;


public enum HueCode
{
    R(0), YR(1), Y(2), GY(3), G(4), BG(5), B(6), PB(7), P(8), RP(9);
    
    private final int value;
    
    /**
     * @param value Value passed
     */
    private HueCode (int value)
    {
        this.value = value;
    }
    
    /**
     * @return Value returned
     */
    public int getValue ()
    {
        return value; 
    }
}

