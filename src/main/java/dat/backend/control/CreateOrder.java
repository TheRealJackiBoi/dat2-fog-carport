package dat.backend.control;


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

@WebServlet(name = "ordering", urlPatterns = {"/ordering"} )
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
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null){
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }else {
            int orderId;
            int userId = user.getId();
            double length = Double.parseDouble(request.getParameter("length"));
            double width = Double.parseDouble(request.getParameter("width"));
            double height = Double.parseDouble(request.getParameter("height"));

            //dosent take shed variables in at the moment to keep things simple.
            double s_width = 0;
            double s_length = 0;

            try {
                orderId = OrdersFacade.addOrder(width, length, height, userId, s_width, s_length, connectionPool);
                session.setAttribute("orderId", orderId);

                //needs to be send to the right path once that is created
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (DatabaseException e) {
                request.setAttribute("errormessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

        }

    }

}