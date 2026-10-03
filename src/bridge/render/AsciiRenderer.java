package bridge.render;

/** I3: describes shapes as text art; added without touching existing code. */
public class AsciiRenderer implements Renderer {

    @Override
    public String drawCircle(int radius) {
        return "ASCII circle radius=" + radius + " art=(" + "o".repeat(radius) + ")";
    }

    @Override
    public String drawSquare(int side) {
        return "ASCII square side=" + side + " art=[" + "#".repeat(side) + "]";
    }
}
