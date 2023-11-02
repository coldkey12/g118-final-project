package don.bitlab.models;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String content;
    private LocalDateTime postDate;
    private User user;
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDateTime postDate) {
        this.postDate = postDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Comment() {
    }

    public Comment(Long id, String content, LocalDateTime postDate, User user, Post post) {
        this.id = id;
        this.content = content;
        this.postDate = postDate;
        this.user = user;
        this.post = post;
    }
}
