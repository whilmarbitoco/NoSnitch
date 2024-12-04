package controllers;

import core.Auth;
import core.Prompt;
import core.Validator;
import java.util.LinkedList;
import javax.swing.JFrame;
import models.Models;
import models.Post;
import views.EditPostView;
import views.ProfileView;


public class ProfileController extends Controller<ProfileView> {
    
    public ProfileController(Models model) {
        models = model;
        view = new ProfileView();
        
        init();
    }
    
    private void init() {
        view.setController(this);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        setPosts();
        
        String postCount = Integer.toString(models.post.getPostCount(Auth.user.id));
        String commentCount = Integer.toString(models.comment.getCommentCount(Auth.user.id));
        view.setData(Auth.user.username, Auth.anon, postCount, commentCount);
    }
    
    private void setPosts() {
        view.clear();
        LinkedList<Post> posts = models.post.getUserPost(Auth.user.id);
        
        for (Post post : posts) {
            view.addPost(post);
        }
    }
    
    public void changeBoard(String board) {
        new HomeController(models).changeBoard(board);
        view.dispose();
    }
    
    public void deletePost(int id) {
        int choice = Prompt.cancel(view, "Are you want to delete this post?", "Confirm Delete");
        
        if (choice != 0) {
            return;
        }
        
        models.post.deleteById(id);
        setPosts();
    }
    
    public void editPost(Post post) {
        EditPostView editPost = new EditPostView(this, post);
        editPost.setLocationRelativeTo(null);
        editPost.setVisible(true);
    }
    
    public void submitEdit(String editPost, int postId, JFrame editView) {
        Post post = models.post.getById(postId);
        
        if (post == null) {
            Prompt.error(view, "Something went wrong...", "Error");
            return;
        }
        
        if (Validator.wordCount(editPost, 80)) {
            Prompt.error(view, "Post is limited to 80 words only", "Cannot Post");
            return;
        }
        
        post.post = editPost;
        models.post.updateById(postId, post);
        setPosts();
        editView.dispose();
    }
}
