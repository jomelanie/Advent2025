package jo.melanie.advent.twentyfive.puzzle.five;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FreshFoodDatabase {

    private final ArrayList<FreshFoodIdRange> freshRanges;

    public FreshFoodDatabase(ArrayList<FreshFoodIdRange> freshRanges) {
        this.freshRanges = freshRanges;
        this.freshRanges.sort(Comparator.comparing(FreshFoodIdRange::min));
    }

    public boolean isIdInAFreshRange(BigInteger id) {
        return this.freshRanges
                .stream()
                .anyMatch(range -> idInGivenRange(id, range));
    }

    private static boolean idInGivenRange(BigInteger id, FreshFoodIdRange range) {
        return range.min().compareTo(id) <= 0 &&
                range.max().compareTo(id) >= 0;
    }

    public String totalNumberOfFreshIds() {

        // id ranges are sorted, let's merge them to a new array.
        final List<FreshFoodIdRange> combinedRange = combineRanges();

        BigInteger totalFreshIds = BigInteger.ZERO;

        for (FreshFoodIdRange range : combinedRange) {
            final BigInteger difference = range.max().subtract(range.min()).add(BigInteger.ONE);
            totalFreshIds = totalFreshIds.add(difference);
        }

        return totalFreshIds.toString();
    }

    private List<FreshFoodIdRange> combineRanges() {
        final List<FreshFoodIdRange> combinedRange = new ArrayList<>();
        FreshFoodIdRange rangeBeingConstructed = null;
        for (FreshFoodIdRange currentRange : this.freshRanges) {
            if (rangeBeingConstructed == null) {
                rangeBeingConstructed = currentRange;
            } else {
                final BigInteger currentMin = currentRange.min();
                final BigInteger currentMax = currentRange.max();
                if (idInGivenRange(currentMin, rangeBeingConstructed)) {
                    final BigInteger newMax = idInGivenRange(currentMax, rangeBeingConstructed) ? rangeBeingConstructed.max() : currentMax;
                    rangeBeingConstructed = new FreshFoodIdRange(rangeBeingConstructed.min(), newMax);
                } else {
                    combinedRange.add(rangeBeingConstructed);
                    rangeBeingConstructed = currentRange;
                }
            }
        }
        combinedRange.add(rangeBeingConstructed);
        return combinedRange;
    }
}
