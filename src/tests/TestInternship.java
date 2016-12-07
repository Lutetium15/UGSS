package tests;

import static org.junit.Assert.*;

import model.Internship;
import org.junit.Test;



/**
 * Tests of the internship class.
 * @author GROUP8(Dema)
 * @version 12/04/2016
 */
public class TestInternship {

    // CONSTRUCTOR TESTS

    /**
     * Tests constructor under normal expected parameters.
     */
    @Test
    public void testConstructorNormal() {            
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);

        // Getters are also tested here
        assertEquals("Amazon", inship.getMyCompany());
        assertEquals("SDE", inship.getMyPosition());
        assertEquals("Coding", inship.getMySkillsReq());
        assertEquals("I code stuff", inship.getMyDescription());
        assertEquals("", inship.getMyMiscComments());
        assertEquals(20.00, inship.getMyWage(), .00001);
        assertEquals(3, inship.getMyDuration(), .00001);
    }

    /**
     * Tests constructor to see whether it properly throws exception if no company name passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNullCompanyConstructor() {           
        final Internship inship = new Internship(
                null, "SDE", "Coding", "I code stuff", "", 20.00, 3);
    }

    /**
     * Tests constructor to see whether it properly throws exception if no position passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNullPositionConstructor() {           
        final Internship inship = new Internship(
                "Amazon", null, "Coding", "I code stuff", "", 20.00, 3);
    }

    /**
     * Tests constructor to see whether it accepts other nulls.
     */
    @Test
    public void testOkNullConstructor() {           
        Internship inship = new Internship(
                "Amazon", "SDE", null, "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship = new Internship("Amazon", "SDE", "Coding", null, "", 20.00, 3);
        assertNotNull(inship);
        inship = new Internship("Amazon", "SDE", "Coding", "I code stuff", null, 20.00, 3);
        assertNotNull(inship);
    }

    /**
     * Tests constructor to see whether it properly throws exception if negative wage passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeWage() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", -20.00, 3);
    }

    /**
     * TTests constructor to see whether 
     * it properly throws exception if negative duration passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeDurationConstructor() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, -3);
    }

    // SETTER TESTS
    
    /**
     * Tests setWage to see whether it properly sets the wage field.
     */
    @Test
    public void testSetWageNormal() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyWage(40.00);
        assertEquals(40.00, inship.getMyWage(), .00001);
    }
    
    /**
     * Tests setWage to see whether it properly throws an exception when wage is negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetWageNegative() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyWage(-40.00);
    }
    
    /**
     * Tests setDuration to see whether it properly sets the duratin field.
     */
    @Test
    public void testSetDurationNormal() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyDuration(4);
        assertEquals(4, inship.getMyDuration(), .00001);
    }
    
    /**
     * Tests setDuration to see whether 
     * it properly throws an exception when duration is negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetDurationNegative() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyDuration(-4);
    }

    /**
     * Tests setCompany to see whether it properly sets the company field.
     */
    @Test
    public void testSetCompanyNormal() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyCompany("Microsoft");
        assertEquals("Microsoft", inship.getMyCompany());
    }

    /**
     * Tests setCompany to see whether
     *  it properly throws an exception when company name passed in is null..
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetCompanyNull() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyCompany(null);
    }

    /**
     * Tests setPosition to see whether it properly sets the position field.
     */
    @Test
    public void testSetPositionNormal() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyPosition("QA");
        assertEquals("QA", inship.getMyPosition());
    }

    /**
     * Tests setPosition to see whether
     *  it properly throws an exception when position passed in is null..
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPositionNull() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyPosition(null);
    }

    /**
     * Tests setReqSkills to see whether it properly sets the SkillsReq field.
     */
    @Test
    public void testSetSkillsReq() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMySkillsReq("Working hard");
        assertEquals("Working hard", inship.getMySkillsReq());

        inship.setMySkillsReq(null);
        assertNull(inship.getMySkillsReq());       
    }

    /**
     * Tests setDescription to see whether it properly sets the description field.
     */
    @Test
    public void testSetDescription() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyDescription("Have to work hard");
        assertEquals("Have to work hard", inship.getMyDescription());

        inship.setMyDescription(null);
        assertNull(inship.getMyDescription());       
    }

    /**
     * Tests setMiscComments to see whether it properly sets the MiscComments field.
     */
    @Test
    public void testSetMiscComments() {           
        final Internship inship = new Internship(
                "Amazon", "SDE", "Coding", "I code stuff", "", 20.00, 3);
        assertNotNull(inship);
        inship.setMyMiscComments("Poor chap has to work hard");
        assertEquals("Poor chap has to work hard", inship.getMyMiscComments());

        inship.setMyMiscComments(null);
        assertNull(inship.getMyMiscComments());       
    }

}
