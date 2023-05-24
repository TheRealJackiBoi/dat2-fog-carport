package dat.backend.model.services;


public class CarportSVG {

    public static SVG createNewSvg(int x, int y, int height, int width, String viewbox){
        return new SVG(x,y,height,width,viewbox);
    }
    public static SVG addBeams(SVG svg, int length, int width){
        for(int i = 0; i <= length; i+=50){
            svg.addRect(i, 30, width-35, 4.5);
        }
        //svg.addRect(length+250, 30, width-35, 4.5);
        return svg;
    }
    public static SVG addSides(SVG svg, int length, int width){
        svg.addRect(0,30, 10, length);
        svg.addRect(0,width-15, 10, length);

        return svg;
    }
    public static SVG addPoles(SVG svg, int length, int width){
        svg.addRect(0,30, 10,10);
        svg.addRect(0, width-15, 10,10);
        svg.addRect(length-5,30, 10,10);
        svg.addRect(length-5,width-15, 10,10);

        if (length > 300){
            svg.addRect(length/2, 30, 10, 10);
            svg.addRect(length/2, width-15, 10,10);
        }
        if (length > 600){
            svg.addRect(length/2, 30, 10, 10);
            svg.addRect(length/2, width-15, 10,10);

        }
        if (length > 900){
            svg.addRect(length/2, 30, 10, 10);
            svg.addRect(length/2, width-15, 10,10);
        }
        return svg;
    }
    public static SVG addShed(SVG svg, int length, int width, int shedWidth, int shedLength){
        svg.addRect(length - shedLength, 65, shedWidth, width);
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
    public static SVG addArrows(SVG svg, int x1, int y1, int x2, int y2){
        svg.addArrow(x1, y1, x2, y2);

        return svg;

    }
    public static SVG addText(SVG svg, int width, int length, int rotate, double number){
        svg.addText(width, length, rotate, number);
        return svg;
    }




}