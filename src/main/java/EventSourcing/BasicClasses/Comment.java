package EventSourcing.BasicClasses;

import java.util.UUID;

public class Comment {
    private UUID commentID;
    private UUID parentID;      //parent comment id for nested comment
    private UUID postID; /*TODO: Add this stuff*/
    private String message;

    //Empty constructor
    public Comment(){};

    //Constructor
    public Comment(UUID commentID, UUID parentID, UUID postID, String message) {
        this.commentID = commentID;
        this.parentID = parentID;
        this.message = message;
        this.postID = postID;

    }

    //Getters and setters
    public UUID getCommentID()  { return commentID; }
    public void setCommentID(UUID commentID)  { this.commentID = commentID; }

    public UUID getParentID()  { return parentID; }
    public void getParentID(UUID parentID)  { this.parentID = parentID; }

    public String getMessage()  { return message; }
    public void setMessage(String message)  { this.message = message; }

    public void setParentID(UUID parentID) {
        this.parentID = parentID;
    }

    public UUID getPostID() {
        return postID;
    }

    public void setPostID(UUID postID) {
        this.postID = postID;
    }

    @Override
    public String toString() {
        return "Comment{" +
                ", commentID=" + commentID +
                ", postID=" + postID +
                ", comment='" + message + '\'' +
                '}';
    }
}
