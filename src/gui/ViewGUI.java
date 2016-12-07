package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Alumni;
import model.AlumniCollection;
import model.DataTypes;
import model.Internship;
import model.Job;
import model.TransferCollege;
/**
 * Shows Alumni information and allows it to be modified.
 * @author GROUP8(Dema)
 * @version 12/04/2016
 */
public class ViewGUI extends JPanel implements ActionListener {
    /** For serializing. */
    private static final long serialVersionUID = -1587871730380712674L;
    /** Button for searching name.*/
    private JButton myNameSearch;
    /**Button for searching id.*/
    private JButton myIdSearch;
    /**Button for editing.*/
    private JButton myEditBtn;
    /**Button to confirm the modify.*/
    private JButton myModifyBtn;
    /**Button to add selection.*/
    private JButton myAddSelection;
    /**Button to remove selection.*/
    private JButton myRemoveSelection;
    /**Button to modify selection.*/
    private JButton myModifySelection;
    /**Button to view internship.*/
    private JButton myViewInternshipsBtn;
    /**Button to view jobs.*/
    private JButton myViewJobsBtn;
    /**Button to view transfer colleges.*/
    private JButton myViewCollegesBtn; 
    /** Text field for searching. */
    private JTextField mySearchTerm;
    
    /**Current panel view tab.*/
    private JPanel myCurrentPanel;
    /**Current panel for list.*/
    private JPanel myListPanel;
    
    /** Holds the data for the table in the view tab. */
    private Object[][] myData;
    
    /** Holds the current list of alumni. */
    private List<Alumni> myAlumniList;
    
    /** Current alumni selected. */
    private Alumni myCurrentAlumniSelected;
    
    /** The columns of the list on the front page. */
    private String[] myAlumniColumnNames = {"id", "name",
        "degreeTrack", "degreeLevel", "year",
        "term", "gpa", "uniEmail", "persEmail",
        "Internships", "Jobs", "TransferColleges"};
    
    /** Text boxes for modifying alumni information. */
    private JTextField[] myModifyFields = new JTextField[8];
    
    /** Text boxes for modifying alumni's job information. */
    private JTextField[] myJobFields = new JTextField[6];
    
    /** Text boxes for modifying alumni's internship information. */
    private JTextField[] myInternFields = new JTextField[7];
    
    /** Text boxes for modifying alumni's college information. */
    private JTextField[] myCollegeFields = new JTextField[5];
    
    /** Main table displayed on main page. */
    private JTable myTable;
   
   /** Active box for last job. */
    private JCheckBox myActiveBox;
    
   /** Combo boxes for making selections of internships/jobs/colleges. */
    @SuppressWarnings("rawtypes")
    private JComboBox myModifyTypeSelections;
    
    /**Selection Combo Box.*/
    @SuppressWarnings("rawtypes")
    private JComboBox myItemRemoveModifySelection;
    
    /** Boolean to check whether last search was by name. */
    private boolean myLastSearchByName;

    /**
     * Constructs the GUI object.
     */
    ViewGUI() {
        setLayout(new BorderLayout());

        myAlumniList = getData(null, null);
        myLastSearchByName = true;
        createComponents();
        setVisible(true);
        setSize(500, 500);
    }
    
    /**
     * Gets Alumni to display. Allows for specific Alumni to be displayed, 
     * otherwise it gets all of them.
     * Specific alumni can be searched for by id or name only.
     * @param theSearchType The field it needs to look for.
     * @param theSearchKey The search term it needs to find in that field.
     * @return list of Alumni found.
     */
    private List<Alumni> getData(final DataTypes theSearchType, final String theSearchKey) {

        List<Alumni> searchData = new ArrayList<Alumni>();
        // If there is specific data to look for,
        //load that data. Otherwise, display all alumni.
        if (theSearchKey != null && theSearchType != null) {
            // Search for names only if looking for name
            if (theSearchType == DataTypes.NAME) {
                searchData = AlumniCollection.searchName(theSearchKey);
            } else if (theSearchType == DataTypes.ID) {
                // Search for an ID
                try {
                    final int id = Integer.parseInt(theSearchKey);
                    final Alumni alum = AlumniCollection.searchID(id);
                    
                    if (alum != null) {
                        searchData.clear();
                        searchData.add(alum);
                    }
                    
                } catch (final NumberFormatException e) {
                    searchData = AlumniCollection.getAlumni();
                }              
            }
        } else {
            // Otherwise, just display all alumni
            searchData = AlumniCollection.getAlumni();
        }

        if (searchData != null) {
            myData = new Object[searchData.size()][myAlumniColumnNames.length];
            for (int i = 0; i < searchData.size(); i++) {
                myData[i][0] = searchData.get(i).getMyID();
                myData[i][1] = searchData.get(i).getMyName();
                myData[i][2] = searchData.get(i).getMyDegreeTrack();
                myData[i][3] = searchData.get(i).getMyDegreeLevel();
                myData[i][4] = searchData.get(i).getMyYear();
                myData[i][5] = searchData.get(i).getMyTerm();
                myData[i][6] = searchData.get(i).getMyCurrentGPA();
                myData[i][7] = searchData.get(i).getMyUniEmail();
                myData[i][8] = searchData.get(i).getMyPersEmail();
                myData[i][9] = 0;
                if (searchData.get(i).getMyInternships() != null) {
                    myData[i][9] = searchData.get(i).getMyInternships().size();
                }
                myData[i][10] = 0;     
                if (searchData.get(i).getMyJobs() != null) {
                    myData[i][10] = searchData.get(i).getMyJobs().size();
                }
                myData[i][11] = 0; 
                if (searchData.get(i).getMyTransferColleges() != null) {
                    myData[i][11] = searchData.get(i).getMyTransferColleges().size(); 
                }
            }
        }

        return searchData;
    }

    /**
     * Creates the basic components in this tab.
     */
    private void createComponents() {
        add(createSearchPanel(), BorderLayout.NORTH);
        myCurrentPanel = new JPanel();
        myListPanel = createListPanel();
        
        myCurrentPanel.add(myListPanel);
        add(myCurrentPanel, BorderLayout.CENTER);
        
        final JPanel btnPanel = new JPanel();
                
        myEditBtn = new JButton("Edit");
        myEditBtn.addActionListener(this);
        btnPanel.add(myEditBtn);
        
        myViewInternshipsBtn = new JButton("View Internships");
        myViewInternshipsBtn.addActionListener(this);
        btnPanel.add(myViewInternshipsBtn);
        
        myViewJobsBtn = new JButton("View Jobs");
        myViewJobsBtn.addActionListener(this);
        btnPanel.add(myViewJobsBtn);
        
        myViewCollegesBtn = new JButton("View Colleges");
        myViewCollegesBtn.addActionListener(this);
        btnPanel.add(myViewCollegesBtn);
        
        add(btnPanel, BorderLayout.SOUTH);
   
    }
    
    /**
     * Creates the search panel from where users can search for alumni.
     * @return a JPanel with attached components.
     */
    private JPanel createSearchPanel() {
        final JPanel mainPanel = new JPanel(new GridLayout(3, 2));
       
        mySearchTerm = new JTextField(25);
        final JLabel searchLabel = new JLabel("Search For: ");
        
        myNameSearch = new JButton("Search by Name");
        myNameSearch.addActionListener(this);
        
        myIdSearch = new JButton("Search by ID");
        myIdSearch.addActionListener(this);
        
        mainPanel.add(searchLabel);
        mainPanel.add(mySearchTerm);
        mainPanel.add(myNameSearch);
        mainPanel.add(myIdSearch);
        
        return mainPanel;
    }
    
    /**
     * Creates the list of Alumni to display.
     * @return a JPanel with attached components.
     */
    @SuppressWarnings("serial")
    private JPanel createListPanel() {
        final JPanel mainPanel = new JPanel();
        final TableModel model = new DefaultTableModel(myData, myAlumniColumnNames)
        {
            public boolean isCellEditable(final int theRow, final int theColumn) {
                return false; //This causes all cells to be not editable
            }
   
        };
        myTable = new JTable(model);
        myTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JScrollPane scrollPane = new JScrollPane(myTable);
        myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mainPanel.add(scrollPane);
               
        return mainPanel;        
    }
    
    /**
     * Creates a window that allows information regarding a college to be entered.
     * @return a JPanel with attached components.
     */
    private JPanel createCollegeWindow() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(6, 0));
        final String[] labelNames = {"College Name:", "GPA:", "Degree:", "Year:", "Term:"};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            final JLabel addLabel = new JLabel(labelNames[i]);
            myCollegeFields[i] = new JTextField(25);
            panel.add(addLabel);
            panel.add(myCollegeFields[i]);
            fields.add(panel);
        }
        
        return fields;
    }   
    
    /**
     * Creates and brings up a window from which the user
     * can choose what college they would like an action to be performed on.
     * @return int signifying whether user wants to go through with action.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private int collegeSelectionWindow() {
        final List<TransferCollege> colleges = myCurrentAlumniSelected.getMyTransferColleges();

        if (colleges != null && colleges.size() > 0) {
            final List<String> revised = new ArrayList<String>();
            for (int i = 0; i < colleges.size(); i++) {
                revised.add("College: " + colleges.get(i).getMyName() 
                        + ", Year: " + colleges.get(i).getMyYear());
            }
            final JPanel mainPanel = new JPanel();
            final JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new GridLayout(1, 1));
            final Object[] options = revised.toArray();
            myItemRemoveModifySelection = new JComboBox(options);
            if (options.length != 0) {
                myModifyTypeSelections.setSelectedIndex(0);
            }
            comboPanel.add(new JLabel("Select College: "));
            comboPanel.add(myItemRemoveModifySelection);
            mainPanel.add(comboPanel, BorderLayout.CENTER);
            final int choice = JOptionPane.showConfirmDialog(null, 
                    mainPanel, "College Selection", JOptionPane.OK_OPTION);
            return choice;
        } else {
            JOptionPane.showMessageDialog(null,
                    "There are no Colleges!", "Error", JOptionPane.ERROR_MESSAGE);
            return JOptionPane.CANCEL_OPTION;
        }
    }
    
    
    /**
     * Modifies the selected college.
     */
    private void performModifyCollegeSelection() {
        final int choice = collegeSelectionWindow();
        if (choice == JOptionPane.OK_OPTION) {
            if (myItemRemoveModifySelection.getSelectedIndex() >= 0) {                 
                final int check = JOptionPane.showConfirmDialog(null, 
                        createCollegeWindow(), "Data Entry", JOptionPane.OK_CANCEL_OPTION);
                if (check == JOptionPane.CANCEL_OPTION || check == JOptionPane.CLOSED_OPTION) {
                    return;
                }
                final List<TransferCollege> lsColleges =
                        myCurrentAlumniSelected.getMyTransferColleges();
                final TransferCollege modifiedCollege = 
                        lsColleges.get(myItemRemoveModifySelection.getSelectedIndex());
                if (myCollegeFields[0].getText().length() != 0) {
                    modifiedCollege.setMyName(myCollegeFields[0].getText());
                }
                if (myCollegeFields[1].getText().length() != 0) {
                    try {
                        Double.parseDouble(myCollegeFields[1].getText());
                        modifiedCollege.setMyGPA(
                                Double.parseDouble(myCollegeFields[1].getText()));
                    } catch (final NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "GPA must be a valid number.",
                                "Invalid number", JOptionPane.ERROR_MESSAGE);
                        return;
                    }                 
                }
                if (myCollegeFields[2].getText().length() != 0) {
                    modifiedCollege.setMyDegree(myCollegeFields[2].getText());
                }
                if (myCollegeFields[3].getText().length() != 0) {
                    try {
                        Integer.parseInt(myCollegeFields[3].getText());
                    } catch (final NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Year must be a valid number", 
                                "Invalid Entry", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    modifiedCollege.setMyYear(myCollegeFields[3].getText());
                }
                if (myCollegeFields[4].getText().length() != 0) {                   
                    modifiedCollege.setMyTerm(myCollegeFields[4].getText());
                }

                lsColleges.remove(myItemRemoveModifySelection.getSelectedIndex());
                lsColleges.add(modifiedCollege);

                final boolean success = AlumniCollection.updateAlumni(
                        myCurrentAlumniSelected, DataTypes.COLLEGES, lsColleges);

                if (success) {
                    JOptionPane.showMessageDialog(
                            null, "College was successfully modified");                 
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "Error modifying College.", 
                            "Modify Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
       
    /**
     * Performs the remove college action.
     */
    private void performRemoveCollege() {
        final int choice = collegeSelectionWindow();
        if (choice == JOptionPane.OK_OPTION) {
            if (myItemRemoveModifySelection.getSelectedIndex() >= 0) {         
                final List<TransferCollege> colleges =
                        myCurrentAlumniSelected.getMyTransferColleges();
                final Object item = 
                      colleges.remove(myItemRemoveModifySelection.getSelectedIndex());
                boolean success = false;
                if (item != null) {
                    success = AlumniCollection.updateAlumni(
                            myCurrentAlumniSelected, DataTypes.COLLEGES, colleges);
                }
                if (success) {
                    JOptionPane.showMessageDialog(null, "College successfully removed");
                } else {
                    JOptionPane.showMessageDialog(null, 
                            "Error removing College!", "Error", JOptionPane.ERROR_MESSAGE);
                }                  
            }
        }
    }
    
    /**
     * Performs the add college action.
     */
    private void performAddCollege() {
        final int check = JOptionPane.showConfirmDialog(null,
              createCollegeWindow(), "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if (check == JOptionPane.CANCEL_OPTION || check == JOptionPane.CLOSED_OPTION) {
            return;
        }     
      
        if (myCollegeFields[0].getText().length() == 0) {
            JOptionPane.showMessageDialog(null,
                  "College name is required", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
        }
      
        try {
            Double.parseDouble(myCollegeFields[1].getText());
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "GPA is required and must be a valid number.", 
                    "Invalid number", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            Integer.parseInt(myCollegeFields[3].getText());
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                  "Year must be a valid number", "Invalid Entry", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        final TransferCollege college = new TransferCollege(myCollegeFields[0].getText(), 
               Double.parseDouble(myCollegeFields[1].getText()), myCollegeFields[2].getText(), 
               myCollegeFields[3].getText(), myCollegeFields[4].getText());
        
        List<TransferCollege> lsColleges = myCurrentAlumniSelected.getMyTransferColleges();
        if (lsColleges == null) {
            lsColleges = new ArrayList<TransferCollege>();
        }

        lsColleges.add(college);
        
        final boolean success = AlumniCollection.updateAlumni(
                myCurrentAlumniSelected, DataTypes.COLLEGES, lsColleges);

        if (success) {
            JOptionPane.showMessageDialog(null,
                    "College was successfully added");                 
        } else {
            JOptionPane.showMessageDialog(null, 
                    "Error adding College.", "Add Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    /**
     * Creates and displays a window asking user to select a job.
     * @return int signifying user's choice.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private int displayJobSelectionWindow() {
        final List<Job> jobs = myCurrentAlumniSelected.getMyJobs();

        if (jobs != null && jobs.size() > 0) {
            final List<String> revised = new ArrayList<String>();
            for (int i = 0; i < jobs.size(); i++) {
                revised.add("Company: "
                        + jobs.get(i).getMyCompany() 
                        + ", Position: " + jobs.get(i).getMyPosition());
            }
            final JPanel mainPanel = new JPanel();
            final JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new GridLayout(1, 1));
            final Object[] options = revised.toArray();
            myItemRemoveModifySelection = new JComboBox(options);
            if (options.length != 0) {
                myModifyTypeSelections.setSelectedIndex(0);
            }
            comboPanel.add(new JLabel("Select Job: "));
            comboPanel.add(myItemRemoveModifySelection);
            mainPanel.add(comboPanel, BorderLayout.CENTER);

            return JOptionPane.showConfirmDialog(
                    null, mainPanel, "Select Job", JOptionPane.OK_OPTION);
        } else {
            JOptionPane.showMessageDialog(
                    null, "There are no Jobs!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return JOptionPane.CANCEL_OPTION;
    }

    /**
     * Modifies the selected job.
     */
    private void performModifyJobSelection() {       

        final int choice = displayJobSelectionWindow();
        if (choice == JOptionPane.OK_OPTION) { // User wants to go ahead with modification
            if (myItemRemoveModifySelection.getSelectedIndex() >= 0) {
                final int check = JOptionPane.showConfirmDialog(
                        null, createJobWindow(true),
                        "Data Entry", JOptionPane.OK_CANCEL_OPTION);
                if (check != JOptionPane.OK_OPTION) {
                    return;
                }
                final List<Job> lsJobs = myCurrentAlumniSelected.getMyJobs();
                final Job newJob = lsJobs.get(myItemRemoveModifySelection.getSelectedIndex());
                if (myJobFields[0].getText().length() != 0) {
                    newJob.setMyCompany(myJobFields[0].getText());
                }
                if (myJobFields[1].getText().length() != 0) {
                    newJob.setMyPosition(myJobFields[1].getText());
                }
                if (myJobFields[2].getText().length() != 0) {
                    newJob.setMySkillsReq(myJobFields[2].getText());
                }
                if (myJobFields[3].getText().length() != 0) {
                    newJob.setMyDescription(myJobFields[3].getText());
                }
                if (myJobFields[4].getText().length() != 0) {
                    newJob.setMyMiscComments(myJobFields[4].getText());
                }
                if (myJobFields[5].getText().length() != 0) {
                    try {
                        newJob.setSalary(Double.parseDouble(myJobFields[5].getText()));
                    } catch (final NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Salary must be a valid number");
                        return;
                    }
                }     
                lsJobs.remove(myItemRemoveModifySelection.getSelectedIndex());
                lsJobs.add(newJob);
                
                final boolean success = AlumniCollection.updateAlumni(
                        myCurrentAlumniSelected, DataTypes.JOB, lsJobs);

                if (success) {
                    JOptionPane.showMessageDialog(
                            null, "Job was successfully modified");                 
                } else {
                    JOptionPane.showMessageDialog(
                            null, "Error modifiying Job.",
                            "modify Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }
    
    /**
     * Adds a job to the Alumni.
     */
    private void performAddJob() {
        final int check = JOptionPane.showConfirmDialog(
                null, createJobWindow(false),
                "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if (check != JOptionPane.OK_OPTION) {
            return;
        }
        // Check data
        boolean requiredEntered = true;
        for (int i = 0; i < myJobFields.length; i++) {
            final String data = myJobFields[i].getText();
            if (data.length() != 0) {           
                if (requiredEntered & data.length() > 50) {
                    JOptionPane.showMessageDialog(
                            null, "Data entered must be less than 50 characters",
                            "Entry is too Long", JOptionPane.ERROR_MESSAGE);
                    requiredEntered = false;
                    return;
                }             
            } else if (requiredEntered & (i == 0 || i == 1 || i == 5)) {
                JOptionPane.showMessageDialog(
                        null, "Error! Company Name, Position, and Salary are all required.",
                        "Missing Required Entry", JOptionPane.ERROR_MESSAGE);
                requiredEntered = false;
            }
        }
        
        if (requiredEntered) {
            try {
                Double.parseDouble(myJobFields[5].getText());
            } catch (final NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null, "Salary must be a valid number", 
                        "Invalid Number", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Job newJob = new Job(
                      myJobFields[0].getText(), 
                      myJobFields[1].getText(), myJobFields[2].getText(), 
                      myJobFields[3].getText(), myJobFields[4].getText(), 
                      Double.parseDouble(myJobFields[5].getText()), myActiveBox.isSelected());
            List<Job> lsJobs = 
                    myCurrentAlumniSelected.getMyJobs();
            if (lsJobs == null) {
                lsJobs = new ArrayList<Job>();
            }
            lsJobs.add(newJob);
            final boolean success = AlumniCollection.updateAlumni(
                    myCurrentAlumniSelected, DataTypes.JOB, lsJobs);      
            if (success) {
                JOptionPane.showMessageDialog(
                        null, "Job was successfully added");                 
            } else {
                JOptionPane.showMessageDialog(
                        null, "Error adding Job.", "Add Error", JOptionPane.ERROR_MESSAGE);
            }
        }
      
    }
    
    /**
     * Removes a Job.
     */
    private void performRemoveJob() {

        final int choice = displayJobSelectionWindow();
        if (choice == JOptionPane.OK_OPTION) { // User wants to go ahead with removal
            if (myItemRemoveModifySelection.getSelectedIndex() >= 0) {
                final List<Job> jobs = myCurrentAlumniSelected.getMyJobs();

                if (jobs != null) {
                    final Object item = jobs.remove(
                            myItemRemoveModifySelection.getSelectedIndex());
                    if (item != null) {
                        final boolean success = AlumniCollection.updateAlumni(
                                myCurrentAlumniSelected, DataTypes.JOB, jobs);
                        if (success) {
                            JOptionPane.showMessageDialog(null, "Job successfully removed");
                        } else {
                            JOptionPane.showMessageDialog(
                                    null, "Error removing Job!", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }                  
                    }
                }
            }
        }
    }
    
    /**
     * Creates the job window with the ability to type in text.
     * @param theModify the job to modify.
     * @return a panel with attached components.
     */
    private JPanel createJobWindow(final boolean theModify) {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(8, 0));
        final String[] labelNames = {"Enter Company Name:", "Enter Position: ",
            "Enter Required Skills: ", "Enter Description: ", "Enter Comments:",
            "Enter Salary: "};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            final JLabel addLabel = new JLabel(labelNames[i]);
            myJobFields[i] = new JTextField(25);
            panel.add(addLabel);
            panel.add(myJobFields[i]);
            fields.add(panel);
        }
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 0));
        myActiveBox = new JCheckBox();
        myActiveBox.setSelected(false);
        if (theModify) {
            myActiveBox.setSelected(
                    myCurrentAlumniSelected.getMyJobs().get(
                            myItemRemoveModifySelection.getSelectedIndex()).isActive());
        }
        panel.add(new JLabel("Active?"));
        panel.add(myActiveBox);
        fields.add(panel);

        return fields;

    }
    
    /**
     * Creates the internship window with the ability to type in text.
     * @return a panel with attached components.
     */
    @SuppressWarnings("unused")
    private JPanel createInternshipWindow() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(9, 0));
        final String[] labelNames = {"Enter Company Name:", 
            "Enter Position: ", "Enter Required Skills: ",
            "Enter Description: ", "Enter Comments:",
            "Enter Wage: ", "Enter Duration (in Months):"};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            final JLabel addLabel = new JLabel(labelNames[i]);
            myInternFields[i] = new JTextField(25);
            panel.add(addLabel);
            panel.add(myInternFields[i]);
            fields.add(panel);
        }
        return fields;
    }
    
    /**
     * Creates and displays a window asking user to select a job.
     * @return int signifying user's choice.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private int displayInternshipSelectionWindow() {
        final List<Internship> internships = myCurrentAlumniSelected.getMyInternships();
        
        if (internships != null && internships.size() > 0) {
            final List<String> revised = new ArrayList<String>();
            for (int i = 0; i < internships.size(); i++) {
                revised.add("Company: " 
                        + internships.get(i).getMyCompany() 
                        + ", Position: " + internships.get(i).getMyPosition());
            }
            final JPanel mainPanel = new JPanel();
            final JPanel comboPanel = new JPanel();
            comboPanel.setLayout(new GridLayout(1, 1));
            final Object[] options = revised.toArray();
            myItemRemoveModifySelection = new JComboBox(options);
            if (options.length != 0) {
                myModifyTypeSelections.setSelectedIndex(0);
            }
            comboPanel.add(new JLabel("Select internship: "));
            comboPanel.add(myItemRemoveModifySelection);
            mainPanel.add(comboPanel, BorderLayout.CENTER);
            return JOptionPane.showConfirmDialog(
                    null, mainPanel, "Select Internship", JOptionPane.OK_OPTION);
        } else {
            JOptionPane.showMessageDialog(null, 
                    "There are no Internships", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return JOptionPane.CANCEL_OPTION;
    }
    
    /**
     * Modifies the selected internship.
     */
    private void performModifyInternshipSelection() {

        final int choice = displayInternshipSelectionWindow();
        if (choice == JOptionPane.OK_OPTION) { // User wants to go ahead with modification
            if (myItemRemoveModifySelection.getSelectedIndex() >= 0) {
                final int check = JOptionPane.showConfirmDialog(
                        null, displayInternshipWindow(), 
                        "Data Entry", JOptionPane.OK_CANCEL_OPTION);
                if (check != JOptionPane.OK_OPTION) {
                    return;
                }

                final List<Internship> lsIntShip = myCurrentAlumniSelected.getMyInternships();
                final Internship modifiedShip = lsIntShip.get(
                        myItemRemoveModifySelection.getSelectedIndex());
                if (myInternFields[0].getText().length() != 0) {
                    modifiedShip.setMyCompany(myInternFields[0].getText());
                }
                if (myInternFields[1].getText().length() != 0) {
                    modifiedShip.setMyPosition(myInternFields[1].getText());
                }
                if (myInternFields[2].getText().length() != 0) {
                    modifiedShip.setMySkillsReq(myInternFields[2].getText());
                }
                if (myInternFields[3].getText().length() != 0) {
                    modifiedShip.setMyDescription(myInternFields[3].getText());
                }
                if (myInternFields[4].getText().length() != 0) {
                    modifiedShip.setMyMiscComments(myInternFields[4].getText());
                }
                if (myInternFields[5].getText().length() != 0) {
                    if (myInternFields[5].getText().length() > 10) {
                        JOptionPane.showMessageDialog(null, "Wage is too large.");
                        return;
                    }
                    try {
                        modifiedShip.setMyWage(
                                Double.parseDouble(myInternFields[5].getText()));
                    } catch (final NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Wage must be a valid number");
                        return;
                    }
                }     
                if (myInternFields[6].getText().length() != 0) {
                    if (myInternFields[6].getText().length() > 10) {
                        JOptionPane.showMessageDialog(null, "Duration is too long.");
                        return;
                    }
                    try {
                        modifiedShip.setMyDuration(
                                Integer.parseInt(myInternFields[6].getText()));
                    } catch (final NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Duration must be a valid whole number");
                        return;
                    }
                }
                lsIntShip.remove(myItemRemoveModifySelection.getSelectedIndex());
                lsIntShip.add(modifiedShip);

                final boolean success = AlumniCollection.updateAlumni(
                        myCurrentAlumniSelected, DataTypes.INTNSHIP, lsIntShip);
                if (success) {
                    JOptionPane.showMessageDialog(
                            null, "Internship was successfully Modified");                 
                } else {
                    JOptionPane.showMessageDialog(
                            null, "Error modifying Internship.",
                            "Modify Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    /**
     * Displays Edit Internship Window.
     * @return JPanel that is the fields to display.
     */
    private JPanel displayInternshipWindow() {
        final JPanel fields = new JPanel();
        fields.setLayout(new GridLayout(9, 0));
        final String[] labelNames = {"Enter Company Name:", 
            "Enter Position: ", "Enter Required Skills: ",
            "Enter Description: ", "Enter Comments:",
            "Enter Wage: ", "Enter Duration (in months):"};
        for (int i = 0; i < labelNames.length; i++) {
            final JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 0));
            final JLabel addLabel = new JLabel(labelNames[i]);
            myInternFields[i] = new JTextField(25);
            panel.add(addLabel);
            panel.add(myInternFields[i]);
            fields.add(panel);
        }
        return fields;
    }
    
    /**
     * Removes an internship.
     */
    private void performRemoveIntship() {
        final int choice = displayInternshipSelectionWindow();
        if (choice == JOptionPane.OK_OPTION) { // User wants to go ahead with modification
            if (myItemRemoveModifySelection.getSelectedIndex() >= 0) {       
                final List<Internship> internships = 
                        myCurrentAlumniSelected.getMyInternships();
                final Object item = internships.remove(
                        myItemRemoveModifySelection.getSelectedIndex());
                if (item != null) {
                    final boolean success = 
                            AlumniCollection.updateAlumni(
                                    myCurrentAlumniSelected, DataTypes.INTNSHIP, internships);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Internship successfully removed");
                    } else {
                        JOptionPane.showMessageDialog(
                                null, "Error removing internship!", 
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }                  
                }
            }
        }
    }
    
    /**
     * Adds a Internship to the Alumni.
     */
    private void performAddInternship() {
      
        final int check = JOptionPane.showConfirmDialog(
                null, displayInternshipWindow(), "Data Entry", JOptionPane.OK_CANCEL_OPTION);
        if (check != JOptionPane.OK_OPTION) {
            return;
        }

        if (myInternFields[0].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Company name is required");
            return;
        }
        if (myInternFields[1].getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Position is required");
            return;
        }
        
        try {
            if (myInternFields[5].getText().length() > 10) {
                JOptionPane.showMessageDialog(null, "Wage is too large.");
                return;
            }
            Double.parseDouble(myInternFields[5].getText());          
        } catch (final NumberFormatException e) {
            
            JOptionPane.showMessageDialog(null, "Wage is required and must be a valid number");
            return;
        }
        
        try {
            if (myInternFields[6].getText().length() > 10) {
                JOptionPane.showMessageDialog(null, "Duration is too long.");
                return;
            }
            Integer.parseInt(myInternFields[6].getText());
        } catch (final NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null, "Duration is required and must be a valid whole number");
            return;
        }
        
        final Internship intnship = new Internship(
                myInternFields[0].getText(),
                myInternFields[1].getText(), myInternFields[2].getText(), 
                myInternFields[3].getText(), myInternFields[4].getText(),
                Double.parseDouble(
                        myInternFields[5].getText()),
                        Integer.parseInt(myInternFields[6].getText()));    
        
        List<Internship> lsIntShip = myCurrentAlumniSelected.getMyInternships();
        if (lsIntShip == null) {
            lsIntShip = new ArrayList<Internship>();
        }

        lsIntShip.add(intnship);

        final boolean success = AlumniCollection.updateAlumni(
                myCurrentAlumniSelected, DataTypes.INTNSHIP, lsIntShip);

        if (success) {
            JOptionPane.showMessageDialog(
                    null, "Internship was successfully added");                 
        } else {
            JOptionPane.showMessageDialog(
                    null, "Error adding Internship.", "Add Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    /**
     * Determines what it needs to show to the user and shows it.
     * @param theType type of field it needs to show.
     */
    private void performView(final DataTypes theType) {
        JPanel mainPanel = null;
        if (theType == DataTypes.INTNSHIP) {
            int size = 0;
            if (myCurrentAlumniSelected.getMyInternships() != null) {
                size = myCurrentAlumniSelected.getMyInternships().size();
            }
            final String[] labelNames = {"Company Name",
                "Position ", "Required Skills",
                "Description ", "Comments", "Wage: ", "Duration"};
            final Object[][] data = new Object[size][labelNames.length];
             
            for (int i = 0; i < data.length; i++) {
                data[i][0] = myCurrentAlumniSelected.getMyInternships().get(i).getMyCompany();
                data[i][1] = myCurrentAlumniSelected.getMyInternships().get(i).getMyPosition();
                data[i][2] = myCurrentAlumniSelected.getMyInternships().
                        get(i).getMySkillsReq();
                data[i][3] = myCurrentAlumniSelected.getMyInternships().
                        get(i).getMyDescription();
                data[i][4] = myCurrentAlumniSelected.getMyInternships().
                        get(i).getMyMiscComments();
                data[i][5] = myCurrentAlumniSelected.getMyInternships().get(i).getMyWage();
                data[i][6] = myCurrentAlumniSelected.getMyInternships().get(i).getMyDuration();
            }
            mainPanel = createTable(data, labelNames);          
        } else if (theType == DataTypes.JOB) { // If job, load all job data into table
            final String[] labelNames = {"Company Name:",
                "Position: ", "Required Skills: ",
                "Description: ", "Comments:", "Salary: "};
            int size = 0;
            if (myCurrentAlumniSelected.getMyJobs() != null) {
                size = myCurrentAlumniSelected.getMyJobs().size();
            }
            final Object[][] data = new Object[size][labelNames.length];
            
            for (int i = 0; i < data.length; i++) {
                data[i][0] = myCurrentAlumniSelected.getMyJobs().get(i).getMyCompany();
                data[i][1] = myCurrentAlumniSelected.getMyJobs().get(i).getMyPosition();
                data[i][2] = myCurrentAlumniSelected.getMyJobs().get(i).getMySkillsReq();
                data[i][3] = myCurrentAlumniSelected.getMyJobs().get(i).getMyDescription();
                data[i][4] = myCurrentAlumniSelected.getMyJobs().get(i).getMyMiscComments();
                data[i][5] = myCurrentAlumniSelected.getMyJobs().get(i).getSalary();
            }
            mainPanel = createTable(data, labelNames);          
        } else if (theType == DataTypes.COLLEGES) {
            
            int size = 0;
            if (myCurrentAlumniSelected.getMyTransferColleges() != null) {
                size = myCurrentAlumniSelected.getMyTransferColleges().size();
            }
            final String[] labelNames = {"College Name:", 
                "GPA:", "Degree:", "Year:", "Term:"};
            final Object[][] data = new Object[size][labelNames.length];
            
            for (int i = 0; i < data.length; i++) {
                data[i][0] = myCurrentAlumniSelected.getMyTransferColleges().
                        get(i).getMyName();
                data[i][1] = myCurrentAlumniSelected.getMyTransferColleges().
                        get(i).getMyGPA();
                data[i][2] = myCurrentAlumniSelected.getMyTransferColleges().
                        get(i).getMyDegree();
                data[i][3] = myCurrentAlumniSelected.getMyTransferColleges().
                        get(i).getMyYear();
                data[i][4] = myCurrentAlumniSelected.getMyTransferColleges().
                        get(i).getMyTerm();
            }
            mainPanel = createTable(data, labelNames);          
        }
        
        
        if (mainPanel != null) {
            JOptionPane.showMessageDialog(null, mainPanel, "View", JOptionPane.PLAIN_MESSAGE);
        }
          
    }
    
    /**
     * Creates a table based on information passed in.
     * @param theData The data to be entered.
     * @param theColumnNames The names of the columns.
     * @return A panel containing a table.
     */
    @SuppressWarnings("serial")
    private JPanel createTable(final Object[][] theData, final String[] theColumnNames) {
        final JPanel mainPanel = new JPanel();
        final TableModel model = new DefaultTableModel(theData, theColumnNames)
        {
            public boolean isCellEditable(final int theRow, final int theColumn) {
                return false; //This causes all cells to be not editable
            }
        };
        final JTable tempTable = new JTable(model);
        final JScrollPane scrollPane = new JScrollPane(tempTable);
        tempTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mainPanel.add(scrollPane);
        
        return mainPanel;
    }

    /**
     * Opens up the edit windows for the selected Alumni.
     * @param myCurrentAlumniSelected
     */
    private void performEdit() {
        
        final JPanel mainPanel = new JPanel(new BorderLayout());
        
        mainPanel.add(makeUpperPanel(), BorderLayout.NORTH);
        mainPanel.add(makeLowerPanel(), BorderLayout.SOUTH);
                   
        JOptionPane.showConfirmDialog(
                null, mainPanel, "Edit Alumni", JOptionPane.PLAIN_MESSAGE);
        
        
    }
    
    /**
     * Makes the upper panel for the edit panel.
     * @return a JPanel with components attached.
     */
    private JPanel makeUpperPanel() {
        final JPanel mainPanel = new JPanel(new BorderLayout()); 
        final JPanel labelPanel = new JPanel(new GridLayout(8, 2));   
        final JPanel modifyPanel = new JPanel(new BorderLayout());
        
        // Create the labels and entry boxes for edits
        labelPanel.add(new JLabel("Name: "));
        myModifyFields[0] = new JTextField();
        labelPanel.add(myModifyFields[0]);
        labelPanel.add(new JLabel("Degree Track: "));
        myModifyFields[1] = new JTextField();
        labelPanel.add(myModifyFields[1]);
        labelPanel.add(new JLabel("Degree Level: "));
        myModifyFields[2] = new JTextField();
        labelPanel.add(myModifyFields[2]);
        labelPanel.add(new JLabel("Year: "));
        myModifyFields[3] = new JTextField();
        labelPanel.add(myModifyFields[3]);
        labelPanel.add(new JLabel("Term: "));
        myModifyFields[4] = new JTextField();
        labelPanel.add(myModifyFields[4]);
        labelPanel.add(new JLabel("GPA: "));
        myModifyFields[5] = new JTextField();
        labelPanel.add(myModifyFields[5]);
        labelPanel.add(new JLabel("University Email: "));
        myModifyFields[6] = new JTextField();
        labelPanel.add(myModifyFields[6]);
        labelPanel.add(new JLabel("Personal Email: "));
        myModifyFields[7] = new JTextField();
        labelPanel.add(myModifyFields[7]);
        
        mainPanel.add(labelPanel, BorderLayout.CENTER);
    
        myModifyBtn = new JButton("Modify");
        myModifyBtn.addActionListener(this);
        modifyPanel.add(myModifyBtn, BorderLayout.CENTER);
        mainPanel.add(modifyPanel, BorderLayout.EAST);
        
        return mainPanel;
                     
    }
     
     /**
      * Makes the lower panel of the edit panel.
      * @return a JPanel.
      */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private JPanel makeLowerPanel() {
        final JPanel mainPanel = new JPanel(new BorderLayout());

        // Get choices to display in the combo box
        final JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(1, 1));
        final Object[] options = {"Internship(s)", "Job(s)", "Transfer College(s)"};
        myModifyTypeSelections = new JComboBox(options);
        if (options.length != 0) {
            myModifyTypeSelections.setSelectedIndex(0);
        }
        comboPanel.add(new JLabel("Select item to modify:"));
        comboPanel.add(myModifyTypeSelections);
        mainPanel.add(comboPanel, BorderLayout.NORTH);
        
        // To hold the buttons that will act upon the choice
        final JPanel buttonPanel = new JPanel(new GridLayout(1, 3));

        myAddSelection = new JButton("Add to Selection");
        myAddSelection.addActionListener(this);
        myRemoveSelection = new JButton("Remove from Selection");
        myRemoveSelection.addActionListener(this);
        myModifySelection = new JButton("Modify Selection");
        myModifySelection.addActionListener(this);

        buttonPanel.add(myAddSelection);
        buttonPanel.add(myRemoveSelection);
        buttonPanel.add(myModifySelection);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
               
        return mainPanel;
    }
    
   
    
    /**
     * Modifies the Alumni.
     * Goes through the fields checking to see whether 
     * anything was modified, if so, it checks to see
     * whether the data is valid before updating the Alumni.
     */
    private void performModify() {
        boolean dataEntered = false;
        boolean modified = false;
        for (int i = 0; i < myModifyFields.length; i++) {
            if (myModifyFields[i].getText().length() != 0) {
                dataEntered = true;
                final String textData = myModifyFields[i].getText();
                switch (i) {
                    case 0:
                        if (textData.length() > 25) {
                            JOptionPane.showMessageDialog(null, "Name is too long.");
                        } else {
                            modified = AlumniCollection.updateAlumni(
                                    myCurrentAlumniSelected, DataTypes.NAME, textData);
                        }
                        break;
                    case 1: // Degree Track
                        if (textData.length() > 25) {
                            JOptionPane.showMessageDialog(null, "Degree Track is too long.");
                        } else {
                            modified = AlumniCollection.updateAlumni(
                                    myCurrentAlumniSelected, DataTypes.TRACK, textData);
                        }
                        break;
                    case 2: // Degree Level
                        if (textData.length() > 25) {
                            JOptionPane.showMessageDialog(null, "Degree Level is too long.");
                        } else {
                            modified = AlumniCollection.updateAlumni(
                                    myCurrentAlumniSelected, DataTypes.LEVEL, textData);
                        }
                        break;
                    case 3: // Year
                        if (textData.length() > 25) {
                            JOptionPane.showMessageDialog(null, "Year is too long.");
                        } else {
                            try {
                                Integer.parseInt(textData);
                            } catch (final NumberFormatException e) {
                                JOptionPane.showMessageDialog(null,
                                        "Year should be a number.");
                                return;
                            }
                            modified = AlumniCollection.updateAlumni(
                                myCurrentAlumniSelected, DataTypes.YEAR, textData);
                        }
                        break;
                    case 4: // Term               
                        if (textData.length() > 25) {
                            JOptionPane.showMessageDialog(null, "Term is too long.");
                        } else {
                            modified = AlumniCollection.updateAlumni(
                                    myCurrentAlumniSelected,
                                    DataTypes.TERM, textData);           
                        }                            
                        break;
                    case 5: // GPA       
                        try {
                            Double.parseDouble(textData);
                            if (textData.length() > 4) {
                                JOptionPane.showMessageDialog(
                                        null, "GPA can at most be four digits long");
                            } else {
                                modified = AlumniCollection.updateAlumni(
                                        myCurrentAlumniSelected, DataTypes.GPA, textData);
                            }
                        } catch (final NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "GPA must be a number");
                        }
                        break;
                    case 6: // University Email       
                        if (textData.length() > 25) {
                            JOptionPane.showMessageDialog(
                                    null, "University Email is too long");
                        } else {
                            modified = AlumniCollection.updateAlumni(
                                    myCurrentAlumniSelected, DataTypes.UNIEMAIL, textData);
                        }
                        break;
                    case 7: // Personal Email       
                        if (textData.length() > 25) {
                            JOptionPane.showMessageDialog(null, "Personal Email is too long");
                        } else {
                            modified = AlumniCollection.updateAlumni(
                                    myCurrentAlumniSelected, DataTypes.PERSEMAIL, textData);
                        }
                        break;
                    default:
                        break;
                }
                myModifyFields[i].setText(null);
            }
        }

        // Check if user entered data
        if (!dataEntered) {
            JOptionPane.showMessageDialog(null, "There's nothing to modify!");
        } else if (modified) {
            // Check whether edit was successful
            JOptionPane.showMessageDialog(null, "Alumni modified");

        } 


    }
    
    @Override
    public void actionPerformed(final ActionEvent theE) {
        if (theE.getSource() == myNameSearch) { // Search by name
            myLastSearchByName = true;
            myCurrentPanel.removeAll();
            myAlumniList = getData(DataTypes.NAME, mySearchTerm.getText());
            // Recreate the list to update it 
            myListPanel = createListPanel();
            myCurrentPanel.add(myListPanel);
            myCurrentPanel.revalidate();
            this.repaint();
            if (myAlumniList.size() == 0) {
                JOptionPane.showMessageDialog(null, "No Alumni found!");
            }
        } else if (theE.getSource() == myIdSearch) { // Search by ID
            myLastSearchByName = false;
            try {
                Integer.parseInt(mySearchTerm.getText());
            } catch (final NumberFormatException e2) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number!");
                return;
            }
            myCurrentPanel.removeAll();
            myAlumniList = getData(DataTypes.ID, mySearchTerm.getText());
            // Recreate the list to update it 
            myListPanel = createListPanel();
            myCurrentPanel.add(myListPanel);
            myCurrentPanel.revalidate();
            this.repaint();
            if (myAlumniList.size() == 0) {
                JOptionPane.showMessageDialog(null, "No Alumni found!");
            }
        } else { 
            // to reuse code
            final int selection2 = myTable.getSelectedRow();
            if (theE.getSource() == myEditBtn) { // Opens up edit panel
                final int rowSelected = myTable.getSelectedRow();
                if (rowSelected == -1) {
                    JOptionPane.showMessageDialog(null, "No Alumni Selected");
                } else {
                    myCurrentAlumniSelected = myAlumniList.get(rowSelected);
                    performEdit();              
                }
            } else if (theE.getSource()
                    == myViewInternshipsBtn) { // Displays selected alumni's internships
                final int rowSelected = myTable.getSelectedRow();
                if (rowSelected == -1) {
                    JOptionPane.showMessageDialog(null, "No Alumni Selected");
                } else {
                    myCurrentAlumniSelected = myAlumniList.get(rowSelected);
                    performView(DataTypes.INTNSHIP);        
                }
            } else if (theE.getSource() == myViewJobsBtn) { // Displays selected alumni's jobs
                final int rowSelected = myTable.getSelectedRow();
                if (rowSelected == -1) {
                    JOptionPane.showMessageDialog(null, "No Alumni Selected");
                } else {
                    myCurrentAlumniSelected = myAlumniList.get(rowSelected);
                    performView(DataTypes.JOB);

                }
            } else if (theE.getSource()
                    == myViewCollegesBtn) { // Displays selected alumni's colleges
                final int rowSelected = myTable.getSelectedRow();
                if (rowSelected == -1) {
                    JOptionPane.showMessageDialog(null, "No Alumni Selected");
                } else {
                    myCurrentAlumniSelected = myAlumniList.get(rowSelected);
                    performView(DataTypes.COLLEGES);

                }
            } else if (theE.getSource()
                    == myModifyBtn) {  // Edits the currently selected Alumni          
                performModify();

            } else if (theE.getSource() == myAddSelection) { // Brings up panel for adding 
                // internships/jobs/colleges
                final int selection = myModifyTypeSelections.getSelectedIndex();
                if (selection == 0) { // Internship is being added
                    performAddInternship();
                } else if (selection == 1) { // Job is being added
                    performAddJob();
                } else if (selection == 2) { // College is being added
                    performAddCollege();
                }
            } else if (theE.getSource()
                    == myRemoveSelection) { // Brings up panel for removing 
                // internships/jobs/colleges
                final int selection = myModifyTypeSelections.getSelectedIndex();
                if (selection == 0) { // Internship is being removed
                    performRemoveIntship();
                } else if (selection == 1) { // Job is being removed
                    performRemoveJob();
                } else if (selection == 2) { // College is being removed
                    performRemoveCollege();
                }
            } else if (theE.getSource()
                    == myModifySelection) { // Brings up panel for modifying 
                // internships/jobs/colleges
                final int selection = myModifyTypeSelections.getSelectedIndex();
                if (selection == 0) { // Internship is being modified
                    performModifyInternshipSelection();
                } else if (selection == 1) { // Job is being modified
                    performModifyJobSelection();
                } else if (selection == 2) { // College is being modified
                    performModifyCollegeSelection();
                }
            }
            if (myLastSearchByName) {
                myCurrentPanel.removeAll();
                myAlumniList = getData(DataTypes.NAME, mySearchTerm.getText());
                // Recreate the list to update it 
                myListPanel = createListPanel();
                myCurrentPanel.add(myListPanel);
                myCurrentPanel.revalidate();
                this.repaint();
                if (selection2 >= 0) {
                    myTable.setRowSelectionInterval(selection2, selection2);
                }                
            } else { // Search by ID
                myCurrentPanel.removeAll();
                myAlumniList = getData(DataTypes.ID, mySearchTerm.getText());
                // Recreate the list to update it 
                myListPanel = createListPanel();
                myCurrentPanel.add(myListPanel);
                myCurrentPanel.revalidate();
                this.repaint();
                if (selection2 >= 0) {
                    myTable.setRowSelectionInterval(selection2, selection2);
                }
            }
            for (int i = 0; i < myAlumniList.size(); i++) {
                if (myAlumniList.get(i).getMyID() == myCurrentAlumniSelected.getMyID()) {
                    myCurrentAlumniSelected = myAlumniList.get(i);
                }
            }
        }

    }
}
