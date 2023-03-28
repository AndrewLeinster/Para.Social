import java.util.Set;
import java.util.HashSet;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

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

    public boolean searchApplicable(String search)
    {
        
        if ((compareStrings(ID, search) == true)|| (compareStrings(name, search) == true) || (compareStrings(workPlace, search) == true) || (compareStrings(homeTown, search) == true))
        {
            System.out.println("This should print");
            return true;
        }
        // for testing only
        else
        {
            System.out.println("search:" + search);
            System.out.println(ID);
            System.out.println(name);
            System.out.println(workPlace);
            System.out.println(homeTown);
        }
    
        return false;

    }

    public boolean compareStrings(String a, String b)
    {
        if (a.equals(b))
        {
            return true;
        }
        else
        {
            return false;
        }
        /* 
        for (int i = 0; i < a.length() - b.length(); i++)
        {
            if (a.substring(i, i + b.length()).equals(b))
            {
                System.out.println("Strings match");
                return true;
            }
        }*/

        //return false;
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