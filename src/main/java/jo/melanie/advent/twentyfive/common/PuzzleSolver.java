package jo.melanie.advent.twentyfive.common;

public abstract class PuzzleSolver {

    protected final boolean useTestData;
    protected final int part;

    public PuzzleSolver(boolean useTestData, int part) {
        this.useTestData = useTestData;
        this.part = part;
    }

    protected abstract String getFilename();

    protected abstract String solve();

    public void solveAndTime() {

        final long startTime = System.currentTimeMillis();

        final String solution = solve();

        final long endTime = System.currentTimeMillis();
        final long totalTimeToSolve = endTime - startTime;

        final String output = "Solved in " + totalTimeToSolve + "ms\n" +
                "\n" +
                "SOLUTION: " + solution;
        System.out.println(output);

    }

}
