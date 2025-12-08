package jo.melanie.advent.twentyfive.puzzle.five;

import jo.melanie.advent.twentyfive.common.PuzzleSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PuzzleFiveSolver extends PuzzleSolver {

    public PuzzleFiveSolver(boolean useTestData, int part) {
        super(useTestData, part);
    }

    @Override
    protected String getInputFilePath() {
        return PUZZLE_INPUT_PATH + "five.txt";
    }

    @Override
    protected String solve() {

        final ArrayList<FreshFoodIdRange> freshIds = this.useTestData ? getTestFreshRanges() : getPuzzleFreshRanges();

        final FreshFoodDatabase foodDatabase = new FreshFoodDatabase(freshIds);

        final int numberOfFreshIdsInStock = this.useTestData ?
                findTotalFreshIdsFromTestData(foodDatabase)
                : findTotalFreshIdsFromPuzzleData(foodDatabase);

        return String.valueOf(numberOfFreshIdsInStock);
    }

    private int findTotalFreshIdsFromPuzzleData(FreshFoodDatabase foodDatabase) {
        int freshIdCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(getInputFilePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isBlank() && !line.contains("-")) { // don't love this but here we are
                    if (foodDatabase.isIdInAFreshRange(new BigInteger(line))) {
                        freshIdCount++;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file.");
            e.printStackTrace();
        }
        return freshIdCount;
    }

    private int findTotalFreshIdsFromTestData(FreshFoodDatabase foodDatabase) {

        final List<Integer> ids = List.of(
                1,
                5,
                8,
                11,
                17,
                32);

        int freshIdCount = 0;
        for (int id : ids) {
            if (foodDatabase.isIdInAFreshRange(BigInteger.valueOf(id))) {
                freshIdCount++;
            }
        }

        return freshIdCount;
    }

    private ArrayList<FreshFoodIdRange> getTestFreshRanges() {
        final ArrayList<FreshFoodIdRange> testList = new ArrayList<FreshFoodIdRange>();
        testList.add(new FreshFoodIdRange(BigInteger.valueOf(3), BigInteger.valueOf(5)));
        testList.add(new FreshFoodIdRange(BigInteger.valueOf(10), BigInteger.valueOf(14)));
        testList.add(new FreshFoodIdRange(BigInteger.valueOf(16), BigInteger.valueOf(20)));
        testList.add(new FreshFoodIdRange(BigInteger.valueOf(12), BigInteger.valueOf(18)));
        return testList;
    }

    private ArrayList<FreshFoodIdRange> getPuzzleFreshRanges() {
        final ArrayList<FreshFoodIdRange> puzzleList = new ArrayList<FreshFoodIdRange>();
        try (BufferedReader br = new BufferedReader(new FileReader(getInputFilePath()))) {
            String line;
            while ((line = br.readLine()) != null && !line.isBlank()) {
                final FreshFoodIdRange range = extractRangeFromLine(line);
                puzzleList.add(range);
            }
        } catch (IOException e) {
            System.err.println("Error reading file.");
            e.printStackTrace();
        }
        return puzzleList;
    }

    private FreshFoodIdRange extractRangeFromLine(String line) {
        final String[] minAndMax = line.split("-");
        final BigInteger min = new BigInteger(minAndMax[0]);
        final BigInteger max = new BigInteger(minAndMax[1]);
        return new FreshFoodIdRange(min, max);
    }

}
