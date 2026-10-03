package bridge.shape;

import bridge.render.Renderer;

/** A2: square defined by its side length. */
public class Square extends Shape {

    private final int side;

    public Square(String id, int side, Renderer implementation) {
        super(id, implementation);
        this.side = side;
    }

    @Override
    public String describeData() {
        return "side=" + side;
    }

    @Override
    public String execute() {
        return renderer().drawSquare(side);
    }
}
