package bridge.shape;

import bridge.render.Renderer;
import java.util.Objects;

/** Abstraction: a shape with an identity, drawn through a replaceable Renderer. */
public abstract class Shape {

    private final String id;
    private Renderer implementation; // the bridge to the Renderer hierarchy

    protected Shape(String id, Renderer implementation) {
        this.id = Objects.requireNonNull(id, "id");
        this.implementation = Objects.requireNonNull(implementation, "implementation");
    }

    public String getId() {
        return id;
    }

    /** Domain data of the shape, for example "radius=2". */
    public abstract String describeData();

    /** Draws the shape using the current renderer. */
    public abstract String execute();

    public void setImplementation(Renderer implementation) {
        this.implementation = Objects.requireNonNull(implementation, "implementation");
    }

    protected Renderer renderer() {
        return implementation;
    }
}
