package jo.melanie.advent.twentyfive.common;

import java.util.Arrays;
import java.util.Optional;

public class AdventSolver {

    private final static int PUZZLE_NUMBER = 3;

    public static void main(String[] args) {

        final boolean useTestData = Arrays.asList(args).contains("-t");
        final int part = getPartFromArgs(args);

        final SolvingFactory solvingFactory = new SolvingFactory();

        final PuzzleSolver solver = solvingFactory.createSolver(PUZZLE_NUMBER, useTestData, part);

        solver.solveAndTime();

    }

    private static int getPartFromArgs(String[] args) {

        final Optional<String> partArg = Arrays
                .stream(args)
                .filter(arg -> arg.startsWith("-p"))
                .findFirst();

        if (partArg.isEmpty()) {
            return  1;
        }

        final String partRequested = partArg.get().substring(2);
        try {
            return Integer.parseInt(partRequested);
        } catch (NumberFormatException e) {
            return 1;
        }
    }

}
