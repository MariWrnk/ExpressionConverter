import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Tests {

    @Test
    public void mapTypeCheckTest() throws Exception {
        Map mp = new Map("element * 12", "element + 5");
        mp.process();
        boolean result = mp.typeCheck();
        assertTrue(result);
    }

    @Test
    public void mapTypeCheckFailTest() throws Exception {
        Map mp = new Map("element + 9", "element > 5");
        mp.process();
        boolean result = mp.typeCheck();
        assertFalse(result);
    }

    @Test
    public void mapExpressionTest() throws Exception {
        Map mp = new Map("element + 8", "element * 5");
        mp.process();
        String result = mp.toString();
        String expexted = "((element * 5) + 8)";
        assertEquals(expexted, result);
    }

    @Test(expected = Exception.class)
    public void filterSyntaxTest() throws Exception {
        Filter fl = new Filter("element > 5 < 8", "element");
        fl.process();
    }

    @Test
    public void filterExpressionTest() throws Exception {
        Filter fl = new Filter("element > 5", "(element+1)");
        fl.process();
        String expected = "(element>4)";
        String result = fl.toString();
        assertEquals(expected, result);
    }

    @Test(expected = Exception.class)
    public void filterTypeCheckTest() throws Exception {
        Filter fl = new Filter("element * 5", "(element+1)");
        fl.process();
    }

    @Test
    public void expressionResultCheck() throws Exception {
        Expression ex = new Expression("filter{(element > 9)}%>%map{(element + 1)}%>%filter{(element < 25)}");
        ex.run();
        String expected = "filter{(element>9)&(element<24)}%>%map{(element+1)}";
        String result = ex.response();
        assertEquals(expected, result);
    }

}
