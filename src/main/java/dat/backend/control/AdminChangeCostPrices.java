package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Materials;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.MaterialsFacade;
import dat.backend.model.services.Authentication;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * The type Admin change cost prices.
 */
@WebServlet(name = "AdminChangeCostPrices", value = "/admin_change_costprices")
public class AdminChangeCostPrices extends HttpServlet {

    private ConnectionPool connectionPool;

    @Override
    public void init() throws ServletException
    {
        this.connectionPool = ApplicationStart.getConnectionPool();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Authenticate user role. If the method returns FALSE (user is != admin or salesman) we redirect to index
        if (!Authentication.isRoleAllowed("admin", request) && (!Authentication.isRoleAllowed("salesman", request))) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        // Check if user is logged in, otherwise redirect them to index page
        if (Authentication.isUserLoggedIn(request, connectionPool) == 0) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        List<Materials> list;

        try {
            list = MaterialsFacade.getAllMaterials(connectionPool);

            request.setAttribute("materials", list);
            request.getRequestDispatcher("WEB-INF/admin_change_costprices.jsp").forward(request, response);

        } catch (DatabaseException e) {
            request.setAttribute("errormessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int materialId = Integer.parseInt(request.getParameter("materialid"));
        double newCostPrice = Double.parseDouble(request.getParameter("newcostprice"));

        List<Materials> list;
        if(request.getParameter("newcostprice") != null){
            try{
                MaterialsFacade.adjustCostPrice(materialId, newCostPrice,connectionPool);
                list = MaterialsFacade.getAllMaterials(connectionPool);

                request.setAttribute("materials", list);
                request.getRequestDispatcher("WEB-INF/admin_change_costprices.jsp").forward(request, response);
            } catch (DatabaseException e) {
                request.setAttribute("errormessage", e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("admin_change_costprices").forward(request,response);
        }

    }
}
