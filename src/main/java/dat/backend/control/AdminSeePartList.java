package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Part;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.PartsFacade;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminSeePartList", value = "/admin_stykliste")
public class AdminSeePartList extends HttpServlet {

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
        // If user is not an admin or salesman, redirect to index page (no access)
        if (user == null || user.getRole().equals("customer")) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        int order_id = 0;
        if (request.getParameter("order_id") != null) {
            order_id = Integer.parseInt(request.getParameter("order_id"));
        }
        else {
            request.setAttribute("errormessage", "Didn't get an order id to cancel");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        List<Part> partsList;

        try {
            partsList = PartsFacade.getPartsListByOrderId(order_id, connectionPool);

            request.setAttribute("partslist", partsList);
            request.getRequestDispatcher("WEB-INF/admin_see_partslist.jsp").forward(request, response);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
