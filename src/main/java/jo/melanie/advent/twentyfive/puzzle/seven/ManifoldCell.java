package jo.melanie.advent.twentyfive.puzzle.seven;

public class ManifoldCell {

    private ManifoldCellContents contents;

    public ManifoldCell(ManifoldCellContents contents) {
        this.contents = contents;
    }

    public ManifoldCellContents getContents() {
        return contents;
    }

    public void setContents(ManifoldCellContents contents) {
        this.contents = contents;
    }


}
