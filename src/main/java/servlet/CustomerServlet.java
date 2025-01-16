package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import dao.CustomerDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    private CustomerDAO customerDAO;

    @Override
    public void init() {
        Connection conn = DBUtils.getConnection();
        customerDAO = new CustomerDAO();
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
                    listCustomers(req, resp);
                    break;
                case "search":
                    searchCustomers(req, resp);
                    break;
                case "delete":
                    deleteCustomer(req, resp);
                    break;
                default:
                    listCustomers(req, resp);
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
                    addCustomer(req, resp);
                    break;
                case "update":
                    updateCustomer(req, resp);
                    break;
                default:
                    listCustomers(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listCustomers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<User> customers = customerDAO.getAllCustomers();
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/views/listCustomers.jsp").forward(req, resp);
    }

    private void searchCustomers(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String keyword = req.getParameter("keyword");
        List<User> customers = customerDAO.searchCustomers(keyword);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/views/listCustomers.jsp").forward(req, resp);
    }

    private void addCustomer(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User customer = new User();
        customer.setUsername(req.getParameter("username"));
        customer.setPassword(req.getParameter("password"));
        customer.setFullName(req.getParameter("fullName"));
        customer.setPhoneNumber(req.getParameter("phoneNumber"));
        customer.setIdentityCardNumber(req.getParameter("identityCardNumber"));
        customer.setRole("customer");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());

        customerDAO.addCustomer(customer);
        resp.sendRedirect("CustomerServlet?action=list");
    }

    private void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("userId"));
        User customer = new User();
        customer.setUserId(id);
        customer.setFullName(req.getParameter("fullName"));
        customer.setPhoneNumber(req.getParameter("phoneNumber"));
        customer.setIdentityCardNumber(req.getParameter("identityCardNumber"));
        customer.setUpdatedAt(LocalDateTime.now());

        customerDAO.updateCustomer(customer);
        resp.sendRedirect("CustomerServlet?action=list");
    }

    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int id = Integer.parseInt(req.getParameter("userId"));
        customerDAO.deleteCustomer(id);
        resp.sendRedirect("CustomerServlet?action=list");
    }
}
