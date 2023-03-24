import java.util.Set;
import java.util.HashSet;
import java.awt.*;
import java.util.ArrayList;

public class User {

    private String name;
    private String ID;
    private String workPlace;
    private String homeTown;
    private String pfp;
    
    private ArrayList<String> friends;
   // private TreeSet posts;


    public User(String name, String ID, String workPlace, String homeTown, String pfp, ArrayList<String> friends)

    {
        this.name = name;
        this.ID = ID;
        this.workPlace = workPlace;
        this.homeTown = homeTown;
        this.pfp = pfp;
        this.friends = friends;
        this.posts = null;
    }

    public User()
    {
        name = null;
        ID = null;
        workPlace = null;
        homeTown = null;
        pfp = null;
        friends = null;
        posts = null;
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

    public void displayPosts()
    {


    }

    public ArrayList<String> getMutuals(User a, User b)
    {
        ArrayList<String> mutuals = a.getFriends();
        mutuals.retainAll(b.getFriends());

        return mutuals;
    }

    public boolean searchApplicable(String search)
    {
        
        if (compareStrings(ID, search) || compareStrings(name, search) || compareStrings(workPlace, search) || compareStrings(homeTown, search))
        {
            return true;
        }
    
        return false;

    }

    public boolean compareStrings(String a, String b)
    {
        for (int i = 0; i < a.length() - b.length(); i++)
        {
            if (a.substring(i, i + b.length()).equals(b))
            {
                return true;
            }
        }

        return false;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getWorkPlace()
    {
        return workPlace;
    }

    public void setWorkPlace(String workPlace)
    {
        this.workPlace = workPlace;
    }
    
    public String getHomeTown()
    {
        return homeTown;
    }

    public void setHomeTown(String homeTown)
    {
        this.homeTown = homeTown;
    }

    public String getPfp()
    {
        return pfp;
    }

    public void setPfp(String pfp)
    {
        this.pfp = pfp;
    }

    public ArrayList<String> getFriends()
    {
        return friends;
    }

    public void setFriends(ArrayList<String> friends)
    {
        this.friends = friends;
    }

    public void addFriend(String friend)
    {
        friends.add(friend);
    }

    public void removeFriend(String friend)
    {
        friends.remove(friend);
    }

}