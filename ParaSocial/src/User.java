import java.util.ArrayList;

/**
 * 
 * Contains all fields and methods regarding a user
 * 
 * @author Laura Clark, Adam Munro, Iona Cavill and Andrew Leinster
 * @version 1.0.0
 */
public class User {

    private String name;
    private String ID;
    private String workPlace;
    private String homeTown;
    private String pfp;
    private ArrayList<String> friends;


    public User(String name, String ID, String workPlace, String homeTown, String pfp, ArrayList<String> friends)
    {
        this.name = name;
        this.ID = ID;
        this.workPlace = workPlace;
        this.homeTown = homeTown;
        this.pfp = pfp;
        this.friends = friends;  
    }

    public User()
    {
        name = null;
        ID = null;
        workPlace = null;
        homeTown = null;
        pfp = null;
        friends = null;
    }

    /**
     * Get the details of all of a person's friends
     * @return The details of a person's friends as a String
     */
    public String getFriendInfo()
    {
        User[] friendList = friends.toArray(new User[friends.size()]);
        String friendInfo = "";
        for (int i = 0; i < friendList.length; i++)
        {
           
           friendInfo += "\n" + friendList[i].getUserInfo();
        }
        return friendInfo;
    }

    /**
     * Returns the user details as a string
     * @return The user information as a string
     */
    public String getUserInfo()
    {
        String userInfo = "ID:" + ID + "\nName: " + name + "\nWorkplace: " + workPlace + "\nHome Town: " + homeTown;
        return userInfo;
    }

    /**
     * Returns two users mutual friends
     * 
     * @param a the first user
     * @param b the second user
     * @return an ArrayList of the IDs of the mutual friends
     */
    public ArrayList<String> getMutuals(User a, User b)
    {
        ArrayList<String> mutuals = new ArrayList<String>();
        for (int i=0; i<a.getFriends().size(); i++)
        {
          mutuals.add(a.getFriends().get(i));
        }
        mutuals.retainAll(b.getFriends());
        return mutuals;
    }

    /**
     * Checks if a User should be returned given the inputted search
     * 
     * @param search the String input recieved from the user as a search
     * @return True/False dependant on whether the User is or isn't applicable to the inputted search
     */
    public boolean searchApplicable(String search)
    {
        //checks if the search term is found in any of the users details
        if ((compareStrings(ID, search)) || (compareStrings(name, search)) || (compareStrings(workPlace, search)) || (compareStrings(homeTown, search)))
        {
            return true;
        }
        return false;
    }

    /**
     * compares two strings to see if one appears anywhere in the other
     * 
     * @param a the String being checked
     * @param b the String being searched
     * @return true/false dependant on whether String a appears in String a
     */
    public boolean compareStrings(String a, String b)
    {   
        //converts both to lowercase as for the search to not be case sensitive
        a = a.toLowerCase();
        b = b.toLowerCase();

        for (int i = 0; i < a.length() - b.length() + 1 ; i++)
        {   
            //checks all possible substrings of String a that are the same length as String b to see if they match
            if (a.substring(i, i + b.length()).equals(b))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * returns the name of a user
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * sets the value of name
     * 
     * @param name the new name of the user
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * returns the ID of a user
     * 
     * @return ID
     */
    public String getID()
    {
        return ID;
    }

    /**
     * sets the value of ID
     * 
     * @param ID the new ID of the user
     */
    public void setID(String ID)
    {
        this.ID = ID;
    }

    /**
     * returns the workPlace of a user
     * 
     * @return workPlace
     */
    public String getWorkPlace()
    {
        return workPlace;
    }

    /**
     * sets the value of workPlace
     * 
     * @param workPlace the new workPlace of the user
     */
    public void setWorkPlace(String workPlace)
    {
        this.workPlace = workPlace;
    }
    
    /**
     * returns the homeTown of a user
     * 
     * @return homeTown
     */
    public String getHomeTown()
    {
        return homeTown;
    }

    /**
     * sets the value of homeTown
     * 
     * @param homeTown the new homeTown of the user
     */
    public void setHomeTown(String homeTown)
    {
        this.homeTown = homeTown;
    }

    /**
     * returns the filepath of a users profile picture
     * 
     * @return pfp
     */
    public String getPfp()
    {
        return pfp;
    }

    /**
     * sets the value of pfp
     * 
     * @param pfp the new filepath of the users profile picture
     */
    public void setPfp(String pfp)
    {
        this.pfp = pfp;
    }

    /**
     * returns the ArrayList of a users friends IDs
     * 
     * @return friends
     */
    public ArrayList<String> getFriends()
    {
        return friends;
    }

    /**
     * sets the value of friends
     * 
     * @param friends the new ArrayList of IDs
     */
    public void setFriends(ArrayList<String> friends)
    {
        this.friends = friends;
    }

    /**
     * adds a friend to the ArrayList of friends
     * 
     * @param friend the ID of the friend to be added
     */
    public void addFriend(String friend)
    {
        friends.add(friend);
    }

    /**
     * removes a friend from the ArrayList of friends
     * 
     * @param friend the ID of the friend to be removed
     */
    public void removeFriend(String friend)
    {
        friends.remove(friend);
    }
}