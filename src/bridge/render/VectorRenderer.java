package bridge.render;

/** I1: describes shapes as scalable vector primitives. */
public class VectorRenderer implements Renderer {

    @Override
    public String drawCircle(int radius) {
        return "VECTOR circle radius=" + radius;
    }

    @Override
    public String drawSquare(int side) {
        return "VECTOR square side=" + side;
    }
}
