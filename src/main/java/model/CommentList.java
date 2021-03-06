package model;

import java.util.ArrayList;

public class CommentList {

    public ArrayList<Comment> comments;

    public CommentList(){
        comments = new ArrayList<>();
    }

    public void add(Comment c){
        comments.add(c);
    }

    @Override
    public String toString() {
       String s = "";
        for (Comment c : comments
             ) {

            s+= c + "\n";
        }
        return s;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
