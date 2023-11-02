package don.bitlab.servlets;

import don.bitlab.db.UserDb;
import don.bitlab.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            req.getRequestDispatcher("signin.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter(USER_EMAIL);
        String password = req.getParameter(USER_PASSWORD);
        String redirect = "/sign-in?errorEmail";
        User user = UserDb.getUserByEmail(email);
        if (user != null) {
            redirect = "/sign-in?errorPassword";
            if (password.equals(user.getPassword())) {
                req.getSession().setAttribute("currentUser", user);
                redirect = "/";
            }
        }
        resp.sendRedirect(redirect);
    }
}
