import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class Tester {
    private Set<User> users;
    private Set<Post> posts;

    public Tester()
    {
        Set<User> users = new HashSet<User>();
        Set<Post> posts = new HashSet<Post>();
    }
    public void addUsers()
    {
        User user1 = new User("Laura", "1", "Starbucks", "Glenrothes", "Images/PFPs/1ALP0101.jpg", new ArrayList<Integer>());
        User user2 = new User("Adam", "2", "O2", "Dunfermline", "Images/PFPs/1ALP0209.jpg", new ArrayList<Integer>());
        User user3 = new User("Iona", "3", "Tesco", "Monifieth", "Images/PFPs/1ALP0265.jpg", new ArrayList<Integer>());
        User user4 = new User("Andrew", "4", "Self-Employed", "idk somewhere in fife?", "Images/PFPs/1ALP02429.jpg", new ArrayList<Integer>());
        User user5 = new User("Marcus", "5", "Old Course", "Monikie", "Images/PFPs/1ALP1004.jpg", new ArrayList<Integer>());
        
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

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
    }
}
