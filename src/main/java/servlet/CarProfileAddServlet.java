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

@WebServlet("/add")
public class CarProfileAddServlet extends HttpServlet {
    private CarProfileDAO carProfileDAO = new CarProfileDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // This will simply display the form for adding a new car
    	
        request.getRequestDispatcher("views/carProfile/addcar.jsp").forward(request, response);
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

            // Create a new CarProfile object with the input data
            CarProfile car = new CarProfile(0L, licensePlate, brand, model, year, vin, user, Collections.emptyList(), Collections.emptyList());

            // Save the new car profile to the database
            carProfileDAO.addCar(car);

            // Redirect to the car profiles list page after successful addition
            // If you're using a relative path
            response.sendRedirect("carprofiles");
            
            // OR if you want to use an absolute path for the JSP file:
            // response.sendRedirect("Views/carprofiles/carprofiles.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while adding the car.");
        }
    }

}
