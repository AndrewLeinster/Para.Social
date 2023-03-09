import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Menu {

    private JFrame window;
    private JPanel topPanel, mainPanel, sidePanel;
    private JLabel titleLabel;
    private JButton displayButton, friendsButton;
    private JFormattedTextField textField;
    User user1 = new User();

    /**
     * Constructor for menu class
     */
    public Menu()
    {
        // create the window
        window = new JFrame();

        // create the panels
        createMainPanels();

        window.setJMenuBar(createMenuBar());
        window.setContentPane(topPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("para.social");
        window.setVisible(true);
    }

      /**
     * main method
     * use it to launch a GUI program on the
     * Event Dispatch Thread (EDT)
     */
    public static void main(String[] args)
    {
        // use the Swing 'invokeLater' method to create a new 
        // Runnable object to run on the EDT
        // uses an anonymous class to represent the thread to run
        javax.swing.SwingUtilities.invokeLater(new Runnable()
                                                   {
                                                      // override the 'run' method that any Runnable object has
                                                      // put code here that needs to be run on a thread
                                                      public void run()
                                                      {
                                                          // call separate method to set up the GUI and run it
                                                          runProgram();
                                                      }
                                                    });

  }

  /*
	 * Static method to create an instance of Menu class
	 */
	public static void runProgram()
	{
		// create an instance of Menu class
        Menu newMenu = new Menu();
	}

    /**
     * Create the main panels
     */
    public void createMainPanels()
    {
        BorderLayout layout = new BorderLayout();
        topPanel = new JPanel(layout);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("0xF5CCE8"));
    
        // add main panel to top panel
        topPanel.add(mainPanel, BorderLayout.CENTER);

        //create a JButton
        displayButton = new JButton("Display user details");
        displayButton.setBackground(Color.decode("0x9F6BA0"));
        mainPanel.add(displayButton);

        //create a JButton
        friendsButton = new JButton("Display Friends");
        friendsButton.setBackground(Color.decode("0x9F6BA0"));
        mainPanel.add(friendsButton);

        mainPanel.add(createTextPanel());

        // action listener for the display button
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(displayButton, "Display details");
                user1.displayUserInfo();
            }
        });

        //action listener for display friends
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(displayButton, "Display friends");
                user1.displayFriends();
            }
        });
    }

    /**
     * Create the menu bar
     * @return The menu bar
     */
    public JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar(); 

        // add a menu called to menuBar
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);

        // add a menu item
        JMenuItem menuItem = new JMenuItem("Menu Option 1");
        JMenuItem menuItem2 = new JMenuItem("Menu Option 2");
        menu.add(menuItem);
        menu.add(menuItem2);

        //make menu items do things
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// do something
		    }
		});

        menuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// do something
		    }
		});

        menuBar.setBackground(Color.GRAY);
		return menuBar;
    }

    public JPanel createTextPanel() {
	    JPanel textPanel = new JPanel();
	    titleLabel = new JLabel("Search");
	    
	    // create a formatted text field called scaleField
	    textField = new JFormattedTextField(new String("Search for a friend"));
	    textField.setColumns(3);
	   
	   textPanel.add(titleLabel);
	   textPanel.add(textField);

	    textPanel.setMaximumSize(new Dimension(200, 40));
	    textPanel.setBackground(Color.lightGray);
	    return textPanel;
	  }

}
