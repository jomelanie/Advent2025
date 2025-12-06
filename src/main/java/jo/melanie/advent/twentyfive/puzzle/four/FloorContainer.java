package jo.melanie.advent.twentyfive.puzzle.four;

public class FloorContainer {

    private ContainerContents containerContents = ContainerContents.EMPTY; // presume empty until we get to it
    private int paperNeighbours = 0;

    public void setContainerContents(ContainerContents containerContents) {
        this.containerContents = containerContents;
    }

    public ContainerContents getContainerContents() {
        return this.containerContents;
    }

    public void addPaperNeighbour() {
        this.paperNeighbours++;
    }

    public int getPaperNeighbours() {
        return this.paperNeighbours;
    }

}
