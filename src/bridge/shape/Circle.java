package bridge.shape;

import bridge.render.Renderer;

/** A1: circle defined by its radius. */
public class Circle extends Shape {

    private final int radius;

    public Circle(String id, int radius, Renderer implementation) {
        super(id, implementation);
        this.radius = radius;
    }

    @Override
    public String describeData() {
        return "radius=" + radius;
    }

    @Override
    public String execute() {
        return renderer().drawCircle(radius);
    }
}
