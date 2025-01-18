package servlet;

import java.io.IOException;
import java.util.List;

import dao.SparePartDAO;
import entity.SpareParts;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SparePartServlet")
public class SparePartServlet extends HttpServlet {
    private SparePartDAO sparePartDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        sparePartDAO = new SparePartDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy danh sách các phụ tùng từ cơ sở dữ liệu
        List<SpareParts> spareParts = sparePartDAO.getAllSpareParts();
        request.setAttribute("spareParts", spareParts);
        // Chuyển hướng đến trang hiển thị danh sách phụ tùng
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/sparePart/spare_part_list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin từ form
        String sparePartIdStr = request.getParameter("id");
        String quantityStr = request.getParameter("quantity");
        
        if (sparePartIdStr != null && quantityStr != null) {
            try {
                int sparePartId = Integer.parseInt(sparePartIdStr);
                int quantity = Integer.parseInt(quantityStr);
                
                // Cập nhật số lượng phụ tùng trong cơ sở dữ liệu
                boolean isUpdated = sparePartDAO.updateStock(sparePartId, quantity);
                
                if (isUpdated) {
                    response.sendRedirect("views/sparePart/spare_part_list.jsp");
                } else {
                    // Trả về thông báo lỗi nếu không cập nhật thành công
                    response.getWriter().println("Error updating stock.");
                }
            } catch (NumberFormatException e) {
                // Xử lý lỗi khi đầu vào không phải là số
                response.getWriter().println("Invalid input data.");
            }
        } else {
            // Trả về thông báo nếu thiếu tham số
            response.getWriter().println("Missing input data.");
        }
    }
}
