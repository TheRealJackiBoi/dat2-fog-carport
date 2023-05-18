package dat.backend.control;

import dat.backend.model.entities.User;
import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "signup", urlPatterns = {"/signup"})
public class SignUp extends HttpServlet {
    private ConnectionPool connectionPool;


    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.setAttribute("user", null); // invalidating user object in session scope
        boolean error;

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        int zip = Integer.parseInt(request.getParameter("zip"));
        String city = request.getParameter("city");
        String address = request.getParameter("address");

        try {
            // Fetches email list from checkEmail method and sends it to the signup JSP
            List<String> emailList = UserFacade.checkEmail(connectionPool);
            request.setAttribute("emailList", emailList);
            if (!emailList.contains(email)) {
                error = false;
                UserFacade.createUser(email, password, name, zip, city, address, "customer", connectionPool);
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            } else {
                error = true;
                session.setAttribute("error", true);
                response.sendRedirect("signup.jsp");
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp");
        }
    }
}
