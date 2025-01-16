package servlet;

import java.io.IOException;

import dao.DashboardDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private DashboardDAO dashboardDAO = new DashboardDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy thông tin user từ session
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            // Chưa đăng nhập, chuyển hướng đến trang đăng nhập
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        try {
            // Lấy dữ liệu từ database
            int newAppointments = dashboardDAO.getNewAppointments();
            double revenue = dashboardDAO.getRevenue();
            int totalCars = dashboardDAO.getTotalCars();

            // Lưu dữ liệu vào request attributes
            request.setAttribute("newAppointments", newAppointments);
            request.setAttribute("revenue", revenue);
            request.setAttribute("totalCars", totalCars);
            request.setAttribute("user", user); // Thêm thông tin người dùng vào request

            // Chuyển hướng đến trang dashboard.jsp
            request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi lấy dữ liệu.");
        }
    }
}
