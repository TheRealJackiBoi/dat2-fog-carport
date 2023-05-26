package dat.backend.control;

import dat.backend.model.config.ApplicationStart;
import dat.backend.model.entities.Order;
import dat.backend.model.entities.User;
import dat.backend.model.exceptions.DatabaseException;
import dat.backend.model.persistence.ConnectionPool;
import dat.backend.model.persistence.OrdersFacade;
import dat.backend.model.persistence.OrdersMapper;
import dat.backend.model.services.CarportSVG;
import dat.backend.model.services.SVG;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Locale;

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

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            Locale.setDefault(new Locale("US"));



            double exactLength = order.getCarportLength()*100;
            double exactWidth = order.getCarportWidth()*100;
            System.out.println(exactLength);
            System.out.println(exactWidth);
            int length = (int) exactLength;
            int width = (int) exactWidth;

            SVG carport = CarportSVG.createNewSvg(0,0,100, 100, "0 0 " + (length + 55) + " " + (width +90));
            SVG outerSvg = CarportSVG.createNewSvg(0,0,100,100, "0 0 " + (length + 55) + " " + (width +90));
            carport = CarportSVG.addBeams(carport, length, width);
            carport = CarportSVG.addSides(carport, length, width);
            carport = CarportSVG.addPoles(carport, length, width);

            outerSvg = CarportSVG.addDashedLines(outerSvg, length, width);
            outerSvg = CarportSVG.addLine(outerSvg, length, width);
            outerSvg = CarportSVG.addText(outerSvg, length/2, width+40,0, exactLength);
            outerSvg = CarportSVG.addText(outerSvg, 0, length/2,90, exactWidth);

            carport.addInnerSvg(outerSvg);

            request.setAttribute("svg", carport.toString());
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

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            Locale.setDefault(new Locale("US"));



            double exactLength = order.getCarportLength()*100;
            double exactWidth = order.getCarportWidth()*100;
            System.out.println(exactLength);
            System.out.println(exactWidth);
            int length = (int) exactLength;
            int width = (int) exactWidth;


            SVG carport = CarportSVG.createNewSvg(0,0,100, 100, "0 0 " + length + 55 + " " + width +90);
            SVG outerSvg = CarportSVG.createNewSvg(0,0,100,100, "0 0 " + length + 55 + " " + width +90);
            carport = CarportSVG.addBeams(carport, length, width);
            carport = CarportSVG.addSides(carport, length, width);
            carport = CarportSVG.addPoles(carport, length, width);

            outerSvg = CarportSVG.addDashedLines(outerSvg, length, width);
            outerSvg = CarportSVG.addLine(outerSvg, length, width);
            outerSvg = CarportSVG.addText(outerSvg, length/2, width+40,0, exactLength);
            outerSvg = CarportSVG.addText(outerSvg, 0, length/2,90, exactWidth);

            carport.addInnerSvg(outerSvg);

            request.setAttribute("svg", carport.toString());
            request.setAttribute("order", order);
            request.getRequestDispatcher("WEB-INF/view_order.jsp").forward(request, response);
        } catch (DatabaseException e) {
            request.setAttribute("errormessage", "Couldn't find your order in our system, please contact support. \n" + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }
}
