package api;

import model.Comment;
import model.CommentList;
import org.springframework.web.client.RestTemplate;
import java.util.UUID;

public class CommentRequest {

    private static RestTemplate restAPI = new RestTemplate();
    private static String commentServiceURL = "http://70.65.105.239:55555/comments/";


    public CommentRequest(){};

    public CommentList getCommentsForUserID(UUID id){
         return restAPI.getForObject(commentServiceURL + "user/" + id.toString(), CommentList.class);
    }

    public CommentList getAllComments(){
        return restAPI.getForObject(commentServiceURL, CommentList.class);
    }

    public Comment getCommentByCommentID(UUID id){
        return restAPI.getForObject(commentServiceURL + "commentID/" + id.toString(), Comment.class);

    }


    public static void main(String[] args) {
        CommentRequest r = new CommentRequest();
        System.out.println(r.getCommentsForUserID(UUID.fromString("90b9ce7f-93d7-4623-98c4-a961c20bac7c")));
        System.out.println(r.getAllComments());
        System.out.println(r.getCommentByCommentID(UUID.fromString("cf72de3a-1645-4999-8f3c-01f854c3271b")));
    }



}
