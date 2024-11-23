package models;

import java.io.Serializable;
import java.security.Key;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Random;


public class Post extends Model<Post> implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public int userId;
    public String anon;
    public String post;
    public String board;
    public LocalDate date;

    public Post() {
        super();
        modelName = "Post";
        load();
    }

    
    public Post(int userId, String anon, String board, String post) {
        this.userId = userId;
        this.anon = anon;
        this.board = board;
        this.post = post;
        this.date = LocalDate.now();
    }
    
    public LinkedList<Post> getUserPost(int id) {
        LinkedList<Post> userPosts = new LinkedList<>();
        
        for (Post userPost : model) {
            if (userPost.userId == id) {
                userPosts.add(userPost);
            }
        }
        
        return userPosts;
    }
    
    public int getPostCount(int id) {
        int count = 0;
        
        for (Post p : model) {
            if (p.userId == id) {
                count++;
            }
        }
        
        return count;
    }
    
    public LinkedList<Post> getByBoard(String board) {
        LinkedList<Post> res = new LinkedList<>();
        
        for (Post post : model) {
            if (post.board.equalsIgnoreCase(board)) {
                res.add(post);
            }
        }
        
        return res;
    }
    
    public void seed() {
        String[] samplePosts = {
        "Hello world!",
        "Today is a great day!",
        "Random post #1",
        "Java is fun.",
        "Learning to code is awesome.",
        "I love programming.",
        "Coding challenges are the best.",
        "Just sharing some thoughts.",
        "Here's a random idea.",
        "Stay positive!"
        };
        
        Random random = new Random();
        
        for (int i = 0; i < samplePosts.length; i++) {
            String samplePost = samplePosts[i];
            int userId = random.nextInt(100) + 1;
            String post = samplePosts[random.nextInt(samplePosts.length)];
            String anon = "Anonymous#" + Integer.toString(random.nextInt(100));
            
            Post postTest = new Post(userId, anon, "General",post);
            
            this.add(postTest);
        }
        save();
    }
   
}
