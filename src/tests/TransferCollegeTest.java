package tests;

import static org.junit.Assert.*;

import model.TransferCollege;
import org.junit.Before;
import org.junit.Test;


/**
 * A test class for transfer College.
 * @author GROUP8(Bui)
 * @version 12/6/2016
 *
 */
public class TransferCollegeTest {
    /**
     * Set up method.
     */
    @Before
    public void setUp() {
    }
    /**
     * Test Constructor.
     */
    @Test
    public void testConstructor() {
        final TransferCollege trans = new TransferCollege("SSC", 3.8, "CSS", "2012", "Spring");
        assertEquals("SSC", trans.getMyName());
        assertEquals(3.8, trans.getMyGPA(), 0.000001);
        assertEquals("CSS", trans.getMyDegree());
        assertEquals("2012", trans.getMyYear());
        assertEquals("Spring", trans.getMyTerm());
    }
    /**
     * Tests constructor with null College name passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNullCollegeConstructor() {           
        final TransferCollege trans = new TransferCollege(null, 3.8, "abc", "abc", "abc");
    }
    /**
     * Tests constructor with null degree name passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNullDegreeConstructor() {           
        final TransferCollege trans = new TransferCollege("abc", 3.8, null, "abc", "abc");
    }
    /**
     * Tests constructor with null degree name passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNullYearConstructor() {           
        final TransferCollege trans = new TransferCollege("abc", 3.8, "abc", null, "abc");
    }
    /**
     * Tests constructor with null term passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNullTermsConstructor() {           
        final TransferCollege trans = new TransferCollege("abc", 3.8, "abc", "abc", null);
    }
    
   /**
    * Test toString method.
    */
    @Test
    public void testToString() {
        final TransferCollege trans = new TransferCollege("abc", 3.8, "abc", "abc", "Spring");
        assertEquals("abc", trans.toString());
    }
    /**
     * Testing set Name.
     */
    @Test
    public void testSetName() {
        final TransferCollege trans = new TransferCollege("SSC", 3.8, "CSS", "2012", "Spring");
        trans.setMyName("temp");
        assertEquals("temp", trans.getMyName());
    }
    /**
     * Testing set GPA.
     */
    @Test
    public void testSetGPA() {
        final TransferCollege trans = new TransferCollege("SSC", 3.8, "CSS", "2012", "Spring");
        trans.setMyGPA(4.0);
        assertEquals(4.0, trans.getMyGPA(), 0.000001);
    }
    /**
     * Testing set Degree.
     */
    @Test
    public void testSetDegree() {
        final TransferCollege trans = new TransferCollege("SSC", 3.8, "CSS", "2012", "Spring");
        trans.setMyDegree("CES");
        assertEquals("CES", trans.getMyDegree());
    }
    /**
     * Testing set Year.
     */
    @Test
    public void testSetYear() {
        final TransferCollege trans = new TransferCollege("SSC", 3.8, "CSS", "2012", "Spring");
        trans.setMyYear("2016");
        assertEquals("2016", trans.getMyYear());
    }
    /**
     * Testing set Term.
     */
    @Test
    public void testSetTerm() {
        final TransferCollege trans = new TransferCollege("SSC", 3.8, "CSS", "2012", "Spring");
        trans.setMyTerm("Summer");
        assertEquals("Summer", trans.getMyTerm());
    }
}
