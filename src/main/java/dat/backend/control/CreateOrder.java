package dat.backend.control;


import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.OrdersFacade;
import dat.backend.model.persistence.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ordering", urlPatterns = {"/bestil"} )
public class CreateOrder extends HttpServlet
{
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {

            int currentOrderId;
            int userId = user.getId();

            //pulling dimensions from form on ordering.jsp
            double length = Double.parseDouble(request.getParameter("length"));
            double width = Double.parseDouble(request.getParameter("width"));
            double height = Double.parseDouble(request.getParameter("height"));

            //dosent take shed variables in at the moment to keep things simple.
            double s_width = 0;
            double s_length = 0;

            if (request.getParameter("submit").equals("update")) {

                if (session.getAttribute("current_order_id") == null) {
                    try {
                        currentOrderId = OrdersFacade.addOrder(width, length, height, userId, s_width, s_length, connectionPool);
                        session.setAttribute("current_order_id", currentOrderId);

                        Order order = OrdersFacade.getOrderByOrderId(currentOrderId, connectionPool);
                        //forwarding variables to jsp form
                        request.setAttribute("length", order.getCarportLength());
                        request.setAttribute("width", order.getCarportWidth());
                        request.setAttribute("height", order.getCarportHeight());

                        request.getRequestDispatcher("WEB-INF/ordering.jsp").forward(request, response);

                    } catch (DatabaseException e) {
                        request.setAttribute("errormessage", e.getMessage());
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                } else {
                    try {
                        currentOrderId = (int)session.getAttribute("current_order_id");
                        OrdersFacade.updateSpecificOrderById(currentOrderId, width, length, height, connectionPool);
                        Order order = OrdersFacade.getOrderByOrderId(currentOrderId, connectionPool);

                        //forwarding variables to jsp form
                        request.setAttribute("length", order.getCarportLength());
                        request.setAttribute("width", order.getCarportWidth());
                        request.setAttribute("height", order.getCarportHeight());
                        // Forwards to this servlets get method to update the output on the jsp page
                        request.getRequestDispatcher("WEB-INF/ordering.jsp").forward(request, response);
                    }
                    catch (DatabaseException e) {
                        request.setAttribute("errormessage", e.getMessage());
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
            }
            else if (request.getParameter("submit").equals("order")) {
                if (session.getAttribute("current_order_id") == null) {
                    try {
                        currentOrderId = OrdersFacade.addOrder(width, length, height, userId, s_width, s_length, connectionPool);
                        OrdersFacade.changeStatusByOrderIdToOrderPlaced(currentOrderId, connectionPool);
                        session.setAttribute("orderId", currentOrderId);

                        request.setAttribute("confirmed_order_id", currentOrderId);
                        session.setAttribute("current_order_id", null);
                        // TODO: Redirect to some kind of orderconfirmation
                        request.getRequestDispatcher("index.jsp").forward(request, response);

                    } catch (DatabaseException e) {
                        request.setAttribute("errormessage", e.getMessage());
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
                else {
                    try {
                        currentOrderId = (int)session.getAttribute("current_order_id");
                        OrdersFacade.updateSpecificOrderById(currentOrderId, width, length, height, connectionPool);
                        OrdersFacade.changeStatusByOrderIdToOrderPlaced(currentOrderId, connectionPool);

                        request.setAttribute("confirmed_order_id", currentOrderId);
                        session.setAttribute("current_order_id", null);
                        // TODO: Redirect to some kind of orderconfirmation
                        request.getRequestDispatcher("index.jsp").forward(request, response);

                    } catch (DatabaseException e) {
                        request.setAttribute("errormessage", e.getMessage());
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
            }
            else {
                // You shouldn't end up here
                request.setAttribute("errormessage", "Hmmm, der gik noget galt🤔");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
//If order id in session scope, get that, then forward ordervalues for form in jsp, else forward null object in order
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {

            // checking if there's an order in the making
            // if true -> get values from orders and forward to jsp form
            // else forward without values
            if (session.getAttribute("current_order_id") != null) {
                int currentOrderId = (int) session.getAttribute("current_order_id");
                int userId = ((User) session.getAttribute("user")).getId();

                try {
                    Order order = OrdersFacade.getOrderByOrderId(currentOrderId, connectionPool);

                    //forwarding variables to jsp form
                    request.setAttribute("length", order.getCarportLength());
                    request.setAttribute("width", order.getCarportWidth());
                    request.setAttribute("height", order.getCarportHeight());

                    request.getRequestDispatcher("WEB-INF/ordering.jsp").forward(request, response);
                } catch (DatabaseException e) {
                    request.setAttribute("errormessage", e.getMessage());
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            else {
                request.getRequestDispatcher("WEB-INF/ordering.jsp").forward(request, response);
            }

        }
    }

}