package don.bitlab.db;

import don.bitlab.models.Category;
import don.bitlab.models.Post;
import don.bitlab.models.User;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostDb extends DbConnector {

    public static List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        try {
            var statement = connection.prepareStatement(
                    "SELECT * FROM final.post ORDER BY ID DESC"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post();
                post.setId(resultSet.getLong("id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                post.setPostDate(resultSet.getObject("post_date", LocalDateTime.class));

                Category category = new Category();
                category = CategoryDb.getCategoryById(resultSet.getLong("category_id"));
                post.setCategory(category);

                User user = new User();
                user = UserDb.getUserById(resultSet.getLong("user_id"));
                post.setUser(user);

                posts.add(post);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    public static Post getPostById(Long id) {
        Post post = null;
        try {
            var statement = connection.prepareStatement(
                    "SELECT * FROM final.post WHERE id = ?"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                post = new Post();
                post.setId(id);
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                post.setPostDate(resultSet.getObject("post_date", LocalDateTime.class));

                Category category = new Category();
                category = CategoryDb.getCategoryById(resultSet.getLong("category_id"));
                post.setCategory(category);

                User user = new User();
                user = UserDb.getUserById(resultSet.getLong("user_id"));
                post.setUser(user);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public static void updatePost(Post post) {
        try {
            var statement = connection.prepareStatement(
                    "UPDATE final.post " +
                            "SET title = ?, content = ?, category_id = ? " +
                            "WHERE id = ?"
            );
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setLong(3, post.getCategory().getId());
            statement.setLong(4, post.getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
