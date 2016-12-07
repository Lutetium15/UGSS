package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Alumni;
import model.Internship;
import model.Job;
import model.TransferCollege;

import org.junit.Before;
import org.junit.Test;



/**
 * Test for Alumni Class.
 * @author GROUP8(Alec)
 * @version 12/6/2016
 *
 */
public class AlumniTest {
    /**
     * Alumni for Testing.
     */
    private Alumni myTestAlumni;
    /**
     * Sets up the environment for testing.
     * @throws Exception if there is a problem during setup.
     */
    @Before
    public void setUp() throws Exception {
        myTestAlumni = new Alumni("ARW", "CSS", 
                "BS", "2017", "Spring", 3.7, 
                "arw96@uw.edu", "junk@live.com",
                null, null, null);
    }
    /**
     * Tests the Constructors.
     */
    @Test
    public void testConstructors() {
        final Alumni newAlumni = new Alumni("Junk", 
                "Junk", "Junk", "Junk", "Junk", 3,
                "Junk", "Junk", null, null, null);
        final Alumni newAlumni2 = new Alumni(null,
                null, null, "Junk", "Junk", 3,
                "Junk", "Junk", null, null, null);
        final Alumni newAlumni3 = new Alumni("Junk", 0, "Junk", "Junk");
        final Alumni newAlumni4 = new Alumni(null, 3, null, null);
        assertNotNull(newAlumni);
        assertNotNull(newAlumni2);
        assertNotNull(newAlumni3);
        assertNotNull(newAlumni4);
    }

    /**
     * Test Name Getters and Setters.
     */
    @Test
    public void testMyName() {
        assertEquals(myTestAlumni.getMyName(), "ARW");
        myTestAlumni.setMyName("Alec Walsh");
        assertEquals(myTestAlumni.getMyName(), "Alec Walsh");
    }
    /**
     * Test Id Getters and Setters.
     */
    @Test
    public void testMyID() {
        assertEquals(myTestAlumni.getMyID(), 0);
        myTestAlumni.setMyID(9001);
        assertEquals(myTestAlumni.getMyID(), 9001);
    }
    /**
     * Test DegreeTrack Getters and Setters.
     */
    @Test
    public void testMyDegreeTrack() {
        assertEquals(myTestAlumni.getMyDegreeTrack(), "CSS");
        myTestAlumni.setMyDegreeTrack("CES");
        assertEquals(myTestAlumni.getMyDegreeTrack(), "CES");
    }
    /**
     * Test Degree Level Getters and Setters.
     */
    @Test
    public void testMyDegreeLevel() {
        assertEquals(myTestAlumni.getMyDegreeLevel(), "BS");
        myTestAlumni.setMyDegreeLevel("Ph.D");
        assertEquals(myTestAlumni.getMyDegreeLevel(), "Ph.D");
    }
    /**
     * Test Year Getters and Setters.
     */
    @Test
    public void testMyYear() {
        assertEquals(myTestAlumni.getMyYear(), "2017");
        myTestAlumni.setMyYear("2020");
        assertEquals(myTestAlumni.getMyYear(), "2020");
    }
    /**
     * Test Term Getters and Setters.
     */
    @Test
    public void testMyTerm() {
        assertEquals(myTestAlumni.getMyTerm(), "Spring");
        myTestAlumni.setMyTerm("Winter");
        assertEquals(myTestAlumni.getMyTerm(), "Winter");
    }
    /**
     * Test GPA Getters and Setters.
     */
    @Test
    public void testMyGPA() {
        assertEquals(myTestAlumni.getMyCurrentGPA(), 3.7, .01);
        myTestAlumni.setMyCurrentGPA(4.0);
        assertEquals(myTestAlumni.getMyCurrentGPA(), 4.0, .01);
    }
    /**
     * Test University Email Getters and Setters.
     */
    @Test
    public void testMyUniEmail() {
        assertEquals(myTestAlumni.getMyUniEmail(), "arw96@uw.edu");
        myTestAlumni.setMyUniEmail("arw@uw.edu");
        assertEquals(myTestAlumni.getMyUniEmail(), "arw@uw.edu");
    }
    /**
     * Test Personal Email Getters and Setters.
     */
    @Test
    public void testMyPersEmail() {
        assertEquals(myTestAlumni.getMyPersEmail(), "junk@live.com");
        myTestAlumni.setMyPersEmail("junk@gmail.com");
        assertEquals(myTestAlumni.getMyPersEmail(), "junk@gmail.com");
    }
    /**
     * Test Transfer College Getters and Setters.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testMyTransferColleges() {
        assertEquals(myTestAlumni.getMyTransferColleges(), null);
        final ArrayList list = new ArrayList();
        list.add(new TransferCollege("TheSchool", 4, "AS", "2014", "Spring"));
        myTestAlumni.setMyTransferColleges(list);
        assertEquals(myTestAlumni.getMyTransferColleges().get(0).toString(), "TheSchool");
    }
    /**
     * Test Job Getters and Setters.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testMyJobs() {
        assertEquals(myTestAlumni.getMyJobs(), null);
        final ArrayList list = new ArrayList();
        list.add(new Job("TheWork", "Manager", "None", "None", "None", 13, true));
        myTestAlumni.setMyJobs(list);
        assertEquals(myTestAlumni.getMyJobs().get(0).toString(), "TheWork");
    }
    /**
     * Test Internship Getters and Setters.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testMyInternships() {
        assertEquals(myTestAlumni.getMyInternships(), null);
        final ArrayList list = new ArrayList();
        list.add(new Internship("TheInternship", "Manager", "None", "None", "None", 13, 1));
        myTestAlumni.setMyInternships(list);
        assertEquals(myTestAlumni.getMyInternships().get(0).toString(), "TheInternship");
    }
}
