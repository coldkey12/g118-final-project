package don.bitlab.db;

import don.bitlab.models.Comment;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDb extends DbConnector {
    public static List<Comment> getCommentsById(Long id) {
        List<Comment> comments = new ArrayList<>();
        try {
            var statement = connection.prepareStatement(
                    "SELECT * FROM final.comments WHERE post_id = ?"
            );
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Comment comment = new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setContent(resultSet.getString("content"));
                comment.setPostDate(resultSet.getObject("post_date", LocalDateTime.class));
                comment.setUser(UserDb.getUserById(resultSet.getLong("user_id")));
                comment.setPost(PostDb.getPostById(resultSet.getLong("post_id")));

                comments.add(comment);
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public static void addComment(Comment comment) {
        try {
            var statement = connection.prepareStatement(
                    "INSERT INTO final.comments(content,post_date,user_id,post_id) " +
                            "VALUES(?,now(),?,?)"
            );
            statement.setString(1,comment.getContent());
            statement.setLong(2,comment.getUser().getId());
            statement.setLong(3,comment.getPost().getId());
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
