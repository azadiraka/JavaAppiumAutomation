import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {

    int actual = this.getLocalNumber();

    @Test
    public void testGetLocalNumber() {
        int expected = 14;
        Assert.assertTrue("getLocalNumber() returns the wrong value",actual == expected);
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("getClassNumber() returns the wrong value, it doesn't more than 45", this.getClassNumber() > 45);
    }
}
