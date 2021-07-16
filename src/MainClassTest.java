import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    int actual = this.getLocalNumber();

    @Test
    public void testGetLocalNumber() {
        int expected = 14;
        Assert.assertTrue("getLocalNumber() returns the wrong value",actual == expected);
    }
}
