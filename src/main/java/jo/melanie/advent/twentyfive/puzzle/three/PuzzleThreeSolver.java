package jo.melanie.advent.twentyfive.puzzle.three;

import jo.melanie.advent.twentyfive.common.PuzzleSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PuzzleThreeSolver extends PuzzleSolver {

    public PuzzleThreeSolver(boolean useTestData, int part) {
        super(useTestData, part);
    }

    @Override
    protected String getFilename() {
        return "src/main/resources/three.txt";
    }

    @Override
    protected String solve() {
        return this.useTestData ?
                getSolutionForTestData()
                : getSolutionForFile();
    }

    private String getSolutionForFile() {

        BigInteger result = BigInteger.ZERO;
        try (BufferedReader br = new BufferedReader(new FileReader(getFilename()))) {
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

        return this.part == 2 ?
        largestJoltageUsingNNumbers(pack, 12) :
        largestJoltageUsingTwoNumbers(pack);
    }

    private BigInteger largestJoltageUsingNNumbers(String pack, int numBatteries) {

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

    private BigInteger largestJoltageUsingTwoNumbers(String pack) {
        int largestFirstJoltage  = 0;
        int largestSecondJoltage = 0;
        for (int i = 0; i < pack.length(); i++) {
            char batteryJoltageChar = pack.charAt(i);
            final int batteryJoltage = Character.getNumericValue(batteryJoltageChar);
            if (notTheLastBattery(pack, i) && batteryJoltage > largestFirstJoltage) {
                largestFirstJoltage = batteryJoltage;
                largestSecondJoltage = 0;
            } else if (batteryJoltage > largestSecondJoltage) {
                largestSecondJoltage = batteryJoltage;
            }
        }
        return BigInteger.valueOf(largestFirstJoltage * 10 + largestSecondJoltage);
    }

    private static boolean notTheLastBattery(String pack, int i) {
        return i < pack.length() - 1;
    }
}
