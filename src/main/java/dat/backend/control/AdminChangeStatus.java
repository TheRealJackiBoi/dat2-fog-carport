package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersFacade;
import dat.backend.model.persistence.UserFacade;
import dat.backend.model.services.Authentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * The type Admin change status.
 */
@WebServlet(name = "AdminChangeStatus", value = "/admin-change-status")
public class AdminChangeStatus extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Authenticate user role. If the method returns FALSE (user is != admin or salesman) we redirect to index
        if (!Authentication.isRoleAllowed("admin", request) && (!Authentication.isRoleAllowed("salesman", request))) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        // Check if user is logged in, otherwise redirect them to index page
        if (Authentication.isUserLoggedIn(request, connectionPool) == 0) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        // Fetch parameters from JSP
        int order_id = Integer.parseInt(request.getParameter("order_id"));
        String status = request.getParameter("statusselect");

        try {
            if (status.equals("Cancelled")) {
                OrdersFacade.changeStatusByOrderIdToCancelled(order_id, connectionPool);
            }
            else if (status.equals("Accepted")) {
                OrdersFacade.changeStatusByOrderIdToAccepted(order_id, connectionPool);
            }
            else if (status.equals("Pending")) {
                OrdersFacade.changeStatusByOrderIdToPending(order_id, connectionPool);
            }
            else if (status.equals("Order_placed")) {
                OrdersFacade.changeStatusByOrderIdToOrderPlaced(order_id, connectionPool);
            }
            else if (status.equals("Creating")) {
                OrdersFacade.changeStatusByOrderIdToOrderCreating(order_id, connectionPool);
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("admin-view-orders");

    }
}
