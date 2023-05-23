package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditUser", value = "/edituser")
public class EditUser extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Find the user object in session scope
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        // Fetch userId from current session user
        try {
            user = UserFacade.getUserById(id, connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.setAttribute("edituser", user);
        request.getRequestDispatcher("WEB-INF/edituserinfo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // Variable to keep visual track of errors
        boolean error;

        // Save variables for facade methods
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        request.setAttribute("email", user);

        // Saves the current session email as a variable
        String currentEmail = user.getEmail();

        // Fetch user ID for updateUser method
        int id = user.getId();

        // Fetches email list from checkEmail method and sends it to the signup JSP
        List<String> emailList = null;
        try {
            emailList = UserFacade.checkEmail(connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.setAttribute("emailList", emailList);

        try {
            // Check if email already exists or if user is changing elements other than email
            if (!emailList.contains(email) || email.equals(currentEmail)) {
                error = false;
                request.setAttribute("error", false);
                // Update the current user
                UserFacade.updateUser(id, name, zip, city, address, role, connectionPool);
                // Save updated user to sessionscope
                user = UserFacade.getUserById(((User)session.getAttribute("user")).getId(), connectionPool);
                request.getSession().setAttribute("user", user);

                // Remove the "error" attribute if user successfully changes information, so it won't show again
                session.removeAttribute("error");

                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                error = true;
                session.setAttribute("error", true);
                response.sendRedirect("edituser");
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
}
