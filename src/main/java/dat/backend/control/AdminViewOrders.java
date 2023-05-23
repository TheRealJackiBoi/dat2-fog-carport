package dat.backend.control;


import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.config.ApplicationStart;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.OrdersFacade;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.UserFacade;

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
public class AdminViewOrders extends HttpServlet
{
    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Order> orders;

        if(user == null){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else {

            try {
                if (request.getParameter("search") != null){
                    int inputString = Integer.parseInt(request.getParameter("search"));
                    request.setAttribute("userIdSearch", inputString);
                }

                orders = OrdersFacade.getAllOrdersPlusEmail(connectionPool);
                request.setAttribute("list", orders);
                request.getRequestDispatcher("WEB-INF/admin-view-orders.jsp").forward(request, response);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        }


    }

}