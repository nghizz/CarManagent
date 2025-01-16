package servlet;

import dao.CustomerDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            long userId = Long.parseLong(request.getParameter("userId"));
            User customer = customerDAO.getAllCustomers().stream()
                                        .filter(c -> c.getUserId() == userId)
                                        .findFirst()
                                        .orElse(null);
            if (customer != null) {
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("/WEB-INF/views/editCustomer.jsp").forward(request, response);
            } else {
                // Nếu không tìm thấy khách hàng, chuyển về danh sách
                response.sendRedirect("/WEB-INF/views/customers");
            }
        } else if ("delete".equals(action)) {
            long userId = Long.parseLong(request.getParameter("userId"));
            customerDAO.deleteCustomer(userId);
            response.sendRedirect("/customers");
        } else if ("search".equals(action)) {
            String name = request.getParameter("name");
            List<User> customers = customerDAO.searchCustomers(name);
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/WEB-INF/views/listCustomers.jsp").forward(request, response);
        } else {
            List<User> customers = customerDAO.getAllCustomers();
            request.setAttribute("customers", customers);
            request.getRequestDispatcher("/WEB-INF/views/listCustomers.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            User customer = new User();
            customer.setUsername(request.getParameter("username"));
            customer.setFullName(request.getParameter("fullName"));
            customer.setPhoneNumber(request.getParameter("phone"));
            customerDAO.addCustomer(customer);
            response.sendRedirect("/WEB-INF/views/customers");
        } else if ("update".equals(action)) {
            User customer = new User();
            customer.setUserId(Long.parseLong(request.getParameter("userId")));
            customer.setFullName(request.getParameter("fullName"));
            customer.setPhoneNumber(request.getParameter("phone"));
            customerDAO.updateCustomer(customer);
            response.sendRedirect("/WEB-INF/views/customers");
        }
    }
}
