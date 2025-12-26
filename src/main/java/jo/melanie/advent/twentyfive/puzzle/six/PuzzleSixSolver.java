package jo.melanie.advent.twentyfive.puzzle.six;

import jo.melanie.advent.twentyfive.common.PuzzleSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PuzzleSixSolver extends PuzzleSolver {

    public PuzzleSixSolver(boolean useTestData, int part) {
        super(useTestData, part);
    }

    @Override
    protected String getInputFilePath() {
        return PUZZLE_INPUT_PATH + "six.txt";
    }

    @Override
    protected String solve() {

        final List<Operation> processedOperations = transformDataToOperations();

        final BigInteger totalOfOperations = totalOperationResults(processedOperations);

        return totalOfOperations.toString();
    }

    private List<Operation> transformDataToOperations() {

        if (this.part == 2) {

            final char[][] input = processInputToCharArray();

            final int lineLength = input[0].length;
            final int numLines = input.length;

            final List<Operation> operations = new ArrayList<>();

            List<BigInteger> numbers = new ArrayList<>();
            Operator operator = null;
            for (int i=0; i < lineLength; i++) {
                final char[] transposedLine = new char[numLines - 1];
                for (int j = 0; j < numLines - 1; j++) {
                    transposedLine[j] = input[j][i];
                }

                final String column = new String(transposedLine);


                if (columnIsSeparator(column)) {
                    operations.add(calculateOperation(operator, numbers));
                    numbers = new ArrayList<>();
                    operator = null;
                } else {
                    final BigInteger number = new BigInteger(column.trim());
                    numbers.add(number);

                    char operatorCharacter = input[numLines - 1][i];
                    if (isAnOperator(operatorCharacter)) {
                        operator = Operator.valueOfChar(operatorCharacter);
                    }
                }

            }

            operations.add(calculateOperation(operator, numbers));

            return operations;

        } else {
            final List<Operation> operations = initiateListWithData();

            return processNumbers(operations);
        }

    }

    private boolean isAnOperator(char c) {
        return c == '*' || c == '+';
    }

    private Operation calculateOperation(Operator operator, List<BigInteger> numbers) {

        final Operation operation = new Operation(operator);
        for (BigInteger number : numbers) {
            operation.includeNewNumber(number);
        }

        return operation;

    }

    private boolean columnIsSeparator(String column) {
        return column == null || column.isBlank();
    }

    private char[][] processInputToCharArray() {

        final List<String> input = this.useTestData ? getTestInput() : getFileInput();

        final int numCols = input.size();

        final char[][] result = new char[numCols][];

        for (int i = 0; i < numCols; i++) {
            result[i] = input.get(i).toCharArray();
        }

        return result;

    }

    private List<String> getFileInput() {
        final Path filePath = Paths.get(getInputFilePath());
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> getTestInput() {

        return List.of("123 328  51 64 ",
                " 45 64  387 23 ",
                "  6 98  215 314",
                "*   +   *   +  ");

    }

    private BigInteger totalOperationResults(List<Operation> operations) {
        BigInteger result = BigInteger.ZERO;
        for (Operation operation : operations) {
            result = result.add(operation.getResult());
        }
        return result;
    }

    private List<Operation> processNumbers(List<Operation> operations) {

        final List<Operation> processedOperations = operations.stream()
                .map(op -> new Operation(op.getOperator())) // Assuming a copy constructor exists
                .toList();

        if (this.useTestData) {
            processTestData(processedOperations);
        } else {
            processPuzzleData(processedOperations);
        }

        return processedOperations;

    }

    private void processPuzzleData(List<Operation> operations) {
        try (BufferedReader br = new BufferedReader(new FileReader(getInputFilePath()))) {
            String line;
            while ((line = br.readLine()) != null && !line.contains("*")) { // crude way of checking for last line :/
                includeNumbersInOperations(operations, line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processTestData(List<Operation> operations) {

    }

    private static void includeNumbersInOperations(List<Operation> operations, String numbers) {
        final String[] individualNumbers = numbers.trim().split("\\s+");
        for (int i = 0; i < individualNumbers.length; i++) {
            final BigInteger number = new BigInteger(individualNumbers[i]);
            operations.get(i).includeNewNumber(number);
        }
    }

    private List<Operation> initiateListWithData() {
        if (this.useTestData) {
            final String operations = "*   +   *   +  ";
            return initiateListForOperators(operations);
        } else {
            final String operations = fetchLastLineOfTestFile();
            return initiateListForOperators(operations);
        }
    }

    private String fetchLastLineOfTestFile() {
        try (Stream<String> lines = Files.lines(Path.of(getInputFilePath()))) {
            return lines.reduce((first, second) -> second).orElse(null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read test file", e);
        }
    }

    private static List<Operation> initiateListForOperators(String operations) {
        final String[] operatorSymbols = operations.split("\\s+");
        final List<Operation> result = new ArrayList<>();
        for (String symbol : operatorSymbols) {
            final Operator operator = Operator.valueOfSymbol(symbol);
            result.add(new Operation(operator));
        }
        return result;
    }


}
