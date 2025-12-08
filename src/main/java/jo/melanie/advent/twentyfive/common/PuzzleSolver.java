package jo.melanie.advent.twentyfive.common;

public abstract class PuzzleSolver {

    protected static final String PUZZLE_INPUT_PATH = "src/main/resources/";
    protected final boolean useTestData;
    protected final int part;

    public PuzzleSolver(boolean useTestData, int part) {
        this.useTestData = useTestData;
        this.part = part;
    }

    protected abstract String getInputFilePath();

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
