package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Part;
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
        //gets the orderId by the button from the previous page
        int orderId = (int) request.getAttribute("button");
        List<Part> partsList;

        try {
            partsList = PartsFacade.getPartsListByOrderId(orderId, connectionPool);

            request.setAttribute("partslist", partsList);
            request.getRequestDispatcher("").forward(request, response);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
