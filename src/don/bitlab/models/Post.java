package don.bitlab.models;

import java.time.LocalDateTime;

public class Post {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime postDate;
    private Category category;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Post() {
    }

    public Post(Long id, String title, String content, LocalDateTime postDate, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.category = category;
    }
}
