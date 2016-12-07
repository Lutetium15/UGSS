package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Alumni;
import model.AlumniCollection;
import model.Internship;
import model.Job;
import model.TransferCollege;

/**
 * Creates a tab for adding alumni and their information to the system.
 * @author GROUP8(Alec)
 * @version 12/3/2016
 *
 */
public class AlumniAddGUI extends JPanel implements ActionListener {
    /**
     * Required serialization #.
     */
    private static final long serialVersionUID = 1779520078061383929L;
    /**
     * String representing the word Cancelled.
     */
    private static final String STRCANCEL = "cancelled";
    /**
     * ArrayList containing jobs to add to the alumni.
     */
    @SuppressWarnings("rawtypes")
    private ArrayList myJobs;
    /**
     * ArrayList containing colleges to add to the alumni.
     */
    @SuppressWarnings("rawtypes")
    private ArrayList myColleges;
    /**
     * ArrayList containing internships to add to the alumni.
     */
    @SuppressWarnings("rawtypes")
    private ArrayList myInternships;
    /**
     * Add Panel.
     */
    private JPanel myPnlAdd = new JPanel();
    /**
     * Labels for Fields.
     */
    private JLabel[] myTxfLabel = new JLabel[8];
    /**
     * Fields for input jobs.
     */
    private JTextField[] myTxfFieldJobs = new JTextField[6];
    /**
     * Fields for input internships.
     */
    private JTextField[] myTxfFieldInternships = new JTextField[7];
    /**
     * Fields for input schools.
     */
    private JTextField[] myTxfFieldSchools = new JTextField[5];
    /**
     * Fields for input.
     */
    private JTextField[] myTxfField = new JTextField[8];
    /**
     * Button to add alumni.
     */
    private JButton myBtnAddAlumni; 
    /**
     * Button to add college.
     */
    private JButton myBtnTransferCollege; 
    /**
     * Button to add internship.
     */
    private JButton myBtnInternships;
    /**
     * Button to add job.
     */
    private JButton myBtnJobs;
    /**
     * Button to remove transferCollege, internship, or job info.
     */
    private JButton myBtnRemove;
    /**
     * JComboBox holding current transfer colleges.
     */
    @SuppressWarnings("rawtypes")
    private JComboBox myComboCollege;
    /**
     * JComboBox holding current job records.
     */
    @SuppressWarnings("rawtypes")
    private JComboBox myComboJob;
    /**
     * JComboBox holding current internships records.
     */
    @SuppressWarnings("rawtypes")
    private JComboBox myComboInternships;
    /**
     * Button for removing selected transfer college.
     */
    private JButton myRemoveCollege;
    /**
     * Button for removing selected job record.
     */
    private JButton myRemoveJob;
    /**
     * Button for removing selected internship.
     */
    private JButton myRemoveInternship;

    /**
     * Used for Alumni administration.
     */
    public AlumniAddGUI() {
        setLayout(new BorderLayout());
        createComponents();
        setVisible(true);
        setSize(500, 500);
    }

    /**
     * Creates the components that allow data to be inputted into the system.
     */
    private void createComponents() {
        myPnlAdd = new JPanel();
        myPnlAdd.setLayout(new GridLayout(9, 0));
        final String[] labelNames = {"Enter Name:", "Enter Degree Track: ",
            "Enter Degree Level: ", "Enter Graduated Year: ",
            "Enter Graduated Term: ", "Enter GPA: ",
            "Enter University Email: ", 
            "Enter Personal Email: "};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfField[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfField[i]);
            myPnlAdd.add(panel);
        }
        final JPanel panel = new JPanel();
        myBtnTransferCollege = new JButton("New Transfer College");
        myBtnTransferCollege.addActionListener(this);
        myBtnInternships = new JButton("New Internship");
        myBtnInternships.addActionListener(this);
        myBtnJobs = new JButton("New Job");
        myBtnJobs.addActionListener(this);
        myBtnAddAlumni = new JButton("Add");
        myBtnAddAlumni.addActionListener(this);
        myBtnRemove = new JButton("Remove Records");
        myBtnRemove.addActionListener(this);
        final JPanel bPanel = new JPanel();
        final JPanel superPanel = new JPanel();
        superPanel.setLayout(new GridLayout(2, 0));
        bPanel.add(myBtnTransferCollege);
        bPanel.add(myBtnInternships);
        bPanel.add(myBtnJobs);
        bPanel.add(myBtnRemove);
        superPanel.add(bPanel);
        superPanel.add(myBtnAddAlumni);
        //panel.add(superPanel);
        myPnlAdd.add(panel);
        add(myPnlAdd, BorderLayout.CENTER);
        add(superPanel, BorderLayout.PAGE_END);
    }

    /**
     * Make the buttons work!
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void actionPerformed(final ActionEvent theE) {
        if (theE.getSource() == myBtnAddAlumni) {
            performAddAlumni();
        } else if (theE.getSource() == myBtnTransferCollege) {
            if (myColleges == null) {
                myColleges = new ArrayList();
            }
            final Object temp = performAddTransferCollege();
            if (!STRCANCEL.equals(temp.toString())) {
                myColleges.add((TransferCollege) temp);
                JOptionPane.showMessageDialog(null, "Transfer College " + temp + " added!");
            }
        } else if (theE.getSource() == myBtnInternships) {
            if (myInternships == null) {
                myInternships = new ArrayList();
            }
            final Object temp = performAddInternship();
            if (!STRCANCEL.equals(temp.toString())) {
                myInternships.add((Internship) temp);
                JOptionPane.showMessageDialog(null, "Internship at " + temp + " added!");
            }
        } else if (theE.getSource() == myBtnJobs) {
            if (myJobs == null) {
                myJobs = new ArrayList();
            }
            final Object temp = performAddJob();
            if (!STRCANCEL.equals(temp.toString())) {
                myJobs.add((Job) temp);
                JOptionPane.showMessageDialog(null, "Job at " + temp + " added!");
            }
        } else if (theE.getSource() == myBtnRemove) {
            if (myJobs == null) {
                myJobs = new ArrayList();
            }
            if (myColleges == null) {
                myColleges = new ArrayList();
            }
            if (myInternships == null) {
                myInternships = new ArrayList();
            }
            performRemove();
        } else if (theE.getSource() == myRemoveCollege) {
            if (myColleges != null && myColleges.size() != 0) {
                myColleges.remove(myComboCollege.getSelectedIndex());
                refreshCombo(myComboCollege, myColleges);
            }
        } else if (theE.getSource() == myRemoveJob) {
            if (myJobs != null && myJobs.size() != 0) {
                myJobs.remove(myComboJob.getSelectedIndex());
                refreshCombo(myComboJob, myJobs);
            }
        } else if (theE.getSource() == myRemoveInternship) {
            if (myInternships != null && myInternships.size() != 0) {
                myInternships.remove(myComboInternships.getSelectedIndex());
                refreshCombo(myComboInternships, myInternships);
            }
        }
    }
    /**
     * Method to refresh the comboBoxes.
     * @param theJC The JComboBox to refresh.
     * @param theAR The ArrayList to refresh it from.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void refreshCombo(final JComboBox theJC, final ArrayList theAR) {
        theJC.removeAllItems();
        for (int i = 0; i < theAR.size(); i++) {
            theJC.addItem(theAR.get(i));
        }
    }

    /**
     * Removes selected Job, Internship, or College Records.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private void performRemove() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(4, 0));
        myComboCollege = new JComboBox(myColleges.toArray());
        myComboJob = new JComboBox(myJobs.toArray());
        myComboInternships = new JComboBox(myInternships.toArray());
        myRemoveCollege = new JButton("Remove");
        myRemoveCollege.addActionListener(this);
        myRemoveJob = new JButton("Remove");
        myRemoveJob.addActionListener(this);
        myRemoveInternship = new JButton("Remove");
        myRemoveInternship.addActionListener(this);
        fields.add(myComboCollege);
        fields.add(myRemoveCollege);
        fields.add(myComboJob);
        fields.add(myRemoveJob);
        fields.add(myComboInternships);
        fields.add(myRemoveInternship);
        JOptionPane.showConfirmDialog(null, fields, 
                "Remove Records", JOptionPane.OK_CANCEL_OPTION);
    }

    /**
     * Adds a Job Record.
     * @return Job Object or Cancel String.
     */
    private Object performAddJob() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(8, 0));
        final String[] labelNames = {"*Enter Company Name:", 
                                        "*Enter Position: ", "Enter Required Skills: ",
                                        "Enter Description: ",
                                        "Enter Comments:", "*Enter Salary: "};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfFieldJobs[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfFieldJobs[i]);
            fields.add(panel);
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        final JCheckBox activeBox = new JCheckBox();
        activeBox.setSelected(false);
        panel.add(new JLabel("Active?"));
        panel.add(activeBox);
        fields.add(panel);
        final int check = JOptionPane.showConfirmDialog(null, fields, 
                "Add Job Form", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.CANCEL_OPTION) {
            return STRCANCEL;
        } 
        if (myTxfFieldJobs[0].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Company Name");
            return STRCANCEL;
        } else if (myTxfFieldJobs[1].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Position");
            return STRCANCEL;
        } else if (myTxfFieldJobs[2].getText().length() == 0) {
            myTxfFieldJobs[2].setText("");
        } else if (myTxfFieldJobs[3].getText().length() == 0) {
            myTxfFieldJobs[3].setText("");
        } else if (myTxfFieldJobs[4].getText().length() == 0) {
            myTxfFieldJobs[4].setText("");
        } else if (myTxfFieldJobs[5].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Salary");
            return STRCANCEL;
        }        

        if (myTxfFieldJobs[5].getText().length() == 0) {
            try { 
                Double.parseDouble(myTxfFieldJobs[5].getText());
            } catch (final NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Salary Must be a Decimal");
                return STRCANCEL;
            }
        }
        return new Job(myTxfFieldJobs[0].getText(), myTxfFieldJobs[1].getText(),
                myTxfFieldJobs[2].getText(),
                myTxfFieldJobs[3].getText(),
                myTxfFieldJobs[4].getText(),
                Double.parseDouble(myTxfFieldJobs[5].getText()),
                activeBox.isSelected());
    }

    /**
     * Adds an internship record.
     * @return Internship object or Cancel String
     */
    private Object performAddInternship() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(9, 0));
        final String[] labelNames = {"*Enter Company Name:", "*Enter Position: ", 
            "Enter Required Skills: ",
            "Enter Description: ",
            "Enter Comments:", "*Enter Wage: ",
            "*Enter Duration (in years):"};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfFieldInternships[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfFieldInternships[i]);
            fields.add(panel);
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        fields.add(panel);
        final int check = JOptionPane.showConfirmDialog(null, fields,
                "Add Internship Form", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.CANCEL_OPTION) {
            return STRCANCEL;
        }
        if (myTxfFieldInternships[0].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Company Name");
            return STRCANCEL;
        } else if (myTxfFieldInternships[1].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Position");
            return STRCANCEL;
        } else if (myTxfFieldInternships[2].getText().length() == 0) {
            myTxfFieldInternships[2].setText("");
        } else if (myTxfFieldInternships[3].getText().length() == 0) {
            myTxfFieldInternships[3].setText("");
        } else if (myTxfFieldInternships[4].getText().length() == 0) {
            myTxfFieldInternships[4].setText("");
        } else if (myTxfFieldInternships[5].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Wage");
            return STRCANCEL;
        } else if (myTxfFieldInternships[6].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter Duration");
            return STRCANCEL;
        }
        if (myTxfFieldInternships[5].getText().length() == 0) {
            try {
                Double.parseDouble(myTxfFieldInternships[5].getText());
            } catch (final NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Wage Must be a Decimal");
                return STRCANCEL;
            }
        }
        if (myTxfFieldInternships[6].getText().length() == 0) {
            try {
                Integer.parseInt(myTxfFieldInternships[6].getText());
            } catch (final NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Duration Must be an Integer");
                return STRCANCEL;
            }
        }
        return new Internship(myTxfFieldInternships[0].getText(),
                myTxfFieldInternships[1].getText(),
                myTxfFieldInternships[2].getText(),
                myTxfFieldInternships[3].getText(),
                myTxfFieldInternships[4].getText(),
                Double.parseDouble(myTxfFieldInternships[5].getText()),
                Integer.parseInt(myTxfFieldInternships[6].getText()));
    }

    /**
     * Adds a new Transfer College Record.
     * @return Transfer College object or Cancel String.
     */
    private Object performAddTransferCollege() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(6, 0));
        final String[] labelNames = {"*College Name:", 
                                        "*GPA:", "*Degree:", 
                                        "*Year:", "*Term:"};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            myTxfLabel[i] = new JLabel(labelNames[i]);
            myTxfFieldSchools[i] = new JTextField(25);
            panel.add(myTxfLabel[i]);
            panel.add(myTxfFieldSchools[i]);
            fields.add(panel);
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        fields.add(panel);
        final int check = JOptionPane.showConfirmDialog(null, fields, 
                "Add Transfer College Form", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.CANCEL_OPTION) {
            return STRCANCEL;
        }
        if (myTxfFieldSchools[0].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a College Name");
            return STRCANCEL;
        } else if (myTxfFieldSchools[1].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a GPA");
            return STRCANCEL;
        } else if (myTxfFieldSchools[2].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Degree");
            return STRCANCEL;
        } else if (myTxfFieldSchools[3].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Year");
            return STRCANCEL;
        } else if (myTxfFieldSchools[4].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Term");
            return STRCANCEL;
        }
        if (myTxfFieldSchools[1].getText().length() != 0) {
            try {
                Double.parseDouble(myTxfFieldSchools[1].getText());
            } catch (final NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "GPA must be formatted as a decimal");
                return STRCANCEL;
            }
        }
        return new TransferCollege(myTxfFieldSchools[0].getText(), 
                Double.parseDouble(myTxfFieldSchools[1].getText()),
                myTxfFieldSchools[2].getText(), 
                myTxfFieldSchools[3].getText(), 
                myTxfFieldSchools[4].getText());
    }

    /**
     * Allows to add an Alumni.
     */
    @SuppressWarnings("unchecked")
    private void performAddAlumni() {
        final String name = myTxfField[0].getText();
        if (name.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter an Alumni name");
            myTxfField[0].setFocusable(true);
            return;
        }
        final String dTrack = myTxfField[1].getText();
        if (dTrack.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Degree Track");
            myTxfField[1].setFocusable(true);
            return;
        }
        final String dLevel = myTxfField[2].getText();
        if (dLevel.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter an Degree Level");
            myTxfField[2].setFocusable(true);
            return;
        }
        final String year = myTxfField[3].getText();
        if (year.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Graduated Year");
            myTxfField[3].setFocusable(true);
            return;
        }
        final String term = myTxfField[4].getText();
        if (term.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Graduated Term");
            myTxfField[4].setFocusable(true);
            return;
        }
        final String strGpa = myTxfField[5].getText();
        double gpa = 0.0;
        if (strGpa.length() != 0) {
            try {
                gpa = Double.parseDouble(strGpa);
            } catch (final NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Enter GPA as decimal");
                myTxfField[5].setText("");
                myTxfField[5].setFocusable(true);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Enter a GPA");
            myTxfField[5].setFocusable(true);
            return;
        }
        final String uEmail = myTxfField[6].getText();
        if (uEmail.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter an University Email");
            myTxfField[6].setFocusable(true);
            return;
        }
        final String pEmail = myTxfField[7].getText();
        if (pEmail.length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter a Personal Email");
            myTxfField[7].setFocusable(true);
            return;
        }
        Alumni al;
        if (myInternships == null || myInternships.size() == 0) {
            myInternships = null;
        }
        if (myJobs == null || myJobs.size() == 0) {
            myJobs = null;
        }
        if (myColleges == null || myColleges.size() == 0) {
            myColleges = null;
        }
        al = new Alumni(name, dTrack, dLevel,
                year, term, gpa, uEmail, pEmail, myInternships, myJobs, myColleges);
        String message = "Alumni add failed";
        if (AlumniCollection.add(al)) {
            message = "Alumni added";
            myColleges = null;
            myJobs = null;
            myInternships = null;
        }
        JOptionPane.showMessageDialog(null, message);
        // Clear all text fields.
        for (int i = 0; i < myTxfField.length; i++) {
            if (myTxfField[i].getText().length() != 0) {
                myTxfField[i].setText("");
            }
        }

    }
}

