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
        this.freshRanges.sort((o1, o2) -> o1.min().compareTo(o2.max()));
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

}
