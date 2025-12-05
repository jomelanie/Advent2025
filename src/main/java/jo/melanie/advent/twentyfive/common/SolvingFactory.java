package jo.melanie.advent.twentyfive.common;

import jo.melanie.advent.twentyfive.puzzle.one.PuzzleOneSolver;
import jo.melanie.advent.twentyfive.puzzle.three.PuzzleThreeSolver;
import jo.melanie.advent.twentyfive.puzzle.two.PuzzleTwoSolver;

public class SolvingFactory {

    public PuzzleSolver createSolver(int puzzle, boolean useTestData, int part) {

        return switch (puzzle) {
            case 1 -> new PuzzleOneSolver(useTestData, part);
            case 2 -> new PuzzleTwoSolver(useTestData, part);
            case 3 -> new PuzzleThreeSolver(useTestData, part);
            default -> throw new UnsupportedOperationException();
        };

    }


}
