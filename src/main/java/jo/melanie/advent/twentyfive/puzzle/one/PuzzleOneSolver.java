package jo.melanie.advent.twentyfive.puzzle.one;

import jo.melanie.advent.twentyfive.common.PuzzleSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PuzzleOneSolver extends PuzzleSolver {

    final boolean includePassingZeroInCount;

    public PuzzleOneSolver(boolean useTestData, int part) {
        super(useTestData, part);
        this.includePassingZeroInCount = part == 2;
    }

    @Override
    protected String getFilename() {
        return "src/main/resources/one.txt";
    }

    @Override
    protected String solve() {

        final SafeDial safeDial = this.useTestData ?
                createAndTurnTestDial() :
                createAndTurnPuzzleDial();

        return String.valueOf(safeDial.getNumberOfTimesZeroHit());
    }

    private SafeDial createAndTurnTestDial() {

        SafeDial safeDial = new SafeDial(this.includePassingZeroInCount);

        safeDial.turnDial("L68");
        safeDial.turnDial("L30");
        safeDial.turnDial("R48");
        safeDial.turnDial("L5");
        safeDial.turnDial("R60");
        safeDial.turnDial("L55");
        safeDial.turnDial("L1");
        safeDial.turnDial("L99");
        safeDial.turnDial("R14");
        safeDial.turnDial("L82");

        return safeDial;

    }

    private SafeDial createAndTurnPuzzleDial() {

        SafeDial safeDial = new SafeDial(this.includePassingZeroInCount);

        try (BufferedReader br = new BufferedReader(new FileReader(getFilename()))) {
            String line;
            while ((line = br.readLine()) != null) {
                safeDial.turnDial(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file.");
            e.printStackTrace();
        }

        return safeDial;

    }

}
