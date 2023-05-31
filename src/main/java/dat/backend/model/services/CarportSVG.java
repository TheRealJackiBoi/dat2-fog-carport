package dat.backend.model.services;


/**
 * The type Carport svg.
 */
public class CarportSVG {
    /**
     * The X start svg.
     */
    int xStartSvg = 0;
    /**
     * The Y start svg.
     */
    int yStartSvg = 0;
    /**
     * The X end svg.
     */
    int xEndSvg = 200;
    /**
     * The Y end svg.
     */
    int yEndSvg = 200;

    /**
     * Create new svg svg.
     *
     * @param x       the x
     * @param y       the y
     * @param height  the height
     * @param width   the width
     * @param viewbox the viewbox
     * @return the svg
     */
    public static SVG createNewSvg(int x, int y, int height, int width, String viewbox){
        return new SVG(x,y,height,width,viewbox);
    }

    /**
     * Add beams svg.
     *
     * @param svg    the svg
     * @param length the length
     * @param width  the width
     * @return the svg
     */
    public static SVG addBeams(SVG svg, int length, int width){
        for(int i = 0; i <= length; i+=50){
            svg.addRect(i+50, 30, width-35, 4.5);
        }
        //svg.addRect(length+250, 30, width-35, 4.5);
        return svg;
    }

    /**
     * Add sides svg.
     *
     * @param svg    the svg
     * @param length the length
     * @param width  the width
     * @return the svg
     */
    public static SVG addSides(SVG svg, int length, int width){
        svg.addRect(50,30, 10, length);
        svg.addRect(50,width-15, 10, length);

        return svg;
    }

    /**
     * Add poles svg.
     *
     * @param svg    the svg
     * @param length the length
     * @param width  the width
     * @return the svg
     */
    public static SVG addPoles(SVG svg, int length, int width){
        svg.addRect(50,30, 10,10);
        svg.addRect(50, width-15, 10,10);
        svg.addRect(length+45,30, 10,10);
        svg.addRect(length+45,width-15, 10,10);

        if (length >= 600){
            svg.addRect(length/2, 30, 10, 10);
            svg.addRect(length/2, width-15, 10,10);
        }
        if (length > 900){
            svg.addRect( (length/2), 30, 10, 10);
            svg.addRect(length/2, width-15, 10,10);

        }

        return svg;
    }

    /**
     * Add shed svg.
     *
     * @param svg        the svg
     * @param length     the length
     * @param width      the width
     * @param shedWidth  the shed width
     * @param shedLength the shed length
     * @return the svg
     */
    public static SVG addShed(SVG svg, int length, int width, int shedWidth, int shedLength){
        svg.addRect(length - shedLength, 65, shedWidth, width);
        return svg;
    }


    /**
     * Add dashed lines svg.
     *
     * @param svg    the svg
     * @param length the length
     * @param width  the width
     * @return the svg
     */
    public static SVG addDashedLines(SVG svg, int length, int width){
        svg.addLine(50, 30, length+50, width-5);
        svg.addLine(length+50, 30, 50, width-5);
        return svg;
    }

    /**
     * Add line svg.
     *
     * @param svg    the svg
     * @param length the length
     * @param width  the width
     * @return the svg
     */
    public static SVG addLine(SVG svg, int length, int width){
        //buttom scale lines
        svg.addLine(50, width+25, length+55, width+25);
        svg.addLine(50, width, 50, width+25);
        svg.addLine(length+55, width, length+55, width+25);

        //side Scale lines
        svg.addLine(15, 30, 15, width);
        svg.addLine(15, 30, 50, 30);
        svg.addLine(15, width-5, 50, width-5);
        return svg;
    }

    /**
     * Add arrows svg.
     *
     * @param svg the svg
     * @param x1  the x 1
     * @param y1  the y 1
     * @param x2  the x 2
     * @param y2  the y 2
     * @return the svg
     */
    public static SVG addArrows(SVG svg, int x1, int y1, int x2, int y2){
        svg.addArrow(x1, y1, x2, y2);
        return svg;
    }

    /**
     * Add text svg.
     *
     * @param svg    the svg
     * @param width  the width
     * @param length the length
     * @param rotate the rotate
     * @param number the number
     * @return the svg
     */
    public static SVG addText(SVG svg, int width, int length, int rotate, double number){
        svg.addText(width, length, rotate, number);
        return svg;
    }
}