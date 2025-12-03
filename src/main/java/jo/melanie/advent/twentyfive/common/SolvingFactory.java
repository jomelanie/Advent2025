package jo.melanie.advent.twentyfive.common;

import jo.melanie.advent.twentyfive.puzzle.one.PuzzleOneSolver;
import jo.melanie.advent.twentyfive.puzzle.two.PuzzleTwoSolver;

public class SolvingFactory {

    public PuzzleSolver createSolver(int puzzle, boolean useTestData, int part) {

        switch (puzzle) {
            case 1:
                return new PuzzleOneSolver(useTestData, part);
            case 2:
                return new PuzzleTwoSolver(useTestData, part);
            default:
                throw new UnsupportedOperationException();
        }

    }


}
