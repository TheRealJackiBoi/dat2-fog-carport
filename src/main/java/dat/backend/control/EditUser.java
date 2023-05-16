package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.persistence.UserMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EditUser", value = "/edituser")
public class EditUser extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    // TODO: FIX methods - it doesn't show current info


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Find the user object in session scope
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String email = user.getEmail();
        try {
            user = UserFacade.getUserByEmail(email, connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.setAttribute("edituser", user);
        request.getRequestDispatcher("WEB-INF/edituserinfo.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String role = request.getParameter("role");

        User user = null;

        try {
            user = UserFacade.getUserByEmail(email, connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        request.setAttribute("email", user);

        // Fetch user ID for updateUser method
        int id = user.getId();

        try {
            // Update the current user
            UserFacade.updateUser(id, email, password, name, zip, city, address, role, connectionPool);

            // Save updated user to sessionscope
            HttpSession session = request.getSession();
            user = UserFacade.getUserByEmail(((User)session.getAttribute("user")).getEmail(), connectionPool);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("edituser");
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        /*
        try {
            UserMapper.updateUser(id, email, password, name, zip, city, address, role, connectionPool);

            //update current user
            HttpSession session = request.getSession();
            user = UserFacade.getUserByEmail(((User)session.getAttribute("user")).getEmail(), connectionPool);
            request.getSession().setAttribute("user", user);
            response.sendRedirect("edituser");
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
         */
    }
}
