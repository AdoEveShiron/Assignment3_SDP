package bridge.render;

/** Implementor: low-level drawing operations that every renderer must provide. */
public interface Renderer {

    String drawCircle(int radius);

    String drawSquare(int side);
}
