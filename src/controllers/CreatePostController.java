package controllers;

import javax.swing.JOptionPane;
import models.Post;
import views.CreatePostView;
import views.HomeView;



public class CreatePostController {
    CreatePostView createPostView;
    HomeView parent;
    HomeController homeController;

    public CreatePostController(CreatePostView createPostView, HomeView parent, HomeController homeController) {
        this.createPostView = createPostView;
        this.parent = parent;
        this.homeController = homeController;
        
        init();
    }
    
    public void init() {
        this.createPostView.setController(this);
        this.createPostView.setLocationRelativeTo(parent);
        this.createPostView.setVisible(true);
    }
    
    public void createPost(String board, String post) {
        if (post.length() > 80) {
            JOptionPane.showMessageDialog(createPostView, "Post is limited to 80 characters only", "Cannot Post", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        System.out.println(board);
        System.out.println(post);
        JOptionPane.showMessageDialog(createPostView, "Post Created", "Post Success", JOptionPane.INFORMATION_MESSAGE);

        Post newPost = new Post(0, null, board, post);
        homeController.updatePost(newPost);
        this.createPostView.dispose();
    }
    
}
