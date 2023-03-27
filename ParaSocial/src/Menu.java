import java.awt.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.util.Set;
import java.util.ArrayList;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu {

  private JFrame window;
  private JPanel topPanel, mainPanel, leftPanel, rightPanel, friendsPanel, mutualsPanel, postsPanel;
  private JScrollPane scrollPanel, scrollFriendsPanel, scrollMutualsPanel;
  private JLabel titleLabel, profileLabel;
  private JButton editButton, editProfilePictureButton, nameButton, idButton, workplaceButton, hometownButton;
  private JFormattedTextField textField;
  // private Tree allPosts;
  private ImageIcon profile1, profileIcon;
  private Set<User> users;
  private boolean displayed, button;
  private JFileChooser chooser;
  private Main main;
  User user1 = new User("Laura", "1", "Starbucks", "Glenrothes", "Images/PFPs/1ALP0101.jpg", new ArrayList<String>());
  User user2 = new User("Adam", "2", "O2", "Dunfermline", "Images/PFPs/1ALP0209.jpg", new ArrayList<String>());
  User user3 = new User("Iona", "3", "Tesco", "Monifieth", "Images/PFPs/1ALP0265.jpg", new ArrayList<String>());
  User user4 = new User("Andrew", "4", "Self-Employed", "North-East Fife", "Images/PFPs/1ALP9275.jpg",
      new ArrayList<String>());
  User user5 = new User("Marcus", "5", "Old Course", "Monikie", "Images/PFPs/1ALP1004.jpg", new ArrayList<String>());
  // colours
  String teaGreen, beige, cornsilk, papayaWhip, buff;

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

    main = new Main();
    main.addUser(user1);
    main.addUser(user2);
    main.addUser(user3);
    main.addUser(user4);
    main.addUser(user5);

    teaGreen = "0xCCD5AE";
    beige = "0xE9EDC9";
    cornsilk = "0xFEFAE0";
    papayaWhip = "0xFAEDCD";
    buff = "0xD4A373";

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

    // panel to display friends friends
    friendsPanel = new JPanel();
    scrollFriendsPanel = new JScrollPane(friendsPanel);
    scrollFriendsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollFriendsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    //panel to display mutual friends
    mutualsPanel = new JPanel();
    scrollMutualsPanel = new JScrollPane(mutualsPanel);
    scrollMutualsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scrollMutualsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    mainPanel.setBackground(Color.decode(cornsilk));
    rightPanel.setBackground(Color.decode(beige));
    leftPanel.setBackground(Color.decode(teaGreen));
    friendsPanel.setBackground(Color.decode(beige));
    mutualsPanel.setBackground(Color.decode(beige));


    // add main, left and right panels to top panel
    topPanel.add(mainPanel, BorderLayout.CENTER);
    topPanel.add(scrollPanel, BorderLayout.EAST);
    topPanel.add(leftPanel, BorderLayout.WEST);

    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

    // friends panel will be added to display later, to replace right panel
    friendsPanel.setLayout(new BoxLayout(friendsPanel, BoxLayout.Y_AXIS));
    friendsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

     // mutuals panel will be added to display later
     mutualsPanel.setLayout(new BoxLayout(mutualsPanel, BoxLayout.Y_AXIS));
     mutualsPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

    createFriendsPanel();
    createProfilePanel();
    createPostPanel();
  }

  /**
   * Method to display all of an inputted users details to the user
   * @param user The user to display the details of
   * @param panel The panel to add the information to
   */
  public void displayUserInfo(User user, JPanel panel)
  {
      ImageIcon friendProfile = new javax.swing.ImageIcon(getClass().getResource(user.getPfp()));
      ImageIcon friendProfileResized = resizeImage(friendProfile, 100, 100);
      JLabel friendProfiLabel = new JLabel(friendProfileResized);
      panel.add(friendProfiLabel);
      JLabel friendName = new JLabel(user.getName());
      friendName.setFont(new Font("Sans", Font.PLAIN, 20));
      panel.add(friendName);
      JLabel friendId = new JLabel("ID: " + user.getID());
      friendId.setFont(new Font("Sans", Font.PLAIN, 16));
      panel.add(friendId);
      JLabel friendWork = new JLabel("Workplace: " + user.getWorkPlace());
      friendWork.setFont(new Font("Sans", Font.PLAIN, 16));
      panel.add(friendWork);
      JLabel friendHome = new JLabel("Hometown: " + user.getHomeTown());
      friendHome.setFont(new Font("Sans", Font.PLAIN, 16));
      panel.add(friendHome);
      SwingUtilities.updateComponentTreeUI(window);
  }

  /**
   * Method to create the right hand panel to display friends
   */
  public void createFriendsPanel() {

    JLabel friendsInfo = new JLabel("Friends");
    friendsInfo.setFont(new Font("Sans", Font.PLAIN, 26));
    rightPanel.add(friendsInfo);

    // rightPanel.add(createTextPanel());
    for (int i = 0; i < user1.getFriends().size(); i++) 
    {
      // display user info
      User currentFriend = main.IDtoUser(user1.getFriends().get(i));
      displayUserInfo(currentFriend, rightPanel);

      // view friends button
      JButton viewFriends = new JButton("View " + currentFriend.getName() + "'s Friends");
      viewFriends.setBackground(Color.decode(buff));
      rightPanel.add(viewFriends);
      SwingUtilities.updateComponentTreeUI(window);

      // https://stackoverflow.com/questions/33799800/java-local-variable-mi-defined-in-an-enclosing-scope-must-be-final-or-effective
      final Integer inneri = new Integer(i);
      viewFriends.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
          topPanel.remove(scrollPanel);
          topPanel.add(scrollFriendsPanel, BorderLayout.EAST);
          scrollFriendsPanel.repaint();

          User friend = main.IDtoUser(user1.getFriends().get(inneri));
          JLabel friendFriendsLabel = new JLabel(friend.getName() + "'s friends");
          friendFriendsLabel.setFont(new Font("Sans", Font.PLAIN, 26));
          friendsPanel.add(friendFriendsLabel);

          // display the info of each of the friends friends
          for (int k = 0; k < friend.getFriends().size(); k++) {
            User friendsFriend = main.IDtoUser(friend.getFriends().get(k));
            displayUserInfo(friendsFriend, friendsPanel);
            
            //add an add friends button to each friend
            JButton addFriend = new JButton("Add Friend");
            addFriend.setBackground(Color.decode(buff));
            friendsPanel.add(addFriend);

            // add the friend to main users friend list
            addFriend.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e)
              {
                // check if they try to add themseleves
                if (friendsFriend.getID() == user1.getID())
                {
                    JOptionPane.showMessageDialog(null, "You cannot add yourself as a friend!");
                }
                // check if user is already in their friends list
                else if (user1.getFriends().contains(friendsFriend.getID()))
                {
                  JOptionPane.showMessageDialog(null, "This person is already in your friends list!");
                }
                // else, add them to friends list
                else
                {
                  user1.addFriend(friendsFriend.getID());
                  rightPanel.removeAll();
                  rightPanel.revalidate();
                  rightPanel.repaint();
                  JOptionPane.showMessageDialog(null, "Friend successfully added!");
                  createFriendsPanel();
                }
                SwingUtilities.updateComponentTreeUI(window);
              }
            });
          }
            // back button to go back to main user friends
            JButton backButton = new JButton("Back");
            backButton.setBackground(Color.decode(buff));
            friendsPanel.add(backButton);
            SwingUtilities.updateComponentTreeUI(window);

            // this is a back button to take you back to the right panel
            backButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                // remove all content from the friends panel
                friendsPanel.removeAll();
                friendsPanel.revalidate();
                friendsPanel.repaint();

                mutualsPanel.removeAll();
                        mutualsPanel.revalidate();
                        mutualsPanel.repaint();
                        
                
                // go back to the main right panel
                topPanel.remove(scrollFriendsPanel);
                topPanel.add(scrollPanel, BorderLayout.EAST);
                topPanel.remove(scrollMutualsPanel);
                SwingUtilities.updateComponentTreeUI(window);
              }
            });

            // add button to show mutual friends
            JButton showMutualFriends = new JButton("Show Mutual Friends");
            showMutualFriends.setBackground(Color.decode(buff));
            friendsPanel.add(showMutualFriends);

            showMutualFriends.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e)
              {
                  topPanel.remove(scrollFriendsPanel);
                  topPanel.add(scrollMutualsPanel, BorderLayout.EAST);
                  ArrayList<String> mutualFriends = user1.getMutuals(user1, friend);
                  System.out.println(mutualFriends.size());
                  for (int j=0; j<mutualFriends.size(); j++)
                  {
                    User mutual = main.IDtoUser(mutualFriends.get(j));
                    displayUserInfo(mutual, mutualsPanel);
                  }
                  mutualsPanel.add(backButton);
                  SwingUtilities.updateComponentTreeUI(window);
              }
            });
          }
        });
    }
  }


  public void createPostPanel() {
    JLabel postInfo = new JLabel("Posts");
    postInfo.setFont(new Font("Sans", Font.PLAIN, 26));
    mainPanel.add(postInfo);
    Tree tree = new Tree();
    
      JButton likeButton = new JButton("Like");
      likeButton.setBackground(Color.decode(buff));
      mainPanel.add(likeButton);

      Post newpost = new Post();
      Node newNode = new Node(newpost);
      tree.setRoot(newNode);

      tree.inorderDisplay(tree.getRoot());

      /*
      ImageIcon postIcon = new javax.swing.ImageIcon(getClass().getResource(user1.getPfp()));
      ImageIcon postIconResizedIcon = resizeImage(friendProfile, 100, 100);
      JLabel friendProfiLabel = new JLabel(friendProfileResized);
      panel.add(friendProfiLabel);
      JLabel friendName = new JLabel(user.getName());*/
  }

  public void displayPosts(Post post) {
    System.out.println("Freeze");
    for (int i = 0; i<5 /*user1.getUserPosts*/; i++){
      JButton likeButton = new JButton("Bees");
      System.out.println("Seas");
      likeButton.setBackground(Color.decode(beige));
      System.out.println("Seize");
      //mainPanel.add(likeButton);
      System.out.println("Breeze");

    }
  }


  /**
   * Resize an image and put it into form so that it can be displayed using JLabel
   * 
   * @param testProfile ImageIcon that is being used as a profile picture
   * @param width The width to set the ImageIcon to
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
    profile.setFont(new Font("Sans", Font.PLAIN, 26));
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
    editButton.setBackground(Color.decode(buff));
    leftPanel.add(editButton);

    // create profile picture edit button
    editProfilePictureButton = new JButton("Edit Profile Picture");
    editProfilePictureButton.setBackground(Color.decode(buff));

    // create each edit button
    nameButton = new JButton("Change Name");
    nameButton.setBackground(Color.decode(buff));
    idButton = new JButton("Change ID");
    idButton.setBackground(Color.decode(buff));
    workplaceButton = new JButton("Change Workplace");
    workplaceButton.setBackground(Color.decode(buff));
    hometownButton = new JButton("Change Hometown");
    hometownButton.setBackground(Color.decode(buff));

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
              submitButton.setBackground(Color.decode(buff));
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
              submitButton.setBackground(Color.decode(buff));
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
              submitButton.setBackground(Color.decode(buff));
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
              submitButton.setBackground(Color.decode(buff));
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

      String[] filePathArray = file.getPath().split("src");
      String relative = filePathArray[1];

      ImageIcon newProfilePic = new ImageIcon();
      newProfilePic = new javax.swing.ImageIcon(getClass().getResource(relative));
      profileLabel.setIcon(resizeImage(newProfilePic, 300, 300));
      SwingUtilities.updateComponentTreeUI(window);
    } else {
      System.out.println("Error choosing image!");
    }
  }

  public static void displayMessage(String message, String title) {
    int messageType = JOptionPane.PLAIN_MESSAGE;
    JOptionPane.showMessageDialog(null, message, title, messageType);
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
