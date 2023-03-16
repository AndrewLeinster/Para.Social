import java.util.Set;
import java.util.HashSet;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

public class Main {

    private Set<User> users;
    private Set<Post> posts;
    private String fileName;

    public Main()
    {
        users = null;
        posts = null;
        fileName = "paraSocial.txt";
    }

    public void writeToFile()
    {
        Set<User> added = null;

        Iterator<User> userIterator = users.iterator();

        User nextUser;

        // declare variables needed to write to file
		FileOutputStream outputStream = null;
		PrintWriter printWriter = null;
		
		// try catch statement to catch any input mismatch errors
		try
		{
			// create new objects of FileOutputStream and PrintWriter
			outputStream = new FileOutputStream(fileName);
			printWriter = new PrintWriter(outputStream);

            while (userIterator.hasNext())
            {
                // get next object in set
                nextUser = userIterator.next();

                added.add(nextUser);
                
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
        Set<User> friends = u.getFriends();

        printWriter.println(u.getName());
        printWriter.println(u.getID());
        printWriter.println(u.getWorkPlace());
        printWriter.println(u.getHomeTown());

        printWriter.println(friends.size());
        
        Iterator<User> friendIterator = friends.iterator();

        User nextFriend;

        for (int i = 0; i < friends.size(); i++)
        {
            nextFriend = friendIterator.next();

            writeUser(nextFriend, printWriter);
        }
    }

}