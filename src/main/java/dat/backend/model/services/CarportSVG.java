package dat.backend.model.services;

public class CarportSVG
{
    public static SVG createNewSVG(int x, int y, int height, int width, String viewbox)
    {
        return new SVG(x, y, height, width, viewbox);
    }

    public static SVG addBeams(SVG svg, int length, int width)
    {
        for (int i = 0; i <= length; i +=50)
        {
            svg.addRect(i+50, 30, width-35, 4.5);
        }
        return svg;
    }
    public static SVG addSides(SVG svg, int length, int width){
        svg.addRect(50,30, 10, length);
        svg.addRect(50,width-15, 10, length);

        return svg;
    }
    public static SVG addPoles(SVG svg, int length, int width){
        svg.addRect(50,30, 10,10);
        svg.addRect(50, width-15, 10,10);
        svg.addRect(length+45,30, 10,10);
        svg.addRect(length+45,width-15, 10,10);
        return svg;
    }
    public static SVG addDashedLines(SVG svg, int length, int width){
        svg.addLine(50, 30, length+55, width-5);
        svg.addLine(length+55 , 30, 50, width-5);
        return svg;
    }
    public static SVG addLine(SVG svg, int length, int width){
        svg.addLine(30, 30, 30, width);
        svg.addLine(50, width+20, length+55, width+20 );
        return svg;
    }
    public static SVG addText(SVG svg, int width, int length, int number){
        svg.addText(width, length, 0, number);
        return svg;
    }
    public static SVG addArrow(SVG svg, int length, int width){
        svg.addArrowLine(55, 50, 55, 280);
        //svg.addText(45, 165, "rotate(-90)", 230, "cm");

        return svg;
    }


}