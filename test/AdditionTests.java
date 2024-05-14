import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//for a way to remember how to make unit tests!
class AdditionTests {
    @Test
    void testSimpleAddition() {
        int x = 1;
        int y = 2;
        assertEquals(3,add(x,y));
    }

    private int add(int x,int y)
    {
        return x+y;
    }


    @Test
    public void testPositiveNegativeAddition() {
        assertEquals(-5,add(-5,0));
    }


    @Test
    public void testNegativePositiveAddition() {
        assertEquals(4,add(2,2));
    }


    @Test
    public void testNegativeAddition() {
        assertEquals(-3,add(-2,-1));
    }


}