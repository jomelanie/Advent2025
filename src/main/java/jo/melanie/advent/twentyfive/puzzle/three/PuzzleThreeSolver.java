package jo.melanie.advent.twentyfive.puzzle.three;

import jo.melanie.advent.twentyfive.common.PuzzleSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PuzzleThreeSolver extends PuzzleSolver {

    public PuzzleThreeSolver(boolean useTestData, int part) {
        super(useTestData, part);
    }

    @Override
    protected String getInputFilePath() {
        return PUZZLE_INPUT_PATH + "three.txt";
    }

    @Override
    protected String solve() {
        return this.useTestData ?
                getSolutionForTestData()
                : getSolutionForFile();
    }

    private String getSolutionForFile() {

        BigInteger result = BigInteger.ZERO;
        try (BufferedReader br = new BufferedReader(new FileReader(getInputFilePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                final BigInteger joltage = largestJoltageForPack(line);
                result = result.add(joltage);
            }
        } catch (IOException e) {
            System.err.println("Error reading file.");
            e.printStackTrace();
            return "";
        }
        return  String.valueOf(result);
    }

    private String getSolutionForTestData() {
        final List<String> inputs = List.of("987654321111111",
                "811111111111119",
                "234234234234278",
                "818181911112111");
        BigInteger total = BigInteger.ZERO;
        for (String input : inputs) {
            total = total.add(largestJoltageForPack(input));
        }
        return String.valueOf(total);
    }

    private BigInteger largestJoltageForPack(String pack) {

        final int numOfBatteries = this.part == 2 ? 12 : 2;
        return largestJoltageForGivenNumberOfBatteries(pack, numOfBatteries);
    }

    private BigInteger largestJoltageForGivenNumberOfBatteries(String pack, int numBatteries) {

        final List<Integer> batteryValues = new ArrayList<>();
        for (char c : pack.toCharArray()) {
            batteryValues.add(Character.getNumericValue(c));
        }

        final StringBuilder finalJoltage = new StringBuilder(12);

        int maxIdx = pack.length() - numBatteries + 1;
        int minIdx = 0;

        for (int i = 0; i < numBatteries; i++) {

            int biggestNumberInIndexRange = 0;
            int newMinIdx = 0;

            for (int j = minIdx; j < maxIdx; j++) {
                int currentJoltage = batteryValues.get(j);
                if (currentJoltage > biggestNumberInIndexRange) {
                    biggestNumberInIndexRange = currentJoltage;
                    newMinIdx = j;
                }
            }

            finalJoltage.append(biggestNumberInIndexRange);
            minIdx = newMinIdx + 1;
            maxIdx++;

        }

        return new BigInteger(finalJoltage.toString());
    }

}
