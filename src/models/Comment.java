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
    
    public LinkedList<Comment> getPostComment(int postId) {
        LinkedList<Comment> res = new LinkedList<>();
        
        for (Comment c : model) {
            if (c.postId == postId) {
                res.add(c);
            }
        }
        
        return res;
    }
    
    public LinkedList<Comment> getUserComment(int userId) {
        LinkedList<Comment> res = new LinkedList<>();
        
        for (Comment c : model) {
            if (c.userId == userId) {
                res.add(c);
            }
        }
        
        return res;
    }
    
   
    public int getCommentCount(int userId) {
        return getUserComment(userId).size();
    }
    
}
