package dat.backend.control;


import dat.backend.model.entities.Order;
import dat.backend.model.entities.Status;
import dat.backend.model.entities.User;
import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.OrdersFacade;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.StatusFacade;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.services.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "adminViewOrders", urlPatterns = {"/admin-view-orders"} )
public class AdminViewOrders extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Authenticate user role. If the method returns FALSE (user is != admin or salesman) we redirect to index
        if (!Authentication.isRoleAllowed("admin", request) && (!Authentication.isRoleAllowed("salesman", request))) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        // Check if user is logged in, otherwise redirect them to index page
        if (Authentication.isUserLoggedIn(request, connectionPool) == 0) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Order> orders;
        List<Status> statuslist;

        if(user == null) {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {

            try {
                if (request.getParameter("search") != null){
                    int inputString = Integer.parseInt(request.getParameter("search"));
                    request.setAttribute("userIdSearch", inputString);
                }

                orders = OrdersFacade.getAllOrdersPlusEmail(connectionPool);
                statuslist = StatusFacade.getAllStatus(connectionPool);
                request.setAttribute("list", orders);
                request.setAttribute("statuslist", statuslist);
                request.getRequestDispatcher("WEB-INF/admin-view-orders.jsp").forward(request, response);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}