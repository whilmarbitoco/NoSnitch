package controllers;

import core.Auth;
import java.util.LinkedList;
import models.Models;
import models.Post;
import views.HomeView;


public class HomeController extends Controller<HomeView>{
        
    public HomeController(Models model) {
        models = model;
        view = new HomeView();

        init();
        this.setPost(models.post.getAll());
    }
    
    private void init() {        
        setInfo();
        view.setController(this);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }
    
    private void setPost(LinkedList<Post> posts) {
        for (Post post : posts) {
            view.addPost(post);
        }
    }
    
    private void setInfo() {
        String postCount = Integer.toString(models.post.getPostCount(Auth.user.id));
        String commentCount = Integer.toString(models.comment.getCommentCount(Auth.user.id));

        view.setData(Auth.user.username, Auth.anon, postCount, commentCount);

    }
    
    public void createPost() {
        view.clear();
        new CreatePostController(view, models).update(() -> this.updatePost());
    }
    
    public void updatePost() {
        view.setBoardName("board/all");
        setPost(models.post.getAll());
        setInfo();
    }
    
    public void gotoComment(Post post) {
        new CommentController(post,view, models);
        this.view.dispose();
    }
    
    
    public void changeBoard(String board) {
        view.setBoardName("board/"+board);
        
        if (board.equals("all")) { 
            view.clear();
            setPost(models.post.getAll());
            return;
        }
        
        view.clear();
        setPost(models.post.getByBoard(board));
        
    }
}
