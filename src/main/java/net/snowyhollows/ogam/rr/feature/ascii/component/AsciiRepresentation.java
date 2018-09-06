package net.snowyhollows.ogam.rr.feature.ascii.component;

public interface AsciiRepresentation {

    final class Color {
        final public static Color RED = new Color("red");
        final public static Color YELLOW = new Color("yellow");
        final public static Color CYAN = new Color("cyan");
        final public static Color GREEN = new Color("green");
        final public static Color WHITE = new Color("white");
        final public static Color BLACK = new Color("black");
        public static final Color GREY = new Color("grey");

        private final String name;

        public Color(String red) {
            this.name = red;
        }

        public String name() {
            return name;
        }
    }
    Color getColor();
    Color getBackgroundColor();
    char getChar();
}
