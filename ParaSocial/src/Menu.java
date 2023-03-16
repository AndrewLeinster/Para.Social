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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.util.Set;
import java.util.HashSet;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;


public class Menu{

    private JFrame window;
    private JPanel topPanel, mainPanel, leftPanel, rightPanel;
    private JLabel titleLabel, profileLabel;
    private JButton editButton, friendsButton;
    private JFormattedTextField textField;
    private Tree allPosts;
    private ImageIcon profile1;
    private Set<User> allUsers;
    User user1 = new User("Dave Smith", "12345", "a place", "dundee", null, null, null);
    User user2 = new User("Steve", "id", "a workplace", "edinburgh", null, null, null);
    User user3 = new User("abbie", "skdjfh", "asda", "glasgow", null, null, null);
    

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
        window.setSize(1000, 700);
        window.setVisible(true);
        window.pack();

        Set<User> friends1 = new HashSet<User>();
        friends1.add(user2);
        friends1.add(user3);
        user1.setFriends(friends1);

        //user1.setPfp(profile1);
    
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
        rightPanel = new JPanel();
        leftPanel = new JPanel();
        mainPanel.setBackground(Color.decode("0x3d405b"));
        rightPanel.setBackground(Color.decode("0x81b29a"));
        leftPanel.setBackground(Color.decode("0xf2cc8f"));
    
        // add main, left and right panels to top panel
        topPanel.add(mainPanel, BorderLayout.CENTER);
        topPanel.add(rightPanel, BorderLayout.EAST);
        topPanel.add(leftPanel, BorderLayout.WEST);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        createFriendsPanel();
        createProfilePanel();
    }

    /*
  */

    /**
     * Method to create the right hand panel to display friends
     */
    public void createFriendsPanel()
    {
        rightPanel.add(createTextPanel());
    }

    /**
     * Create the left hand panel with all profile information
     */
    public void createProfilePanel()
    {
       // https://www.youtube.com/watch?v=ntirmRhy6Fw

        //profile image - i hate :)
        // reminder to put this in a try catch
        // as a temporary fix, image has been moved to source code file
        profile1 = new javax.swing.ImageIcon(getClass().getResource("Marcus.jpg"));
        Image profileImage = profile1.getImage();
        // https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
        Image resized = profileImage.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resized);
        profileLabel = new JLabel(resizedIcon);
        leftPanel.add(profileLabel);

        // add profile information
        JLabel name = new JLabel(user1.getName());
        name.setFont(new Font("Sans", Font.PLAIN, 20));
        leftPanel.add(name);
        JLabel id = new JLabel("ID: " + user1.getID());
        id.setFont(new Font("Sans", Font.PLAIN, 16));
        leftPanel.add(id);
        JLabel work = new JLabel("Workplace: " + user1.getWorkPlace());
        work.setFont(new Font("Sans", Font.PLAIN, 16));
        leftPanel.add(work);
        JLabel home = new JLabel("Hometown: " + user1.getHomeTown());
        home.setFont(new Font("Sans", Font.PLAIN, 16));
        leftPanel.add(home);

        // Create a JButton to edit details
	    editButton = new JButton("Edit Profile");
	    editButton.setBackground(Color.decode("0xe07a5f"));
	    leftPanel.add(editButton);
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
	    textField.setColumns(12);
	   
	   textPanel.add(titleLabel);
	   textPanel.add(textField);

	    textPanel.setMaximumSize(new Dimension(200, 40));
	    textPanel.setBackground(Color.lightGray);
	    return textPanel;
	  }
    
      /* 
      public void postsPanel()
      {

        BorderLayout layout = new BorderLayout();
        topPanel = new JPanel(layout);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("0xF5CCE8"));
    
        // add main panel to top panel
        topPanel.add(mainPanel, BorderLayout.CENTER);

        allPosts.postorderDisplay(allPosts.getRoot());

      }*/

      /*
      public void displayPost(Post p)
      {

        topPanel.add(); //add the Username of who posted it
        topPanel.add(); //add the image
        topPanel.add(); //the caption
        topPanel.add(); //like button

      }
      */
}
