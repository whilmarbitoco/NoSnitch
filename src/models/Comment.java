package models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;


public class Comment extends Model<Comment> implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public int postId;
    public int userId;
    public int anonId;
    public String comment;
    public LocalDate date;

    public Comment() {
        super();
        modelName = "Comment";
        load();
    }

    public Comment(int postId, int userId, int anonId, String comment) {
        this.postId = postId;
        this.userId = userId;
        this.anonId = anonId;
        this.comment = comment;
        this.date = LocalDate.now();
    }
   
    public int getCommentCount(int id) {
        int count = 0;
        
        for (Comment com : model) {
            if (com.userId == id) {
                count++;
            }
        }
        return count;
    }
    
}
