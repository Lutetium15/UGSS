package tests;

import static org.junit.Assert.*;

import java.util.List;

import model.Alumni;
import model.AlumniCollection;
import org.junit.Test;



/**
 * Test class for Alumni Collection.
 * @author GROUP8(Dema)
 * @version 12/6/2016
 *
 */
public class AlumniCollectionTests {
    /**
     * Test reportSearch.
     */
    @Test
    public void testReportAlumni() {
        assertNotNull(AlumniCollection.reportsearch("All", "All"));
    }
    /**
     * Test reportAlumni with different Condition.
     */
    @Test
    public void testTrackReportAlumni1() {
        final Object[] degreeTrack = AlumniCollection.getDegreeTrack();
        assertNotNull(degreeTrack[0]);
        final String temp = degreeTrack[0].toString();
        assertNotNull(AlumniCollection.reportsearch("All", temp));
    }
    /**
     * Test reportAlumni with different Condition.
     */
    @Test
    public void testLevelReportAlumni() {
        final Object[] degreeLevel = AlumniCollection.getDegreeLevel();
        assertNotNull(degreeLevel[0]);
        final String temp = degreeLevel[0].toString();
        assertNotNull(AlumniCollection.reportsearch(temp, "All"));
    }
    /**
     * Test reportAlumni with different Condition.
     */
    @Test
    public void testAllReportAlumni() {
        final Object[] degreeTrack = AlumniCollection.getDegreeTrack();
        final Object[] degreeLevel = AlumniCollection.getDegreeLevel();
        final String track = degreeTrack[0].toString();
        final String level = degreeLevel[0].toString();
        assertNotNull(degreeTrack[0]);        
        assertNotNull(degreeLevel[0]);        
        assertNotNull(AlumniCollection.reportsearch(level, track));
    }
    /**
     * Test get degreeTrack.
     */
    @Test
    public void testDegreeTrack() {
        final Object[] degreeTrack = AlumniCollection.getDegreeTrack();
        final Alumni alum = AlumniCollection.searchID(1);
        boolean temp = false;
        assertNotNull(alum);
        assertNotNull(degreeTrack);
        for (int i = 0; i < degreeTrack.length; i++) {
            if (alum.getMyDegreeTrack().equals(degreeTrack[i])) {
                temp = true;
                break;
            } else {
                temp = false;
            }
        }
        assertTrue(temp);
    }
    /**
     * Test get degreeLevel.
     */
    @Test
    public void testDegreeLevel() {
        final Object[] degreeLevel = AlumniCollection.getDegreeLevel();
        final Alumni alum = AlumniCollection.searchID(1);
        boolean temp = false;
        assertNotNull(alum);
        assertNotNull(degreeLevel);
        for (int i = 0; i < degreeLevel.length; i++) {
            if (alum.getMyDegreeLevel().equals(degreeLevel[i])) {
                temp = true;
                break;
            } else {
                temp = false;
            }
        }
        assertTrue(temp);
    }
    
    /** Tests whether name is correctly gotten. */
    @Test
    public void testGetName() {
        final List<Alumni> alumni = AlumniCollection.searchName("Alec Walsh");
        Alumni alum = null;
        if (alumni.size() != 0) {
            alum = alumni.get(0);
        }
        assertNotNull(alum);
        assertEquals("Alec Walsh", alum.getMyName());
    }
    
    /** Tests whether ID is correctly gotten. */
    @Test
    public void testGetID() {
        final Alumni alumni = AlumniCollection.searchID(1);
        assertNotNull(alumni);
        assertEquals("Alec Walsh", alumni.getMyName());
        assertEquals(1, alumni.getMyID());
    }
    
    /** Tests whether returned list not not null. */
    @Test
    public void testGetAlumni() {
        final List<Alumni> alumni = AlumniCollection.getAlumni();
        assertNotNull(alumni);
    }
    
    
    
}
