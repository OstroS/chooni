package pl.akiba.helper;

import java.util.Map;
import java.util.Set;
import junit.framework.TestCase;

/**
 *
 * @author kostrows
 */
public class StringHelperTest extends TestCase {

    private StringHelper stringHelper;
    private String testString;

    public StringHelperTest(String testName) {
        super(testName);

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        stringHelper = new StringHelper();
        testString = "key1=val1&key2=asdasdad&   key3 =    123{:..' & key4 = val4";
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getValue method, of class StringHelper.
     */
    public void testGetValueTestCase1() {
        System.out.println("getValue#TestCase1");
        StringHelper instance = new StringHelper();
        String expResult = "123{:..'";
        String result = instance.getValue(testString, "key3");
        assertEquals(expResult, result);

    }

    public void testGetValueTestCase2() {
        System.out.println("getValue#TestCase2");
        StringHelper instance = new StringHelper();
        String expResult = "val1";
        String result = instance.getValue(testString, "key1");
        assertEquals(expResult, result);

    }

    public void testGetValueTestCase3() {
        System.out.println("getValue#TestCase3");
        StringHelper instance = new StringHelper();
        String expResult = "val4";
        String result = instance.getValue(testString, "key4");
        assertEquals(expResult, result);

    }

    public void testGetKeyValueMap() {
        System.out.println("testGetKeyValueMap#TestCase1");
        StringHelper instance = new StringHelper();
        Map<String, String> keyValueMap = instance.getKeyValueMap(testString);
        Set<String> keySet = keyValueMap.keySet();
        
        for (String key : keySet) {
            String result = keyValueMap.get(key);
            String expResult = instance.getValue(testString, key);
            assertEquals(expResult, result);
            
        }
    }
}
