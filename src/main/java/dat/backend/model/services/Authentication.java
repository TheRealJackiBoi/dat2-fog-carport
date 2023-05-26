package dat.backend.model.services;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication {
    // This method checks whether the role taken as argument is allowed to view the page
    public static boolean isRoleAllowed(String role, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // If the user is NOT null (it exists), we first prepare the message to be sent to
        // index, before returning TRUE or FALSE whether the parameter role matches the logged-in user role
        // to determine if it's allowed to view the page or not
        if (user != null) {
            String message = "Du har ikke adgang til denne side";
            request.setAttribute("message", message);
            return user.getRole().equals(role);
        }
        return false;
    }

    // This method will check if the user is assigned an id (if user != null)
    public static int isUserLoggedIn(HttpServletRequest request, ConnectionPool connectionPool) {
       int id = 0;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try {
            // Checks if the user has an ID (is logged in)
            id = user.getId();
            user = UserFacade.getUserById(id, connectionPool);
            request.setAttribute("edituser", user);
            // If user is not logged in, the user ID will return null, so we catch the exception
        } catch (DatabaseException | NullPointerException e) {
            String message = "Venligst log ind før du prøver at tilgå andre sider";
            request.setAttribute("message", message);
            e.printStackTrace();
        }
        return id;
    }
}
