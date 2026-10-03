import bridge.render.AsciiRenderer;
import bridge.render.RasterRenderer;
import bridge.render.Renderer;
import bridge.render.VectorRenderer;
import bridge.shape.Circle;
import bridge.shape.Shape;
import bridge.shape.Square;

/** Client: runs the demonstration checks against the real application. */
public class Main {

    private static final int CIRCLE_RADIUS = 2;
    private static final int SQUARE_SIDE = 3;

    private static int total = 0;
    private static int passed = 0;

    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--demo")) {
            runDemo();
        } else {
            System.out.println("Usage: java -cp out Main --demo");
        }
    }

    private static void runDemo() {
        runCombinationChecks();
        checkRuntimeSwitch();
        runExtensionChecks();
        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
        if (passed != total) {
            System.exit(1);
        }
    }

    private static void runCombinationChecks() {
        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();
        checkCombination("T1", new Circle("C-1", CIRCLE_RADIUS, vector), vector, "VECTOR circle radius=2");
        checkCombination("T2", new Circle("C-1", CIRCLE_RADIUS, raster), raster,
                "RASTER circle radius=2 bitmap=5x5px");
        checkCombination("T3", new Square("S-1", SQUARE_SIDE, vector), vector, "VECTOR square side=3");
        checkCombination("T4", new Square("S-1", SQUARE_SIDE, raster), raster,
                "RASTER square side=3 bitmap=3x3px");
    }

    private static void runExtensionChecks() {
        Renderer ascii = new AsciiRenderer();
        checkCombination("T6", new Circle("C-1", CIRCLE_RADIUS, ascii), ascii, "ASCII circle radius=2 art=(oo)");
        checkCombination("T7", new Square("S-1", SQUARE_SIDE, ascii), ascii, "ASCII square side=3 art=[###]");
    }

    private static void checkCombination(String checkId, Shape shape, Renderer renderer, String expected) {
        String actual = shape.execute();
        String participants = shape.getClass().getSimpleName() + " + " + renderer.getClass().getSimpleName();
        report(checkId, actual.equals(expected), participants, "result=" + actual, "expected=" + expected);
    }

    private static void checkRuntimeSwitch() {
        Renderer vector = new VectorRenderer();
        Shape original = new Circle("C-5", CIRCLE_RADIUS, vector);
        String idBefore = original.getId();
        String dataBefore = original.describeData();
        String before = original.execute();

        Shape sameReference = original;
        sameReference.setImplementation(new RasterRenderer());
        String after = sameReference.execute();

        boolean sameObject = original == sameReference;
        boolean stateUnchanged = idBefore.equals(sameReference.getId())
                && dataBefore.equals(sameReference.describeData());
        boolean resultsCorrect = before.equals("VECTOR circle radius=2")
                && after.equals("RASTER circle radius=2 bitmap=5x5px");

        report("T5", sameObject && stateUnchanged && resultsCorrect,
                "sameObject=" + sameObject + " | stateUnchanged=" + stateUnchanged,
                "before=" + before + " | after=" + after,
                "expected before=VECTOR circle radius=2 | after=RASTER circle radius=2 bitmap=5x5px");
    }

    private static void report(String checkId, boolean ok, String participants, String actual, String expected) {
        total++;
        if (ok) {
            passed++;
        }
        System.out.println(checkId + " " + (ok ? "PASS" : "FAIL") + " | " + participants + " | " + actual);
        if (!ok) {
            System.out.println("   " + expected);
        }
    }
}
