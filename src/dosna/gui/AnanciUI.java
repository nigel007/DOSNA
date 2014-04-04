package dosna.gui;

import dosna.dhtAbstraction.DataManager;
import dosna.osn.actor.Actor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 * The Main User Interface class of DOSNA.
 *
 * @author Joshua Kissoon
 * @since 20140401
 */
public class AnanciUI extends JFrame
{

    /* Properties */
    private final static int FRAME_WIDTH = 1200;
    private final static int FRAME_HEIGHT = 800;

    private final DataManager dataManager;
    private final Actor actor;

    /* Components */
    private JSplitPane splitPane;
    private JPanel leftSection, rightSection;
    private JScrollPane leftSectionSP, rightSectionSP;

    /* Listeners */
    private final ActionListener actionListener;

    /**
     * Initialize the UI Class
     *
     * @param mngr  The DataManager used to read and store data
     * @param actor Actor currently logged in
     */
    public AnanciUI(final DataManager mngr, final Actor actor)
    {
        this.dataManager = mngr;
        this.actor = actor;
        this.actionListener = new AnanciUIActionListener();
    }

    /**
     * Create the GUI and populate it with the necessary elements
     */
    public void create()
    {
        this.createMainMenu();
        this.addWindowListener(new AnanciUIWindowListener());

        /**
         * @section Left Side
         */
        leftSection = new JPanel();
        leftSectionSP = new JScrollPane(leftSection);
        //leftSectionSP.setMinimumSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 2));

        /* Status Add Form */
        StatusAddForm saf = new StatusAddForm();
        leftSection.add(saf, BorderLayout.NORTH);

        /**
         * @section Right Side
         */
        rightSection = new JPanel();
        rightSectionSP = new JScrollPane(rightSection);

        /**
         * @section Populating all content to the Main UI
         */
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.leftSectionSP, this.rightSectionSP);
        splitPane.setDividerLocation(FRAME_WIDTH / 2);
        this.getContentPane().add(splitPane, BorderLayout.CENTER);
    }

    /**
     * Create the main menu and add it to the JFrame
     */
    private void createMainMenu()
    {
        final JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu menu;
        JMenuItem menuItem;

        /* Setting up the Home menu */
        menu = new JMenu("Home");
        menuBar.add(menu);

        menuItem = new JMenuItem("Connections");
        menuItem.addActionListener(this.actionListener);
        menuItem.setActionCommand(AnanciUIActionListener.AC_CONNECTIONS);
        menu.add(menuItem);


        /* Setting up the Actor menu */
        menu = new JMenu("You");
        menuBar.add(menu);

        /* Setting up the Help menu */
        menu = new JMenu("Help");
        menuBar.add(menu);
    }

    /**
     * Display the GUI to the screen.
     */
    public void display()
    {
        this.setTitle("Ananci - " + this.actor.getName());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Refresh the content on the GUI
     */
    public void refresh()
    {
        this.invalidate();
        this.validate();
        this.repaint();
    }

    /**
     * Subclass that handles Action Events for this UI
     */
    private class AnanciUIActionListener implements ActionListener
    {

        /* Action Commands used */
        public static final String AC_CONNECTIONS = "AC_CONNECTIONS";

        @Override
        public void actionPerformed(ActionEvent evt)
        {
            switch (evt.getActionCommand())
            {
                case AC_CONNECTIONS:
                    /* Load the Connections Manager UI */
                    break;
            }
        }
    }

    /**
     * Subclass that handles Window Events for this UI
     */
    private class AnanciUIWindowListener extends WindowAdapter
    {

        @Override
        public void windowClosing(final WindowEvent e)
        {
            /* Save the state before closing */
            try
            {
                AnanciUI.this.dataManager.shutdown(true);
            }
            catch (IOException ex)
            {
                System.err.println("Shutdown save state operation failed; message: " + ex.getMessage());
            }
        }
    }
}
