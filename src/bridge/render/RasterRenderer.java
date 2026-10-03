package bridge.render;

/** I2: describes shapes as a bitmap of pixels. */
public class RasterRenderer implements Renderer {

    @Override
    public String drawCircle(int radius) {
        int diameter = 2 * radius + 1;
        return "RASTER circle radius=" + radius + " bitmap=" + bitmapSize(diameter);
    }

    @Override
    public String drawSquare(int side) {
        return "RASTER square side=" + side + " bitmap=" + bitmapSize(side);
    }

    private String bitmapSize(int pixels) {
        return pixels + "x" + pixels + "px";
    }
}
