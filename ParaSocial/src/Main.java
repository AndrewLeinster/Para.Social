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
import java.util.Collections;

/**
 * 
 * Handles wider scale elements of the program including the master User set, the value of primaryUser and the act of reading and writing users to files
 * 
 * @author Laura Clark, Adam Munro, Iona Cavill and Andrew Leinster
 * @version 1.0.0
 */
public class Main {

    private Set<User> users;
    private String fileName;

    public Main() {
        users = new HashSet<User>();
        fileName = "paraSocial.txt";
    }

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Main m1 = new Main();

        m1.readIn();

        // create iterator to loop through set of users
        Iterator<User> userIterator = m1.users.iterator();

        // create instance of user to be used for the next user
        User nextUser;

        // while there is more items in the set
        while (userIterator.hasNext()) {
            // get next object in set
            nextUser = userIterator.next();

            nextUser.getUserInfo();
            int loop = nextUser.getFriends().size();

            for (int i = 0; i < loop; i++) {
                System.out.println(nextUser.getFriends().get(i));
            }
        }

        m1.writeToFile();
    }

    /**
     * adds a user to the set of user objects, users
     * 
     * @param u the user to be added
     */
    public void addUsers(User u) {
        users.add(u);
    }

    /**
     * writes all of the users in users to a file
     */
    public void writeToFile() {

        // create iterator to loop through set of users
        Iterator<User> userIterator = users.iterator();

        // create instance of user to be used for the next user
        User nextUser;

        // declare variables needed to write to file
        FileOutputStream outputStream = null;
        PrintWriter printWriter = null;

        // try... catch to catch any file errors
        try {
            // create new objects of FileOutputStream and PrintWriter
            outputStream = new FileOutputStream(fileName);
            printWriter = new PrintWriter(outputStream);

            // while there is more items in the set
            while (userIterator.hasNext()) {
                // get next object in set
                nextUser = userIterator.next();

                // call the writeUser method
                writeUser(nextUser, printWriter);
            }
        } catch (IOException e) {
            // error message
            System.out.println("error writing to file" + e);
        } finally {
            // close file
            if (printWriter != null)
                printWriter.close();
        }
    }

    /**
     * writes an individual user
     * 
     * @param u the user being written
     * @param printWriter the printwriter previously initialised
     */
    public void writeUser(User u, PrintWriter printWriter) {
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
        for (int i = 0; i < friends.size(); i++) {
            // write friendID to file
            printWriter.println(friends.get(i));
        }
    }

    /**
     * reads in all the users from a file
     */
    public void readIn() {
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
        try {
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
            while (name != null) {
                // create a new user with the values that have been read in
                User newUser = new User(name, id, workPlace, homeTown, pfp, new ArrayList<String>());

                // for the number of friends
                for (int i = 0; i < Integer.parseInt(noOfFriends); i++) {
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
                pfp = bufferedReader.readLine();
                noOfFriends = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading in file" + e);
        } finally {
            // if the file was opened
            if (bufferedReader != null) {
                // try...catch...finally to catch any file errors
                try {
                    // try to close it
                    bufferedReader.close();
                } catch (IOException e) {
                    // warn user file wasn't properly closed
                    System.out.println("Sorry, there has been a problem closing the file" + e);
                }
            }
        }
    }

    /**
     * recieves a String input and searches for the matching user ID and returns the user object
     * 
     * @param ID the ID being searched for
     * @return the user object that matches the ID, or null if the ID isn't found
     */
    public User IDtoUser(String ID) {

        User[] userArray = users.toArray(new User[users.size()]);

        for (int i = 0; i < userArray.length; i++) {

            if (ID.equals(userArray[i].getID())) {
                return userArray[i];
            }
        }

        System.out.println("user not found");
        return null;
    }

    /**
     * Add a given user to the set of users
     * 
     * @param a The user to add to the set of users
     */
    public void addUser(User a) {
        users.add(a);
    }

    /**
     * Get an arraylist of friend recommendations by comparing workplace and
     * hometowns
     * 
     * @param user The user to get friend recommendations for
     * @return The arrayList of users who could be friend recommendations
     */
    public ArrayList<User> getFriendRecommendations(User user) {

        User[] userArray = users.toArray(new User[users.size()]);
        ArrayList<User> recommendations = new ArrayList<User>();

        for (int i = 0; i < userArray.length; i++) {
            System.out.println(userArray[i].getHomeTown());
            System.out.println(user.getHomeTown());
            if (userArray[i].getWorkPlace().equals(user.getWorkPlace())
                    || userArray[i].getHomeTown().equals(user.getHomeTown())) {
                recommendations.add(userArray[i]);
            }
        }
        return recommendations;
    }

    /**
     * searches a users friends
     * 
     * @param search the String search term
     * @param a the user who's friends list is being searched
     * @return the list of friends returned by the search ordered by searchStrength
     */
    public ArrayList<User> searchFriend(String search, User a) {

        //creates an ArrayList to hold all search results
        ArrayList<User> result = new ArrayList<User>();
        //another for the searchStrength of each result
        ArrayList<Integer> strength = new ArrayList<Integer>();

        ArrayList<String> friends = a.getFriends();

        for (int i = 0; i < friends.size(); i++) {
            if (IDtoUser(friends.get(i)).searchApplicable(search) == true) {

                strength.add(getSearchStrength(search, IDtoUser(friends.get(i))));
                result.add(IDtoUser(friends.get(i)));

            }
        }

        ArrayList<User> sortedResult = new ArrayList<User>();
        int index;

        for (int i = 0; i < result.size(); i++) {

            //sets index to the highest searchStrength
            index = strength.indexOf(Collections.max(strength));
            //then removes it from the list
            strength.remove(index);
            //adds the result of the same index to sorted result, therefore sorting the
            //result list from highest to lowest searchStrength
            sortedResult.add(result.get(index));
            //removes the result
            result.remove(index);

        }
       
        return sortedResult;
    }

    /**
     * 
     * Finds the search strength of each applicable user to determine how they
     * should be ordered in the search results
     * 
     * @param search the term searched
     * @param a      the User being searched
     * @return searchStrength
     */
    public int getSearchStrength(String search, User a) {
        int searchStrength = 0;

        String[] userDetails = { a.getID(), a.getName(), a.getWorkPlace(), a.getHomeTown() };

        //checks to see how many times a match is found within the users details
        for (int i = 0; i < userDetails.length; i++) {
            if (compareStrings(userDetails[i], search)) {
                //increases searchStrength for every match found
                searchStrength++;
            }
        }

        //then increases searchStrength by the number of mutual friends the primary user and the user being searched have
        searchStrength = searchStrength + a.getMutuals(getPrimaryUser(), a).size();

        //multiplies search strength by two if the searched User is friends with the primary user
        if (a.getFriends().contains(getPrimaryUser().getID())) {
            searchStrength = searchStrength * 2;
        }

        return searchStrength;
    }

    /**
     * compares two strings to see if one appears anywhere in the other
     * 
     * @param a the String being checked
     * @param b the String being searched
     * @return true/false dependant on whether String a appears in String a
     */
    public boolean compareStrings(String a, String b) {

        //converts both to lowercase as for the search to not be case sensitive
        a = a.toLowerCase();
        b = b.toLowerCase();

        for (int i = 0; i < a.length() - b.length(); i++) {

            //checks all possible substrings of String a that are the same length as String b to see if they match
            if (a.substring(i, i + b.length()).equals(b)) {
                return true;
            }
        }

        return false;
    }

    /**
     * returns the primaryUser
     * 
     * @return the user at index 0
     */
    public User getPrimaryUser() {
        User[] userArray = users.toArray(new User[users.size()]);
        return userArray[0];
    }

    /**
     * returns the set of users
     * 
     * @return users
     */
    public Set<User> getUsers() {
        return users;
    }

}