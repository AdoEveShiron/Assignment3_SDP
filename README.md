# Assignment 3 – Bridge Pattern (Topic A: Drawing)

| Field | Value |
|---|---|
| Name | **Dinmukhammed Dauletkhan** |
| Group | **SE-2538** |
| Topic | **A – Drawing** (Shape / Renderer) |
| Repository | https://github.com/AdoEveShiron/Assignment3_SDP |
| Base commit (working I1/I2 version) | `2a52256882e9de0688942b1a9e75637fedbcdaee` |

Course: ShP-2216 Software Design Patterns, 2026-2027. JDK 17+, JDK classes only.

## Role map

| Role | Class | Source path |
|---|---|---|
| Abstraction | `Shape` | `src/bridge/shape/Shape.java` |
| A1 (refined abstraction) | `Circle` (radius 2) | `src/bridge/shape/Circle.java` |
| A2 (refined abstraction) | `Square` (side 3) | `src/bridge/shape/Square.java` |
| Implementor | `Renderer` | `src/bridge/render/Renderer.java` |
| I1 | `VectorRenderer` | `src/bridge/render/VectorRenderer.java` |
| I2 | `RasterRenderer` | `src/bridge/render/RasterRenderer.java` |
| I3 (extension) | `AsciiRenderer` | `src/bridge/render/AsciiRenderer.java` |
| Client | `Main` | `src/Main.java` |

## Where to find the key elements

- **Bridge field:** `private Renderer implementation;` in `Shape.java` (set via the constructor).
- **`execute()`:** declared abstract in `Shape`; implemented in `Circle.execute()` (calls `drawCircle`) and `Square.execute()` (calls `drawSquare`).
- **`setImplementation(Renderer)`:** `Shape.java`.
- **T5 check:** `Main.checkRuntimeSwitch()` in `src/Main.java`.

## Build and run

```
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

## Expected results

```
T1 PASS | Circle + VectorRenderer | result=VECTOR circle radius=2
T2 PASS | Circle + RasterRenderer | result=RASTER circle radius=2 bitmap=5x5px
T3 PASS | Square + VectorRenderer | result=VECTOR square side=3
T4 PASS | Square + RasterRenderer | result=RASTER square side=3 bitmap=3x3px
T5 PASS | sameObject=true | stateUnchanged=true | before=VECTOR circle radius=2 | after=RASTER circle radius=2 bitmap=5x5px
T6 PASS | Circle + AsciiRenderer | result=ASCII circle radius=2 art=(oo)
T7 PASS | Square + AsciiRenderer | result=ASCII square side=3 art=[###]
SUMMARY: 7/7 PASS
```

PASS/FAIL is computed by comparing the actual `execute()` result (and, for T5, `==`, the ID and the domain data) with expected values. A failing check also prints its expected value, and the process exits with code 1.

## Extension

`extension.diff` is `git diff 2a52256882e9de0688942b1a9e75637fedbcdaee HEAD -- src`. Only `AsciiRenderer.java` (new) and `Main.java` (T6–T7) changed in `src/`.
