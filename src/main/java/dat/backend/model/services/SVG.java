package dat.backend.model.services;

import java.util.Locale;

public class SVG {

        private int x;
        private int y;
        private int height;
        private int width;
        private String viewBox;
        private StringBuilder svgString = new StringBuilder();
        private final static String HEADERTEMPLATE = "<svg x=\"%d\" y=\"%d\" height=\"%d%%\" width=\"%d%%\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
        private final static String RECTTEMPLATE = "<rect x=\"%d\" y=\"%d\" height=\"%f\" width=\"%f\" style=\"stroke:#000000; fill: #ffffff\" />";
        private final String lineTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" stroke=\"black\" stroke-dasharray=\"5,5\"/>";
        private final String arrowTemplate = "    <defs>\n" +
                "\n" +
                "        <marker\n" +
                "                id=\"arrow\"\n" +
                "                markerWidth=\"12\"\n" +
                "                markerHeight=\"12\"\n" +
                "                refX=\"12\"\n" +
                "                refY=\"6\"\n" +
                "                orient=\"auto\">\n" +
                "            <path d=\"M0,0 L12,6 L0,12 L0,0 \" style=\"fill: #000000;\"/>\n" +
                "        </marker>\n" +
                "    </defs>\n" +
                " <line x1=\"50\" y1=\"%d\" x2=\"50\" y2=\"50\"\n" +
                "          style=\"stroke: #000000;\n" +
                "\tmarker-end: url(#arrow);\"/>\n" +
                "\n" +
                "    <line x1=\"50\" y1=\"%d\" x2=\"%d\" y2=\"%d\"\n" +
                "          style=\"stroke: #000000;\n" +
                "\tmarker-end: url(#arrow);\"/>";
        private final String TEXT_TEMPLATE = "<text style=\"text-anchor: middle\" transform=\"translate(%f,%f) rotate(%f)\">%s</text>";

        private final static String ARROWTEMPLATE = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000;\n" +
                "marker-start: url(#beginArrow);\n" + "marker-end: url(#endArrow);\"/>\n";

        public SVG(int x, int y, int height, int width, String viewBox) {
            svgString.append(String.format(HEADERTEMPLATE, x, y, height, width, viewBox));
            this.x = x;
            this.y = y;
            this.height = height;
            this.width = width;
            this.viewBox = viewBox;
        }

        public void addRect(int x, int y, double height, double width){
            svgString.append(String.format(RECTTEMPLATE, x, y, height, width));

        }
        public void addLine(int x1, int y1, int x2, int y2){
            svgString.append(String.format(lineTemplate, x1, y1, x2, y2));

        }
        public void addArrow(int x1, int y1, int x2, int y2){
            svgString.append(String.format(ARROWTEMPLATE, x1, y1, x2, y2));


        }
        public void addText(float translateX, float translateY, float rotate, int text) {
            svgString.append(String.format(Locale.ENGLISH, TEXT_TEMPLATE, translateX, translateY, rotate, text));
        }
        public void addInnerSvg(SVG innerSVGDrawing){
            svgString.append(innerSVGDrawing);
        }

        @Override
        public String toString() {
            return svgString + "</svg>";
        }
}


