package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * The type Customer see order.
 */
@WebServlet(name = "CustomerSeeOrder", urlPatterns = {"/CustomerSeeOrder"})
public class CustomerSeeOrder extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Order> customer_orders;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {

            try {
                customer_orders = OrdersFacade.getOrdersByUserId(user.getId(), connectionPool);

                request.setAttribute("customer_orders", customer_orders);
                request.getRequestDispatcher("WEB-INF/customer_see_orders.jsp").forward(request, response);
            } catch (DatabaseException e) {
                request.setAttribute("errormessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    //For deleting an order with the "Annuller" button on each order

}
