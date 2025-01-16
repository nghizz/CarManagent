package servlet;

import dao.ScheduleDAO;
import dao.UserDAO;
import dao.CarProfileDAO;
import entity.Schedule;
import entity.User;
import entity.CarProfile;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void init() throws ServletException {
        emf = Persistence.createEntityManagerFactory("your-persistence-unit-name");
        em = emf.createEntityManager();
    }

    @Override
    public void destroy() {
        em.close();
        emf.close();
    }

    // Xử lý GET để hiển thị tất cả lịch hẹn
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        ScheduleDAO scheduleDAO = new ScheduleDAO(em);
        // Lấy danh sách tất cả lịch hẹn
        request.setAttribute("schedules", scheduleDAO.getAllSchedules());
        request.getRequestDispatcher("/WEB-INF/views/scheduleList.jsp").forward(request, response);
    }

    // Xử lý POST để thêm lịch hẹn
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Lấy thông tin từ form
        LocalDateTime serviceDate = LocalDateTime.parse(request.getParameter("serviceDate"));
        String status = request.getParameter("status");
        String carCondition = request.getParameter("carCondition");
        Long userId = Long.parseLong(request.getParameter("userId"));
        Long carId = Long.parseLong(request.getParameter("carId"));
        Long technicianId = Long.parseLong(request.getParameter("technicianId"));

        // Truy vấn các đối tượng User, CarProfile, và Technician từ database
        UserDAO userDAO = new UserDAO(em);
        User user = userDAO.getUserById(userId);

        CarProfileDAO carProfileDAO = new CarProfileDAO(em);
        CarProfile car = carProfileDAO.getCarProfileById(carId);

        User technician = userDAO.getUserById(technicianId); // Kỹ thuật viên là một User

        // Tạo mới đối tượng Schedule và thiết lập các giá trị
        Schedule schedule = new Schedule();
        schedule.setServiceDate(serviceDate);
        schedule.setStatus(status);
        schedule.setCarCondition(carCondition);
        schedule.setUser(user); // Thiết lập người dùng
        schedule.setCar(car); // Thiết lập xe
        schedule.setTechnician(technician); // Thiết lập kỹ thuật viên

        // Thêm lịch hẹn vào cơ sở dữ liệu
        ScheduleDAO scheduleDAO = new ScheduleDAO(em);
        boolean success = scheduleDAO.addSchedule(schedule);

        if (success) {
            response.sendRedirect("ScheduleServlet");
        } else {
            request.setAttribute("errorMessage", "Failed to add schedule");
            request.getRequestDispatcher("/WEB-INF/views/addSchedule.jsp").forward(request, response);
        }
    }
}
