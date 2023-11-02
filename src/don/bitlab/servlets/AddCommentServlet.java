package don.bitlab.servlets;

import don.bitlab.db.CommentDb;
import don.bitlab.db.PostDb;
import don.bitlab.models.Comment;
import don.bitlab.models.Post;
import don.bitlab.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Comment comment = new Comment();

        Post post = PostDb.getPostById(Long.valueOf(req.getParameter("post_id")));
        comment.setPost(post);

        User user = (User) req.getSession().getAttribute("currentUser");
        System.out.println(user.getFullName());
        comment.setUser(user);

        comment.setContent(req.getParameter("comment_content"));

        CommentDb.addComment(comment);
        resp.sendRedirect("/post-details?id=" + post.getId());
    }
}
