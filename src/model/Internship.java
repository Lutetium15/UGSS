package model;

import java.io.Serializable;

/**
 * Holds data regarding an internship. 
 * @author GROUP8(Dema)
 *@version 11/15/2016
 */
public class Internship extends Employment implements Serializable {
    /**
     * id for serialization.
     */
    private static final long serialVersionUID = -8442795014510871481L;

    /** Hourly pay rate (dollars). */
    private double myWage;
    
    /** Duration of internship (months). */
    private int myDuration;
    
    /**
     * Constructs the Internship object.
     * @param theCompany The company that the internship took place at.
     * @param thePosition The position title of internship.
     * @param theSkillsReq The skills needed during this internship.
     * @param theDescription A description of what the job entailed.
     * @param theMiscComments Any miscellaneous comments.
     * @param theWage Hourly wage in dollars.
     * @param theDuration Length of internship in months.
     */
    public Internship(final String theCompany,
            final String thePosition, final String theSkillsReq, 
            final String theDescription, final String theMiscComments,
            final double theWage, final int theDuration) {
        super(theCompany, thePosition, theSkillsReq, theDescription, theMiscComments);
        if (theWage < 0 || theDuration < 0) {
            throw new IllegalArgumentException();
        }
        myWage = theWage;
        myDuration = theDuration;
    }

    /**
     * @return the wage.
     */
    public double getMyWage() {
        return myWage;
    }

    /**
     * @param theWage changes the wage.
     */
    public void setMyWage(final double theWage) {
        if (theWage < 0) {
            throw new IllegalArgumentException();
        }
        myWage = theWage;
    }

    /**
     * @return the duration.
     */
    public int getMyDuration() {
        return myDuration;
    }

    /**
     * @param theDuration changes the duration.
     */
    public void setMyDuration(final int theDuration) {
        if (theDuration < 0) {
            throw new IllegalArgumentException();
        }
        myDuration = theDuration;
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
