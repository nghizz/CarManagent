package servlet;

import java.io.IOException;
import java.util.Collections;

import dao.CarProfileDAO;
import entity.CarProfile;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit")
public class CarProfileEditServlet extends HttpServlet {
    private CarProfileDAO carProfileDAO = new CarProfileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long carId = Long.parseLong(request.getParameter("carId"));
            CarProfile car = carProfileDAO.getCarById(carId);
            if (car != null) {
                request.setAttribute("car", car);
                request.getRequestDispatcher("views/carProfile/editcar.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Car profile not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while fetching the car profile for editing.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

            // Create a new CarProfile with updated data
            CarProfile car = new CarProfile(carId, licensePlate, brand, model, year, vin, user, Collections.emptyList(), Collections.emptyList());

            // Update the car profile in the database
            carProfileDAO.updateCar(car);

            // Redirect to the car profiles list page after successful update
            response.sendRedirect("carprofile");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while updating the car.");
        }
    }
}
