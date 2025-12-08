package jo.melanie.advent.twentyfive.puzzle.two;

import jo.melanie.advent.twentyfive.common.PuzzleSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class PuzzleTwoSolver extends PuzzleSolver {

    public PuzzleTwoSolver(boolean useTestData, int part) {
        super(useTestData, part);
    }

    @Override
    protected String getInputFilePath() {
        return PUZZLE_INPUT_PATH + "two.txt";
    }

    @Override
    protected String solve() {

        final String input = this.useTestData ? getTestInput() : getFileInput();

        final String[] ranges = input.split(",");

        final BigInteger total = findIdsWithDuplicatePatterns(ranges);

        return String.valueOf(total);
    }

    private BigInteger findIdsWithDuplicatePatterns(String[] ranges) {

        BigInteger total = BigInteger.ZERO;

        final Map<Integer, List<Integer>> divisors = generateDivisorsForUpTo(10); //TODO: Make this variable based on input

        final LongPredicate patternDetector = this.part == 1 ?
                this::idHasNumberPatternTwice :
                id -> idHasAnyNumberOfRepeatedPatterns(id, divisors);

        for (String range : ranges) {
            final String[] minAndMax = range.split("-");
            final long min = Long.parseLong(minAndMax[0]);
            final long max = Long.parseLong(minAndMax[1]) + 1;

            final long totalForRange =
                    LongStream.range(min, max)
                            .filter(patternDetector)
                            .sum();

            total = total.add(BigInteger.valueOf(totalForRange));
        }

        return total;
    }

    private Map<Integer, List<Integer>> generateDivisorsForUpTo(int max) {
        final Map<Integer, List<Integer>> result = new HashMap<>();
        for (int i = 2; i <= max; i++) {
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    result.computeIfAbsent(i, _ -> new ArrayList<>()).add(j);
                }
            }
        }
        return result;
    }

    private boolean idHasNumberPatternTwice(long id) {

        final String idCode = String.valueOf(id);
        final int idLength = idCode.length();

        if (idLength % 2 == 1) {
            return false;
        }

        final int idMiddleIndex = idLength / 2;
        final String idFirstHalf = idCode.substring(0, idMiddleIndex);
        final String idSecondHalf = idCode.substring(idMiddleIndex);

        return idFirstHalf.equals(idSecondHalf);

    }

    private boolean idHasAnyNumberOfRepeatedPatterns(long id,
                                                     Map<Integer, List<Integer>> divisors) {

        final String idCode = String.valueOf(id);
        final int idLength = idCode.length();

        if (!idHasFirstNumberInCode(idCode)) {
            return false;
        }

        if (idIsSameNumberRepeated(idCode)) {
            return true;
        }

        return divisors.containsKey(idLength) &&
                idIsNumberRepeatedForDivisors(idCode, divisors.get(idLength));
    }

    private boolean idHasFirstNumberInCode(String idCode) {
        return idCode.substring(1).contains(idCode.substring(0, 1));
    }

    private boolean idIsNumberRepeatedForDivisors(String idCode, List<Integer> divisors) {

        if (divisors == null || divisors.isEmpty()) {
            return false;
        }

        final int idCodeLength = idCode.length();
        for (int divisor : divisors) {
            if (repeatedForDivisor(idCode, divisor, idCodeLength)) {
                return true;
            }
        }

        return false;

    }

    private static boolean repeatedForDivisor(String idCode, int divisor, int idCodeLength) {
        final String firstChunk = idCode.substring(0, divisor);
        for (int i = divisor; i < idCodeLength; i+= divisor) {
            final String nextChunk = idCode.substring(i, i + divisor);
            if (!firstChunk.equals(nextChunk)) {
                return false;
            }
        }
        return true;
    }

    private boolean idIsSameNumberRepeated(String idCode) {
        final char firstChar = idCode.charAt(0);
        for (int i = 0; i < idCode.length(); i++) {
            if (firstChar != idCode.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private String getTestInput() {
        return "11-22,95-115,998-1012,1188511880-1188511890,222220-222224," +
                "1698522-1698528,446443-446449,38593856-38593862,565653-565659," +
                "824824821-824824827,2121212118-2121212124";
    }

    private String getFileInput() {
        final String line;
        try (BufferedReader br = new BufferedReader(new FileReader(getInputFilePath()))) {
            line = br.readLine(); // should only be 1
        } catch (IOException e) {
            System.err.println("Error reading file.");
            e.printStackTrace();
            return "";
        }
        return line;
    }

}
