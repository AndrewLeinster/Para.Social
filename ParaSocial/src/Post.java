import java.util.ArrayList;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Post {

    private LocalDateTime timePosted;
    private String postImage;
    private String caption;
    private int numberOfLikes;
    private ArrayList<String> likedBy;
    private String postedBy;
    private String ID;

    public Post(String postImage, String caption, int numberOfLikes, ArrayList<String> likedBy, LocalDateTime timePosted, String postedBy, String ID)
    {
        this.timePosted = timePosted;
        this.postImage = postImage;
        this.caption = caption;
        this.numberOfLikes = numberOfLikes;
        this.likedBy = likedBy;
        this.postedBy =  postedBy;
        this.ID = ID;
    }

    public Post()
    {
        timePosted = null;
        postImage = null;
        caption = null;
        numberOfLikes = 0;
        likedBy = null;
    }

    /**
     * 
     * Adds a user to the likedBy arrayList for the post and increases numberOfLikes by 1
     * 
     * @param userID the ID of the user liking the post
     */
    public void likePost(String userID)
    {
        likedBy.add(userID); 
        numberOfLikes++;
    }

    /**
     * 
     * removes a like from a post
     * 
     * @param userID the User disliking the post
     */
    public void dislikePost(String userID)
    {
        for (int i = 0; i < likedBy.size(); i++)
        {
            if (userID.equals(likedBy.get(i)))
            {
                likedBy.remove(i);
            }
        }

        numberOfLikes--;
        
    }

    /**
     * Checks if a User has liked a specific post
     * 
     * @param userID the User being checked
     * @return boolean value indicating whether they have or have not liked it
     */
    public boolean liked(String userID)
    {

        for (int i = 0; i < likedBy.size(); i++)
        {
            if (userID.equals(likedBy.get(i)))
            {
                return true; //if found return true, breaking the for loop
            }
        }

        return false; //if the end of the list is reached, return false

    }

    /**
     * 
     * returns the time the post was posted
     * 
     * @return timePosted
     */
    public LocalDateTime getTimePosted()
    {
        return timePosted;
    }

    /**
     * 
     * Sets the value of timePosted
     * 
     * @param timePosted
     */
    public void setTimePosted(LocalDateTime timePosted)
    {
        this.timePosted = timePosted;
    }

    /**
     * 
     * returns the file path of the post's image
     * 
     * @return postImage
     */
    public String getPostImage()
    {
        return postImage;
    }

    /**
     * 
     * Sets the value of postImage
     * 
     * @param postImage
     */
    public void setPostImage(String postImage)
    {
        this.postImage = postImage;
    }

    /**
     * 
     * returns the value of caption
     * 
     * @return caption
     */
    public String getCaption()
    {
        return caption;
    }

    /**
     * 
     * Sets the value of caption
     * 
     * @param caption
     */
    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    /**
     * 
     * returns the number of likes the post has recieved
     * 
     * @return numberOfLikes
     */
    public int getNumberOfLikes()
    {
        return numberOfLikes;
    }

    /**
     * 
     * sets the value of the number of likes
     * 
     * @param numberOfLikes
     */
    public void setNumberOfLikes(int numberOfLikes)
    {
        this.numberOfLikes = numberOfLikes;
    }

    /**
     * 
     * gets the elapsed time between two points
     * 
     * @param start the first, earlier point in time
     * @param end the second, later point in time
     * @return elapsedTime
     */
    public long getElapsedTime(LocalDateTime start, LocalDateTime end)
    {
        long elapsedTime = ChronoUnit.SECONDS.between(start, end);

        return elapsedTime;
    }

    /**
     * 
     * gets the arrayList of the ID's of all users who have liekd the post
     * 
     * @return likedBy
     */
    public ArrayList<String> getLikedBy()
    {
        return likedBy;
    }

    /**
     * 
     * sets the value of the likedBy arrayList
     * 
     * @param likedBy 
     */
    public void setLikedBy(ArrayList<String> likedBy)
    {
        this.likedBy = likedBy;
    }

    /**
     * 
     * returns the value of postedBy 
     * 
     * @return postedBy
     */
    public String getPostedBy()
    {
        return postedBy;
    }

    /**
     * 
     * sets the value of postedBy
     * 
     * @param postedBy
     */
    public void setPostedBy(String postedBy)
    {
        this.postedBy = postedBy;
    }

    /**
     * 
     * returns the post ID
     * 
     * @return ID
     */
    public String getID()
    {
        return ID;
    }

    /**
     * 
     * sets the value of the post ID
     * 
     * @param ID
     */
    public void setID(String ID)
    {
        this.ID = ID;
    }


}