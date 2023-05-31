package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * The type Cancel order.
 */
@WebServlet(name = "cancelorder", value = "/annuller_order")
public class CancelOrder extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        int order_id = 0;
        if (request.getParameter("order_id") != null) {
            order_id = Integer.parseInt(request.getParameter("order_id"));
        }
        else if (request.getAttribute("order_id") != null) {
            order_id = (int) request.getAttribute("order_id");
        }
        else {
            request.setAttribute("errormessage", "Didn't get an order id to cancel");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        if (session.getAttribute("current_order_id") != null) {
            session.setAttribute("current_order_id", null);
        }

        try {
            OrdersFacade.changeStatusByOrderIdToCancelled(order_id, connectionPool);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
