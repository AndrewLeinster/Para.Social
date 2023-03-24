import java.util.Set;
import java.util.HashSet;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.ArrayList;


public class Main {

    private Set<User> users;
    private Set<Post> posts;
    private String fileName;

    public Main()
    {
        users = new HashSet<User>();
        posts = new HashSet<Post>();
        fileName = "paraSocial.txt";
    }

    public static void main(String[] args)
    {
        test();
    }

    public static void test()
    {
        Main m1 = new Main();

        m1.readIn();

        // create iterator to loop through set of users
        Iterator<User> userIterator = m1.users.iterator();

        // create instance of user to be used for the next user
        User nextUser;

        // while there is more items in the set
        while (userIterator.hasNext())
        {
            // get next object in set
            nextUser = userIterator.next();

            nextUser.getUserInfo();
            int loop = nextUser.getFriends().size();

            for (int i = 0; i < loop; i ++)
            {
                System.out.println(nextUser.getFriends().get(i));
            }
        }

        m1.writeToFile();
    }

    public void addUsers(User u)
    {
        users.add(u);
    }

    public void writeToFile()
    {

        // create iterator to loop through set of users
        Iterator<User> userIterator = users.iterator();

        // create instance of user to be used for the next user
        User nextUser;

        // declare variables needed to write to file
		FileOutputStream outputStream = null;
		PrintWriter printWriter = null;
		
		// try... catch to catch any file errors
		try
		{
			// create new objects of FileOutputStream and PrintWriter
			outputStream = new FileOutputStream(fileName);
			printWriter = new PrintWriter(outputStream);

            // while there is more items in the set
            while (userIterator.hasNext())
            {
                // get next object in set
                nextUser = userIterator.next();
                
                // call the writeUser method
                writeUser(nextUser, printWriter);
            }
        }
        catch (IOException e)
		{
			// error message
			System.out.println("error writing to file" + e);
		}
		finally
		{
			//close file
			if (printWriter != null)
	              printWriter.close();
		}
    }

    public void writeUser (User u, PrintWriter printWriter)
    {
        // create new ArrayList to store friends list
        ArrayList<String> friends = u.getFriends();

        // write user details to the file
        printWriter.println(u.getName());
        printWriter.println(u.getID());
        printWriter.println(u.getWorkPlace());
        printWriter.println(u.getHomeTown());
        printWriter.println(u.getPfp());

        printWriter.println(friends.size());
        
        // for each friend they have
        for (int i = 0; i < friends.size(); i++)
        {
            // write friendID to file
            printWriter.println(friends.get(i));
        }
    }

    public void readIn()
    {
        // declare variables needed to read in file
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;

        String name;
        String id;
        String workPlace;
        String homeTown;
        String pfp;
        String noOfFriends;
        String friendID;

        // try... catch to catch any file errors
        try
        {
            // create new objects of FileReader and BufferedReader
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);

            // read in user details
            name = bufferedReader.readLine();
            id = bufferedReader.readLine();
            workPlace = bufferedReader.readLine();
            homeTown = bufferedReader.readLine();
            pfp = bufferedReader.readLine();
            noOfFriends = bufferedReader.readLine();

            // while there are still lines to read in
            while (name != null)
            {
                // create a new user with the values that have been read in
                User newUser = new User(name, id, workPlace, homeTown, pfp, new ArrayList<String>());

                // for the number of friends
                for (int i = 0; i < Integer.parseInt(noOfFriends); i++)
                {
                    // read in the friendID
                    friendID = bufferedReader.readLine();
                    // add the friend to the friends list
                    newUser.addFriend(friendID);
                }

                // add new user to set of users
                users.add(newUser);

                // read in user details
                name = bufferedReader.readLine();
                id = bufferedReader.readLine();
                workPlace = bufferedReader.readLine();
                homeTown = bufferedReader.readLine();
                noOfFriends = bufferedReader.readLine();
            }    
        }
        catch (FileNotFoundException e)
		{
			System.out.println("File not found");
		}
		catch (IOException e)
		{
			System.out.println("Error reading in file" + e);
		}
		finally
		{
			// if the file was opened
            if (bufferedReader != null)
            {
            	// try...catch...finally to catch any file errors
                try 
                {
                    // try to close it
                    bufferedReader.close();
                }
                catch (IOException e)
                {
                    // warn user file wasn't properly closed
                    System.out.println("Sorry, there has been a problem closing the file" + e);
                }
            }
		}
    }

}

