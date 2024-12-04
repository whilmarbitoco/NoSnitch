# 🌟 NoSnitch 📝  
**Stay anonymous. Stay connected.**  

NoSnitch is a platform designed to foster free expression among students by allowing them to post anonymous notes and comments. Inspired by the fun and spontaneity of the bulletin board during **Aces Tagum College's intramurals**, this project brings that experience into the digital age. A project for **CC104 - Data Structures and Algorithms**.  

---

## ✨ Features  
📝 **Post Notes:** Share your thoughts anonymously on categorized boards.  
💬 **Comment on Notes:** Engage with others by adding your feedback or ideas.  
📂 **Board Categories:** Choose from topics like **General**, **Love**, **School**, and **Art** for focused discussions.  
⏳ **Automatic Deletion:** Notes vanish after a set duration to keep things fresh.  
🔒 **Login & Sign-Up:** Secure accounts prevent spam and misuse.  
💾 **Data Persistence with Serialization:**  NoSnitch saves notes, comments, and user accounts to files, ensuring data is retained even when the system shuts down—a practical alternative to a full database!  

---

## 🚀 How to Use  
1. 🔑 **Sign Up:** Create an account to access the platform.  
2. 📌 **Pick a Board:** Browse categories that match your interests.  
3. ✍️ **Post a Note:** Share your message anonymously.  
4. 👀 **Explore & Engage:** Read other posts and leave your comments.  

---

## 🎯 Purpose  
NoSnitch promotes a safe and judgment-free digital environment for students to express themselves. Whether it’s a heartfelt confession, an interesting idea, or a random thought, NoSnitch is here to let every voice be heard!  

---

🌈 **Happy posting, and remember: No snitching! 🤫**

---

## MVC Architecture

The **Model-View-Controller (MVC)** architecture is implemented in the NoSnitch project to separate the logic, presentation, and user interaction layers. Here's how each part is defined:

### 1. **Model**
   - The **Model** is responsible for the core data handling, including the interaction with posts, comments, users, and sessions. It defines the structure of the data and the logic for managing them.
   - Example models include `User.java`, `Post.java`, and `Comment.java`, which define data structures and methods like `getByEmail()`, `getUserPost()`, etc.
   - Models also handle data persistence using serialization (`.ser` files), ensuring data is saved even after the application shuts down.

### 2. **View**
   - The **View** is responsible for rendering the user interface, allowing users to interact with the application.
   - Views are represented by `.form` files and their corresponding `.java` classes, like `HomeView.java`, `LoginView.java`, `PostView.java`, etc.
   - These views present the data provided by the controllers and handle the interaction with the user.

### 3. **Controller**
   - The **Controller** acts as the intermediary between the **Model** and the **View**. It takes user input from the views, processes it (via the model), and updates the view accordingly.
   - Example controllers are `LoginController.java`, `SignupController.java`, `CommentController.java`, and `CreatePostController.java`.

---

## Base Model & Writer Class

The **Base Model** and **Writer Class** are fundamental to the project's structure, helping manage data serialization and model behaviors.

### **Base Model (`Model.java`)**
   - The `Model` class serves as the superclass for all data models, providing shared functionality for saving, loading, and managing data in memory.
   - Each model, such as `Post`, `Comment`, or `User`, extends this base model to inherit common methods, such as `save()`, `load()`, and serialization management.

### **Writer Class (`Writer.java`)**
   - The `Writer` class manages file writing operations, such as saving and loading serialized data (`.ser` files).
   - It handles persistence by saving objects to disk and loading them back into memory when the application starts.

---

## Base Model & Other Models

Here’s a breakdown of how the **Base Model** interacts with other models in NoSnitch:

- **Base Model (Model.java)**: Provides methods like `save()`, `load()`, and `add()` to handle common operations for all data models.
- **Post Model (Post.java)**: Represents a post made by a user, containing fields like `userId`, `anon`, `post`, and `board`. It provides methods like `getUserPost()` and `getByBoard()` to manage posts.
- **Comment Model (Comment.java)**: Handles comments related to posts, with fields like `postId`, `userId`, and `comment`. It provides methods like `getPostComment()` and `getUserComment()` to manage and retrieve comments.
- **User Model (User.java)**: Manages user data, such as `email`, `username`, and `password`. It includes methods like `getByEmail()` for retrieving a user by their email.

---

## Project Folder Structure

The **project folder structure** is organized to separate different aspects of the application into logical components.

### **Folder Structure**

```plaintext
src/
├── assets
├── controllers
│   ├── ChangePassController.java
│   ├── CommentController.java
│   ├── Controller.java
│   ├── CreatePostController.java
│   ├── HomeController.java
│   ├── LoginController.java
│   ├── ProfileController.java
│   ├── SettingsController.java
│   └── SignupController.java
├── core
│   ├── Auth.java
│   ├── Prompt.java
│   ├── TimeHelper.java
│   ├── Validator.java
│   └── Writer.java
├── models
│   ├── Comment.java
│   ├── data
│   │   ├── Comment.ser
│   │   ├── Post.ser
│   │   ├── Session.ser
│   │   └── User.ser
│   ├── Model.java
│   ├── Models.java
│   ├── Post.java
│   ├── Session.java
│   └── User.java
├── nosnitch
│   └── NoSnitch.java
└── views
    ├── ChangePassView.form
    ├── ChangePassView.java
    ├── CommentsView.form
    ├── CommentsView.java
    ├── CommentView.form
    ├── CommentView.java
    ├── CreatePostView.form
    ├── CreatePostView.java
    ├── EditPostView.form
    ├── EditPostView.java
    ├── HomeView.form
    ├── HomeView.java
    ├── LoginView.form
    ├── LoginView.java
    ├── MyPostView.form
    ├── MyPostView.java
    ├── PostView.form
    ├── PostView.java
    ├── ProfileView.form
    ├── ProfileView.java
    ├── SettingsView.form
    ├── SettingsView.java
    ├── SignUpView.form
    ├── SignUpView.java
    └── View.java
```

### **Explanation of Folder Structure**

1. **`assets/`**: Stores static files such as images, CSS, and other assets.
2. **`controllers/`**: Contains the logic for handling user actions and interactions.
3. **`core/`**: Includes utility classes like authentication (`Auth.java`), validation (`Validator.java`), and file writing (`Writer.java`).
4. **`models/`**: Contains the data models representing different entities in the system, like `Post`, `Comment`, `User`, and `Session`. Also includes serialized data files (`.ser`) to store the application's state.
5. **`nosnitch/`**: Likely houses a specialized feature or service for the platform (`NoSnitch.java`).
6. **`views/`**: Stores Java classes and form files that define the user interface, such as login, sign-up, and post management.
