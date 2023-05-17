package dat.backend.model.services;

import java.util.Locale;

public class SVG
{
    private int x;
    private int y;
    private int height;
    private int width;
    private String viewbox;
    private StringBuilder svgString = new StringBuilder();

    private final static String HEADERTEMPLATE =
            "<svg x=\"%d\" y=\"%d\" height=\"%d%%\" width=\"%d%%\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final static String RECTTEMPLATE =
            "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String LINETEMPLATE = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"black\" stroke-dasharray=\"5,5\"/>";
    private final String TEXT_TEMPLATE = "<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(%f)\">%s</text>";
    private final static String ARROWHEADSTEMPLATE = "<defs>\n" +
            "        <marker id=\"beginArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"0\" refY=\"6\" orient=\"auto\">\n" +
            "            <path d=\"M0,6 L12,0 L12,12 L0,6\" style=\"fill: #000000;\" />\n" +
            "        </marker>\n" +
            "        <marker id=\"endArrow\" markerWidth=\"12\" markerHeight=\"12\" refX=\"12\" refY=\"6\" orient=\"auto\">\n" +
            "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\" />\n" +
            "        </marker>\n" +
            "    </defs>";
    private final static String ARROWLINETEMPLATE = "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:#000000; marker-start: url(#beginArrow); marker-end: url(#endArrow); \"/>";


    public SVG(int x, int y, int height, int width, String viewbox)
    {
        svgString.append(String.format(HEADERTEMPLATE, x, y, height, width, viewbox));
        svgString.append(ARROWHEADSTEMPLATE);
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.viewbox = viewbox;
    }

    public void addRect(int x, int y, double height, double width)
    {
        svgString.append(String.format(RECTTEMPLATE, x, y, height, width));
    }

    public void addLine(int x1, int y1, int x2, int y2){
        svgString.append(String.format(LINETEMPLATE, x1, y1, x2, y2));

    }
    public void addText(float translateX, float translateY, float rotate, int text) {
        svgString.append(String.format(Locale.ENGLISH, TEXT_TEMPLATE, translateX, translateY, rotate, text));
    }
    public void addArrowLine(double x1, double y1, double x2, double y2){
        svgString.append(String.format(ARROWLINETEMPLATE, x1, y1, x2, y2));
    }

    public void addInnerSvg(SVG innerSVGDrawing)
    {
        svgString.append(innerSVGDrawing);
    }

    @Override
    public String toString()
    {
        return svgString + "</svg>";
    }


}