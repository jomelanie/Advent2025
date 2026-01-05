package jo.melanie.advent.twentyfive.common;

import jo.melanie.advent.twentyfive.puzzle.five.PuzzleFiveSolver;
import jo.melanie.advent.twentyfive.puzzle.four.PuzzleFourSolver;
import jo.melanie.advent.twentyfive.puzzle.one.PuzzleOneSolver;
import jo.melanie.advent.twentyfive.puzzle.seven.PuzzleSevenSolver;
import jo.melanie.advent.twentyfive.puzzle.six.PuzzleSixSolver;
import jo.melanie.advent.twentyfive.puzzle.three.PuzzleThreeSolver;
import jo.melanie.advent.twentyfive.puzzle.two.PuzzleTwoSolver;

public class SolvingFactory {

    public PuzzleSolver createSolver(int puzzle, boolean useTestData, int part) {

        return switch (puzzle) {
            case 1 -> new PuzzleOneSolver(useTestData, part);
            case 2 -> new PuzzleTwoSolver(useTestData, part);
            case 3 -> new PuzzleThreeSolver(useTestData, part);
            case 4 -> new PuzzleFourSolver(useTestData, part);
            case 5 -> new PuzzleFiveSolver(useTestData, part);
            case 6 -> new PuzzleSixSolver(useTestData, part);
            case 7 -> new PuzzleSevenSolver(useTestData, part);
            default -> throw new UnsupportedOperationException();
        };

    }


}
