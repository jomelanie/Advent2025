package jo.melanie.advent.twentyfive.puzzle.four;

import jo.melanie.advent.twentyfive.common.PuzzleSolver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PuzzleFourSolver extends PuzzleSolver {

    public PuzzleFourSolver(boolean useTestData, int part) {
        super(useTestData, part);
    }

    @Override
    protected String getFilename() {
        return "src/main/resources/four.txt";
    }

    @Override
    protected String solve() {
        final List<String> input = inputForPuzzle();
        final PrintingFloor floor = new PrintingFloor(input);

        if (this.part == 1) {
            return String.valueOf(floor.totalPapersHavingMaxPaperNeighbours(4));
        } else {
            int totalPapersRemoved = 0;
            int papersRemoved;
            while ((papersRemoved = floor.removePaperWithMaxNeighboursAndUpdateNeighbourCount(4)) != 0) {
                totalPapersRemoved += papersRemoved;
            }
            return String.valueOf(totalPapersRemoved);
        }

    }

    private List<String> inputForPuzzle() {

        return this.useTestData ? testInput() : fileInput();

    }

    private List<String> fileInput() {
        final Path filePath = Paths.get(getFilename());
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> testInput() {

        return List.of(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                "@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
        );
    }
}
