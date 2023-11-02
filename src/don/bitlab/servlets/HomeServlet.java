package don.bitlab.servlets;

import don.bitlab.db.DbConnector;
import don.bitlab.db.PostDb;
import don.bitlab.models.Post;
import don.bitlab.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("/sign-in");
            return;
        }
        List<Post> posts = PostDb.getPosts();
        req.setAttribute("posts", posts);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
}
