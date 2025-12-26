package jo.melanie.advent.twentyfive.puzzle.six;

import java.math.BigInteger;

public class Operation {

    private final Operator operator;
    private BigInteger result;

    public Operation(Operator operator) {
        this.operator = operator;
    }

    public void includeNewNumber(BigInteger newNumber) {
        if (this.result == null) {
            this.result = newNumber;
        } else {
            performOperationWith(newNumber);
        }
    }

    private void performOperationWith(BigInteger newNumber) {
        switch (this.operator) {
            case ADD -> this.result = this.result.add(newNumber);
            case MULTIPLY -> this.result = this.result.multiply(newNumber);
        }
    }

    public BigInteger getResult() {
        return this.result;
    }

    public Operator getOperator() {
        return operator;
    }
}
