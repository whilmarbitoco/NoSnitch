package controllers;

import java.util.LinkedList;
import javax.swing.JOptionPane;
import models.Models;
import models.Post;
import models.User;
import views.CommentsView;
import views.CreatePostView;
import views.HomeView;
import views.LoginView;


public class HomeController {
    
   Models models;
    HomeView homeView;
    
    User user;
    int anonymousId;

    public HomeController(Models models, User user) {
        this.models = models;
        this.homeView = new HomeView();
        this.user = user;
        this.anonymousId = user.generateId();

        init();
        this.setPost(models.post.getAll());
    }
    
    public void init() {        
        setInfo();
        homeView.setController(this);
        homeView.setLocationRelativeTo(null);
        homeView.setVisible(true);
    }
    
    public void setPost(LinkedList<Post> posts) {
        for (Post post : posts) {
            homeView.addPost(post);
        }
    }
    
    public void setInfo() {
        String postCount = Integer.toString(models.post.getPostCount(user.id));
        String commentCount = Integer.toString(models.comment.getCommentCount(user.id));

        homeView.setData(user.username, "Anonymous#" + Integer.toString(anonymousId), postCount, commentCount);

    }
    
    public void createPost() {
        new CreatePostController(new CreatePostView(), homeView, this);
    }
    
    public void updatePost(Post post) {
        post.userId =  user.id;
        post.anon = "Anonymous#" + Integer.toString(anonymousId);
        models.post.add(post);
        this.homeView.addPost(post);
    }
    
    public void gotoComment(Post post) {
        new CommentController(new CommentsView(), post,homeView, models,user.id, anonymousId);
        this.homeView.dispose();
    }
    
    public void logout() {
        
        int choice = JOptionPane.showConfirmDialog(homeView, "Do you really want to logout?", "Confirm Logout", JOptionPane.CANCEL_OPTION);
     
        if (choice != 0) {
            return;
        }
        
        new LoginController(models, new LoginView());
        this.homeView.dispose();
    }
    
    public void changeBoard(String board) {
        homeView.setBoardName("board/"+board);
        
        if (board.equals("all")) { 
            setPost(models.post.getAll());
            return;
        }
        
        homeView.clear();
        setPost(models.post.getByBoard(board));
        
    }
}
