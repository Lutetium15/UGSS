package model;

import java.io.Serializable;

/**
 * Holds data regarding a place of employment. 
 * @author GROUP8(Dema)
 *@version 11/15/2016
 */
public abstract class Employment implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5345334229977780966L;

    /** The employer name. */
    private String myCompany;

    /** The position title. */
    private String myPosition;

    /** Skills needed for this position. */
    private String mySkillsReq;

    /** Description of position. */
    private String myDescription;

    /** Any other comments. */
    private String myMiscComments;

    /**
     * Constructs the Employment object.
     * @param theCompany The company that the internship took place at.
     * @param thePosition The position title of internship.
     * @param theSkillsReq The skills needed during this internship.
     * @param theDescription A description of what the job entailed.
     * @param theMiscComments Any miscellaneous comments.
     */
    protected Employment(final String theCompany, 
            final String thePosition, final String theSkillsReq, 
            final String theDescription, final String theMiscComments) {
        if (theCompany == null || thePosition == null) {
            throw new IllegalArgumentException();
        }
        myCompany = theCompany;
        myPosition = thePosition;
        mySkillsReq = theSkillsReq;
        myDescription = theDescription;
        myMiscComments = theMiscComments;
    }

    /**
     * @return myCompany.
     */
    public String getMyCompany() {
        return myCompany;
    }

    /**
     * Changes myCompany.
     * @param theCompany new company that myCompany is changed to.
     */
    public void setMyCompany(final String theCompany) {
        if (theCompany == null) {
            throw new IllegalArgumentException();
        }
        myCompany = theCompany;
    }

    /**
     * @return myPosition.
     */
    public String getMyPosition() {
        return myPosition;
    }

    /**
     * Changes myPosition.
     * @param thePosition new position that myPosition is changed to.
     */
    public void setMyPosition(final String thePosition) {
        if (thePosition == null) {
            throw new IllegalArgumentException();
        }
        myPosition = thePosition;
    }

    /**
     * @return mySkillsReq.
     */
    public String getMySkillsReq() {
        return mySkillsReq;
    }

    /**
     *Changes mySkillsReq.
     * @param theSkillsReq new requirements for this position.
     */
    public void setMySkillsReq(final String theSkillsReq) {
        mySkillsReq = theSkillsReq;
    }

    /**
     * @return myDescription.
     */
    public String getMyDescription() {
        return myDescription;
    }

    /**
     * Changes myDescription.
     * @param theDescription new description for this position.
     */
    public void setMyDescription(final String theDescription) {
        myDescription = theDescription;
    }

    /**
     * @return myMiscComments.
     */
    public String getMyMiscComments() {
        return myMiscComments;
    }

    /**
     * Changes myMiscComments.
     * @param theMiscComments new miscellaneous comments for this position.
     */
    public void setMyMiscComments(final String theMiscComments) {
        myMiscComments = theMiscComments;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return myCompany;
    }
    
}
