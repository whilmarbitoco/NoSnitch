package models;

public class Models {
    
    public Post post;
    public User user;
    public Comment comment;

    public Models() {
        this.post = new Post();
        this.user = new User();
        this.comment = new Comment();
        System.out.println(user);
    }
    
}
