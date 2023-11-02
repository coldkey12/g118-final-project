package don.bitlab.servlets;

import don.bitlab.db.CategoryDb;
import don.bitlab.db.CommentDb;
import don.bitlab.db.DbConnector;
import don.bitlab.db.PostDb;
import don.bitlab.models.Category;
import don.bitlab.models.Comment;
import don.bitlab.models.Post;
import don.bitlab.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/post-details")
public class PostDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Post post = PostDb.getPostById(id);

        List<Comment> comments = CommentDb.getCommentsById(id);
        req.setAttribute("comments",comments);

        List<Category> categories = CategoryDb.getAllCategories();
        req.setAttribute("categories", categories);

        req.setAttribute("post", post);
        req.getRequestDispatcher("post-details.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("post_id"));
        Post post = PostDb.getPostById(id);

        Category category = CategoryDb.getCategoryById(
                Long.parseLong(req.getParameter("post_category_id"))
        );
        post.setCategory(category);

        post.setTitle(req.getParameter("post_title"));
        post.setContent(req.getParameter("post_content"));

        PostDb.updatePost(post);

        resp.sendRedirect("/");
    }
}
