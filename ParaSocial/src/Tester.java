import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;

public class Tester {
    private Main main;

    public Tester()
    {
       main = new Main();
    }

    public static void main(String[] args)
    {
        Tester t = new Tester();
        t.addUsers();
        t.write();

    }

    public void write()
    {
        main.writeToFile();
    }

    public void addUsers()
    {
        User user1 = new User("Laura", "1", "Starbucks", "Glenrothes", "Images/PFPs/Laura.jpg", new ArrayList<String>());
        User user2 = new User("Adam", "2", "O2", "Dunfermline", "Images/PFPs/Adam.jpg", new ArrayList<String>());
        User user3 = new User("Iona", "3", "Tesco", "Monifieth", "Images/PFPs/Iona.jpg", new ArrayList<String>());
        User user4 = new User("Andrew", "4", "Self-Employed", "Fife", "Images/PFPs/Andrew.jpg", new ArrayList<String>());
        User user5 = new User("Marcus", "5", "Old Course", "Monikie", "Images/PFPs/Marcus.jpg", new ArrayList<String>());
        User user6 = new User("Adri", "6", "Odeon", "Las Vegas", "Images/PFPs/Adri.jpg", new ArrayList<String>());
        User user7 = new User("Beth", "7", "DUSA", "Falkirk", "Images/PFPs/Beth.jpg", new ArrayList<String>());
        
/*
        for (int i=1; i<=getUserCount();i++) {
            Random random = new Random();
            int max_friends = random.nextInt(getUserCount()) + 1; 

            for (int j=1; j<=max_friends; j++) {
                String user = "user" + i;
                user.addFriend(user2.getID());
            }
        }
*/
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
        user6.addFriend(user7.getID());
        user6.addFriend(user1.getID());
        user6.addFriend(user2.getID());
        user6.addFriend(user5.getID());
        user6.addFriend(user1.getID());
        user7.addFriend(user1.getID());
        user7.addFriend(user2.getID());
        user7.addFriend(user5.getID());
        user7.addFriend(user1.getID());


        main.addUsers(user1);
        main.addUsers(user2);
        main.addUsers(user3);
        main.addUsers(user4);
        main.addUsers(user5);
    }

    public int getUserCount() {
        int max = 31;

        // Any thoughts on getting a max ID value?

        return max;
    }
}
