import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu {

  private JFrame window;
  private JPanel topPanel, mainPanel, leftPanel, rightPanel;
  private JLabel titleLabel, profileLabel, newResizedProfile;
  private JButton editButton, editProfilePictureButton, nameButton, idButton, workplaceButton, hometownButton;
  private JFormattedTextField textField;
  //private Tree allPosts;
  private ImageIcon profile1, profileIcon;
  //private Set<User> allUsers;
  private boolean displayed;
  private JFileChooser chooser;
  User user1 = new User("Dave Smith", "12345", "a place", "dundee", null, null, null);
  User user2 = new User("Steve", "id", "a workplace", "edinburgh", null, null, null);
  User user3 = new User("abbie", "skdjfh", "asda", "glasgow", null, null, null);

  /**
   * Constructor for menu class
   */
  public Menu() {
    displayed = false;
    // create the window
    window = new JFrame();

    // create the panels
    createMainPanels();

    window.setJMenuBar(createMenuBar());
    window.setContentPane(topPanel);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setTitle("para.social");
    window.setVisible(true);
    window.pack();

    Set<User> friends1 = new HashSet<User>();
    friends1.add(user2);
    friends1.add(user3);
    user1.setFriends(friends1);
    user1.setPfp(profile1.getImage());

  }

  /**
   * main method to launch GUI program on EDT
   */
  public static void main(String[] args) {
    // use annonymous class
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      // override run method 
      public void run() {
        // call separate method to set up and run GUI
        runProgram();
      }
    });

  }

  /*
   * Static method to create an instance of Menu class
   */
  public static void runProgram() {
    // create an instance of Menu class
    Menu newMenu = new Menu();
  }

  /**
   * Create the main panels
   */
  public void createMainPanels() {
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
  public void createFriendsPanel() {
    rightPanel.add(createTextPanel());
  }

  /**
   * Resize the image and put it into form so that it can be displayed using JLabel
   * 
   * @param
   * @return
   */
  public ImageIcon resizeImage(ImageIcon testProfile)
  {
     // https://www.youtube.com/watch?v=ntirmRhy6Fw
    // reminder to put this in a try catch
    Image testProfileImage = testProfile.getImage();
    // https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    Image resized = testProfileImage.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(resized);

    return resizedIcon;
  }

  /**
   * Create the left hand panel with all profile information
   */
  public void createProfilePanel() {

    // as a temporary fix, image has been moved to source code file
    profile1 = new javax.swing.ImageIcon(getClass().getResource("Images/PFPs/Marcus.jpg"));
    profileIcon = resizeImage(profile1);
    profileLabel = new JLabel(profileIcon);

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

    // create profile picture edit button
    editProfilePictureButton = new JButton("Edit Profile Picture");
    editProfilePictureButton.setBackground(Color.decode("0xe07a5f"));

    // create each edit button
    nameButton = new JButton("Change Name");
    nameButton.setBackground(Color.decode("0xe07a5f"));
    idButton = new JButton("Change ID");
    idButton.setBackground(Color.decode("0xe07a5f"));
    workplaceButton = new JButton("Change Workplace");
    workplaceButton.setBackground(Color.decode("0xe07a5f"));
    hometownButton = new JButton("Change Hometown");
    hometownButton.setBackground(Color.decode("0xe07a5f"));

    // action listener for edit button
    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // only display the buttons if they have not already been displayed
        if (displayed == false) {

          // change name
          leftPanel.add(nameButton);
          nameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              JTextField changeName = new JTextField("Change your name");
              leftPanel.add(changeName);

              JButton submitButton = new JButton("Submit");
              submitButton.setBackground(Color.decode("0xe07a5f"));
              leftPanel.add(submitButton);
              // add action listener to submit button
              submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  user1.setName(changeName.getText());
                  name.setText(changeName.getText());
                  leftPanel.remove(submitButton);
                  leftPanel.remove(changeName);
                }
              });
              SwingUtilities.updateComponentTreeUI(window);
            }

          });

          // change id button
          leftPanel.add(idButton);
          idButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              JTextField changeID = new JTextField("Change your ID");
              leftPanel.add(changeID);

              JButton submitButton = new JButton("Submit");
              submitButton.setBackground(Color.decode("0xe07a5f"));
              leftPanel.add(submitButton);
              // add action listener to submit button
              submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  user1.setID(changeID.getText());
                  id.setText("ID: " + changeID.getText());
                  leftPanel.remove(submitButton);
                  leftPanel.remove(changeID);
                }
              });
              SwingUtilities.updateComponentTreeUI(window);
            }

          });

          // change workplace
          leftPanel.add(workplaceButton);
          workplaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              JTextField changeWorkPlace = new JTextField("Change your Workplace");
              leftPanel.add(changeWorkPlace);

              JButton submitButton = new JButton("Submit");
              submitButton.setBackground(Color.decode("0xe07a5f"));
              leftPanel.add(submitButton);
              // add action listener to submit button
              submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  user1.setWorkPlace(changeWorkPlace.getText());
                  work.setText("Workplace: " + changeWorkPlace.getText());
                  leftPanel.remove(submitButton);
                  leftPanel.remove(changeWorkPlace);
                }
              });
              SwingUtilities.updateComponentTreeUI(window);
            }

          });

          // change hometown
          leftPanel.add(hometownButton);
          hometownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              JTextField changeHometown = new JTextField("Change your hometown");
              leftPanel.add(changeHometown);

              JButton submitButton = new JButton("Submit");
              submitButton.setBackground(Color.decode("0xe07a5f"));
              leftPanel.add(submitButton);
              // add action listener to submit button
              submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                  user1.setHomeTown(changeHometown.getText());
                  home.setText("Hometown:" + changeHometown.getText());
                  leftPanel.remove(submitButton);
                  leftPanel.remove(changeHometown);

                }
              });
              SwingUtilities.updateComponentTreeUI(window);
            }

          });

          // change profile picture
          leftPanel.add(editProfilePictureButton);
          editProfilePictureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
              /* 
              // changing the profile picture to a specific different picture now works
              ImageIcon profile2 = new javax.swing.ImageIcon(getClass().getResource("Images/PFPs/Person.jpg"));
              profileLabel.setIcon(resizeImage(profile2));
              SwingUtilities.updateComponentTreeUI(window);*/

              selectFile();
            }

          });
          displayed = true;
          SwingUtilities.updateComponentTreeUI(window);
        } else {
          leftPanel.remove(nameButton);
          leftPanel.remove(idButton);
          leftPanel.remove(workplaceButton);
          leftPanel.remove(hometownButton);
          leftPanel.remove(editProfilePictureButton);
          displayed = false;
          SwingUtilities.updateComponentTreeUI(window);
        }
      }
    });
  }

  /**
   * Create the menu bar
   * 
   * @return The menu bar
   */
  public JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();

    // add a menu called to menuBar
    JMenu menu = new JMenu("Menu");
    menuBar.add(menu);

    // add a menu item
    JMenuItem menuItem = new JMenuItem("Menu Option 1");
    JMenuItem menuItem2 = new JMenuItem("Menu Option 2");
    menu.add(menuItem);
    menu.add(menuItem2);

    // make menu items do things
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

  /**
   * Creates a text panel to search
   * 
   * @return
   */
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

  /**
   * Select a new file to set the profile picture to
   * @return
   */
  public void selectFile()
  {
    chooser = new JFileChooser();

    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg");
    chooser.setFileFilter(filter);

    // display file chooser
    int returnVal = chooser.showOpenDialog(window);

    // check if user has selected a file and open it
    if (returnVal == JFileChooser.APPROVE_OPTION)
    {
      // open dialog box to select files
      File file = chooser.getSelectedFile();
      // get the file path
      System.out.println(file.getAbsolutePath());
      //JOptionPane.showMessageDialog(null, file.getPath());

      String[] filePathArray = file.getPath().split("src");
      String relative = filePathArray[1];

      ImageIcon newProfilePic = new ImageIcon();
      newProfilePic = new javax.swing.ImageIcon(getClass().getResource(relative));
    
      user1.setPfp(newProfilePic.getImage());
      profileLabel.setIcon(resizeImage(newProfilePic));
      SwingUtilities.updateComponentTreeUI(window);
      
    }
    else
    {
      //test message
      JOptionPane.showMessageDialog(null, "Test");
        //return null;
    }
  }

  /*
   * public void postsPanel()
   * {
   * 
   * BorderLayout layout = new BorderLayout();
   * topPanel = new JPanel(layout);
   * mainPanel = new JPanel();
   * mainPanel.setBackground(Color.decode("0xF5CCE8"));
   * 
   * // add main panel to top panel
   * topPanel.add(mainPanel, BorderLayout.CENTER);
   * 
   * allPosts.postorderDisplay(allPosts.getRoot());
   * 
   * }
   */

  /*
   * public void displayPost(Post p)
   * {
   * 
   * topPanel.add(); //add the Username of who posted it
   * topPanel.add(); //add the image
   * topPanel.add(); //the caption
   * topPanel.add(); //like button
   * 
   * }
   */
}
