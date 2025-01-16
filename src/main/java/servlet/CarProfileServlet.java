package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Collections;
import dao.CarProfileDAO;
import entity.CarProfile;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/carprofile")
public class CarProfileServlet extends HttpServlet {
    private CarProfileDAO carProfileDAO = new CarProfileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<CarProfile> carProfiles = carProfileDAO.getAllCars();
            request.setAttribute("carProfiles", carProfiles);
            request.getRequestDispatcher("views/carProfile/carprofiles.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching car profiles.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String licensePlate = request.getParameter("licensePlate");
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            int year = Integer.parseInt(request.getParameter("year"));
            String vin = request.getParameter("vin");
            long userId = Long.parseLong(request.getParameter("userId"));
            
            // Fetch User by ID from DB
            User user = carProfileDAO.getUserById(userId);
            if (user == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found.");
                return;
            }

            CarProfile car = new CarProfile(0L, licensePlate, brand, model, year, vin, user, Collections.emptyList(), Collections.emptyList());

            carProfileDAO.addCar(car);
            response.sendRedirect("carprofile");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the car.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long carId = Long.parseLong(request.getParameter("carId"));
            String licensePlate = request.getParameter("licensePlate");
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            int year = Integer.parseInt(request.getParameter("year"));
            String vin = request.getParameter("vin");
            long userId = Long.parseLong(request.getParameter("userId"));

            // Fetch User by ID from DB
            User user = carProfileDAO.getUserById(userId);
            if (user == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User not found.");
                return;
            }

            CarProfile car = new CarProfile(carId, licensePlate, brand, model, year, vin, user, Collections.emptyList(), Collections.emptyList());

            carProfileDAO.updateCar(car);
            response.sendRedirect("carprofile");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the car.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long carId = Long.parseLong(request.getParameter("carId"));
            carProfileDAO.deleteCar(carId);
            response.sendRedirect("carprofile");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while deleting the car.");
        }
    }
}
