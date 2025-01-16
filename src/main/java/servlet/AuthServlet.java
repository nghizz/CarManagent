package servlet;

import java.io.IOException;

import dao.UserDAO;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AuthServlet")
public class AuthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public AuthServlet() {
        super();
        
        // Tạo EntityManagerFactory và EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("carManagementPU");
        EntityManager em = emf.createEntityManager();
        userDAO = new UserDAO(em);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("login".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userDAO.getUserByUsername(username);

            if (user != null && password.equals(user.getPassword())) {
                // Đăng nhập thành công
                request.getSession().setAttribute("user", user);

                // Chuyển hướng đến trang chính sau khi đăng nhập thành công
                response.sendRedirect("DashboardServlet"); // Thay 'home.jsp' bằng trang bạn muốn chuyển đến
            } else {
                // Đăng nhập thất bại
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                String errorJson = "{ \"error\": \"Tên đăng nhập hoặc mật khẩu không đúng.\" }";
                response.getWriter().write(errorJson);
            }
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hiển thị form đăng nhập
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp");
        dispatcher.forward(request, response);
    }
}
