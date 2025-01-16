package servlet;

import java.io.IOException;
import dao.UserDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthServlet
 */
@WebServlet(urlPatterns = {"/login", "/register", "/logout"})
public class AuthServlet extends HttpServlet {

    private final UserDAO userDao = new UserDAO();  // Sử dụng UserDao

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/logout")) {
            logout(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/views" + path + ".jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/login")) {
            login(request, response);
        } else if (path.equals("/register")) {
            register(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.getUserByUsername(username);  // Sử dụng UserDao

        if (user != null && user.getPassword().equals(password)) {
            // Lưu thông tin user vào session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Chuyển hướng đến trang quản lý sau khi đăng nhập thành công
            if (user.getRole().equals("ADMIN")) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else if (user.getRole().equals("STAFF")) {
                response.sendRedirect(request.getContextPath() + "/staff");
            }
        } else {
            request.setAttribute("errorMessage", "Sai tên đăng nhập hoặc mật khẩu");
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String identityCardNumber = request.getParameter("identityCardNumber");

        User existingUser = userDao.getUserByUsername(username);  // Sử dụng UserDao
        if (existingUser != null) {
            request.setAttribute("errorMessage", "Tên đăng nhập đã tồn tại");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole(role);
        newUser.setFullName(fullName);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setIdentityCardNumber(identityCardNumber);

        userDao.addUser(newUser);  // Sử dụng UserDao để thêm người dùng

        response.sendRedirect(request.getContextPath() + "/login");
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
