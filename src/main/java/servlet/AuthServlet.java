package servlet;

import java.io.IOException;

import com.mysql.cj.log.LogFactory;

import dao.UserDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/login")
public class AuthServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static UserDAO userDao = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            User user = userDao.loginUser(username, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userLogin", user);
                session.setAttribute("userRole", user.getRole());
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                request.setAttribute("loginFail", "User name or password is incorrect");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            
        }
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}
