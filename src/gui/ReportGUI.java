package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.Alumni;
import model.AlumniCollection;

/**
 * Report Tab for GUI will generate report based on user input.
 * @author GROUP8(Bui)
 * @version 11/29/2016
 */
public class ReportGUI extends JPanel implements ActionListener {
    
    /**
     * auto generated ID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * create variable to avoid check style.
     */
    private static final int FIVE = 500;
    /**
     * String variable to use throughout the class.
     */
    private static final String ALL = "All";
    /**
     * JCombo Box.
     */
    private JComboBox<String> myCmbCategories;
    /**
     * JComboBox for degreeTrack.
     */
    private JComboBox<String> myCmbDegreeTrack;
    /**
     * JPanel for search.
     */
    private JPanel myPnlReport;
    /**
     * JPanel for content.
     */
    private JPanel myPnlContent;
    /**
     * Generate button.
     */
    private JButton myBtnGenerate;
    /**
     * Create a table for display data.
     */
    private JTable myTable;
    /**
     * Scroll pane for table.
     */
    private JScrollPane myScrollPane;
    /**
     * Item Column names.
     */
    private String[] myItemColumnNames = {"Name",
                                          "Student ID", "Degree Track", 
                                          "Degree Level"};
    /**
     * Data to display.
     */
    private Object [][] myData;
    /**
     * List of Alumni.
     */
    private List<Alumni> myList;    
    /**
     * Constructor for report GUI.
     */
    public ReportGUI() {
        setLayout(new BorderLayout());
        myList = getData(ALL, ALL);
        createComponents();
        setVisible(true);
        setSize(FIVE, FIVE);
    }
    
   /**
    * Get data for report.
    * @param theLevel degree Level
    * @param theTrack degree Track
    * @return data to display
    */
    private List<Alumni> getData(final String theLevel,
            final String theTrack) {

        if (theLevel != null && theTrack != null) {
            myList = AlumniCollection.reportsearch(theLevel, theTrack);
        } else {
            myList = AlumniCollection.getAlumni();
        }
        if (myList != null) {
            myData = new Object[myList.size()][myItemColumnNames.length];
            for (int i = 0; i < myList.size(); i++) {
                myData[i][0] = myList.get(i).getMyName();
                myData[i][1] = myList.get(i).getMyID();
                myData[i][2] = myList.get(i).getMyDegreeTrack();
                myData[i][3] = myList.get(i).getMyDegreeLevel();
            }
        }

        return myList;
    }
    
    /**
     * Create components for report tab.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void createComponents() {
        final Object [] degreeLevel = AlumniCollection.getDegreeLevel();
        final Object[] degreeTrack = AlumniCollection.getDegreeTrack();
        myPnlReport = new JPanel();
        myPnlReport.setLayout(new GridLayout(3, 0));
        final JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new GridLayout(1, 1));
        
        if (degreeLevel != null) {
            myCmbCategories = new JComboBox(degreeLevel);
            comboPanel.add(new JLabel("Select Degree Level: "));
            comboPanel.add(myCmbCategories);
            myPnlReport.add(comboPanel);
        }
        
        final JPanel comboDegreeTrack = new JPanel();
        comboDegreeTrack.setLayout(new GridLayout(1, 1));
        if (degreeTrack != null) {
            myCmbDegreeTrack = new JComboBox(degreeTrack);
            comboDegreeTrack.add(new JLabel("Select Degree Track: "));
            comboDegreeTrack.add(myCmbDegreeTrack);
            myPnlReport.add(comboDegreeTrack);
        } 
        add(myPnlReport, BorderLayout.NORTH);
        final JPanel panel = new JPanel();
        myBtnGenerate = new JButton("Generate Report");
        myBtnGenerate.addActionListener(this);
        panel.add(myBtnGenerate);
        myPnlReport.add(panel);  
        myPnlContent = new JPanel();
        myTable = new JTable(myData, myItemColumnNames);
        myScrollPane = new JScrollPane(myTable);
        myPnlContent.add(myScrollPane);
        add(myPnlContent, BorderLayout.SOUTH);
        
    }
    
    
    /**
     * Perform actions when generate button is clicked.
     */
    @Override
    public void actionPerformed(final ActionEvent theE) {
        if (theE.getSource() == myBtnGenerate) {
            final String searchKey = (String) myCmbCategories.getSelectedItem();
            final String searchKey1 = (String) myCmbDegreeTrack.getSelectedItem();
            myList = getData(searchKey, searchKey1);
            myPnlContent.removeAll();
            myTable = new JTable(myData, myItemColumnNames);
            myScrollPane = new JScrollPane(myTable);
            myPnlContent.add(myScrollPane);
            myPnlContent.revalidate();
            this.repaint();
        }       
    }
}
