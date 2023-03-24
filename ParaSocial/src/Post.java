import java.util.ArrayList;
import java.awt.*;
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

    public void likePost(String a)
    {
        likedBy.add(a);
        numberOfLikes = likedBy.size();
        
    }

    public LocalDateTime getTimePosted()
    {
        return timePosted;
    }

    public void setTimePosted(LocalDateTime timePosted)
    {
        this.timePosted = timePosted;
    }

    public String getPostImage()
    {
        return postImage;
    }

    public void setPostImage(String postImage)
    {
        this.postImage = postImage;
    }

    public String getCaption()
    {
        return caption;
    }

    public void setCaption(String caption)
    {
        this.caption = caption;
    }

    public int getNumberOfLikes()
    {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes)
    {
        this.numberOfLikes = numberOfLikes;
    }

    public long getElapsedTime(LocalDateTime start, LocalDateTime end)
    {
        long elapsedTime = ChronoUnit.SECONDS.between(start, end);

        return elapsedTime;
    }

    public ArrayList<String> getLikedBy()
    {
        return likedBy;
    }

    public void setLikedBy(ArrayList<String> likedBy)
    {
        this.likedBy = likedBy;
    }

    public String getPostedBy()
    {
        return postedBy;
    }

    public void setPostedBy(String postedBy)
    {
        this.postedBy = postedBy;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }


}