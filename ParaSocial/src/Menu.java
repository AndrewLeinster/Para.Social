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
import java.util.ArrayList;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu {

  private JFrame window;
  private JPanel topPanel, mainPanel, leftPanel, rightPanel, friendsPanel;
  private JScrollPane scrollPanel, scrollFriendsPanel;
  private JLabel titleLabel, profileLabel;
  private JButton editButton, editProfilePictureButton, nameButton, idButton, workplaceButton, hometownButton;
  private JFormattedTextField textField;
  // private Tree allPosts;
  private ImageIcon profile1, profileIcon;
  private Set<User> users;
  private boolean displayed, button;
  private JFileChooser chooser;
  private Main main;
  // User user1 = new User("Dave", "12345", "a place", "dundee", null, null);
  // User user2 = new User("Steve", "id", "a workplace", "edinburgh", null, null);
  // User user3 = new User("abbie", "skdjfh", "asda", "glasgow", null, null);
  User user1 = new User("Laura", "1", "Starbucks", "Glenrothes", "Images/PFPs/1ALP0101.jpg", new ArrayList<String>());
  User user2 = new User("Adam", "2", "O2", "Dunfermline", "Images/PFPs/1ALP0209.jpg", new ArrayList<String>());
  User user3 = new User("Iona", "3", "Tesco", "Monifieth", "Images/PFPs/1ALP0265.jpg", new ArrayList<String>());
  User user4 = new User("Andrew", "4", "Self-Employed", "idk somewhere in fife?", "Images/PFPs/1ALP9275.jpg",
      new ArrayList<String>());
  User user5 = new User("Marcus", "5", "Old Course", "Monikie", "Images/PFPs/1ALP1004.jpg", new ArrayList<String>());

  /**
   * Constructor for menu class
   */
  public Menu() {

    user1.addFriend(user2.getID());
    user1.addFriend(user3.getID());
    user1.addFriend(user5.getID());
    user2.addFriend(user1.getID());
    user2.addFriend(user4.getID());
    user3.addFriend(user1.getID());
    user3.addFriend(user2.getID());
    user3.addFriend(user5.getID());
    user4.addFriend(user1.getID());
    user4.addFriend(user2.getID());
    user4.addFriend(user5.getID());
    user5.addFriend(user2.getID());
    user5.addFriend(user3.getID());
    user5.addFriend(user4.getID());

    System.out.println(user1.getFriends().size());

    main = new Main();

    main.addUser(user1);
    main.addUser(user2);
    main.addUser(user3);
    main.addUser(user4);
    main.addUser(user5);

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

    displayed = false;
    button = false;

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
    // make a scroll panel so you can scroll to see all friends
    scrollPanel = new JScrollPane(rightPanel);
    scrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    leftPanel = new JPanel();
    // panel to display friends friends, set visibility to false
    friendsPanel = new JPanel();
    scrollFriendsPanel = new JScrollPane(friendsPanel);
    scrollFriendsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollFriendsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    mainPanel.setBackground(Color.decode("0x3d405b"));
    rightPanel.setBackground(Color.decode("0x81b29a"));
    leftPanel.setBackground(Color.decode("0xf2cc8f"));
    friendsPanel.setBackground(Color.decode("0x81b29a"));

    // add main, left and right panels to top panel
    topPanel.add(mainPanel, BorderLayout.CENTER);
    topPanel.add(scrollPanel, BorderLayout.EAST);
    topPanel.add(leftPanel, BorderLayout.WEST);

    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

    // friends panel will be added to display later, to replace right panel
    friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
    friendsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

    createFriendsPanel();
    createProfilePanel();
  }

  /**
   * Method to create the right hand panel to display friends
   */
  public void createFriendsPanel() {

    JLabel friendsInfo = new JLabel("Friends");
    friendsInfo.setFont(new Font("Sans", Font.PLAIN, 20));
    rightPanel.add(friendsInfo);

    ArrayList<JButton> viewButtonsArrayList = new ArrayList<JButton>();

    // rightPanel.add(createTextPanel());
    for (int i = 0; i < user1.getFriends().size(); i++) {
      // make strings containing friend info
      String name = main.IDtoUser((user1.getFriends().get(i))).getName();
      String hometown = main.IDtoUser((user1.getFriends().get(i))).getHomeTown();
      String workplace = main.IDtoUser((user1.getFriends().get(i))).getWorkPlace();
      String pfp = main.IDtoUser((user1.getFriends().get(i))).getPfp();

      // add profile information
      ImageIcon friendProfile = new javax.swing.ImageIcon(getClass().getResource(pfp));
      ImageIcon friendProfileResized = resizeImage(friendProfile, 100, 100);
      JLabel friendProfiLabel = new JLabel(friendProfileResized);
      rightPanel.add(friendProfiLabel);
      JLabel friendName = new JLabel(name);
      friendName.setFont(new Font("Sans", Font.PLAIN, 20));
      rightPanel.add(friendName);
      JLabel friendId = new JLabel("ID: " + user1.getFriends().get(i));
      friendId.setFont(new Font("Sans", Font.PLAIN, 16));
      rightPanel.add(friendId);
      JLabel friendWork = new JLabel("Workplace: " + workplace);
      friendWork.setFont(new Font("Sans", Font.PLAIN, 16));
      rightPanel.add(friendWork);
      JLabel friendHome = new JLabel("Hometown: " + hometown);
      friendHome.setFont(new Font("Sans", Font.PLAIN, 16));
      rightPanel.add(friendHome);

      // view friends button
      JButton viewFriends = new JButton("View " + name + "'s Friends");
      viewFriends.setBackground(Color.decode("0xe07a5f"));
      rightPanel.add(viewFriends);

      viewButtonsArrayList.add(viewFriends);
      SwingUtilities.updateComponentTreeUI(window);
    }
    // for every button in the list of buttons, add an action listener to that
    // button
    for (int j = 0; j < viewButtonsArrayList.size(); j++) {
      // https://stackoverflow.com/questions/33799800/java-local-variable-mi-defined-in-an-enclosing-scope-must-be-final-or-effective
      final Integer innerj = new Integer(j);
      viewButtonsArrayList.get(j).addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          topPanel.remove(scrollPanel);
          topPanel.add(scrollFriendsPanel, BorderLayout.EAST);

          User friend = main.IDtoUser(user1.getFriends().get(innerj));
          JLabel friendFriends = new JLabel(friend.getName() + "'s friends");
          friendFriends.setFont(new Font("Sans", Font.PLAIN, 20));
          friendsPanel.add(friendFriends);
          // display the info of each of the friends friends
          for (int k = 0; k < friend.getFriends().size(); k++) {
            String name = main.IDtoUser(friend.getFriends().get(k)).getName();
            String hometown = main.IDtoUser(friend.getFriends().get(k)).getHomeTown();
            String workplace = main.IDtoUser(friend.getFriends().get(k)).getWorkPlace();
            String pfp = main.IDtoUser(friend.getFriends().get(k)).getPfp();

            // add profile information
            ImageIcon friendProfile = new javax.swing.ImageIcon(getClass().getResource(pfp));
            ImageIcon friendProfileResized = resizeImage(friendProfile, 100, 100);
            JLabel friendProfiLabel = new JLabel(friendProfileResized);
            friendsPanel.add(friendProfiLabel);
            JLabel friendName = new JLabel(name);
            friendName.setFont(new Font("Sans", Font.PLAIN, 20));
            friendsPanel.add(friendName);
            JLabel friendId = new JLabel("ID: " + friend.getFriends().get(k));
            friendId.setFont(new Font("Sans", Font.PLAIN, 16));
            friendsPanel.add(friendId);
            JLabel friendWork = new JLabel("Workplace: " + workplace);
            friendWork.setFont(new Font("Sans", Font.PLAIN, 16));
            friendsPanel.add(friendWork);
            JLabel friendHome = new JLabel("Hometown: " + hometown);
            friendHome.setFont(new Font("Sans", Font.PLAIN, 16));
            friendsPanel.add(friendHome);
            SwingUtilities.updateComponentTreeUI(window);
          }

          // add a back button if one is not already added
          if (button == false) {
            // back button to go back to main user friends
            JButton backButton = new JButton("Back");
            backButton.setBackground(Color.decode("0xe07a5f"));
            friendsPanel.add(backButton);
            SwingUtilities.updateComponentTreeUI(window);
            button = true;

            backButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                // go back to the main right panel
                button = false;
                // remove all content from the friends panel
                Component[] friendsPanelItems = friendsPanel.getComponents();
                for (Component friendsPanelItem : friendsPanelItems) {
                  friendsPanel.remove(friendsPanelItem);
                }

                topPanel.remove(scrollFriendsPanel);
                scrollFriendsPanel.revalidate();
                scrollFriendsPanel.repaint();
                topPanel.add(scrollPanel, BorderLayout.EAST);
                SwingUtilities.updateComponentTreeUI(window);
              }
            });
          }
        }
      });

    }
  }

  /**
   * Resize the image and put it into form so that it can be displayed using
   * JLabel
   * 
   * @param The    ImageIcon that is being used as a profile picture
   * @param width  The width to set the ImageIcon to
   * @param height The height to set the ImageIcon to
   * @return The resized profile picture to be displayed on the profile
   */
  public ImageIcon resizeImage(ImageIcon testProfile, int width, int height) {
    // https://www.youtube.com/watch?v=ntirmRhy6Fw
    // reminder to put this in a try catch
    Image testProfileImage = testProfile.getImage();
    // https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    Image resized = testProfileImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(resized);

    return resizedIcon;
  }

  /**
   * Create the left hand panel with all profile information
   */
  public void createProfilePanel() {
    JLabel profile = new JLabel("Your Profile");
    profile.setFont(new Font("Sans", Font.PLAIN, 20));
    leftPanel.add(profile);

    // as a temporary fix, image has been moved to source code file
    profile1 = new javax.swing.ImageIcon(getClass().getResource(user1.getPfp()));
    profileIcon = resizeImage(profile1, 300, 300);
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
              // call method to choose an image to set PFP to
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
   * 
   * @return
   */
  public void selectFile() {
    chooser = new JFileChooser();

    FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg");
    chooser.setFileFilter(filter);

    // display file chooser
    int returnVal = chooser.showOpenDialog(window);

    // check if user has selected a file and open it
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      // open dialog box to select files
      File file = chooser.getSelectedFile();
      // get the file path
      System.out.println(file.getAbsolutePath());
      // JOptionPane.showMessageDialog(null, file.getPath());

      String[] filePathArray = file.getPath().split("src");
      String relative = filePathArray[1];

      ImageIcon newProfilePic = new ImageIcon();
      newProfilePic = new javax.swing.ImageIcon(getClass().getResource(relative));

      // user1.setPfp(newProfilePic.getImage());
      profileLabel.setIcon(resizeImage(newProfilePic, 300, 300));
      SwingUtilities.updateComponentTreeUI(window);

    } else {
      // test message
      JOptionPane.showMessageDialog(null, "Test");
      // return null;
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
