package dat.backend.model.services;

import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authentication {
    public static boolean isRoleAllowed(String role, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String message = "Du har ikke adgang til denne URL";
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
            id = user.getId();
            user = UserFacade.getUserById(id, connectionPool);
            request.setAttribute("edituser", user);
        } catch (DatabaseException | NullPointerException e) {
            String message = "Venligst log ind før du prøver at tilgå andre sider";
            request.setAttribute("message", message);
            e.printStackTrace();
        }
        return id;
    }
}
