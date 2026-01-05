package jo.melanie.advent.twentyfive.puzzle.seven;

import jo.melanie.advent.twentyfive.common.PuzzleSolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PuzzleSevenSolver extends PuzzleSolver {


    public PuzzleSevenSolver(boolean useTestData, int part) {
        super(useTestData, part);
    }

    @Override
    protected String getInputFilePath() {
        return PUZZLE_INPUT_PATH + "seven.txt";
    }

    @Override
    protected String solve() {

        final Manifold manifold = new Manifold(getInput());

        manifold.startBeam();

        return String.valueOf(manifold.getNumSplits());
    }

    private List<String> getInput() {
        if (this.useTestData) {

            return List.of(".......S.......",
                    "...............",
                    ".......^.......",
                    "...............",
                    "......^.^......",
                    "...............",
                    ".....^.^.^.....",
                    "...............",
                    "....^.^...^....",
                    "...............",
                    "...^.^...^.^...",
                    "...............",
                    "..^...^.....^..",
                    "...............",
                    ".^.^.^.^.^...^.",
                    "...............");
        } else {
            final Path filePath = Paths.get(getInputFilePath());
            try {
                return Files.readAllLines(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
