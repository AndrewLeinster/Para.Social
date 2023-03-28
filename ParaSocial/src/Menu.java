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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.util.Set;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu {
  private static Menu newMenu;
  private JFrame window;
  private JPanel topPanel, mainPanel, leftPanel, rightPanel;
  private JScrollPane scrollPanel;
  private JLabel profileLabel;
  private JButton editButton, editProfilePictureButton, nameButton, idButton, workplaceButton, hometownButton;
  // private Tree allPosts;
  private ImageIcon profile1, profileIcon;
  private boolean displayed, editing;
  private JFileChooser chooser;
  private Main main;
  // colours
  String teaGreen, beige, cornsilk, papayaWhip, buff;

  /**
   * Constructor for menu class
   */
  public Menu() {

    main = new Main();

    main.readIn();

    teaGreen = "0xCCD5AE";
    beige = "0xE9EDC9";
    cornsilk = "0xFEFAE0";
    papayaWhip = "0xFAEDCD";
    buff = "0xD4A373";

    // create the window
    window = new JFrame();
    // create the panels
    createMainPanels();

    window.setContentPane(topPanel);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setTitle("para.social");
    window.setVisible(true);
    window.pack();

    displayed = false;
    editing = false;
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
    newMenu = new Menu();
  }

  /**
   * Method to return the instance of the menu we are using
   * 
   * @return The instance of menu
   */
  public Menu getMenu() {
    return newMenu;
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

    mainPanel.setBackground(Color.decode(cornsilk));
    rightPanel.setBackground(Color.decode(beige));
    leftPanel.setBackground(Color.decode(teaGreen));

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

    createFriendsPanel();
    createProfilePanel();
    createPostPanel();
  }

  /**
   * Method to display all of an inputted users details to the user
   * 
   * @param user  The user to display the details of
   * @param panel The panel to add the information to
   */
  public void displayUserInfo(User user, JPanel panel) {
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
   * Create a back button to take the user back to the main friends page
   * 
   * @return The back button
   */
  public JButton backButton() {
    JButton backButton = new JButton("Back");
    backButton.setBackground(Color.decode(buff));

    // this is a back button to take you back to the right panel
    backButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        rightPanel.removeAll();
        rightPanel.revalidate();
        rightPanel.repaint();
        createFriendsPanel();
      }
    });

    return backButton;
  }

  /**
   * Method to create a search bar and add function to filter friends
   */
  public void search() {
    // search/ filter friends
    JFormattedTextField searchbox = new JFormattedTextField("Search/ filter your friends");
    rightPanel.add(searchbox);
    JButton searchButton = new JButton("Search");
    searchButton.setBackground(Color.decode(buff));
    rightPanel.add(searchButton);

    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String searchTerm = (String) searchbox.getValue();
        JLabel filteredFriends = new JLabel("Friends filtered by " + searchTerm);
        filteredFriends.setFont(new Font("Sans", Font.PLAIN, 26));

        ArrayList<User> sortedFriends = main.searchFriend(searchTerm, main.getPrimaryUser());
        // clear the right panel
        rightPanel.removeAll();
        rightPanel.revalidate();
        rightPanel.repaint();
        rightPanel.add(filteredFriends);
        // display message if no friends were found by filter
        if (sortedFriends.size() == 0) {
          JLabel noResults = new JLabel("No friends were found with that search term!");
          noResults.setFont(new Font("Sans", Font.PLAIN, 16));
          rightPanel.add(noResults);
          rightPanel.add(backButton());
        } else {
          // display filtered friends
          for (int j = 0; j < sortedFriends.size(); j++) {
            displayUserInfo(sortedFriends.get(j), rightPanel);
          }
          rightPanel.add(backButton());
        }
      }
    });

  }

  /**
   * Method to create the right hand panel to display friends
   */
  public void createFriendsPanel() {

    JLabel friendsInfo = new JLabel("Friends");
    friendsInfo.setFont(new Font("Sans", Font.PLAIN, 26));
    rightPanel.add(friendsInfo);

    // add search bar
    search();

    // display all friends
    for (int i = 0; i < main.getPrimaryUser().getFriends().size(); i++) {
      User currentFriend = main.IDtoUser(main.getPrimaryUser().getFriends().get(i));
      displayUserInfo(currentFriend, rightPanel);

      // view friends button
      JButton viewFriends = new JButton("View " + currentFriend.getName() + "'s Friends");
      viewFriends.setBackground(Color.decode(buff));
      rightPanel.add(viewFriends);
      SwingUtilities.updateComponentTreeUI(window);

      // https://stackoverflow.com/questions/33799800/java-local-variable-mi-defined-in-an-enclosing-scope-must-be-final-or-effective
      final Integer inneri = new Integer(i);
      viewFriends.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          rightPanel.removeAll();
          rightPanel.revalidate();
          rightPanel.repaint();

          User friend = main.IDtoUser(main.getPrimaryUser().getFriends().get(inneri));
          JLabel friendFriendsLabel = new JLabel(friend.getName() + "'s friends");
          friendFriendsLabel.setFont(new Font("Sans", Font.PLAIN, 26));
          rightPanel.add(friendFriendsLabel);

          // display the info of each of the friends friends
          for (int k = 0; k < friend.getFriends().size(); k++) {
            User friendsFriend = main.IDtoUser(friend.getFriends().get(k));
            displayUserInfo(friendsFriend, rightPanel);

            // add an add friends button to each friend
            JButton addFriend = new JButton("Add Friend");
            addFriend.setBackground(Color.decode(buff));
            rightPanel.add(addFriend);

            // add the friend to main users friend list
            addFriend.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                // check if they try to add themseleves
                if (friendsFriend.getID() == main.getPrimaryUser().getID()) {
                  JOptionPane.showMessageDialog(null, "You cannot add yourself as a friend!");
                }
                // check if user is already in their friends list
                else if (main.getPrimaryUser().getFriends().contains(friendsFriend.getID())) {
                  JOptionPane.showMessageDialog(null, "This person is already in your friends list!");
                }
                // else, add them to friends list
                else {
                  main.getPrimaryUser().addFriend(friendsFriend.getID());
                  JOptionPane.showMessageDialog(null, "Friend successfully added!");
                }
                SwingUtilities.updateComponentTreeUI(window);
              }
            });
          }
          // add button to show mutual friends
          JButton showMutualFriends = new JButton("Show Mutual Friends");
          showMutualFriends.setBackground(Color.decode(buff));
          rightPanel.add(showMutualFriends);

          rightPanel.add(backButton());
          SwingUtilities.updateComponentTreeUI(window);

          showMutualFriends.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              rightPanel.removeAll();
              rightPanel.revalidate();
              rightPanel.repaint();

              JLabel mutualsLabel = new JLabel("Mutual Friends");
              mutualsLabel.setFont(new Font("Sans", Font.PLAIN, 26));
              rightPanel.add(mutualsLabel);
              ArrayList<String> mutualFriends = main.getPrimaryUser().getMutuals(main.getPrimaryUser(), friend);
              if (mutualFriends.size() == 0) {
                JLabel noMutualFriends = new JLabel("No Mutual Friends");
                noMutualFriends.setFont(new Font("Sans", Font.PLAIN, 16));
                rightPanel.add(noMutualFriends);
              }
              for (int j = 0; j < mutualFriends.size(); j++) {
                User mutual = main.IDtoUser(mutualFriends.get(j));
                displayUserInfo(mutual, rightPanel);
              }
              rightPanel.add(backButton());
              SwingUtilities.updateComponentTreeUI(window);
            }
          });
        }
      });
    }
  }

  /**
   * Create panel to display posts
   */
  public void createPostPanel() {
    JLabel postInfo = new JLabel("Posts");
    postInfo.setFont(new Font("Sans", Font.PLAIN, 26));
    mainPanel.add(postInfo);
    Tree tree = new Tree();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    
    FileReader fileReader = null;
		BufferedReader bufferedReader = null;
    String nextLine;

    String fileName = "C:/Users/adamm/Downloads/captions.txt";
    User[] userArray = main.getUsers().toArray(new User[main.getUsers().size()]);

    try{

    for (int i = 1; i <= 91; i++) {

      fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
      
      nextLine = bufferedReader.readLine();

      int x = ThreadLocalRandom.current().nextInt(0, 32 + 1);

      if (i < 10)
      {
       Post newPost = new Post("Images/Posts/" + i + ".jpg", nextLine, ThreadLocalRandom.current().nextInt(1, 1000 + 1), null, LocalDateTime.parse("2023-03-27 11:0" + i, formatter), userArray[x].getName(), Integer.toString(i));
       Node newNode = new Node(newPost);
       tree.addItem(newNode, tree.getRoot(), null);
      }
      else if(i >= 10 && i < 60)
      {
        Post newPost = new Post("Images/Posts/" + i + ".jpg", nextLine, ThreadLocalRandom.current().nextInt(1, 1000 + 1), null, LocalDateTime.parse("2023-03-27 11:" + i, formatter), userArray[x].getName(), Integer.toString(i));
        Node newNode = new Node(newPost);
       tree.addItem(newNode, tree.getRoot(), null);
      }
      else if(i >= 60 && i - 60 > 9){
        Post newPost = new Post("Images/Posts/" + i + ".jpg", nextLine, ThreadLocalRandom.current().nextInt(1, 1000 + 1), null, LocalDateTime.parse("2023-03-27 11:" + (i-60), formatter), userArray[x].getName(), Integer.toString(i));
        Node newNode = new Node(newPost);
       tree.addItem(newNode, tree.getRoot(), null);
      }
      else if(i >= 60 && i - 60 < 10){
        Post newPost = new Post("Images/Posts/" + i + ".jpg", nextLine, ThreadLocalRandom.current().nextInt(1, 1000 + 1), null, LocalDateTime.parse("2023-03-27 11:0" + (i-60), formatter), userArray[x].getName(), Integer.toString(i));
        Node newNode = new Node(newPost);
       tree.addItem(newNode, tree.getRoot(), null);
      }
    }
  }
  catch (IOException e)
  {
    System.out.println("error with file");
  }

    System.out.println("Deez");
    inorderDisplay(tree.getRoot());
  }

  public void inorderDisplay(Node current) {
    System.out.println("trees");
    if (current != null && current.getItem() != null) {
      System.out.println("bees");
      inorderDisplay(current.getLeftNode()); // traverses the tree
      displayPosts(current.getItem()); // displays the current node
      inorderDisplay(current.getRightNode());
    }
  }

  /**
   * Display posts
   */
  public void displayPosts(Post post) {
    System.out.println("Freeze");

    JLabel nameLabel = new JLabel(post.getPostedBy());
    mainPanel.add(nameLabel);

    ImageIcon postIcon = new javax.swing.ImageIcon(getClass().getResource(post.getPostImage()));
    ImageIcon postResizeImageIcon = resizeImage(postIcon, 300, 300);
    JLabel postLabel = new JLabel(postResizeImageIcon);
    mainPanel.add(postLabel);

    JLabel captionLabel = new JLabel(post.getCaption(), SwingConstants.CENTER);
    mainPanel.add(captionLabel);

    JButton LikeButton = new JButton("Like");
    LikeButton.setBackground(Color.CYAN);
    mainPanel.add(LikeButton);

    JLabel likesLabel = new JLabel(Integer.toString(post.getNumberOfLikes()), SwingConstants.CENTER);
    mainPanel.add(likesLabel);

    JLabel spacingLabel = new JLabel("\n \n \n", SwingConstants.CENTER);
    mainPanel.add(spacingLabel);

    JLabel spacing2Label = new JLabel("___________________________________________", SwingConstants.CENTER);
    mainPanel.add(spacing2Label);

    JLabel spacing3Label = new JLabel("\n \n \n", SwingConstants.CENTER);
    mainPanel.add(spacing3Label);
  }

  /**
   * Resize an image and put it into form so that it can be displayed using JLabel
   * 
   * @param testProfile ImageIcon that is being used as a profile picture
   * @param width       The width to set the ImageIcon to
   * @param height      The height to set the ImageIcon to
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

    profile1 = new javax.swing.ImageIcon(getClass().getResource(main.getPrimaryUser().getPfp()));
    profileIcon = resizeImage(profile1, 300, 300);
    profileLabel = new JLabel(profileIcon);

    leftPanel.add(profileLabel);

    // add profile information
    JLabel name = new JLabel(main.getPrimaryUser().getName());
    name.setFont(new Font("Sans", Font.PLAIN, 20));
    leftPanel.add(name);
    JLabel id = new JLabel("ID: " + main.getPrimaryUser().getID());
    id.setFont(new Font("Sans", Font.PLAIN, 16));
    leftPanel.add(id);
    JLabel work = new JLabel("Workplace: " + main.getPrimaryUser().getWorkPlace());
    work.setFont(new Font("Sans", Font.PLAIN, 16));
    leftPanel.add(work);
    JLabel home = new JLabel("Hometown: " + main.getPrimaryUser().getHomeTown());
    home.setFont(new Font("Sans", Font.PLAIN, 16));
    leftPanel.add(home);

    // Create a JButton to edit details
    editButton = new JButton("Edit Profile");
    editButton.setBackground(Color.decode(buff));
    leftPanel.add(editButton);

    // create each edit button
    nameButton = new JButton("Change Name");
    nameButton.setBackground(Color.decode(buff));
    idButton = new JButton("Change ID");
    idButton.setBackground(Color.decode(buff));
    workplaceButton = new JButton("Change Workplace");
    workplaceButton.setBackground(Color.decode(buff));
    hometownButton = new JButton("Change Hometown");
    hometownButton.setBackground(Color.decode(buff));
    editProfilePictureButton = new JButton("Edit Profile Picture");
    editProfilePictureButton.setBackground(Color.decode(buff));

    // action listener for edit button
    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // only display the buttons if they have not already been displayed
        if (displayed == false) {

          // change name
          leftPanel.add(nameButton);
          nameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              if (editing == false) {
                editing = true;
                JTextField changeName = new JTextField("Change your name");
                leftPanel.add(changeName);

                JButton submitButton = new JButton("Submit");
                submitButton.setBackground(Color.decode(buff));
                leftPanel.add(submitButton);
                // add action listener to submit button
                submitButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    main.getPrimaryUser().setName(changeName.getText());
                    name.setText(changeName.getText());
                    leftPanel.remove(submitButton);
                    leftPanel.remove(changeName);
                    editing = false;
                  }
                });
                SwingUtilities.updateComponentTreeUI(window);
              }
            }
          });

          // change id button
          leftPanel.add(idButton);
          idButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              if (editing == false) {
                editing = true;
                JTextField changeID = new JTextField("Change your ID");
                leftPanel.add(changeID);
                JButton submitButton = new JButton("Submit");
                submitButton.setBackground(Color.decode(buff));
                leftPanel.add(submitButton);
                // add action listener to submit button
                submitButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    main.getPrimaryUser().setID(changeID.getText());
                    id.setText("ID: " + changeID.getText());
                    leftPanel.remove(submitButton);
                    leftPanel.remove(changeID);
                    editing = false;
                  }
                });
                SwingUtilities.updateComponentTreeUI(window);
              }
            }
          });

          // change workplace
          leftPanel.add(workplaceButton);
          workplaceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              if (editing = false) {
                editing = true;
                JTextField changeWorkPlace = new JTextField("Change your Workplace");
                leftPanel.add(changeWorkPlace);
                JButton submitButton = new JButton("Submit");
                submitButton.setBackground(Color.decode(buff));
                leftPanel.add(submitButton);
                // add action listener to submit button
                submitButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    main.getPrimaryUser().setWorkPlace(changeWorkPlace.getText());
                    work.setText("Workplace: " + changeWorkPlace.getText());
                    leftPanel.remove(submitButton);
                    leftPanel.remove(changeWorkPlace);
                    editing = false;
                  }
                });
                SwingUtilities.updateComponentTreeUI(window);
              }
            }
          });

          // change hometown
          leftPanel.add(hometownButton);
          hometownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              if (editing == false)
              {
                editing = true;
                JTextField changeHometown = new JTextField("Change your hometown");
                leftPanel.add(changeHometown);
                JButton submitButton = new JButton("Submit");
                submitButton.setBackground(Color.decode(buff));
                leftPanel.add(submitButton);
                // add action listener to submit button
                submitButton.addActionListener(new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                    main.getPrimaryUser().setHomeTown(changeHometown.getText());
                    home.setText("Hometown:" + changeHometown.getText());
                    leftPanel.remove(submitButton);
                    leftPanel.remove(changeHometown);
                    editing = false;
                  }
                });
                SwingUtilities.updateComponentTreeUI(window);
              }
            }
          });

          // change profile picture
          leftPanel.add(editProfilePictureButton);
          editProfilePictureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              // call method to choose an image to set PFP to
              if (editing == false)
              {
                  selectFile();
              }
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

    JButton savebutton = new JButton("Save");
    savebutton.setBackground(Color.decode(buff));
    leftPanel.add(savebutton);

    savebutton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {

        main.writeToFile();

      }
    });

  }

  /**
   * Select a new file to set the profile picture to
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
