package jo.melanie.advent.twentyfive.puzzle.four;

import java.util.Arrays;
import java.util.List;

public class PrintingFloor {

    private final FloorContainer[][] containers;
    final int rowSize;
    final int columnSize;

    public PrintingFloor(List<String> inputToBuildFloor) {

        if (inputToBuildFloor.isEmpty()) {
            throw new UnsupportedOperationException();
        }

        this.rowSize = inputToBuildFloor.getFirst().length();
        this.columnSize = inputToBuildFloor.size();
        this.containers = createFloorContainers();

        populateContainers(inputToBuildFloor);

    }

    public int totalPapersHavingMaxPaperNeighbours(int max) {
        int totalPapers = 0;
        for (FloorContainer[] containerRow : this.containers) {
            for (FloorContainer container : containerRow) {
                if (container.getContainerContents() == ContainerContents.PAPER &&
                        container.getPaperNeighbours() < max) {
                    totalPapers++;
                }
            }
        }
        return totalPapers;
    }

    private void printContainer(FloorContainer container) {
        if (container.getContainerContents() == ContainerContents.PAPER &&
                container.getPaperNeighbours() < 4) {
            System.out.print('X');
        } else if (container.getContainerContents() == ContainerContents.PAPER) {
            System.out.print('@');
        } else {
            System.out.print('.');
        }
    }

    private void populateContainers(List<String> inputToBuildFloor) {

        for (int i = 0; i < inputToBuildFloor.size(); i++) {
            final String row = inputToBuildFloor.get(i);
            for (int j = 0; j < row.length(); j++) {
                char inputCharacter = row.charAt(j);
                if (containerHasPaperInIt(inputCharacter)) {
                    this.containers[i][j].setContainerContents(ContainerContents.PAPER);
                    updateNeighbourPaperCount(i, j);
                }
            }
        }

    }

    //  00 01 02
    //  10 11 12
    //  20 21 22
    private void updateNeighbourPaperCount(int row, int column) {
        updatePaperCountFor(row - 1, column - 1);
        updatePaperCountFor(row - 1, column);
        updatePaperCountFor(row - 1, column + 1);

        updatePaperCountFor(row, column - 1);
        updatePaperCountFor(row, column + 1);

        updatePaperCountFor(row + 1, column - 1);
        updatePaperCountFor(row + 1, column);
        updatePaperCountFor(row + 1, column + 1);
    }

    private void updatePaperCountFor(int row, int column) {

        if (row >=0 && row < this.rowSize &&
            column >=0 && column < this.columnSize) {
            this.containers[row][column].addPaperNeighbour();
        }

    }

    private static boolean containerHasPaperInIt(char inputCharacter) {
        return inputCharacter == '@';
    }


    private FloorContainer[][] createFloorContainers() {
        final FloorContainer[][] containers;

        containers = new FloorContainer[this.rowSize][this.columnSize];

        for (int i = 0; i < containers.length; i++) {
            for (int j = 0; j < containers[i].length; j++) {
                containers[i][j] = new FloorContainer();
            }
        }

        return containers;
    }

}
