package dat.backend.control;

import dat.backend.model.entities.Order;
import dat.backend.model.services.CarportSVG;
import dat.backend.model.services.SVG;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "SvgServlet", value = "/svg")
public class SvgServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Locale.setDefault(new Locale("US"));



        double testLenth = 300.12;
        double testWidth = 250.43;
        int length = (int) testLenth;
        int width = (int) testWidth;

        SVG carport = CarportSVG.createNewSvg(0,0,100, 100, "0 0 855 690");
        SVG outerSvg = CarportSVG.createNewSvg(0,0,100,100, "0 0 855 690");
        carport = CarportSVG.addBeams(carport, length, width);
        carport = CarportSVG.addSides(carport, length, width);
        carport = CarportSVG.addPoles(carport, length, width);

        outerSvg = CarportSVG.addDashedLines(outerSvg, length, width);
        outerSvg = CarportSVG.addLine(outerSvg, length, width);
        outerSvg = CarportSVG.addText(outerSvg, length/2, width+40,0, testLenth);
        outerSvg = CarportSVG.addText(outerSvg, 15, length/2,90, testWidth);

        carport.addInnerSvg(outerSvg);

        request.setAttribute("svg1", carport.toString());
        request.getRequestDispatcher("svg-drawing.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}