package controllers;

import javax.swing.JOptionPane;
import models.Comment;
import models.Models;
import models.Post;
import views.CommentsView;
import views.HomeView;
import views.LoginView;


public class CommentController {
    CommentsView commentView;
    HomeView parent;
    Models models;
    Post post;
    int userId;
    int anonId;

    public CommentController(CommentsView commentView,Post post, HomeView parent, Models models, int id, int anonId) {
        this.commentView = commentView;
        this.post = post;
        this.parent = parent;
        this.models = models;
        this.userId = id;
        this.anonId = anonId;
        init();
    }
    
    public void init() {
        this.commentView.setController(this);
        this.commentView.setData(post);
        this.commentView.setLocationRelativeTo(parent);
        initComments();
        this.commentView.setVisible(true);

    }
    
    public void initComments() {
        for (Comment c : models.comment.getAll()) {
            if (post.id == c.postId) {
                this.commentView.setComments(c, "Anonymous#"+Integer.toString(c.anonId));
            }
        }
    }
    
    
    public void createComment(String comment) {
        if (comment.length() > 50) {
            JOptionPane.showMessageDialog(commentView, "COmment is limited to 50 characters only", "Cannot Comment", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Comment newComment = new Comment(post.id, userId, anonId,comment);
        models.comment.add(newComment);
        this.commentView.setComments(newComment, "Anonymous#"+Integer.toString(newComment.anonId));
   
    }
    
    public void logout() {
        new LoginController(models, new LoginView());
        this.commentView.dispose();
    }
}
