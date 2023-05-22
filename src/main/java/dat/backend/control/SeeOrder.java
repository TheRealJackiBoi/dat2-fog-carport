package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersFacade;
import dat.backend.model.persistence.OrdersMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SeeOrder", value = "/se-din-ordre")
public class SeeOrder extends HttpServlet {
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            request.setAttribute("errormessage", "Didn't get an order id to view");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        try {
            Order order = OrdersFacade.getOrderByOrderId(order_id, connectionPool);
            request.setAttribute("order", order);
            request.getRequestDispatcher("WEB-INF/view_order.jsp").forward(request, response);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", "Couldn't find your order in our system, please contact support. \n" + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

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
            request.setAttribute("errormessage", "Didn't get an order id to view");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        try {
            Order order = OrdersFacade.getOrderByOrderId(order_id, connectionPool);
            request.setAttribute("order", order);
            request.getRequestDispatcher("WEB-INF/view_order.jsp").forward(request, response);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", "Couldn't find your order in our system, please contact support. \n" + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }
}
