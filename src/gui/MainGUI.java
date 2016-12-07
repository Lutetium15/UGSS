package gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Main Driver to initialize the GUIs.
 * @author GROUP8
 * @version 12/6/2016
 */
public class MainGUI extends JFrame implements MouseListener {

    /**
     * Id for serialization.
     */
    private static final long serialVersionUID = 7352018073986710489L;
    /**
     * Report panel component.
     */
    private JComponent myRPanel;
    /**
     * JPanel panel.
     */
    private JPanel myPanel;
    /**
     * TabbedPane.
     */
    private JTabbedPane myTabbedPane;

    /**
     * Launches the GUI.
     */
    public MainGUI() {
        super("University Graduated Students System");
        createComponents();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(700, 700);
    }
    
    /**
     * Starts the program.
     * @param theArgs Arguments
     */
    public static void main(final String[] theArgs) {
        new MainGUI();
    }
    
    /**
     * Create the tabs for each part of the system. 
     */
    private void createComponents() {
        myTabbedPane = new JTabbedPane();
        final JComponent alPanel = makeTextPanel("Add Alumni");
        myTabbedPane.addTab("Add Alumni", alPanel);
        final JComponent sPanel = makeTextPanel("View");
        myTabbedPane.addTab("View", sPanel);
        myRPanel = makeTextPanel("Report");
        myRPanel.addMouseListener(this);
        myTabbedPane.addTab("Report", myRPanel);
        add(myTabbedPane);
    }

    /**
     * Create the particular part to add to the tab based on the type.
     * @param theType A String Representing the Type of Tab.
     * @return The Panel to Display.
     */
    private JComponent makeTextPanel(final String theType) {
        myPanel = new JPanel();
        if ("View".equalsIgnoreCase(theType)) {
            myPanel.add(new ViewGUI());
        } else if ("Add Alumni".equalsIgnoreCase(theType)) {
            myPanel.add(new AlumniAddGUI());
        } else if ("Report".equalsIgnoreCase(theType)) {
            myPanel.add(new ReportGUI());
        }
        return myPanel;
    }
    /**
     * Unimplemented method for MouseListener.
     */
    @Override
    public void mouseClicked(final MouseEvent theE) {       
    }
    /**
     * Unimplemented method for MouseListener.
     */
    @Override
    public void mouseEntered(final MouseEvent theE) {
    }
    /**
     * Unimplemented method for MouseListener.
     */
    @Override
    public void mouseExited(final MouseEvent theE) {
    }
    /**
     * This method will recall and update report pane.
     */
    @Override
    public void mousePressed(final MouseEvent theE) {
        if (theE.getSource() == myRPanel) {
            myTabbedPane.remove(myRPanel);
            myRPanel = makeTextPanel("Report");
            myTabbedPane.addTab("Report", myRPanel);
            myTabbedPane.setSelectedComponent(myRPanel);
        }
        
    }
    /**
     * Unimplemented method for MouseListener.
     */
    @Override
    public void mouseReleased(final MouseEvent theE) {
    }
}
