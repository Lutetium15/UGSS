package model;
import java.io.Serializable;

/**
 * TransferCollege class handle transfer colleges. 
 * @author GROUP8(Bui)
 * @version 11/16/2016
 */
public class TransferCollege implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5402189238974342289L;

    /** Variable for name. */
    private String myName;
    
    /** Variable for degree. */
    private String myDegree;
    
    /** Variable for term. */
    private String myTerm;
    
    /** Variable for year. */
    private String myYear;
    
    /** Variable for gpa. **/
    private double myGPA;
    /**
     * Constructor for Transfer College.
     * @param theName name of College
     * @param theGPA  Student's gpa
     * @param theDegree   degree track.
     * @param theYear     year completed
     * @param theTerm     term completed.
     */
    public TransferCollege(final String theName, final double theGPA, 
            final String theDegree, final String theYear, final String theTerm) {
        if (theName == null || theDegree == null || theYear == null
                || theTerm == null) {
            throw new IllegalArgumentException();
        }
        myName = theName;
        myGPA = theGPA;
        myDegree = theDegree;
        myYear = theYear;
        myTerm = theTerm;
    }
    /**
     * @return the myName
     */
    public String getMyName() {
        return myName;
    }
    /**
     * @param theName the myName to set
     */
    public void setMyName(final String theName) {
        this.myName = theName;
    }
    /**
     * @return the myDegree
     */
    public String getMyDegree() {
        return myDegree;
    }
    /**
     * @param theDegree the myDegree to set
     */
    public void setMyDegree(final String theDegree) {
        this.myDegree = theDegree;
    }
    /**
     * @return the myTerm
     */
    public String getMyTerm() {
        return myTerm;
    }
    /**
     * @param theTerm the myTerm to set
     */
    public void setMyTerm(final String theTerm) {
        this.myTerm = theTerm;
    }
    /**
     * @return the myYear
     */
    public String getMyYear() {
        return myYear;
    }
    /**
     * @param theYear the myYear to set
     */
    public void setMyYear(final String theYear) {
        this.myYear = theYear;
    }
    /**
     * @return the myGPA
     */
    public double getMyGPA() {
        return myGPA;
    }
    /**
     * @param theGPA the myGPA to set
     */
    public void setMyGPA(final double theGPA) {
        this.myGPA = theGPA;
    }
    /**
     * toString override to give name.
     */
    @Override
    public String toString() {
        return myName;
    }
    
}
