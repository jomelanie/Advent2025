package jo.melanie.advent.twentyfive.puzzle.one;

public class SafeDial {

    private int currentNumber = 50;
    private int numberOfTimesZeroIsHit = 0;

    private final boolean includePassingZeroInCount;

    public SafeDial(boolean includeUsingZeroInCount) {
        this.includePassingZeroInCount = includeUsingZeroInCount;
        System.out.println("Starting at " + currentNumber);
    }

    public void turnDial(String action) {
        System.out.print(action);

        final String direction = action.substring(0, 1);
        final int numberOfTurns = Integer.parseInt(action.substring(1));

        if ("L".equals(direction)) {
            turnLeft(numberOfTurns);
        } else if ("R".equals(direction)) {
            turnRight(numberOfTurns);
        } else {
            System.out.print(" - Invalid Action");
        }

        if (currentNumber == 0) {
            numberOfTimesZeroIsHit++;
        }

        System.out.print(" Now at: " + currentNumber);
        System.out.println();

    }

    private void turnLeft(int numberOfTurns) {

        System.out.print(" Turning left " + numberOfTurns);

        if (this.includePassingZeroInCount) {
            numberOfTimesZeroIsHit += numberOfTurns / 100;
        }

        int numberToTheLeft = currentNumber - (numberOfTurns % 100);

        if (numberToTheLeft < 0) {
            if (this.includePassingZeroInCount && currentNumber != 0) {
                numberOfTimesZeroIsHit++;
            }
            numberToTheLeft = 100 + numberToTheLeft;
        }

        currentNumber = numberToTheLeft;


    }

    private void turnRight(int numberOfTurns) {

        System.out.print(" Turning right " + numberOfTurns);

        if (this.includePassingZeroInCount) {
            numberOfTimesZeroIsHit += numberOfTurns / 100;
        }

        int newNumber = (currentNumber + numberOfTurns) % 100;

        if (this.includePassingZeroInCount &&
                newNumber > 0 &&
                newNumber < currentNumber) {
            numberOfTimesZeroIsHit++;
        }

        currentNumber = newNumber;

    }

    public int getNumberOfTimesZeroHit() {
        return numberOfTimesZeroIsHit;
    }


}
