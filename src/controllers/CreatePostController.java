package controllers;

import core.Auth;
import core.Prompt;
import models.Models;
import models.Post;
import views.CreatePostView;
import views.HomeView;



public class CreatePostController extends Controller<CreatePostView> {
    HomeView parent;
    Runnable run;

    public CreatePostController(HomeView parent, Models model) {
        models = model;
        view = new CreatePostView();
        this.parent = parent;
        
        init();
    }
    
    private void init() {
        this.view.setController(this);
        this.view.setLocationRelativeTo(parent);
        this.view.setVisible(true);
    }
    
    public void update(Runnable func) {
        this.run = func;
    }
    
    public void createPost(String board, String post) {
        
        if (post.isEmpty()) {
            Prompt.error(view, "Post cannot be empty", "Cannot Post");
            return;
        }
        
        if (Auth.validateText(80, post)) {
            Prompt.error(view, "Post is limited to 80 characters only", "Cannot Post");
            return;
        }
       
        Post newPost = new Post(Auth.user.id, Auth.anon, board, post);
        models.post.add(newPost);
        run.run();
        Prompt.information(view, "Post Created", "Post Success");
        this.view.dispose();
    }
    
}
