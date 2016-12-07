package model;
import java.util.List;

/**
 * Alumni object to store data on a single alumni.
 * 
 * @author GROUP8(Alec)
 * @version 12/3/2016
 *
 */
public class Alumni {
    /**
     * Alumni Name.
     */
    private String myName;
    /**
     * Alumni ID.
     */
    private int myID;
    /**
     * Alumni DegreeTrack.
     */
    private String myDegreeTrack;
    /**
     * Alumni Degree Level.
     */
    private String myDegreeLevel;
    /**
     * Alumni Graduated Year.
     */
    private String myYear;
    /**
     * Alumni Graduated Term.
     */
    private String myTerm;
    /**
     * Alumni GPA.
     */
    private double myCurrentGPA;
    /**
     * Alumni University Email.
     */
    private String myUniEmail;
    /**
     * Alumni Personal Email.
     */
    private String myPersEmail;
    /**
     * Alumni Internships.
     */
    private List<Internship> myInternships;
    /**
     * Alumni Jobs.
     */
    private List<Job> myJobs;
    /**
     * Alumni Transfer Colleges.
     */
    private List<TransferCollege> myTransferColleges;
    /**
     * 
     * @param theName The Name of the Alumni.
     * @param theDegreeTrack The Degree Track of the Alumni.
     * @param theDegreeLevel The Level of Degree of the Alumni.
     * @param theYear The Year the Alumni Graduated.
     * @param theTerm The Term the Alumni Graduated.
     * @param theCurrentGPA The GPA of the Alumni
     * @param theUniEmail The Alumni University Email.
     * @param thePersEmail The Alumni's Personal Email.
     * @param theInternships The Alumni's Internships.
     * @param theJobs The Alumni's Jobs.
     * @param theTransferColleges The Alumni's TransferColleges.
     */
    public Alumni(final String theName, final String theDegreeTrack, 
            final String theDegreeLevel, final String theYear, final String theTerm,
            final double theCurrentGPA, final String theUniEmail, final String thePersEmail, 
            final List<Internship> theInternships, final List<Job> theJobs, 
            final List<TransferCollege> theTransferColleges) {
        myName = theName;
        myDegreeTrack = theDegreeTrack;
        myDegreeLevel = theDegreeLevel;
        myYear = theYear;
        myTerm = theTerm;
        myCurrentGPA = theCurrentGPA;
        myUniEmail = theUniEmail;
        myPersEmail = thePersEmail;
        myInternships = theInternships;
        myJobs = theJobs;
        myTransferColleges = theTransferColleges;
        
    }
    /**
     * Constructor for report GUI that take unique parameters.
     * @param theName name 
     * @param theId    ID  
     * @param theTrack  degreeTrack
     * @param theLevel     degree Level
     */
    public Alumni(final String theName, final int theId, final String theTrack, 
            final String theLevel) {
        myName = theName;
        myID = theId;
        myDegreeTrack = theTrack;
        myDegreeLevel = theLevel;
    }
    /**
     * @return the myName
     */
    public String getMyName() {
        return myName;
    }
    /**
     * @return the myID
     */
    public int getMyID() {
        return myID;
    }
    /**
     * @return the myDegreeTrack
     */
    public String getMyDegreeTrack() {
        return myDegreeTrack;
    }
    /**
     * @return the myDegreeLevel
     */
    public String getMyDegreeLevel() {
        return myDegreeLevel;
    }
    /**
     * @return the myYear
     */
    public String getMyYear() {
        return myYear;
    }
    /**
     * @return the myTerm
     */
    public String getMyTerm() {
        return myTerm;
    }
    /**
     * @return the myCurrentGPA
     */
    public double getMyCurrentGPA() {
        return myCurrentGPA;
    }
    /**
     * @return the myUniEmail
     */
    public String getMyUniEmail() {
        return myUniEmail;
    }
    /**
     * @return the myPersEmail
     */
    public String getMyPersEmail() {
        return myPersEmail;
    }
    /**
     * @return the myInternships
     */
    public List<Internship> getMyInternships() {
        return myInternships;
    }
    /**
     * @return the myJobs
     */
    public List<Job> getMyJobs() {
        return myJobs;
    }
    /**
     * @return the myTransferColleges
     */
    public List<TransferCollege> getMyTransferColleges() {
        return myTransferColleges;
    }
    /**
     * @param theName the myName to set
     */
    public void setMyName(final String theName) {
        this.myName = theName;
    }
    /**
     * @param theID the myID to set
     */
    public void setMyID(final int theID) {
        this.myID = theID;
    }
    /**
     * @param theDegreeTrack the myDegreeTrack to set
     */
    public void setMyDegreeTrack(final String theDegreeTrack) {
        this.myDegreeTrack = theDegreeTrack;
    }
    /**
     * @param theDegreeLevel the myDegreeLevel to set
     */
    public void setMyDegreeLevel(final String theDegreeLevel) {
        this.myDegreeLevel = theDegreeLevel;
    }
    /**
     * @param theYear the myYear to set
     */
    public void setMyYear(final String theYear) {
        this.myYear = theYear;
    }
    /**
     * @param theTerm the myTerm to set
     */
    public void setMyTerm(final String theTerm) {
        this.myTerm = theTerm;
    }
    /**
     * @param theCurrentGPA the myCurrentGPA to set
     */
    public void setMyCurrentGPA(final double theCurrentGPA) {
        this.myCurrentGPA = theCurrentGPA;
    }
    /**
     * @param theUniEmail the myUniEmail to set
     */
    public void setMyUniEmail(final String theUniEmail) {
        this.myUniEmail = theUniEmail;
    }
    /**
     * @param thePersEmail the myPersEmail to set
     */
    public void setMyPersEmail(final String thePersEmail) {
        this.myPersEmail = thePersEmail;
    }
    /**
     * @param theInternships the myInternships to set
     */
    public void setMyInternships(final List<Internship> theInternships) {
        this.myInternships = theInternships;
    }
    /**
     * @param theJobs the myJobs to set
     */
    public void setMyJobs(final List<Job> theJobs) {
        this.myJobs = theJobs;
    }
    /**
     * @param theTransferColleges the myTransferColleges to set
     */
    public void setMyTransferColleges(final List<TransferCollege> theTransferColleges) {
        this.myTransferColleges = theTransferColleges;
    }
}
