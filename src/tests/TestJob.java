package tests;

import static org.junit.Assert.*;

import model.Job;
import org.junit.Test;



/**
 * Tests of the job class.
 * @author GROUP8(Dema)
 *@version 12/04/2016
 */
public class TestJob {
    // CONSTRUCTOR TESTS
    
    /**
     * Tests constructor under normal expected parameters.
     */
    @Test
    public void testConstructorNormal() {            
        final Job job = new Job("Amazon", "SDE", "Coding",
                "I code stuff", "", 80000, true);
        
        // Getters are also tested here
        assertEquals("Amazon", job.getMyCompany());
        assertEquals("SDE", job.getMyPosition());
        assertEquals("Coding", job.getMySkillsReq());
        assertEquals("I code stuff", job.getMyDescription());
        assertEquals("", job.getMyMiscComments());
        assertEquals(80000, job.getSalary(), .00001);
        assertTrue(job.isActive());
    }
    
    /**
     * Tests constructor to see whether it properly throws exception if no company name passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNullCompanyConstructor() {           
        final Job job = new Job(
                    null, "SDE", "Coding", "I code stuff", "", 80000, true);
    }
    
    /**
     * Tests constructor to see whether it properly throws exception if no position passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNullPositionConstructor() {           
        final Job job = new Job(
                    "Amazon", null, "Coding", "I code stuff", "", 80000, true);
    }
    
    /**
     * Tests constructor to see whether it accepts other nulls.
     */
    @Test
    public void testOkNullConstructor() {           
        Job job = new Job("Amazon", "SDE", null, "I code stuff", "", 80000, true);
        assertNotNull(job);
        job = new Job("Amazon", "SDE", "Coding", null, "", 80000, true);
        assertNotNull(job);
        job = new Job("Amazon", "SDE", "Coding", "I code stuff", null, 80000, true);
        assertNotNull(job);
    }
    
    /**
     * Tests constructor to see whether it properly throws exception if negative salary passed.
     */
    @SuppressWarnings("unused")
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSalary() {           
        final Job job = new Job(
                    "Amazon", "SDE", "Coding", "I code stuff", "", -10000, true);
    }
    
    /**
     * Tests constructor to see whether it accepts a false for the active field.
     */
    @Test
    public void testFalseActiveConstructor() {           
        final Job job = new Job(
                "Amazon", "SDE", null, "I code stuff", "", 80000, false);
        assertNotNull(job);
        assertFalse(job.isActive());
    }
    
    // SETTER TESTS
    
    /**
     * Tests setActive to see whether it properly sets the active field.
     */
    @Test
    public void testSetActive() {           
        final Job job = new Job(
                "Amazon", "SDE", null, "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setActive(true);
        assertTrue(job.isActive());
    }
    
    /**
     * Tests setSalary to see whether it properly sets the salary field.
     */
    @Test
    public void testSetSalaryNormal() {           
        final Job job = new Job(
                "Amazon", "SDE", null, "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setSalary(20000);
        assertEquals(20000, job.getSalary(), .00001);
    }
    
    /**
     * Tests setSalary to see whether 
     * it properly throws an exception when set salary is negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetSalaryNegative() {           
        final Job job = new Job(
                "Amazon", "SDE", null, "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setSalary(-20000);
    }
    
    /**
     * Tests setCompany to see whether it properly sets the company field.
     */
    @Test
    public void testSetCompanyNormal() {           
        final Job job = new Job(
                "Amazon", "SDE", "Coding", "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setMyCompany("Microsoft");
        assertEquals("Microsoft", job.getMyCompany());
    }
    
    /**
     * Tests setCompany to see whether 
     * it properly throws an exception when company name passed in is null..
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetCompanyNull() {           
        final Job job = new Job(
                "Amazon", "SDE", "Coding", "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setMyCompany(null);
    }
    
    /**
     * Tests setPosition to see whether it properly sets the position field.
     */
    @Test
    public void testSetPositionNormal() {           
        final Job job = new Job(
                "Amazon", "SDE", "Coding", "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setMyPosition("QA");
        assertEquals("QA", job.getMyPosition());
    }
    
    /**
     * Tests setPosition to see whether
     *  it properly throws an exception when position passed in is null..
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetPositionNull() {           
        final Job job = new Job(
                "Amazon", "SDE", "Coding", "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setMyPosition(null);
    }
    
    /**
     * Tests setReqSkills to see whether it properly sets the SkillsReq field.
     */
    @Test
    public void testSetSkillsReq() {           
        final Job job = new Job(
                "Amazon", "SDE", "Coding", "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setMySkillsReq("Working hard");
        assertEquals("Working hard", job.getMySkillsReq());
        
        job.setMySkillsReq(null);
        assertNull(job.getMySkillsReq());       
    }
    
    /**
     * Tests setDescription to see whether it properly sets the description field.
     */
    @Test
    public void testSetDescription() {           
        final Job job = new Job(
                "Amazon", "SDE", "Coding", "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setMyDescription("Have to work hard");
        assertEquals("Have to work hard", job.getMyDescription());
        
        job.setMyDescription(null);
        assertNull(job.getMyDescription());       
    }
    
    /**
     * Tests setMiscComments to see whether it properly sets the MiscComments field.
     */
    @Test
    public void testSetMiscComments() {           
        final Job job = new Job(
                "Amazon", "SDE", "Coding", "I code stuff", "", 80000, false);
        assertNotNull(job);
        job.setMyMiscComments("Poor chap has to work hard");
        assertEquals("Poor chap has to work hard", job.getMyMiscComments());
        
        job.setMyMiscComments(null);
        assertNull(job.getMyMiscComments());       
    }
    
    
    
}
