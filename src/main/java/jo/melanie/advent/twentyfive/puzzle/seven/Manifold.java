package jo.melanie.advent.twentyfive.puzzle.seven;

import java.util.List;

public class Manifold {

    private final ManifoldCell[][] cells;
    private final int numRows;
    private final int numCols;
    private int numSplits = 0;

    public Manifold(List<String> input) {

        numRows = input.size();
        numCols = input.getFirst().length();
        cells = new ManifoldCell[numRows][numCols];

        fillCells(input);

    }

    private void fillCells(List<String> input) {

        for (int i = 0; i < numRows; i++) {

            final String row = input.get(i);

            for (int j = 0; j < numCols; j++) {

                final char character = row.charAt(j);
                final ManifoldCellContents contents = contentsForCharacter(character);
                cells[i][j] = new ManifoldCell(contents);

            }
        }

    }

    public void startBeam() {
        for (int i = 1; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (i == 1 && cells[0][j].getContents() == ManifoldCellContents.START) {
                    cells[i][j].setContents(ManifoldCellContents.BEAM);
                } else if (cells[i - 1][j].getContents() == ManifoldCellContents.BEAM) {
                    if (cells[i][j].getContents() == ManifoldCellContents.SPLITTER) {
                        numSplits++;
                        if (j > 0) {
                            cells[i][j-1].setContents(ManifoldCellContents.BEAM);
                        }
                        if (j < numCols - 1) {
                            cells[i][j+1].setContents(ManifoldCellContents.BEAM);
                        }
                    } else {
                        cells[i][j].setContents(ManifoldCellContents.BEAM);
                    }
                }
            }
        }
    }

    public int getNumSplits() {
        return this.numSplits;
    }

    private ManifoldCellContents contentsForCharacter(char character) {
        return switch (character) {
            case 'S' -> ManifoldCellContents.START;
            case '^' -> ManifoldCellContents.SPLITTER;
            default -> ManifoldCellContents.EMPTY;
        };
    }

}
