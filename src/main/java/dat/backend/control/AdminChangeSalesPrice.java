package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersFacade;
import dat.backend.model.services.Authentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminChangeSalesPrice", value = "/admin_change_sales_price")
public class AdminChangeSalesPrice extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Authenticate user role
        if (!Authentication.isRoleAllowed("admin", request) && (!Authentication.isRoleAllowed("salesman", request))) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        int orderID = Integer.parseInt(request.getParameter("order_id"));
        Order order = null;

        try{
            order = OrdersFacade.getOrderByOrderId(orderID, connectionPool);
            double salesprice = order.getSalesPrice();
            double costpris = order.getMaterialCost();

            double contributionMargin = salesprice - costpris;
            double degreeOfCoverage = contributionMargin * 100 / salesprice;

            request.setAttribute("degreeOfCoverage", degreeOfCoverage);
            request.setAttribute("contributionMargin", contributionMargin);

            request.setAttribute("order", order);
            request.getRequestDispatcher("WEB-INF/sales_change_salesprice.jsp").forward(request, response);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int orderID = Integer.parseInt(request.getParameter("order_id"));
        int newPrice = Integer.parseInt(request.getParameter("enterSalesPrice"));

        try{
            OrdersFacade.adjustSalesPrice(orderID, newPrice, connectionPool);

            Order order = OrdersFacade.getOrderByOrderId(orderID, connectionPool);

            double salesprice = order.getSalesPrice();
            double costpris = order.getMaterialCost();

            double contributionMargin = salesprice - costpris;
            double degreeOfCoverage = contributionMargin * 100 / salesprice;

            request.setAttribute("degreeOfCoverage", degreeOfCoverage);
            request.setAttribute("contributionMargin", contributionMargin);
            request.setAttribute("order", order);
            request.getRequestDispatcher("WEB-INF/sales_change_salesprice.jsp").forward(request, response);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }
}
