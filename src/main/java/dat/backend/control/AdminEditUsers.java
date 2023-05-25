package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Role;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.RoleFacade;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminEditUsers", value = "/kunder")
public class AdminEditUsers extends HttpServlet {
    private static ConnectionPool connectionPool = ApplicationStart.getConnectionPool();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        // If user is not an admin, redirect to index page (no access)
        if (user == null || !user.getRole().equals("admin")) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        List<User> userList = null;
        try {
            userList = UserFacade.getAllUsers(connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        List<Role> roleList = null;
        try {
            roleList = RoleFacade.getAllRoles(connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.setAttribute("roleList", roleList);
        request.setAttribute("userList", userList);

        request.getRequestDispatcher("WEB-INF/admin_edit_users.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        // Fetch parameters from JSP
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String role = request.getParameter("roleSelect");

        try {
            UserFacade.updateRole(role, user_id, connectionPool);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("kunder");
    }
}

