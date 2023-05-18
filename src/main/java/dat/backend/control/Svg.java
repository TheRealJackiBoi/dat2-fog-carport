package dat.backend.control;

import dat.backend.model.services.CarportSVG;
import dat.backend.model.services.SVG;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "SvgServlet", value = "/svg")
public class Svg extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Locale.setDefault(new Locale("US"));

        int width = 200;
        int length = 500;
        int s_length = 0;
        int s_width = 0;

        SVG carport = CarportSVG.createNewSVG(0, 0, 100, 60, "0 0 855 690");
        SVG outerSVG = CarportSVG.createNewSVG(0, 0, 100, 60, "0 0 855 690");

        carport = CarportSVG.addBeams(carport, length, width);
        carport = CarportSVG.addSides(carport, length, width);
        carport = CarportSVG.addPoles(carport, length, width);

        outerSVG = CarportSVG.addLine(outerSVG, length, width);
        outerSVG = CarportSVG.addDashedLines(outerSVG, length, width);
        outerSVG = CarportSVG.addText(outerSVG, length/2, width+40, length);
        outerSVG = CarportSVG.addText(outerSVG, 15, length/2, width);
        carport.addInnerSvg(outerSVG);

        request.setAttribute("svg", carport.toString());
        request.getRequestDispatcher("/svg-drawing.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}