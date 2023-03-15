import java.util.Set;
import java.util.HashSet;
import java.awt.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Post {

    private LocalDateTime timePosted;
    private Image postImage;
    private String caption;
    private int numberOfLikes;
    private Set<User> likedBy;

    public Post(Image postImage, String caption, int numberOfLikes, HashSet<User> likedBy, LocalDateTime timePosted)
    {
        this.timePosted = timePosted;
        this.postImage = postImage;
        this.caption = caption;
        this.numberOfLikes = numberOfLikes;
        this.likedBy = likedBy;
    }

    public Post()
    {
        timePosted = null;
        postImage = null;
        caption = null;
        numberOfLikes = 0;
        likedBy = null;
    }

    public void likePost(User a)
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

    public Image getPostImage()
    {
        return postImage;
    }

    public void setPostImage(Image postImage)
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

    public Set<User> getLikedBy()
    {
        return likedBy;
    }

    public void setLikedBy(Set<User> likedBy)
    {
        this.likedBy = likedBy;
    }


}