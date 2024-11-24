package controllers;

import core.Auth;
import core.Prompt;
import models.Comment;
import models.Models;
import models.Post;
import views.CommentsView;
import views.HomeView;


public class CommentController extends Controller<CommentsView>{
    
    HomeView parent;
    Post post;

    public CommentController(Post post, HomeView parent, Models model) {
        models = model;
        view = new CommentsView();
        this.post = post;
        this.parent = parent;
        init();
    }
    
    private void init() {
        this.view.setController(this);
        this.view.setData(post);
        this.view.setLocationRelativeTo(parent);
        initComments();
        this.view.setVisible(true);

    }
    
    private void initComments() {
        for (Comment c : models.comment.getPostComment(post.id)) {
            this.view.setComments(c, "Anonymous#"+Integer.toString(c.anonId));
        }
    }
    
    public void changeBoard(String board) {
        new HomeController(models).changeBoard(board);
        view.dispose();
    }
    
    public void createComment(String comment) {
        if (comment.length() > 50) {
            Prompt.error(view, "COmment is limited to 50 characters only", "Cannot Comment");
            return;
        }
        Comment newComment = new Comment(post.id, Auth.user.id, Auth.anonId, comment);
        models.comment.add(newComment);
        this.view.setComments(newComment, Auth.anon);
   
    }
    
}
