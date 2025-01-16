package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import dao.StaffDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {
    private StaffDAO staffDAO;

    @Override
    public void init() {
        Connection conn = DBUtils.getConnection();
        staffDAO = new StaffDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                    listStaff(req, resp);
                    break;
                case "search":
                    searchStaff(req, resp);
                    break;
                case "delete":
                    deleteStaff(req, resp);
                    break;
                default:
                    listStaff(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    addStaff(req, resp);
                    break;
                case "update":
                    updateStaff(req, resp);
                    break;
                default:
                    listStaff(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listStaff(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<User> staffList = staffDAO.getAllStaff();
        req.setAttribute("staffList", staffList);
        req.getRequestDispatcher("/views/listStaff.jsp").forward(req, resp);
    }

    private void searchStaff(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String keyword = req.getParameter("keyword");
        List<User> staffList = staffDAO.searchStaff(keyword);
        req.setAttribute("staffList", staffList);
        req.getRequestDispatcher("/views/listStaff.jsp").forward(req, resp);
    }

    private void addStaff(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User staff = new User();
        staff.setUsername(req.getParameter("username"));
        staff.setPassword(req.getParameter("password"));
        staff.setFullName(req.getParameter("fullName"));
        staff.setPhoneNumber(req.getParameter("phoneNumber"));
        staff.setIdentityCardNumber(req.getParameter("identityCardNumber"));
        staff.setRole("staff");
        staff.setCreatedAt(LocalDateTime.now());
        staff.setUpdatedAt(LocalDateTime.now());

        staffDAO.addStaff(staff);
        resp.sendRedirect("StaffServlet?action=list");
    }

    private void updateStaff(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("userId"));
        User staff = new User();
        staff.setUserId(id);
        staff.setFullName(req.getParameter("fullName"));
        staff.setPhoneNumber(req.getParameter("phoneNumber"));
        staff.setIdentityCardNumber(req.getParameter("identityCardNumber"));
        staff.setUpdatedAt(LocalDateTime.now());

        staffDAO.updateStaff(staff);
        resp.sendRedirect("StaffServlet?action=list");
    }

    private void deleteStaff(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("userId"));
        staffDAO.deleteStaff(id);
        resp.sendRedirect("StaffServlet?action=list");
    }
}
